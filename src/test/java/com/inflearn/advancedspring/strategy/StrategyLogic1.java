package com.inflearn.advancedspring.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic1 implements Strategy{
	@Override
	public void call() {
		log.info("Logic 1 call!");
	}
}
