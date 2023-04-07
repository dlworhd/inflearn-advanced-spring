package com.inflearn.advancedspring.v5;

import com.inflearn.advancedspring.callback.TraceCallback;
import com.inflearn.advancedspring.callback.TraceTemplate;
import com.inflearn.advancedspring.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepository;
	private final TraceTemplate template;

	public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
		this.orderRepository = orderRepository;
		this.template = new TraceTemplate(trace);
	}

	public void save(String itemId) {
		template.execute("OrderService V5", () -> {
			orderRepository.save(itemId);
			return null;
		});
	}
}
