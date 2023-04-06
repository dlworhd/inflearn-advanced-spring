package com.inflearn.advancedspring.v1;


import com.inflearn.advancedspring.util.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

	private final TraceV1 traceV1;

	public void orderItem(String itemId){
		TraceStatus status = null;
		try {
			status = traceV1.begin("OrderRepository.orderItem()");
			if(itemId.equals("ex")){
				throw new IllegalStateException("예외 발생");
			}

			sleep(1000);
			traceV1.end(status);
		} catch (IllegalStateException e) {
			traceV1.exception(status, e);
			throw e;
		}

	}

	private void sleep(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
