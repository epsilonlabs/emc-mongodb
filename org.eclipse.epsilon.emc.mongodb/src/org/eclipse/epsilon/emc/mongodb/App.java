package org.eclipse.epsilon.emc.mongodb;

import org.eclipse.epsilon.eol.EolModule;

import com.mongodb.Mongo;

public class App {
	
	public static void main(String[] args) throws Exception {
		
		MongoModel m = new MongoModel(new Mongo().getDB("test"));
		
		System.out.println(m.getAllOfType("foo").size());
		
	}
	
}
