FROM gradle:jdk19-jammy
COPY . /integrationtests
WORKDIR /integrationtests
ENTRYPOINT ["gradle"]
CMD ["cucumber"]