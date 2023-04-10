package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

	private final ConcreteLogic concreteLogic;

	public TimeProxy(ConcreteLogic concreteLogic) {
		this.concreteLogic = concreteLogic;
	}

	@Override
	public String operation(){
		log.info("Time Decorator 실행");
		Long startTime = System.currentTimeMillis();
		String result = concreteLogic.operation();

		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;
		log.info("ResultTime = {}ms", resultTime);
		return result;


	}
}
