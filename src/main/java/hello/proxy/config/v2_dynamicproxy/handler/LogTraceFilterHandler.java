package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

	private final Object target;
	private final LogTrace logTrace;
	private final String[] patterns;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		String methodName = method.getName();

		// 정해놓은 패턴과 일치하지 않으면 실행만 함 -> LogTrace 적용 안 함
		if(!PatternMatchUtils.simpleMatch(patterns, methodName)){
			return method.invoke(target, args);
		}

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
