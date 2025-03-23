package ru.dmytrium.main.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    @Profile("!prod")
    public DataSource devDataSource() {
        return DataSourceBuilder.create()
                .type(org.postgresql.ds.PGSimpleDataSource.class)
                .url("jdbc:postgresql://localhost:5432/mydatabase")
                .username("postgres")
                .password("postgres")
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        return DataSourceBuilder.create()
                .type(org.postgresql.ds.PGSimpleDataSource.class)
                .url("jdbc:postgresql://my_postgres:5432/mydatabase")
                .username("postgres")
                .password("postgres")
                .build();
    }
}
