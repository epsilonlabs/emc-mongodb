package org.eclipse.epsilon.emc.mongodb;

import java.util.Collection;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.Model;

public class DefaultModel extends Model {

	@Override
	public Collection<?> allContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createInstance(String arg0)
			throws EolModelElementTypeNotFoundException,
			EolNotInstantiableModelElementTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteElement(Object arg0) throws EolRuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<?> getAllOfKind(String arg0)
			throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getAllOfType(String arg0)
			throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getElementById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementId(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEnumerationValue(String arg0, String arg1)
			throws EolEnumerationValueNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeNameOf(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getTypeOf(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasType(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInstantiable(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModelElement(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void load() throws EolModelLoadingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean owns(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setElementId(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
