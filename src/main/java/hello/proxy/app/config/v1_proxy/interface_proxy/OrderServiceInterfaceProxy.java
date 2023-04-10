package hello.proxy.app.config.v1_proxy.interface_proxy;

import hello.proxy.app.util.TraceStatus;
import hello.proxy.v1.OrderServiceV1;
import hello.proxy.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

	private final OrderServiceV1 target;
	private final LogTrace logTrace;

	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;

		try {
			status = logTrace.begin("OrderService orderItem()");
			target.orderItem(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
