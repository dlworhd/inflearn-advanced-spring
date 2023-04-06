package com.inflearn.advancedspring.v1;

import com.inflearn.advancedspring.util.TraceStatus;
import com.inflearn.advancedspring.v1.OrderServiceV1;
import com.inflearn.advancedspring.v1.TraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

	private final OrderServiceV1 orderServiceV1;
	private final TraceV1 trace;

	@GetMapping("/v1/request")
	public String saveItemId(String itemId){
		TraceStatus status = null;
		try {
			status = trace.begin("OrderController.request()"); // begin후 Trace 상태 인스턴스 만들어서 반환
			orderServiceV1.orderItem(itemId); // 예외 발생 가능성 -> 발생하면 trace.end()말고 catch문에 있는 trace.exception() 실행
			trace.end(status);
			return "ok";
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 에러를 로그만 남겼기 때문에 다시 밖으로 던져야 함
		}
	}
}
