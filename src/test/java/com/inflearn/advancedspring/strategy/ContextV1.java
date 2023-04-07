package com.inflearn.advancedspring.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

	private Strategy strategy;

	public ContextV1(Strategy strategy) {
		this.strategy = strategy;
	}

	public void execute(){
		Long startTime = System.currentTimeMillis();
		strategy.call();
		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;


		log.info("ResultTime = {}ms", resultTime);
	}

}
