package com.cg.qgs.model;

public class ReportBean {
	
	private long accountNumber;
	private String insuredName;
	private String insuredStreet;
	private String insuredCity;
	private String insuredState;
	private Long insuredZip;
	private String businessName;
	private String question;
	private String answerDetails;
	private Double policyPremium;
	
	public ReportBean() {
		// TODO Auto-generated constructor stub
	}

	public ReportBean(long accountNumber, String insuredName, String insuredStreet, String insuredCity,
			String insuredState, Long insuredZip, String businessName, String question, String answerDetails,
			Double policyPremium) {
		super();
		this.accountNumber = accountNumber;
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.businessName = businessName;
		this.question = question;
		this.answerDetails = answerDetails;
		this.policyPremium = policyPremium;
	}

	@Override
	public String toString() {
		return "ReportBean [accountNumber=" + accountNumber + ", insuredName=" + insuredName + ", insuredStreet="
				+ insuredStreet + ", insuredCity=" + insuredCity + ", insuredState=" + insuredState + ", insuredZip="
				+ insuredZip + ", businessName=" + businessName + ", question=" + question + ", answerDetails="
				+ answerDetails + ", policyPremium=" + policyPremium + "]";
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredStreet() {
		return insuredStreet;
	}

	public void setInsuredStreet(String insuredStreet) {
		this.insuredStreet = insuredStreet;
	}

	public String getInsuredCity() {
		return insuredCity;
	}

	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}

	public String getInsuredState() {
		return insuredState;
	}

	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}

	public Long getInsuredZip() {
		return insuredZip;
	}

	public void setInsuredZip(Long insuredZip) {
		this.insuredZip = insuredZip;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswerDetails() {
		return answerDetails;
	}

	public void setAnswerDetails(String answerDetails) {
		this.answerDetails = answerDetails;
	}

	public Double getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(Double policyPremium) {
		this.policyPremium = policyPremium;
	}
	

}
