# Project APIs
Author: Jakub Czechowski

## Table of contents
* [How to clone project](#how-to-clone-project)
* [How to configure project for build/run](#How-to-configure-project-for-build-or-run)
* [How to deploy project to Docker](#How-to-deploy-project-to-Docker)
* [How to use project](#problem-statement)

## How to clone project
Repository can be cloned using standard git clone command. 
```
git clone https://github.com/kubaczechowski/Project.git ProjectAPI
```
## How to configure project for build or run
Project is written in Java 17. The development environment is IntelliJ IDEA. In order to build and run project we need to provide the path to the Main.java class. The path is: `com.jc.project.UI.Main`

## How to deploy project to Docker
Before we deploy to docker we need to run/ build our app at least once. It’s due to the fact that in my Dockerfile I copy `out/production` folder to the image.  We can create an image and a container as shown below.
```Docker
docker build -t project-api .
docker run -i -t  project-api
```

## How to use project
In the menu user has three options:
* they can exit the app - **`press 0`**
* they can retrieve only random words (first part of the assignment) - **`press 1`**
* they can retrieve recordings based on random words - **`press 2`**

Then regardless of whether a user has chosen an option 1 or 2 they need to insert a number in the range between 5 and 20 inclusive.
In the case a user provides number out of the range 5-20  / type of data other than natural number, they will be asked to provide answer again. 
