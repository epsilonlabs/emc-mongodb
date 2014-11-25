package org.eclipse.epsilon.emc.mongodb;

import java.util.Collection;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoModel extends DefaultModel {
	
	public static String PROPERTY_DB = "DB";
	
	protected DB db;
	protected MongoPropertyGetter propertyGetter = new MongoPropertyGetter();
	
	public MongoModel() {
		
	}
	
	public MongoModel(DB db) {
		this.db = db;
	}
	
	@Override
	public IPropertyGetter getPropertyGetter() {
		return propertyGetter;
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
		return db.getCollectionNames().contains(type);
	}

	@Override
	public void load(StringProperties properties, String basePath)
			throws EolModelLoadingException {
		super.load(properties, basePath);
		try {
			Mongo mongo = new Mongo();
			this.db = mongo.getDB(properties.getProperty(MongoModel.PROPERTY_DB));
		}
		catch (Exception ex) {
			throw new EolModelLoadingException(ex, this);
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
	
}
