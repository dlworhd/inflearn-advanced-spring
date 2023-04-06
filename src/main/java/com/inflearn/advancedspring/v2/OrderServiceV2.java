package com.inflearn.advancedspring.v2;

import com.inflearn.advancedspring.util.TraceId;
import com.inflearn.advancedspring.util.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

	private final OrderRepositoryV2 orderRepository;
	private final TraceV2 traceV2;

	public void save(TraceId traceId, String itemId) {

		TraceStatus status = null;

		try {
			status = traceV2.beginSync(traceId, "OrderService V2");
			orderRepository.save(status.getTraceId(), itemId);
			traceV2.end(status);
		} catch (Exception e) {
			traceV2.exception(status, e);
			throw e;
		}
	}
}
