package hello.proxy.app.v5;

import hello.proxy.trace.callback.TraceCallback;
import hello.proxy.trace.callback.TraceTemplate;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderControllerV5 {

	private OrderServiceV5 orderService;
	private TraceTemplate template;

	public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
		this.orderService = orderService;
		this.template = new TraceTemplate(logTrace);
	}

	@GetMapping("/v5/request")
	public String saveItemId(String itemId){
		return template.execute("OrderControl V5", new TraceCallback<String>() {
			@Override
			public String call() {
				orderService.save(itemId);
				return "ok";
			}
		});
	}
}
