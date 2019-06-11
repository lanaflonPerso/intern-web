package com.jdc.clinic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.clinic.repo.custom.BaseRepositoryImpl;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = "com.jdc.clinic.repo")
public class PersistanceConfiguration {

}
