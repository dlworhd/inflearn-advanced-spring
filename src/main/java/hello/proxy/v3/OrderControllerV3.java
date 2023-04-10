package hello.proxy.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

	private final OrderServiceV3 orderService;

	@GetMapping("/v3/request")
	public String saveItemId(String itemId){
			orderService.save(itemId);
			return "ok";
	}
}
