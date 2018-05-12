package org.javiermf.iottestbed.aws.dtos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SensorMeasures {

    String sensorName;
    List<Measure> measures = new ArrayList<>();

    long measuresEpochTime;

    public SensorMeasures(String sensorName) {
        this.sensorName = sensorName;
        this.measuresEpochTime = System.currentTimeMillis() / 1000;
    }

    public SensorMeasures addMeasure(Measure measure) {
        measures.add(measure);
        return this;
    }

    public SensorMeasures addMeasure(String signalName, Number value) {
        addMeasure(signalName, measuresEpochTime, value);
        return this;
    }

    public SensorMeasures addMeasure(String signalName, long epochTimestamp, Number value) {
        measures.add(new Measure(signalName, epochTimestamp, value));
        return this;
    }

    public void addMeasures(Collection<Measure> newMeasures) {
        measures.addAll(newMeasures);
    }

    public String getSensorName() {
        return sensorName;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder("SensorMeasures{");

        stringBuffer.append("sensorName=");
        stringBuffer.append(sensorName);
        stringBuffer.append("\n");
        stringBuffer.append("measures={\n");
        for (Measure measure : measures) {
            stringBuffer.append(measure);
        }
        stringBuffer.append("\n}");
        return stringBuffer.toString();
    }
}
