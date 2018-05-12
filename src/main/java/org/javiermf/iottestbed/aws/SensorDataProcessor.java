package org.javiermf.iottestbed.aws;

import org.javiermf.iottestbed.aws.dtos.SensorMeasures;
import org.javiermf.iottestbed.aws.readers.JavaStatusReaderSensors;
import org.javiermf.iottestbed.aws.readers.SensorsDataReader;
import org.javiermf.iottestbed.aws.senders.SensorsDataSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SensorDataProcessor {

    List<SensorsDataReader> sensorsDataReaderList = new ArrayList<>();

    @Autowired
    @Qualifier("AWSDataSender")
    SensorsDataSender sensorsDataSender;

    @Autowired
    JavaStatusReaderSensors javaStatusReaderSensors;

    @Scheduled(fixedDelay = 60 * 1000)
    public void processSensorsData() {
        if (sensorsDataReaderList.isEmpty()) {
            initSensorDataReaderList();
        }

        for (SensorsDataReader sensorsDataReader : sensorsDataReaderList) {
            List<SensorMeasures> sensorMeasures = sensorsDataReader.readSensorsMeasures();
            sensorsDataSender.sendSensorsData(sensorMeasures);
        }
    }

    private void initSensorDataReaderList() {
        sensorsDataReaderList.add(javaStatusReaderSensors);
    }
}
