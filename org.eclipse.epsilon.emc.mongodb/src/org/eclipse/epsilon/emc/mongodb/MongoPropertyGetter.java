package org.eclipse.epsilon.emc.mongodb;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.AbstractPropertyGetter;

import com.mongodb.DBObject;

public class MongoPropertyGetter extends AbstractPropertyGetter {

	@Override
	public Object invoke(Object o, String property) throws EolRuntimeException {
		DBObject dbObject = (DBObject) o;
		return dbObject.get(property);
	}

}
