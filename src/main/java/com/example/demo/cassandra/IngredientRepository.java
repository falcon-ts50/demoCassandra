package com.example.demo.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

@Repository
public interface IngredientRepository extends CassandraRepository<Ingredient, Event.ID> {

}
