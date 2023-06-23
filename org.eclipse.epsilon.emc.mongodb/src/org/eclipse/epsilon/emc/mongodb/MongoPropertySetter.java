package org.eclipse.epsilon.emc.mongodb;

import org.bson.Document;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.AbstractPropertySetter;

public class MongoPropertySetter extends AbstractPropertySetter {

	protected MongoModel model;
	
	public MongoPropertySetter(MongoModel model) {
		this.model = model;
	}
	
	@Override
	public void invoke(Object target, String property, Object value, IEolContext context) throws EolRuntimeException {
		if (!(target instanceof Document)) {
			throw new EolRuntimeException("The target must be a BSON Document");
		}
		
		Document object = (Document) target;
		object.put(property, value);
		
	}
}
