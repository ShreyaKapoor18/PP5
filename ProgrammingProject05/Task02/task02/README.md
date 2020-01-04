<H1> Task 02 </H1> 
# SQLite Database

This application helps the user to create database objects and interact with them. The user can provide a directory on which a database object can be based. This directory should contain image files (.jpg, .jpeg or .png) and corresponding metadata files (.txt) carrying the same file name. The images are stored in the database along with the corresponding metadata.

Columns:
	AUHTOR		the author name
	TITLE		the title of the image
	WEBLINK		the URL related to the image
	PICTURE blob	the image stored as a byte array
	INFOGRAPHIC	the type of the image

It is possible to print the values of the created table. If the user only wants the metadata of a specific sample, then the metadata can be retrieved by specifying either the author or the title and the metadata will be saved as a .txt file.

## Getting Started



These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The Java Version 1.8.0_231 is used for this application. Apache Maven Version 3.6.3  was installed from https://maven.apache.org/download.cgi. Therefore the binaries apache-maven-3.6.3-bin.zip were downloded.

The following dependencies were added to Maven:

JUnit 		Version 3.8.1
SQLite-JDBC 	Version 3.18.0
commons-cli	Version 1.4
commons-io	Version 2.6

by adding the according dependencies to the pom.xml file:

<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
</dependency>

<dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.18.0</version>
</dependency>

<dependency>
	<groupId>commons-cli</groupId>
	<artifactId>commons-cli</artifactId>
	<version>1.4</version>
</dependency>

<dependency>
    	<groupId>commons-io</groupId>
    	<artifactId>commons-io</artifactId>
    	<version>2.6</version>
 </dependency>

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
``

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc


