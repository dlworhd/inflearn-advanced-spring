package com.inflearn.advancedspring.v3;

import com.inflearn.advancedspring.util.TraceId;
import com.inflearn.advancedspring.util.TraceStatus;
import com.inflearn.advancedspring.v2.TraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	private final OrderRepositoryV3 orderRepository;
	private final LogTrace trace;

	public void save(String itemId) {

		TraceStatus status = null;

		try {
			status = trace.begin("OrderService V3");
			orderRepository.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
