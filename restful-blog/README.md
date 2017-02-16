Blog RESTful Web Service Sample
========================

##Introduction

This is a very basic sample application written in Java and Spring Framework which demonstrates how to create a RESTful Web Service.

This sample RESTful web service contains simple Blog resources like Author, Post, and Categories. I will be posting a tutorial on how to create RESTful Web Services on my blog [No-nonsense](http://benjsicam.me).

##Deployment

This application is already deployed on [Cloud Foundry](http://www.cloudfoundry.com). You can play with it by going to [https://restful-blog.cloudfoundry.com/](https://restful-blog.cloudfoundry.com/).

##How to use the deployed application

This sample RESTful web service is secured by Spring Security. If asked for credentials, just enter admin for User and admin for Password. I will also post a tutorial on my blog on securing RESTful Web Services.

### Working with Authors

####GET /resources/author

- will lists all authors

####GET /resources/author/{id} 

- will return the author with the specified ID

####GET /resources/author/{id}/posts

- will return a list of the posts made by the author with the specified ID

####POST /resources/author 

- will create an author in the database

**Parameters:**

* username {String} - username for the author. Used to login.
* email {String} - email of the author
* password {String} - password for the author. Used to login.
* firstName {String} - Author's First Name
* lastName {String} - Author's Last Name

**Sample Request Body**

```javascript
{
	"username": "user",
	"password": "user",
	"email": "info@benjsicam.me",
	"firstName": "Benj",
	"lastName": "Sicam"
}
```

####PUT /resources/author 

- will update the author in the database

**Parameters**

* id {String} - id of the author to be updated.
* username {String} - username for the author. Used to login.
* email {String} - email of the author
* firstName {String} - Author's First Name
* lastName {String} - Author's Last Name

**Sample Request Body**

```javascript
{
	"id": "1",
	"username": "user",
	"email": "info@benjsicam.me",
	"firstName": "Juan",
	"lastName": "dela Cruz"
}
```

####DELETE /resources/author/{id} 

- will delete the author with the specified ID in the database


###Working with Categories

####GET /resources/category

- will lists all authors

####GET /resources/category/{id}

- will return the category with the specified ID

####GET /resources/category/{id}/posts

- will return a list of the posts under the specified category ID

####POST /resources/category

- will create a category in the database

**Parameters:**

* name {String} - name of the category

**Sample Request Body**

```javascript
{
	"name": "My Category"
}
```

####PUT /resources/category

- will update the category in the database

**Parameters:**

* id {String} - the id of the category to be updated.
* name {String} - updated name of the category

**Sample Request Body**

```javascript
{
	"id": "1",
	"name": "My Category"
}
```

####DELETE /resources/category/{id}

- deletes the category with the specified ID


###Working with Posts

####GET /resources/post

- will list all posts

####GET /resources/post/{id}

- will return the post with the specified ID

####GET /resources/post/{id}/author

- will return the author of the post with the specified id

####GET /resources/post/{id}/category

- will return a list of categories in which the post with the specified id is under

####POST /resources/post

- will create a new post in the database

**Parameters:**

* author {Object} - an object which references an author. ID field in the only required field.
* date {String} - format is YYYY-MM-DD
* categories {Array} - An array of objects containing category IDs
* content {String} - content for the post

**Sample Request Body**

```javascript
{
	"author": {
		"id": "1"
	},
	"date": "2013-05-07",
	"categories": [
		{
			"id": "1"
		},
		{
			"id": "3"
		}
	],
	"content": "My content"
}
````

####PUT /resources/post

- will update a post on the database

**Parameters:**

* id {String} - the ID of the post to be updated
* author {Object} - an object which references an author. ID field in the only required field.
* date {String} - format is YYYY-MM-DD
* categories {Array} - An array of objects containing category IDs
* content {String} - content for the post

**Sample Request Body**

```javascript
{
	"id": "1",
	"author": {
		"id": "1"
	},
	"date": "2013-05-07",
	"categories": [
		{
			"id": "1"
		},
		{
			"id": "3"
		}
	],
	"content": "My updated content"
}
````

####DELETE /resources/post/{id}

- deletes the post with the specified ID