package com.cg.qgs.dao;

public interface QueryMapper {

	public static final String getinsertrecord = "INSERT INTO accounts VALUES(account_number_seq.nextval,?,?,?,?,?,?)";
	public static final String viewAccountNo = "SELECT account_number_seq.currval FROM dual ";
	public static final String viewBusinessSegment = "SELECT BUS_SEG_NAME FROM businessSegment";
	public static final String displayAccountNo = "SELECT ACCOUNT_NUMBER from accounts";
	public static final String viewPolicyDetails = "SELECT * from policyquestions where bus_seg_id = (select bus_seg_id from businesssegment where bus_seg_name = ?)";

}
