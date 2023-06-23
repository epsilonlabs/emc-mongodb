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
	
	protected Label connectionStringTextLabel;
	protected Text connectionStringText;	
	
	protected Label databaseTextLabel;
	protected Text databaseText;	
	
	protected void createGroups(Composite control) {
		super.createGroups(control);
		createFilesGroup(control);
	}
	
	protected Composite createFilesGroup(Composite parent) {
		final Composite groupContent = createGroupContainer(parent, "Authentication", 2);
				
		connectionStringTextLabel = new Label(groupContent, SWT.NONE);
		connectionStringTextLabel.setText("Connection string: ");
		
		connectionStringText = new Text(groupContent, SWT.BORDER);
		connectionStringText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		connectionStringText.setToolTipText("empty or mongodb://localhost:27017/");
		
		databaseTextLabel = new Label(groupContent, SWT.NONE);
		databaseTextLabel.setText("Database name: ");
		
		databaseText = new Text(groupContent, SWT.BORDER);
		databaseText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
		groupContent.layout();
		groupContent.pack();
		return groupContent;
	}

	protected void loadProperties(){
		super.loadProperties();
		if (properties == null) return;
		connectionStringText.setText(properties.getProperty(MongoModel.PROPERTY_CONNECTION_STRING));
		databaseText.setText(properties.getProperty(MongoModel.PROPERTY_DB));
	}
	
	
	protected void storeProperties(){
		super.storeProperties();
		properties.put(MongoModel.PROPERTY_CONNECTION_STRING, connectionStringText.getText());
		properties.put(MongoModel.PROPERTY_DB, databaseText.getText());
	}
}
