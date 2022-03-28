package com.example.demo.cassandra;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("ingredients")
public record Ingredient(
        @PrimaryKey
        UUID id,
        String name, com.example.demo.cassandra.Ingredient.Type type){


    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
