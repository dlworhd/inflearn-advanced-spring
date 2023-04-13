package hello.proxy.v3;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.ThreadLocalTrace;
import org.junit.jupiter.api.Test;

class ThreadLocalTraceTest {

	ThreadLocalTrace threadLocalTrace = new ThreadLocalTrace();

	@Test
	void threadLocalTraceTest(){

		TraceStatus status1 = threadLocalTrace.begin("Hello 1");
		TraceStatus status2 = threadLocalTrace.begin("Hello 2");

		threadLocalTrace.end(status1);
		threadLocalTrace.end(status2);

	}
}