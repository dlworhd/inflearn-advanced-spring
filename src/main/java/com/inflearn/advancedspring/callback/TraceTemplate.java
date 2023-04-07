package com.inflearn.advancedspring.callback;

import com.inflearn.advancedspring.util.TraceStatus;
import com.inflearn.advancedspring.v3.LogTrace;

public class TraceTemplate {

	LogTrace logTrace;

	public TraceTemplate(LogTrace logTrace) {
		this.logTrace = logTrace;
	}

	public <T> T execute(String message, TraceCallback<T> callback){
		TraceStatus status = null;
		try {
			status = logTrace.begin(message);
			T result = callback.call();
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
