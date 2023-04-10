package hello.proxy.app.config.v1_proxy.interface_proxy;

import hello.proxy.app.util.TraceStatus;
import hello.proxy.v1.OrderControllerV1;
import hello.proxy.v3.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

	private final OrderControllerV1 target;
	private final LogTrace logTrace;

	@Override
	public String request(String itemId) {
		TraceStatus status = null;

		try {
			status = logTrace.begin("OrderController request()");
			String result = target.request(itemId);
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}

	@Override
	public String nolog() {
		return target.nolog();
	}
}
