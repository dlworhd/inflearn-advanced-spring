package com.inflearn.advancedspring.v2;


import com.inflearn.advancedspring.util.TraceId;
import com.inflearn.advancedspring.util.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
	private final TraceV2 traceV2;


	public void save(TraceId traceId, String itemId){
		TraceStatus status = null;
		try {
			 status = traceV2.beginSync(traceId,"OrderRepository V2");
			if(itemId.equals("ex")){
				throw new IllegalStateException("예외 발생");
			}
			sleep(1000);
			traceV2.end(status);
		} catch (Exception e) {
			traceV2.exception(status, e);
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
