package com.inflearn.advancedspring.v3;

import com.inflearn.advancedspring.util.ThreadLocalService;
import com.inflearn.advancedspring.util.TraceId;
import com.inflearn.advancedspring.util.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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