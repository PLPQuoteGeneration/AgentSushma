package com.cg.qgs.model;

public class PolicyBean {

	private Long accountNumber;
	private int buisnessSegement;
	private Double policyPremium;
	private long policyNumber;
	private String policyQuestionId;
	private String businessId;//business segment id
	private String question;//question desc
	private String answerOne;
	private int ansOneWeightage;
	private int ansTwoWeightage;
	private int ansThreeWeightage;
	private String answerTwo;
	private String answerThree;
	private String questionDetails;
	private String answerDetails;
	
	
	@Override
	public String toString() {
		return "PolicyBean [accountNumber=" + accountNumber + ", buisnessSegement=" + buisnessSegement
				+ ", policyPremium=" + policyPremium + ", policyNumber=" + policyNumber + ", policyQuestionId="
				+ policyQuestionId + ", businessId=" + businessId + ", question=" + question + ", answerOne="
				+ answerOne + ", ansOneWeightage=" + ansOneWeightage + ", ansTwoWeightage=" + ansTwoWeightage
				+ ", ansThreeWeightage=" + ansThreeWeightage + ", answerTwo=" + answerTwo + ", answerThree="
				+ answerThree + ", questionDetails=" + questionDetails + ", answerDetails=" + answerDetails + "]";
	}
	public String getQuestionDetails() {
		return questionDetails;
	}
	public void setQuestionDetails(String questionDetails) {
		this.questionDetails = questionDetails;
	}
	public String getAnswerDetails() {
		return answerDetails;
	}
	public void setAnswerDetails(String answerDetails) {
		this.answerDetails = answerDetails;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBuisnessSegement() {
		return buisnessSegement;
	}
	public void setBuisnessSegement(int buisnessSegement) {
		this.buisnessSegement = buisnessSegement;
	}
	public Double getPolicyPremium() {
		return policyPremium;
	}
	public void setPolicyPremium(Double policyPremium) {
		this.policyPremium = policyPremium;
	}
	
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public String getPolicyQuestionId() {
		return policyQuestionId;
	}
	public void setPolicyQuestionId(String policyQuestionId) {
		this.policyQuestionId = policyQuestionId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerOne() {
		return answerOne;
	}
	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}
	public int getAnsOneWeightage() {
		return ansOneWeightage;
	}
	public void setAnsOneWeightage(int ansOneWeightage) {
		this.ansOneWeightage = ansOneWeightage;
	}
	public int getAnsTwoWeightage() {
		return ansTwoWeightage;
	}
	public void setAnsTwoWeightage(int ansTwoWeightage) {
		this.ansTwoWeightage = ansTwoWeightage;
	}
	public int getAnsThreeWeightage() {
		return ansThreeWeightage;
	}
	public void setAnsThreeWeightage(int ansThreeWeightage) {
		this.ansThreeWeightage = ansThreeWeightage;
	}
	public String getAnswerTwo() {
		return answerTwo;
	}
	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}
	public String getAnswerThree() {
		return answerThree;
	}
	public void setAnswerThree(String answerThree) {
		this.answerThree = answerThree;
	}
	public PolicyBean() {
		super();
	}
	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public PolicyBean(Long accountNumber, int buisnessSegement, Double policyPremium, long policyNumber,
			String policyQuestionId, String businessId, String question, String answerOne, int ansOneWeightage,
			int ansTwoWeightage, int ansThreeWeightage, String answerTwo, String answerThree, String questionDetails,
			String answerDetails) {
		super();
		this.accountNumber = accountNumber;
		this.buisnessSegement = buisnessSegement;
		this.policyPremium = policyPremium;
		this.policyNumber = policyNumber;
		this.policyQuestionId = policyQuestionId;
		this.businessId = businessId;
		this.question = question;
		this.answerOne = answerOne;
		this.ansOneWeightage = ansOneWeightage;
		this.ansTwoWeightage = ansTwoWeightage;
		this.ansThreeWeightage = ansThreeWeightage;
		this.answerTwo = answerTwo;
		this.answerThree = answerThree;
		this.questionDetails = questionDetails;
		this.answerDetails = answerDetails;
	}
	
}
