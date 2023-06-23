# Epsilon-MongoDB Integration

Eclipse plugins that extend Epsilon's Model Connectivity (EMC) layer with support for querying MongoDB databases using languages of the [Epsilon platform](http://www.eclipse.org/epsilon) to perform activities such as code generation, model validation and model-to-model transformation. The MongoDB EMC driver supports read/write access to locally and externally-hosted MongoDB databases.

## Example

The following EOL snippet demonstrates creating Post and nested Comment documents in a MongoDB database.

```
DB.reset();

for (i in 1.to(5)) {
	var post : new DB!Post;

	post.title = "Post " + i;
	post.comments = new Sequence;

	for (j in 1.to(5)) {
		var comment : new DB!Comment;
		comment.text = "Comment " + i + "" + j;
		post.comments.add(comment);
	}

	post.println();
	post.save();
}
```

## Update site

https://github.com/epsilonlabs/emc-mongodb/blob/master/org.eclipse.epsilon.emc.mongodb.updatesite/
