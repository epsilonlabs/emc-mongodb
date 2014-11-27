package org.eclipse.epsilon.emc.mongodb;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.execute.operations.contributors.IOperationContributorProvider;
import org.eclipse.epsilon.eol.execute.operations.contributors.OperationContributor;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoModel extends DefaultModel implements IOperationContributorProvider {
	
	public static final String PROPERTY_DB = "DB";
	public static final String DBOBJECT_ID = "_id";
	public static final String COLLECTION_ID = "_collectionName";
	
	protected DB db;
	protected String databaseName;
	protected MongoPropertyGetter propertyGetter = new MongoPropertyGetter();
	protected MongoPropertySetter propertySetter = new MongoPropertySetter(this);
	
	public MongoModel() {}
	
	public MongoModel(DB db) {
		this.db = db;
	}
	
	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException,
			EolNotInstantiableModelElementTypeException {
		
		DBObject dbObject = new BasicDBObject();
		dbObject.put(DBOBJECT_ID, UUID.randomUUID().toString());
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
		return new MongoCollection(db.getCollection(type));
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
	public void load(StringProperties properties, IRelativePathResolver resolver)
			throws EolModelLoadingException {
		super.load(properties, resolver);
		this.databaseName = properties.getProperty(MongoModel.PROPERTY_DB);
		try {
			this.db = new Mongo().getDB(databaseName);
		}
		catch (Exception ex) {
			throw new EolModelLoadingException(ex, this);
		}
	}
	
	
	public void reset() {
		try {
			this.db.dropDatabase();
			this.db = new Mongo().getDB(databaseName);
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public boolean owns(Object o) {
		return o instanceof DBObject;
	}

	public DB getDb() {
		return db;
	}
	
	public void setDb(DB db) {
		this.db = db;
	}

	@Override
	public OperationContributor getOperationContributor() {
		return new OperationContributor() {
			
			@Override
			public boolean contributesTo(Object target) {
				return target instanceof DBObject;
			}
			
			public void save() {
				DBObject dbObject = (DBObject) target;
				db.getCollection(dbObject.get(COLLECTION_ID) + "").save(dbObject);
			}
			
		};
	}
	
}
