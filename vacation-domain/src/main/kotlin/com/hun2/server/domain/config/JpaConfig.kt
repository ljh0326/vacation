package com.hun2.server.domain.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan("com.hun2.server.domain.entity")
@EnableJpaRepositories("com.hun2.server.domain.repository")
class JpaConfig
