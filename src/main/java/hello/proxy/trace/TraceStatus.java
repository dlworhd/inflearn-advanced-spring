package hello.proxy.trace;

public class TraceStatus {

	private TraceId traceId; // ID마다 레벨이 있기 때문에, 객체로 만들어서 관리 Has - a 관계
	private Long startTimeMs;
	private String message;

	public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
		this.traceId = traceId;
		this.startTimeMs = startTimeMs;
		this.message = message;
	}

	public TraceId getTraceId() {
		return traceId;
	}

	public Long getStartTimeMs() {
		return startTimeMs;
	}

	public String getMessage() {
		return message;
	}
}
