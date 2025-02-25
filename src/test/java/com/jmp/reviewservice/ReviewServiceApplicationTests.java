package com.jmp.reviewservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ReviewServiceApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void mainBeanIsPresent() {
        assertThat(context.getBean(ReviewServiceApplication.class)).isNotNull();
    }
}

class ReviewServiceMainMethodTest {

    @Test
    void testMain() {
        assertDoesNotThrow(() -> ReviewServiceApplication.main(new String[]{}));
    }
}