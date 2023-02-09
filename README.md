# spring-sqs

To run this project we need run the following command:

**docker-compose up**

After that we need to run following commands to set sqs topic:

**aws --endpoint-url=http://127.0.0.1:4566 sqs create-queue --queue-name test-queue**

Import this project in intellij and run.
 