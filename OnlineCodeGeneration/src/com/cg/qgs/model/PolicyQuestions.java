package com.cg.qgs.model;

public class PolicyQuestions {
	
	
	
	private Long accountNumber;
	private int buisnessSegement;
	private Double policyPremium;
	private long policyNumber;
	private String polQuesId;
	private String question;
	private Integer polQuesSeq;
	private String polQuesDesc;
	private String polQuesAns1;
	private Integer polQuesAns1Weightage;
	private String polQuesAns2;
	private Integer polQuesAns2Weightage;
	private String polQuesAns3;
	private Integer polQuesAns3Weightage;
	private String polQuesAns4;
	private Integer polQuesAns4Weightage;

	public PolicyQuestions() {
		// TODO Auto-generated constructor stub
	}

	
	public PolicyQuestions(Long accountNumber, int buisnessSegement,
			Double policyPremium, long policyNumber, String polQuesId,
			String question, Integer polQuesSeq, String polQuesDesc,
			String polQuesAns1, Integer polQuesAns1Weightage,
			String polQuesAns2, Integer polQuesAns2Weightage,
			String polQuesAns3, Integer polQuesAns3Weightage,
			String polQuesAns4, Integer polQuesAns4Weightage) {
		super();
		this.accountNumber = accountNumber;
		this.buisnessSegement = buisnessSegement;
		this.policyPremium = policyPremium;
		this.policyNumber = policyNumber;
		this.polQuesId = polQuesId;
		this.question = question;
		this.polQuesSeq = polQuesSeq;
		this.polQuesDesc = polQuesDesc;
		this.polQuesAns1 = polQuesAns1;
		this.polQuesAns1Weightage = polQuesAns1Weightage;
		this.polQuesAns2 = polQuesAns2;
		this.polQuesAns2Weightage = polQuesAns2Weightage;
		this.polQuesAns3 = polQuesAns3;
		this.polQuesAns3Weightage = polQuesAns3Weightage;
		this.polQuesAns4 = polQuesAns4;
		this.polQuesAns4Weightage = polQuesAns4Weightage;
	}

	@Override
	public String toString() {
		return "PolicyQuestions [accountNumber=" + accountNumber
				+ ", buisnessSegement=" + buisnessSegement + ", policyPremium="
				+ policyPremium + ", policyNumber=" + policyNumber
				+ ", polQuesId=" + polQuesId + ", question=" + question
				+ ", polQuesSeq=" + polQuesSeq + ", polQuesDesc=" + polQuesDesc
				+ ", polQuesAns1=" + polQuesAns1 + ", polQuesAns1Weightage="
				+ polQuesAns1Weightage + ", polQuesAns2=" + polQuesAns2
				+ ", polQuesAns2Weightage=" + polQuesAns2Weightage
				+ ", polQuesAns3=" + polQuesAns3 + ", polQuesAns3Weightage="
				+ polQuesAns3Weightage + ", polQuesAns4=" + polQuesAns4
				+ ", polQuesAns4Weightage=" + polQuesAns4Weightage + "]";
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

	public long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolQuesId() {
		return polQuesId;
	}

	public void setPolQuesId(String polQuesId) {
		this.polQuesId = polQuesId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getPolQuesSeq() {
		return polQuesSeq;
	}

	public void setPolQuesSeq(Integer polQuesSeq) {
		this.polQuesSeq = polQuesSeq;
	}

	public String getPolQuesDesc() {
		return polQuesDesc;
	}

	public void setPolQuesDesc(String polQuesDesc) {
		this.polQuesDesc = polQuesDesc;
	}

	public String getPolQuesAns1() {
		return polQuesAns1;
	}

	public void setPolQuesAns1(String polQuesAns1) {
		this.polQuesAns1 = polQuesAns1;
	}

	public Integer getPolQuesAns1Weightage() {
		return polQuesAns1Weightage;
	}

	public void setPolQuesAns1Weightage(Integer polQuesAns1Weightage) {
		this.polQuesAns1Weightage = polQuesAns1Weightage;
	}

	public String getPolQuesAns2() {
		return polQuesAns2;
	}

	public void setPolQuesAns2(String polQuesAns2) {
		this.polQuesAns2 = polQuesAns2;
	}

	public Integer getPolQuesAns2Weightage() {
		return polQuesAns2Weightage;
	}

	public void setPolQuesAns2Weightage(Integer polQuesAns2Weightage) {
		this.polQuesAns2Weightage = polQuesAns2Weightage;
	}

	public String getPolQuesAns3() {
		return polQuesAns3;
	}

	public void setPolQuesAns3(String polQuesAns3) {
		this.polQuesAns3 = polQuesAns3;
	}

	public Integer getPolQuesAns3Weightage() {
		return polQuesAns3Weightage;
	}

	public void setPolQuesAns3Weightage(Integer polQuesAns3Weightage) {
		this.polQuesAns3Weightage = polQuesAns3Weightage;
	}

	public String getPolQuesAns4() {
		return polQuesAns4;
	}

	public void setPolQuesAns4(String polQuesAns4) {
		this.polQuesAns4 = polQuesAns4;
	}

	public Integer getPolQuesAns4Weightage() {
		return polQuesAns4Weightage;
	}

	public void setPolQuesAns4Weightage(Integer polQuesAns4Weightage) {
		this.polQuesAns4Weightage = polQuesAns4Weightage;
	}


}
