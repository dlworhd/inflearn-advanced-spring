package com.inflearn.advancedspring.v2;

import com.inflearn.advancedspring.util.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

	private final OrderServiceV2 orderService;
	private final TraceV2 traceV2;


	@GetMapping("/v2/request")
	public String saveItemId(String itemId){
		TraceStatus status = null;
		try {
			status = traceV2.begin("OrderController V2");
			orderService.save(status.getTraceId(), itemId);
			traceV2.end(status);
			return "ok";
		} catch (Exception e) {
			traceV2.exception(status, e);
			throw e;
		}
	}
}
