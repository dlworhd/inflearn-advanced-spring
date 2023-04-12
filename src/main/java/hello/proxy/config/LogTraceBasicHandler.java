package hello.proxy.config;

import hello.proxy.app.util.TraceStatus;
import hello.proxy.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class LogTraceBasicHandler implements InvocationHandler {

	private final Object target;
	private final LogTrace logTrace;


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		TraceStatus status = null;

		try {
			// 어떤 메서드가 실행될지 동적으로 적용 가능하다 !
			String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
			status = logTrace.begin(message);
			Object result = method.invoke(target, args);
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
