package com.inflearn.advancedspring.strategy;

import org.junit.jupiter.api.Test;

public class StrategyTest {

	private Strategy strategy;

	@Test
	void testV1(){
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 context1 = new ContextV1(strategyLogic1);
		context1.execute();

		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV1 context2 = new ContextV1(strategyLogic2);
		context2.execute();

	}

	@Test
	void testV2(){
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV2 context2A = new ContextV2();
		context2A.execute(strategyLogic1);

		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV2 context2B = new ContextV2();
		context2B.execute(strategyLogic2);

	}

}
