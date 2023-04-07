package com.inflearn.advancedspring.template;

import com.inflearn.advancedspring.util.TraceStatus;
import com.inflearn.advancedspring.v3.LogTrace;
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
