package com.inflearn.advancedspring.v3;

import com.inflearn.advancedspring.util.TraceStatus;
import com.inflearn.advancedspring.v2.TraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

	private final OrderServiceV3 orderService;
	private final LogTrace logTrace;

	@GetMapping("/v3/request")
	public String saveItemId(String itemId){
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderController V3");
			orderService.save(itemId);
			logTrace.end(status);
			return "ok";
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
