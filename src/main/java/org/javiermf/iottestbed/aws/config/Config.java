package org.javiermf.iottestbed.aws.config;

import com.amazonaws.services.iot.client.AWSIotMqttClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${aws.endpoint}")
    String clientEndpoint;

    @Value("${aws.clientId}")
    String clientId;

    @Value("${aws.accessKey}")
    String accessKey;

    @Value("${aws.secretAccessKey}")
    String secretAccessKey;

    @Bean
    AWSIotMqttClient aWSIotMqttClient() {
        return new AWSIotMqttClient(clientEndpoint, clientId, accessKey, secretAccessKey);
    }
}
