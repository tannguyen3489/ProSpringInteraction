/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apress.prospringintegration.messagestore.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.aggregator.CorrelationStrategy;
import org.springframework.integration.aggregator.HeaderAttributeCorrelationStrategy;
import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.aggregator.SequenceSizeReleaseStrategy;
import org.springframework.integration.jdbc.JdbcMessageStore;

import javax.sql.DataSource;

@Configuration
public class JdbcMessageStoreConfiguration {

    @Value("${correlation-header}")
    private String correlationHeader;

    @Value("#{dataSource}")
    private DataSource dataSource;

    @Bean
    public ReleaseStrategy releaseStrategy() {
        return new SequenceSizeReleaseStrategy(false);
    }

    @Bean
    public CorrelationStrategy correlationStrategy() {
        return new HeaderAttributeCorrelationStrategy(this.correlationHeader);
    }

    @Bean
    public JdbcMessageStore jdbcMessageGroupStore() {
        JdbcMessageStore jdbcMessageGroupStore = new JdbcMessageStore(dataSource);
        return jdbcMessageGroupStore;
    }
}
