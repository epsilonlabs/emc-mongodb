package org.eclipse.epsilon.emc.mongodb;

import org.bson.Document;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.AbstractPropertyGetter;

public class MongoPropertyGetter extends AbstractPropertyGetter {

	@Override
	public Object invoke(Object o, String property, IEolContext context) throws EolRuntimeException {
		if (!(o instanceof Document)) {
			throw new EolRuntimeException("The target must be a BSON Document");
		}
		
		Document object = (Document) o;
		return object.get(property);
	}

}
