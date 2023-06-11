package org.eclipse.epsilon.emc.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.execute.operations.contributors.IOperationContributorProvider;
import org.eclipse.epsilon.eol.execute.operations.contributors.OperationContributor;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;

public class MongoModel extends DefaultModel implements IOperationContributorProvider {
	public static final String PROPERTY_CONNECTION_STRING = "CONNECTION_STRING";
	public static final String PROPERTY_DB = "DB";
	
	public static final String DBOBJECT_ID = "_id";
	public static final String COLLECTION_ID = "_collectionName";
	
	protected MongoDatabase db;
	protected String databaseName;
	protected MongoPropertyGetter propertyGetter = new MongoPropertyGetter();
	protected MongoPropertySetter propertySetter = new MongoPropertySetter(this);
	
	public MongoModel() {}
	
	public MongoModel(MongoDatabase db) {
		this.db = db;
	}
	
	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException,
			EolNotInstantiableModelElementTypeException {
		
		Document dbObject = new Document();
		dbObject.put(DBOBJECT_ID, new ObjectId());
		dbObject.put(COLLECTION_ID, type);
		/*
		if (type.startsWith("d_")) {
			dbObject.put(COLLECTION_ID, type.substring(2));
			db.getCollection(type.substring(2)).save(dbObject);
		}
		else {
			dbObject.put(DBOBJECT_ID, UUID.randomUUID().toString());
		}*/
		return dbObject;
		
	}
	
	@Override
	public IPropertyGetter getPropertyGetter() {
		return propertyGetter;
	}

	@Override
	public IPropertySetter getPropertySetter() {
		return propertySetter;
	}
	
	@Override
	public Collection<?> getAllOfKind(String type)
			throws EolModelElementTypeNotFoundException {
		return getAllOfType(type);
	}

	@Override
	public Collection<?> getAllOfType(String type)
			throws EolModelElementTypeNotFoundException {
		FindIterable<Document> res = db.getCollection(type).find();
		
		// See https://stackoverflow.com/a/46663487
		Collection<Document> collection = new ArrayList<Document>();
		for (Document element : res) {
			collection.add(element);
		}

		return collection;
	}

	@Override
	public boolean hasType(String type) {
		return true;
	}
	
	@Override
	public boolean isInstantiable(String type) {
		return hasType(type);
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);
		String connectionString = properties.getProperty(MongoModel.PROPERTY_CONNECTION_STRING);
		this.databaseName = properties.getProperty(MongoModel.PROPERTY_DB);
		
		try {
			if (connectionString.isBlank()) {
				MongoClient mongoClient = MongoClients.create();
				MongoDatabase db = mongoClient.getDatabase(databaseName);
				this.db = db;
			} else {
				MongoClient mongoClient = MongoClients.create(connectionString);
				MongoDatabase db = mongoClient.getDatabase(databaseName);
				this.db = db;
			}
			
		} catch (Exception ex) {
			throw new EolModelLoadingException(ex, this);
		}
	}

	public void reset() {
		try {
			this.db.drop();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public boolean owns(Object o) {
		return o instanceof Document;
	}

	public MongoDatabase getDb() {
		return db;
	}

	public void setDb(MongoDatabase db) {
		this.db = db;
	}

	@Override
	public OperationContributor getOperationContributor() {
		return new OperationContributor() {
			
			@Override
			public boolean contributesTo(Object target) {
				return target instanceof Document;
			}
			
			public void save() {
				Document doc = (Document) this.getTarget();
				ObjectId id = (ObjectId) doc.get(DBOBJECT_ID);
				String collectionName = doc.getString(COLLECTION_ID);
				Bson withMatchingId = eq(DBOBJECT_ID, id);
				
				ReplaceOptions createObjectIfDoesntExists = new ReplaceOptions().upsert(true);
				
				db.getCollection(collectionName)
				  .replaceOne(withMatchingId, doc, createObjectIfDoesntExists);
			}
			
		};
	}
	
}
