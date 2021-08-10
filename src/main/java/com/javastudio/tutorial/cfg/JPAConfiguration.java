package com.javastudio.tutorial.cfg;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.javastudio.tutorial.repository.jpa")
@EnableTransactionManagement
@EntityScan({ "com.spg.uccs.model"})
public class JPAConfiguration {
}
