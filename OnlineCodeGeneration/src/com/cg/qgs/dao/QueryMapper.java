package com.cg.qgs.dao;

public interface QueryMapper {

	public static final String getinsertrecord = "INSERT INTO accounts VALUES(account_number_seq.nextval,?,?,?,?,?,?)";
	public static final String viewAccountNo = "SELECT account_number_seq.currval FROM dual ";
	public static final String viewBusinessSegment = null;

}
