package com.spaceroom.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class BlogApplicationTests {

	@Test
	void contextLoads() {
	}

}
