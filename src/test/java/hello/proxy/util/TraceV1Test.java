package hello.proxy.util;

import hello.proxy.app.util.TraceStatus;
import hello.proxy.v1.TraceV1;
import org.junit.jupiter.api.Test;

class TraceV1Test {


	@Test
	void begin_end(){
		TraceV1 traceV1 = new TraceV1();
		TraceStatus status = traceV1.begin("hello");
		traceV1.end(status);
	}

	@Test
	void begin_exception(){
		TraceV1 traceV1 = new TraceV1();
		TraceStatus status = traceV1.begin("hello");
		traceV1.exception(status, new RuntimeException("Error!"));


	}


}