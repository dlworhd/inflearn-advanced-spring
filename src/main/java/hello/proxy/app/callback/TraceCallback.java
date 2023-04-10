package hello.proxy.app.callback;

public interface TraceCallback<T> {
	T call();
}
