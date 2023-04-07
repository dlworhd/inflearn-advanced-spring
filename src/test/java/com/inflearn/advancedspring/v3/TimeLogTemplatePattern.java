package com.inflearn.advancedspring.v3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TimeLogTemplatePattern {

	@Test
	void testTemplatePattern(){

		logic1();
		logic2();


	}

	void logic1(){
		Long startTime = System.currentTimeMillis();
		log.info("Business Logic V1");
		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;
		log.info("V1 ResultTime = {}ms", resultTime);
	}

	void logic2(){
		Long startTime = System.currentTimeMillis();
		log.info("Business Logic V2");
		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;
		log.info("V2 ResultTime = {}ms", resultTime);
	}
}
