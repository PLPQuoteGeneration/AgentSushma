package com.cg.qgs.dao;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.AccountBean;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;
import com.cg.qgs.model.ReportBean;

public interface QGSDao {

	List<LoginBean> loginValid(String username, String password)throws QGSException;

	boolean getValidUsername(String userName)throws QGSException;

	int addProfile(LoginBean bean)throws QGSException;

	boolean validAccountNumber(Long accountNumber)throws QGSException;

	List<BusinessSegmentBean> viewBusinessName()throws QGSException;

	List<PolicyBean> getPolicyQuestions(String businessSegment)throws QGSException;

	long generatePolicy(PolicyBean bean)throws QGSException;

	int policyDetails(PolicyBean beans)throws QGSException;
	
	long addAccount(AccountBean accountBean) throws QGSException;

	List<PolicyBean> getDetails(long accountNumber) throws QGSException;

	List<ReportBean> reportGeneration(long accountNumber) throws QGSException;

	long newAccount(AccountBean accountCreation)throws QGSException;

	List<PolicyBean> viewPolicy(String username)throws QGSException;

	boolean checkAccountCreation(String username)throws QGSException;

	long getInsertion(AccountBean accountBean)throws QGSException;
	
	//AGENT
	
	List<BusinessSegmentBean> viewBusinessSegment() throws QGSException;

	boolean checkingAccount(Long accountNumber) throws QGSException;

	List<PolicyBean> viewPolicyDetails(String businessSegment) throws QGSException;

	int insertPolicy(PolicyBean questions) throws QGSException;

	int insertIntoPolicyDetails(List<PolicyBean> questions2) throws QGSException;

	List<PolicyBean> getViewPolicy(String username) throws QGSException;
}
