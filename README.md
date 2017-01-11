#e-commerce#

e-commerce is a sample project to show-case my skills in Scala and Akka. This project is currently in progress, so I will update this readme on a regular basis to indicate modules that are ready for review, the basic approach to each module, and a summary of the skills used. Other nice things, like how to setup and run the app and the scripts to do it all will follow a little later. Feel free to peruse the Issues to get a sense of how  I'm using this project to develop and demonstrate skills.

##Skills Demonstrated##
* **Scala** - The Scala language, best-practices and core features, functional programming, good, clean code.
* **Akka** - Skills so far include, Clustering, Cluster Sharding, Persistence, Akka HTTP. Also concurrency with Futures, and ask/piping patterns. Still on the lookout to demonstrate skills with other Akka features, such as Routing and Supervision.
* **Kafka** - (_not yet_) Kafka will be soon be a star of this project. I plan to use it for communications between Microservices that are NOT direct effects of user interactions (for example, Shipping sending a notification to Inventory when a Shipment of an item is received.
* **Akka Streams** - (_not yet_) Akka Streams will be used for back-pressure where Akka actors are consuming messages off a Kafka topic.
* **Cassandra** - not envisioning any amazing in-depth Cassandra modeling in this project (at least not yet), but C* is the store for Akka Persistence in this project.
* **REST** - all Microservice APIs are RESTful, implemented with Akka HTTP. They're a little sparse right now, but I will continue to enrich them to use REST fully, such as appropriate responses for Posts, resource links, etc.
* **Tooling** - SBT, scalatest, akka test kits (actor, persistence, HTTP, etc), Git.

##Architecture##
The high level view of e-commerce is based on Domain-Driven Design, implemented through the Microservices approach. Each module is designed as a microservice that does one thing and does it well. 

###Modules###
Each module in ecommerce is represented by a microservice, implemented as an Akka application. Each application has a REST API, implemented with Akka HTTP.

####Microservices####
* **ordertracking** - (_not done_) - this is the core domain of the e-commerce project and will manage the lifecycle of an online shopping order from initiation to completion
* **shoppingcart** - manages basic shopping cart functionality. This is a clustered Akka app with Cluster Sharding. Each shoppingcart instance is represented by a Persistent Actor.
* **inventory** - (_in progress_) - this is also a clustered Akka app with Akka Persistence. It maintains the current availablity of a product and places holds on inventory for shopping carts that are in progress, Backorders for that classic "we're out of stock, but we'll have more by whenever" scenario are also managed here.
* **productcatalog** - (_not done_)
* **payment** - (_not done_)
* **fulfillment** - (_not done_)
* **shipping** - (_not done_)

####Integration and UI####
* **Orchestrator** - (_in progress_) - this is an Akka app with a REST API implemented with Akka HTTP. The orchestrator coordinates and combines calls to the microservices above to form complete use cases. For example, if a customer wants to checkout after selecting their items, it would coordinate calls to the **payment** microservice to collect money, the **shoppingcart** microservice to clear their shopping cart, the **inventory** microservice to release the hold(s) on item(s) while the shopping cart was active, and the **ordertracking** microservice to initiate the order with a status of "placed".
* **UI** - (_not done - just a shell so far_) - a Play app that give users a Web UI to shop. The **orchestrator** is injected into the controllers as Play WS.


###Bibliography###

I used various resources to both learn Akka itself, as well as for ideas with regard to patterns for Reactive architecture:
* [*Akka in Action*, Raymond Roestenburg, Manning](https://www.manning.com/books/akka-in-action) - Used this to learn Akka. I referenced the sample code in this book for my basic approach to setting up an Akka app in general, as well as for the cluster sharding and persistence.
* [*Reactive Design Patterns*, Roland Kuhn, Manning](https://www.manning.com/books/reactive-design-patterns) - As the title suggests, I referenced the design patterns in this book to organize the Akka code into patterns that organize concepts and make the code more understandable, refactorable and testable.
* [The online Akka documentation](http://doc.akka.io/docs/akka/2.4/scala.html) - My go-to reference.