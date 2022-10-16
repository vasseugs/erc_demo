package com.example.erc_demo.initializer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestPropertySource("classpath:/application-test.properties")
@ContextConfiguration(initializers = {
    PostgresInitializer.Initializer.class
})
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
    "/sql/truncate_all_procedure_call.sql",
})
public abstract class AbstractTestContainers {

  @Autowired
  protected TestRestTemplate testRestTemplate;

  @BeforeAll
  public static void init() {
    PostgresInitializer.container.start();
  }
}
