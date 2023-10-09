package com.ming.site;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);
	@Test
	void contextLoads() {
		log.info("hello unit test");

	}

}
