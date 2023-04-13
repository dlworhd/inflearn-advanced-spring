package hello.proxy.trace.template;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate<T> {

	private final LogTrace logTrace;

	protected AbstractTemplate(LogTrace logTrace) {
		this.logTrace = logTrace;
	}

	public T execute(String message) {

		TraceStatus status = null;

		try {
			status = logTrace.begin(message);
			T businessLogic = call();
			logTrace.end(status);
			return businessLogic;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}

	}

	public abstract T call();

}
