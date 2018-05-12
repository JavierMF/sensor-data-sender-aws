package org.javiermf.iottestbed.aws.dtos;

public class Measure {

    String signalName;
    long timestamp;
    Number value;

    public Measure(String signalName, long timestamp, Number value) {
        this.signalName = signalName;
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Number getValue() {
        return value;
    }

    public String getSignalName() {
        return signalName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder("{");
        stringBuffer.append(signalName);
        stringBuffer.append(",");
        stringBuffer.append(timestamp);
        stringBuffer.append(",");
        stringBuffer.append(value);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
