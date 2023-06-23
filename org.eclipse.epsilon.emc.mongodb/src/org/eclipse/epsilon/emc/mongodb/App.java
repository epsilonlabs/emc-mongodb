package org.eclipse.epsilon.emc.mongodb;

import org.eclipse.epsilon.eol.EolModule;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class App {
	
	public static void main(String[] args) throws Exception {
		//String connectionString = "mongodb://localhost:27017";
		//MongoClient mongoClient = MongoClients.create(connectionString);

		MongoClient mongoClient = MongoClients.create();
		MongoDatabase db = mongoClient.getDatabase("test");
		
		MongoModel m = new MongoModel(db);
		m.databaseName = "test";
		m.setName("DB");
		
		EolModule module = new EolModule();
		module.getContext().getModelRepository().addModel(m);
		
		module.parse("DB.reset();\n"
			+ "\n"
			+ "for (i in 1.to(5)) {\n"
			+ "    var post : new DB!Post;\n"
			+ "    \n"
			+ "    post.title = 'Post ' + i;\n"
			+ "    post.comments = new Sequence;\n"
			+ "\n"
			+ "    for (j in 1.to(5)) {\n"
			+ "        var comment : new DB!Comment;\n"
			+ "        comment.text = 'Comment ' + i + '' + j;\n"
			+ "        post.comments.add(comment);\n"
			+ "    }\n"
			+ "    \n"
			+ "    post.println();\n"
			+ "    post.save();\n"
			+ "}"
		);
		module.execute();
	}
}
