package com.inflearn.advancedspring.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

	public void execute(Strategy strategy){
		Long startTime = System.currentTimeMillis();
		strategy.call();
		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;

		log.info("ResultTime = {}ms", resultTime);
	}
}
