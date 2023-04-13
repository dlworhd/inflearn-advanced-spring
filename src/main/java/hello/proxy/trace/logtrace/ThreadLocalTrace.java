package hello.proxy.trace.logtrace;

import hello.proxy.trace.TraceId;
import hello.proxy.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalTrace implements LogTrace {

	public static final String PREFIX = "-->";
	public static final String SUFFIX = "<--";
	public static final String EX_SUFFFIX = "<X-";

	/**
	 * ThreadLocal.set() 변수에 값 할당
	 * ThreadLocal.get() 변수의 값 조회
	 * ThreadLocal.remove() 쓰레드 로컬의 값 삭제
	 */
	private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

	@Override
	public TraceStatus begin(String message) {
		syncTraceId();
		TraceId traceId = traceIdHolder.get();
		Long startTimeMs = System.currentTimeMillis();

		log.info("[{}] {}{}",
				traceId.getId(),
				addSpace(PREFIX, traceId.getLevel()),
				message
		);

		return new TraceStatus(traceId, startTimeMs, message);
	}

	@Override
	public void end(TraceStatus status) {
		complete(status, null);
	}

	@Override
	public void exception(TraceStatus status, Exception ex) {
		complete(status, ex);
	}

	public void complete(TraceStatus status, Exception ex) {
		Long stopTimeMs = System.currentTimeMillis();
		Long resultTime = stopTimeMs - status.getStartTimeMs();
		TraceId traceId = status.getTraceId();

		if (ex == null) {
			log.info("[{}] {}{} time={}ms",
					traceId.getId(),
					addSpace(SUFFIX, traceId.getLevel()),
					status.getMessage(),
					resultTime
			);
		} else {
			log.info("[{}] {}{} time={}ms {}",
					traceId.getId(),
					addSpace(EX_SUFFFIX, traceId.getLevel()),
					status.getMessage(),
					resultTime,
					ex.toString()
			);
		}

		releaseId();
	}

	private void releaseId() {
		TraceId traceId = traceIdHolder.get();

		if (traceId.isFirstLevel()) {
			traceIdHolder.remove();
		} else {
			traceIdHolder.set(traceId.createPreviousId());
		}
	}

	public void syncTraceId() {
		TraceId traceId = traceIdHolder.get();

		if (traceId == null) {
			traceIdHolder.set(new TraceId());
		} else {
			traceIdHolder.set(traceId.createNextId());
		}
	}

	public String addSpace(String prefix, int level) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < level; i++) {
			sb.append(i == level - 1 ? "|" + prefix : "|  ");
		}
		return sb.toString();
	}
}
