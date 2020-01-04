<H1> Task 02 </H1> 
## SQLite Database

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

# TODO

### Prerequisites

The Java Version 1.8.0_231 is used for this application. Apache Maven Version 3.6.3  was installed from https://maven.apache.org/download.cgi. Therefore the binaries apache-maven-3.6.3-bin.zip were downloded.

The following dependencies were added to Maven:

JUnit 		Version 3.8.1
SQLite-JDBC 	Version 3.18.0
commons-cli	Version 1.4
commons-io	Version 2.6

by adding the according dependencies to the pom.xml file:

```
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

### Installing

In order to get the application running you will need to download the repository from gitlab from the following link.

```
https://gitlab-sysprog.informatik.uni-bonn.de/ProgrammingLab2/winterterm-2019-20/group-03-descartes/tree/master/ProgrammingProject05
```
How to set up an working environment for this application:

### 1) Installing SQLite

Before trying to install, please check whether the installation has already been made.

```
$ sqlite3
```

If SQLite is already installed, you should get the following message:

```
SQLite version 3.29.0 2019-07-10 17:32:03
Enter ".help" for usage hints.
Connected to a transient in-memory database.
Use ".open FILENAME" to reopen on a persistent database.
sqlite>
```

If not, please follow these instructions to install SQLite.

Go to the SQLite webpage (https://www.sqlite.org/download.html) and download the most recent version of **SQLite-autoconf-*.tar.gz**.
Type the following commands into the command line to unzip and install the package.

```
$ tar xvfz SQLite-autoconf-*.tar.gz

$ cd SQLite-autoconf-*

$ ./configure --prefix = /usr/local

$ make

$ make install
```

Confirm successful installation by typing again

```
$ sqlite3
```

### 2) Installing Spring

# TODO
  
## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Shreya Kapoor**
**Sophia Krix**
**Gemma van der Voort**

## Acknowledgments



