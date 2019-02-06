package com.cg.qgs.service;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.BusinessSegment;
import com.cg.qgs.model.PolicyQuestions;

public interface QGSService {

	boolean getValidations(Accounts accounts) throws QGSException;

	long getInsertion(Accounts accounts) throws QGSException;

	List<BusinessSegment> viewBusinessSegment() throws QGSException;

	boolean checkingAccount(Long accountNumber) throws QGSException;

	List<PolicyQuestions> viewPolicyDetails(String business_segment) throws QGSException;



	

}
