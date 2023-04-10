package hello.proxy.v3;

import hello.proxy.app.util.TraceStatus;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

	FieldLogTrace fieldLogTrace = new FieldLogTrace();

	@Test
	void filedLogTest(){
		TraceStatus status1 = fieldLogTrace.begin("Hello 1");
		TraceStatus status2 = fieldLogTrace.begin("Hello 2");
		fieldLogTrace.end(status2);
		fieldLogTrace.end(status1);
	}

	@Test
	void filedLogTestException(){
		TraceStatus status1 = fieldLogTrace.begin("Hello 1");
		TraceStatus status2 = fieldLogTrace.begin("Hello 2");

		fieldLogTrace.exception(status2, new Exception("Error"));
		fieldLogTrace.exception(status1, new Exception("Error"));
	}


}