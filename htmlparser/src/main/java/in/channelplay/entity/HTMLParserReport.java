package in.channelplay.entity;

import in.channelplay.enums.HTMLParserEnums;

public class HTMLParserReport {

	private Long attemptNum;
	private String dateTime;
	private HTMLParserEnums status;
	private String timeTaken;

	public HTMLParserReport() {
		
	}

	public HTMLParserReport(Long attemptNum, String dateTime, HTMLParserEnums status, String timeTaken) {
		this.attemptNum = attemptNum;
		this.dateTime = dateTime;
		this.status = status;
		this.timeTaken = timeTaken;
	}

	public Long getAttemptNum() {
		return attemptNum;
	}

	public void setAttemptNum(Long attemptNum) {
		this.attemptNum = attemptNum;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public HTMLParserEnums getStatus() {
		return status;
	}

	public void setStatus(HTMLParserEnums status) {
		this.status = status;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

}
