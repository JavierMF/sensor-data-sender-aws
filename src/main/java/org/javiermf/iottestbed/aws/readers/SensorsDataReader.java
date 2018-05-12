package org.javiermf.iottestbed.aws.readers;

import org.javiermf.iottestbed.aws.dtos.SensorMeasures;

import java.util.List;

public interface SensorsDataReader {

    List<SensorMeasures> readSensorsMeasures();
}
