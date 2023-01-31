FROM gradle:jdk8-jammy
COPY . /integrationtests
WORKDIR /integrationtests
#RUN apk update && \
 #   apk add curl

#RUN curl -L https://services.gradle.org/distributions/gradle-7.6-bin.zip -o gradle-7.6-bin.zip
#RUN unzip gradle-7.6-bin.zip

#ENV GRADLE_HOME=/integrationtests/gradle-7.6
#ENV PATH=$PATH:$GRADLE_HOME/bin