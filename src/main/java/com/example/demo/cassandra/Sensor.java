package com.example.demo.cassandra;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("sensor_data")
public record Sensor(@PrimaryKeyColumn(name = "sensor_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
                     int sensorId,
                     @PrimaryKeyColumn(name = "day_key", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
                     String dayKey,
                     @PrimaryKeyColumn(name = "created", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
                     String created,
                     String sensor_name,
                     String description,
                     int device_id,
                     int uid,
                     int serial_number,
                     String model,
                     boolean active,
                     float value) {

}
