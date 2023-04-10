package hello.proxy.v5;

import hello.proxy.app.callback.TraceTemplate;
import hello.proxy.v3.LogTrace;
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
