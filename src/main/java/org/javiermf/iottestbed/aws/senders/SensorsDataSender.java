package org.javiermf.iottestbed.aws.senders;

import org.javiermf.iottestbed.aws.dtos.SensorMeasures;

import java.util.List;

public interface SensorsDataSender {

    void sendSensorsData(List<SensorMeasures> measures);
}
