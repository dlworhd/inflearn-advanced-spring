package hello.proxy.cglib;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

	private final Object target;

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

		log.info("Time Proxy 실행");
		Long startTime = System.currentTimeMillis();

		Object result = method.invoke(target, args);

		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;

		log.info("Time Proxy 종료 = {}ms", resultTime);
		return result;
	}
}
