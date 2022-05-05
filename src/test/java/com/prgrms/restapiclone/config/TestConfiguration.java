package com.prgrms.restapiclone.config;

import com.prgrms.restapiclone.repository.OrderJdbcRepository;
import com.prgrms.restapiclone.repository.OrderRepository;
import com.prgrms.restapiclone.repository.PartJdbcRepository;
import com.prgrms.restapiclone.repository.PartRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DbConfigProperties.class)
@PropertySource(value = "classpath:application-test.yaml", factory = YamlPropertySourceFactory.class)
public class TestConfiguration {

    @Autowired
    DbConfigProperties properties;

    @Bean
    DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    PartRepository partRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new PartJdbcRepository(jdbcTemplate);
    }

    @Bean
    OrderRepository orderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new OrderJdbcRepository(jdbcTemplate);
    }
}
