package hello.proxy.jdkdynamic.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
@Slf4j

public class TimeInvocationHandler implements InvocationHandler {


	// 호출 대상이 되는 클래스의 인스턴스
	private final Object target;

	// Proxy를 이용하면 invoke가 내부적으로 실행된다.
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("Time Proxy 실행");
		Long startTime = System.currentTimeMillis();
		Object result = method.invoke(target, args);
		Long endTime = System.currentTimeMillis();
		log.info("Time Proxy 종료");
		Long resultTime = endTime - startTime;
		log.info("Time Proxy result = {}ms", resultTime);
		return result;
	}
}
