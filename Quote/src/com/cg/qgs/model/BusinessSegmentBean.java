package com.cg.qgs.model;

public class BusinessSegmentBean {

	private String businessId;
	private String businessName;
	private int businessSeq;
	
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public int getBusinessSeq() {
		return businessSeq;
	}
	public void setBusinessSeq(int businessSeq) {
		this.businessSeq = businessSeq;
	}
	@Override
	public String toString() {
		return "BusinessSegmentBean [businessId=" + businessId + ", businessName=" + businessName + ", businessSeq="
				+ businessSeq + "]";
	}
	public BusinessSegmentBean(String businessId, String businessName, int businessSeq) {
		super();
		this.businessId = businessId;
		this.businessName = businessName;
		this.businessSeq = businessSeq;
	}
	public BusinessSegmentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
