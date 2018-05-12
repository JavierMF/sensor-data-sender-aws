[![Build Status](https://travis-ci.org/JavierMF/sensor-data-sender-aws.svg?branch=master)](https://travis-ci.org/JavierMF/sensor-data-sender-aws)


# Sample Spring-Boot Data Sender for AWS IoT Core

Sample project using Spring Boot for sending data to [AWS IoT Core](https://aws.amazon.com/iot-core/). You will need to setup your account properties in an `application.properties` file or in environment variables:

```
aws.endpoint=
aws.clientId=
aws.accessKey=
aws.secretAccessKey=
```
By default sends basic data about the Java Runtime state, but new senders can be easily added implementing the `SensorsDataReader`
interface.
