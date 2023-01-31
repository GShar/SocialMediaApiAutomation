# SocialMediaApiAutomation
This project is an api automation framework to test a social media platform

### Prerequisites

Make sure you have Java installed.
Also make sure you are using an editor with support for both gradle and Java.
I have used the following:
* Intellij Idea as the editor
* Gradle as the build tool
## Running the tests

Please follow the steps below to run the test suite:
1. After cloning, make sure you have gradle installed and run the following command in the root directory:
`gradle wrapper`

This command will create the gradlew file.
1. Now go to the root folder of the project and run the following gradlew command from command line:
    `./gradlew cucumber`
2. Or without gradle wrapper simply: 
   `gradle cucumber`

## Running the tests using Docker 

To  create docker image:
`docker build -t <name of the image> .`

To run docker image:
`docker run -i -t <name of the image> sh`


## Scenarios Covered.

For this project I have focussed mainly on the main functionality, which is 
1. Allow users make new posts
2. Allow users to add comments to posts

Leading to the following scenarios
1. Make new post using the post endpoint
2. Add new comments using the comments endpoint to a Post
3. Additionaly I have covered, fetch comments from the post, as a regression scenario.
4. Also I have added a simple test for checking the schema of User list which is returned by the users endpoint.
   (This was out of scope for this project)






