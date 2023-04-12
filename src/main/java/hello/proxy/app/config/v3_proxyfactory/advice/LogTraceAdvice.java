package hello.proxy.app.config.v3_proxyfactory.advice;

import hello.proxy.app.util.TraceStatus;
import hello.proxy.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

	private final LogTrace logTrace;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TraceStatus status = null;

		try {
			Method method = invocation.getMethod();
			String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";

			status = logTrace.begin(message);
			Object result = invocation.proceed();
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
