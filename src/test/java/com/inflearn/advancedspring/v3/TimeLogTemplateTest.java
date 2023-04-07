package com.inflearn.advancedspring.v3;

import com.inflearn.advancedspring.template.Callback;
import com.inflearn.advancedspring.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TimeLogTemplateTest {

	@Test
	void timeLogTemplateTest(){
		TimeLogTemplate template = new TimeLogTemplate();
		template.execute(new Callback() {
			@Override
			public void call() {
				log.info("Business Logic 1 Execute!");
			}
		});

		template.execute(new Callback() {
			@Override
			public void call() {
				log.info("Business Logic 2 Execute!");
			}
		});
	}

	@Test
	void callbackV2(){
		TimeLogTemplate template = new TimeLogTemplate();
		template.execute(() -> log.info("Business Logic 1 Execute!"));
		template.execute(() -> log.info("Business Logic 2 Execute!"));
	}

}
