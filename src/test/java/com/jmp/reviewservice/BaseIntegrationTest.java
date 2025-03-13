package com.jmp.reviewservice;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.spring.api.DBRider;
import initializer.PostgresInitializer;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

@RunWith(SpringRunner.class)
@Testcontainers
@ContextConfiguration(initializers = {PostgresInitializer.class})
@DBRider
@DBUnit(caseSensitiveTableNames = true, schema = "public")
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public abstract class BaseIntegrationTest {

}
