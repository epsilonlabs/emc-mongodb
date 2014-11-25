Epsilon-MongoDB Integration
===========

Eclipse plugins that extend Epsilon's Model Connectivity (EMC) layer with support for querying MongoDB databases using languages of the [Epsilon platform](http://www.eclipse.org/epsilon) to perform activities such as code generation, model validation and model-to-model transformation. The MongoDB EMC driver supports read-only access to locally-hosted MongoDB databases.

Example
-----------
The following EOL snippet demonstrates iterating through all the documents in a Post collection and printing the values of their title attribute.
```
for (p in Post.all) {
	p.title.println();
}
```

Update site
-----------
https://raw.githubusercontent.com/epsilonlabs/emc-mongodb/master/org.eclipse.epsilon.mongodb.updatesite/
