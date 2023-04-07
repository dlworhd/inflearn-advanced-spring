package com.inflearn.advancedspring.v5;


import com.inflearn.advancedspring.callback.TraceTemplate;
import com.inflearn.advancedspring.template.AbstractTemplate;
import com.inflearn.advancedspring.util.TraceStatus;
import com.inflearn.advancedspring.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryV5 {
	private final TraceTemplate template;

	public OrderRepositoryV5(LogTrace trace) {
		this.template = new TraceTemplate(trace);
	}

	public void save(String itemId) {
		TraceStatus status = null;

		template.execute("OrderRepository", () -> {
			if(itemId.equals("ex")){
				throw new IllegalArgumentException();
			}
			sleep(1000);
			return null;
		});
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
