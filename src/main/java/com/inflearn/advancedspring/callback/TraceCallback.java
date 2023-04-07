package com.inflearn.advancedspring.callback;

public interface TraceCallback<T> {
	T call();
}
