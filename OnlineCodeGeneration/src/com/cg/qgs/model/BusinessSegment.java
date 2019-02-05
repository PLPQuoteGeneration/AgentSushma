package com.cg.qgs.model;

public class BusinessSegment {
	
	private String busSegId;
	private Integer busSegSeq;
	private String busSegName;
	
	public BusinessSegment() {
		// TODO Auto-generated constructor stub
	}

	public BusinessSegment(String busSegId, Integer busSegSeq, String busSegName) {
		super();
		this.busSegId = busSegId;
		this.busSegSeq = busSegSeq;
		this.busSegName = busSegName;
	}

	@Override
	public String toString() {
		return "BusinessSegment [busSegId=" + busSegId + ", busSegSeq="
				+ busSegSeq + ", busSegName=" + busSegName + "]";
	}

	public String getBusSegId() {
		return busSegId;
	}

	public void setBusSegId(String busSegId) {
		this.busSegId = busSegId;
	}

	public Integer getBusSegSeq() {
		return busSegSeq;
	}

	public void setBusSegSeq(Integer busSegSeq) {
		this.busSegSeq = busSegSeq;
	}

	public String getBusSegName() {
		return busSegName;
	}

	public void setBusSegName(String busSegName) {
		this.busSegName = busSegName;
	}
	
}
