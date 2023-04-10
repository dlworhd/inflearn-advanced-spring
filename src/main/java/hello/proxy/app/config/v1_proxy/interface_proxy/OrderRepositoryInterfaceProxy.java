package hello.proxy.app.config.v1_proxy.interface_proxy;

import hello.proxy.app.util.TraceStatus;
import hello.proxy.v1.OrderRepositoryV1;
import hello.proxy.v3.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

	private final OrderRepositoryV1 target;
	private final LogTrace logTrace;


	@Override
	public void save(String itemId) {
		TraceStatus status = null;

		try {
			status = logTrace.begin("OrderRepository save()");
			target.save(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw new IllegalArgumentException(e);
		}


	}
}
