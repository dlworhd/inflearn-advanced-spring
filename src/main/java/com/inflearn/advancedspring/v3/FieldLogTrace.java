package com.inflearn.advancedspring.v3;

import com.inflearn.advancedspring.util.TraceId;
import com.inflearn.advancedspring.util.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

	public static final String PREFIX = "-->";
	public static final String SUFFIX = "<--";
	public static final String EX_SUFFFIX = "<X-";

	private TraceId traceIdHolder; // 동시성 이슈, 동기화


	@Override
	public TraceStatus begin(String message) {
		syncTraceId(); // traceId 초기화 또는 값이 존재하는 경우 존재하는 값의 다음 TraceId 가져오기(Transaction ID는 유지, Level은 증가)
		TraceId traceId = traceIdHolder;
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

	public void complete(TraceStatus status, Exception ex){
		Long stopTimeMs = System.currentTimeMillis();
		Long resultTime = stopTimeMs - status.getStartTimeMs();
		TraceId traceId = status.getTraceId();

		if(ex == null){
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
		if(traceIdHolder.isFirstLevel()){
			traceIdHolder = null;
		} else {
			traceIdHolder = traceIdHolder.createPreviousId();
		}
	}

	public void syncTraceId() {
		if (traceIdHolder == null) {
			traceIdHolder = new TraceId();
		} else {
			traceIdHolder = traceIdHolder.createNextId();
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
