/*******************************************************************************
 * Copyright (c) 2012 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.emc.mongodb.dt;

import org.eclipse.epsilon.common.dt.launching.dialogs.AbstractModelConfigurationDialog;
import org.eclipse.epsilon.emc.mongodb.MongoModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MongoModelConfigurationDialog extends AbstractModelConfigurationDialog {

	protected String getModelName() {
		return "MongoDB Database";
	}
	
	protected String getModelType() {
		return "Mongo";
	}
	
	protected Label fileTextLabel;
	protected Text fileText;	
	
	protected void createGroups(Composite control) {
		super.createGroups(control);
		createFilesGroup(control);
	}
	
	protected Composite createFilesGroup(Composite parent) {
		final Composite groupContent = createGroupContainer(parent, "Database", 2);
				
		
		fileTextLabel = new Label(groupContent, SWT.NONE);
		fileTextLabel.setText("Database: ");
		
		fileText = new Text(groupContent, SWT.BORDER);
		fileText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
		groupContent.layout();
		groupContent.pack();
		return groupContent;
	}

	protected void loadProperties(){
		super.loadProperties();
		if (properties == null) return;
		fileText.setText(properties.getProperty(MongoModel.PROPERTY_DB));
	}
	
	
	protected void storeProperties(){
		super.storeProperties();
		properties.put(MongoModel.PROPERTY_DB, fileText.getText());
	}
}
