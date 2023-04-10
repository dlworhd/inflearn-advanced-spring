package hello.proxy.v2;

import hello.proxy.app.util.TraceStatus;
import hello.proxy.v3.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2{


	private final OrderControllerV2 target;
	private final LogTrace trace;

	public OrderControllerConcreteProxy(OrderControllerV2 orderControllerV2, LogTrace trace) {
		super(null);
		this.target = orderControllerV2;
		this.trace = trace;
	}

	@Override
	public String request(String itemId) {
		TraceStatus status = null;

		try {
			status = trace.begin("OrderController");
			String result = target.request(itemId);
			trace.end(status);
			return result;
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}

	@Override
	public String noLog() {
		return target.noLog();
	}
}
