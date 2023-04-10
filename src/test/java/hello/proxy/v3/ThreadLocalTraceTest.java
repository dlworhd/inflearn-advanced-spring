package hello.proxy.v3;

import hello.proxy.app.util.TraceStatus;
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