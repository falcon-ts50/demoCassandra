package com.example.demo.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorRepository extends CassandraRepository<Sensor, Integer> {

    @Query("SELECT * FROM sensors.sensor_data WHERE sensor_id = :id and day_key = '2022-03-24'")
    List<Sensor> findBySensorId(@Param("id") int id);
}
