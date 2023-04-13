package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

	private final LogTrace logTrace;

	// @Around는 advice에 붙는다고 생각하면 된다. (부가 기능)
	// JoinPoint는 methodInvocation과 같이 어떤 객체, 메서드가 호출되었는지 정보를 알려주는 거라고 보면 된다.
	@Around("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		TraceStatus status = null;

		try {
			String message = joinPoint.getSignature().toShortString();
			status = logTrace.begin(message);

			// 실제 호출 대상인 target을 호출한다.
			Object result = joinPoint.proceed();
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}

}
