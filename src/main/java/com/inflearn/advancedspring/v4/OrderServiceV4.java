package com.inflearn.advancedspring.v4;

import com.inflearn.advancedspring.template.AbstractTemplate;
import com.inflearn.advancedspring.util.TraceStatus;
import com.inflearn.advancedspring.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepository;
	private final LogTrace trace;

	public void save(String itemId) {
		AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
			@Override
			public String call() {
				orderRepository.save(itemId);
				return null;
			}
		};
		template.execute("OrderService V4");
	}
}
