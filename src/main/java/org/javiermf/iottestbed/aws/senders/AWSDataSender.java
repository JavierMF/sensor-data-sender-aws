package org.javiermf.iottestbed.aws.senders;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.javiermf.iottestbed.aws.dtos.Measure;
import org.javiermf.iottestbed.aws.dtos.SensorMeasures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AWSDataSender implements SensorsDataSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSDataSender.class);

    AWSIotMqttClient client;

    @Autowired
    public AWSDataSender(AWSIotMqttClient client) {
        this.client = client;

        try {
            client.connect();
        } catch (AWSIotException e) {
            LOGGER.error("Connecting to AWS", e);
        }
    }

    @Override
    public void sendSensorsData(List<SensorMeasures> measures) {
        for (SensorMeasures sensorMeasures : measures) {
            LOGGER.info("Sending {}", sensorMeasures);
            String sensorId = sensorMeasures.getSensorName();
            String topic = String.format("/devices/%s/data", sensorId);

            for (Measure measure : sensorMeasures.getMeasures()) {
                sendMeasure(sensorId, topic, measure);
            }
        }
    }

    private void sendMeasure(String sensorId, String topic, Measure measure) {
        try {
            String measureJson = buildJSON(sensorId, measure);
            client.publish(topic, measureJson.getBytes());
        } catch (AWSIotException e) {
            LOGGER.error("Sending measure {} to topic {}", measure, topic, e);
        } catch (JSONException e) {
            LOGGER.error("Gnrating JSON for measure {}", measure);
        }
    }

    private String buildJSON(String sensorId, Measure measure) throws JSONException {
        JSONObject measureJSON = new JSONObject();
        measureJSON.put("sensorId", sensorId);
        measureJSON.put("signal", measure.getSignalName());
        measureJSON.put("value", measure.getValue());
        measureJSON.put("timestamp", measure.getTimestamp());

        return measureJSON.toString();
    }
}
