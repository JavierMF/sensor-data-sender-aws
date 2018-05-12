package org.javiermf.iottestbed.aws.readers;

import org.javiermf.iottestbed.aws.dtos.SensorMeasures;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class JavaStatusReaderSensors implements SensorsDataReader {

    @Override
    public List<SensorMeasures> readSensorsMeasures() {

        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeeMemory = Runtime.getRuntime().freeMemory();
        long heapMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        int loadedClassCount = ManagementFactory.getClassLoadingMXBean().getLoadedClassCount();

        SensorMeasures sensorMeasures = new SensorMeasures("JAVA_PROCESS")
                .addMeasure("MEMORY_TOTAL", totalMemory)
                .addMeasure("MEMORY_FREE", freeeMemory)
                .addMeasure("MEMORY_HEAP", heapMemory)
                .addMeasure("CLASSES_LOADED", loadedClassCount);
        return singletonList(sensorMeasures);
    }
}
