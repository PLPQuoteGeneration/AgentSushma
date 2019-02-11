/**
 * 
 */
package com.cg.qgs.dao;

/**
 * @author nsekhar
 *
 */
public interface QueryMapper {
	//ADMIN
	public static final String loginValidation = "select * from userrole where username = ? and password = ?";
	public static final String userValidation = "select username from userrole where username = ?";
	public static final String addProfile = "insert into userrole values(?,?,?)";
	public static final String validateAccountNumber = "select account_number from accounts where account_number = ?";
	public static final String getBusinessName = "select * from businesssegment";
	public static final String getPolicyQuestions = "select * from policyquestions where bus_seg_id = (select bus_seg_id from businesssegment where bus_seg_name = ?)";
	public static final String generatePolicy = "insert into policy values(policy_seq.nextval,?,?,?)";
	public static final String getPolicyNumber = "select policy_seq.currval from dual";
	public static final String insertPolicyDetails = "insert into policydetails values(?,?,?)";
	public static final String addAccount = "insert into accounts values(account_number_seq.nextval,?,?,?,?,?,?)";
	public static final String getAccountNumber = "select account_number_seq.currval from dual";
	public static final String getDetails="select * from policy where account_number=?";
	public static final String detailedReport="select a.insured_name,a.insured_street,a.insured_city,a.insured_state,a.insured_zip,p.business_segment,p.policy_premium,pq.POL_QUES_DESC,pd.answer from accounts a,policy p,policydetails pd,businesssegment bs,policyquestions pq  where a.account_number=? and a.account_number=p.account_number and p.policy_number=pd.policy_number and p.business_segment=bs.bus_seg_name and pd.question_id=pq.pol_ques_id and bs.bus_seg_id=pq.bus_seg_id";
	//INSURED
	public static final String insertQuery = "insert into accounts values(account_number_seq.nextval,?,?,?,?,?,?)";
	public static final String viewAccountNum="select account_number_seq.currval from accounts";
	public static final String viewPolicy = "select * from policy where account_number=(select account_number from accounts where username=?)";
	public static final String checkAccount = "select username from accounts where username = ?";
	//AGENT
	public static final String getinsertrecord = "INSERT INTO accounts VALUES(account_number_seq.nextval,?,?,?,?,?,?)";
	public static final String viewAccountNo = "SELECT account_number_seq.currval FROM dual ";
	public static final String viewBusinessSegment = "SELECT BUS_SEG_NAME FROM businessSegment";
	public static final String displayAccountNo = "SELECT ACCOUNT_NUMBER from accounts";
	public static final String viewPolicyDetails = "SELECT * from policyquestions where bus_seg_id = (select bus_seg_id from businesssegment where bus_seg_name = ?)";
	public static final String getinsertPolicy = "INSERT INTO policy VALUES(policy_seq.nextval,?,?,?)";
	public static final String displayPolicyNo = "SELECT policy_seq.currval from dual";
	public static final String insertIntoPolicyDetails = "insert into policyDetails values(?,?,?)";
	public static final String selectPolicy = "select p.policy_number,p.policy_premium,p.account_number,p.business_segment from policy p,accounts a,userrole u where u.username=a.username and a.account_number=p.account_number and u.username=?";

}
