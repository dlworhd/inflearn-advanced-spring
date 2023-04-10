package hello.proxy.pureproxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TimeDecorator implements Component{

	private final Component component;

	@Override
	public String operation() {

		Long startTime = System.currentTimeMillis();
		log.info("TimeDecorator Start");
		String operation = component.operation();
		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;
		log.info("TimeDecorator End = {}ms", resultTime);

		return operation;
	}
}
