package hello.proxy.v4;

import hello.proxy.app.template.AbstractTemplate;
import hello.proxy.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

	private final OrderServiceV4 orderService;
	private final LogTrace logTrace;

	@GetMapping("/v4/request")
	public String saveItemId(String itemId){

		AbstractTemplate<String> template = new AbstractTemplate<String>(logTrace) {
			@Override
			public String call() {
				orderService.save(itemId);
				return null;
			}
		};
		return template.execute("OrderController V4");
	}
}
