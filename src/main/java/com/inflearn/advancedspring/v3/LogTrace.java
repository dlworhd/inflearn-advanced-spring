package com.inflearn.advancedspring.v3;

import com.inflearn.advancedspring.util.TraceStatus;

public interface LogTrace {
	TraceStatus begin(String message);
	void end(TraceStatus status);
	void exception(TraceStatus status, Exception ex);
}
