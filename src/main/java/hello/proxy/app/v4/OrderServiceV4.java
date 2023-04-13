package hello.proxy.app.v4;

import hello.proxy.trace.template.AbstractTemplate;
import hello.proxy.trace.logtrace.LogTrace;
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
