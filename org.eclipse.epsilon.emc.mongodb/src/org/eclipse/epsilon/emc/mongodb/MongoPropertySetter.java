package org.eclipse.epsilon.emc.mongodb;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.AbstractPropertySetter;

import com.mongodb.DBObject;

public class MongoPropertySetter extends AbstractPropertySetter {

	protected MongoModel model;
	
	public MongoPropertySetter(MongoModel model) {
		this.model = model;
	}
	
	@Override
	public void invoke(Object value) throws EolRuntimeException {
		DBObject dbObject = (DBObject) object;
		dbObject.put(property, value);
	}
	
}
