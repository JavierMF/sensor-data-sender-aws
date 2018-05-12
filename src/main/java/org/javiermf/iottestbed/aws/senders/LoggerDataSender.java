package org.javiermf.iottestbed.aws.senders;

import org.javiermf.iottestbed.aws.dtos.SensorMeasures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoggerDataSender implements SensorsDataSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerDataSender.class);

    @Override
    public void sendSensorsData(List<SensorMeasures> measures) {
        for (SensorMeasures measure : measures) {
            LOGGER.info("Sending {}", measure);
        }

    }
}
