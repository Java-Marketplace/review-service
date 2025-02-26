package com.jmp.reviewservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ReviewMicroserviceApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void mainBeanIsPresent() {
        assertThat(context.getBean(ReviewMicroserviceApplication.class)).isNotNull();
    }
}

class ReviewMicroserviceMainMethodTest {

    @Test
    void testMain() {
        assertDoesNotThrow(() -> ReviewMicroserviceApplication.main(new String[]{}));
    }
}