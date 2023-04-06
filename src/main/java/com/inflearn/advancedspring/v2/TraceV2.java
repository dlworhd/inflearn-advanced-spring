package com.inflearn.advancedspring.v2;

import com.inflearn.advancedspring.util.TraceId;
import com.inflearn.advancedspring.util.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TraceV2 {

	private static final String PREFIX = "-->";
	private static final String COMPLETE_PREFIX = "<--";
	private static final String EX_PREFIX = "<x-";


	public TraceStatus begin(String message) {
		TraceId traceId = new TraceId(); // level = 0, Id = UUID.randomUUID -> 하나의 Transaction에 하나의 ID, 레벨은 스택 깊이를 나타냄
		Long startTimeMs = System.currentTimeMillis();

		log.info("[{}] {}{} ",
				traceId.getId(),
				addSpace(PREFIX, traceId.getLevel()),
				message
		);

		return new TraceStatus(traceId, startTimeMs, message);
	}

	public TraceStatus beginSync(TraceId beforeTraceId, String message){
		TraceId nextId = beforeTraceId.createNextId(); //Id가 올라가는 게 아니라 Level이 올라감
		Long startMilis = System.currentTimeMillis();

		log.info("["+ nextId.getId() +"] " + addSpace(PREFIX, nextId.getLevel()) + message);

		return new TraceStatus(nextId, startMilis, message);
	}

	public void end(TraceStatus status){
		complete(status, null);
	}

	public void exception(TraceStatus status, Exception e){
		complete(status, e);
	}


	private void complete(TraceStatus status, Exception e) {
		Long stopTimeMs = System.currentTimeMillis();
		Long resultTimeMs = stopTimeMs - status.getStartTimeMs();
		TraceId traceId = status.getTraceId();

		if(e == null){
			log.info("[{}] {}{} time={}ms",
					traceId.getId(),
					addSpace(COMPLETE_PREFIX, traceId.getLevel()),
					status.getMessage(),
					resultTimeMs
			);
		} else {
			log.info("[{}] {}{} time={}ms ex={}",
					traceId.getId(),
					addSpace(EX_PREFIX, traceId.getLevel()),
					status.getMessage(),
					resultTimeMs,
					e.toString()
			);
		}

	}


	private static String addSpace(String prefix, int level){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append((i == level - 1) ? "|" + prefix:"|  ");
		}
		return sb.toString();
	}
}
