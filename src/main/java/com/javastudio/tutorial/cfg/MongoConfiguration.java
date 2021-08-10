package com.javastudio.tutorial.cfg;

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.javastudio.tutorial.repository.mongo")
@Import(value = MongoAutoConfiguration.class)
public class MongoConfiguration {
}
