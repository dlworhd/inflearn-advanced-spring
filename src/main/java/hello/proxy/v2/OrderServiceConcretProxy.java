package hello.proxy.v2;


import hello.proxy.app.util.TraceStatus;
import hello.proxy.v3.LogTrace;

public class OrderServiceConcretProxy extends OrderServiceV2{

	private final OrderServiceV2 target;
	private final LogTrace trace;

	public OrderServiceConcretProxy(OrderServiceV2 target, LogTrace trace) {
		super(null);
		this.target = target;
		this.trace = trace;
	}

	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = trace.begin("OrderService.orderItem()");
			target.orderItem(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
