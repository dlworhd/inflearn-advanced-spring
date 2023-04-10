package hello.proxy.v3;

import hello.proxy.app.util.TraceStatus;

public interface LogTrace {
	TraceStatus begin(String message);
	void end(TraceStatus status);
	void exception(TraceStatus status, Exception ex);
}
