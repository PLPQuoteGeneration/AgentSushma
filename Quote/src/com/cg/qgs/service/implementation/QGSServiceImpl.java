/**
 * 
 */
package com.cg.qgs.service.implementation;

import java.util.List;
import java.util.regex.Pattern;

import com.cg.qgs.dao.QGSDao;
import com.cg.qgs.dao.implementation.QGSDaoImplementation;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.AccountBean;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;
import com.cg.qgs.model.ReportBean;
import com.cg.qgs.service.QGSService;

/**
 * @author nsekhar
 *
 */
public class QGSServiceImpl implements QGSService {

	QGSDao dao = new QGSDaoImplementation();
	
	@Override
	public List<PolicyBean> getDetails(long accountNumber) throws QGSException {
		return dao.getDetails(accountNumber);
	}
	
	@Override
	public List<LoginBean> loginValid(String username, String password)throws QGSException {
		// TODO Auto-generated method stub
		return dao.loginValid(username,password);
	}

	@Override
	public boolean getValidUsername(String userName) throws QGSException {
		// TODO Auto-generated method stub
		return dao.getValidUsername(userName);
	}

	@Override
	public int addProfile(LoginBean bean) throws QGSException {
		// TODO Auto-generated method stub
		return dao.addProfile(bean);
	}

	@Override
	public boolean validAccountNumber(Long accountNumber) throws QGSException {
		// TODO Auto-generated method stub
		return dao.validAccountNumber(accountNumber);
	}

	@Override
	public List<BusinessSegmentBean> viewBusinessName() throws QGSException {
		// TODO Auto-generated method stub
		return dao.viewBusinessName();
	}

	@Override
	public List<PolicyBean> getPolicyQuestions(String businessSegment) throws QGSException {
		// TODO Auto-generated method stub
		return dao.getPolicyQuestions(businessSegment);
	}

	@Override
	public long generatePolicy(PolicyBean bean) throws QGSException {
		
		return  dao.generatePolicy(bean);
	}

	@Override
	public int policyDetails(PolicyBean beans) throws QGSException {
		// TODO Auto-generated method stub
		return dao.policyDetails(beans);
	}

	@Override
	public long addAccount(AccountBean accountBean) throws QGSException {
		// TODO Auto-generated method stub
		return dao.addAccount(accountBean);
	}

	@Override
	public boolean validName(String insuredName) throws QGSException {
		String NameRegEx="^[a-zA-Z\\s]{3,30}$";
		return Pattern.matches(NameRegEx, insuredName);
	}

	@Override
	public boolean validStreet(String insuredStreet) throws QGSException {
		String streetRegEx="^[a-zA-Z0-9\\s]{3,40}$";
		return Pattern.matches(streetRegEx, insuredStreet);
	}

	@Override
	public boolean validCity(String insuredCity) throws QGSException {
		String cityRegEx="^[a-zA-Z\\s]{3,15}$";
		return Pattern.matches(cityRegEx, insuredCity);
	}

	@Override
	public boolean validState(String insuredState) throws QGSException {
		String stateRegEx="^[a-zA-Z\\s]{3,15}$";
		return Pattern.matches(stateRegEx, insuredState) ;
	}

	@Override
	public boolean validZip(long insuredZip) throws QGSException {
		String stateRegEx="^[1|2|3|4|5|6|7|8|9]{1}[0-9]{4}$";
		return Pattern.matches(stateRegEx, String.valueOf(insuredZip));
	}

	@Override
	public boolean accountValidation(long accountNumber) throws QGSException {
	String accountNoRegEx="^[1|2|3|4|5|6|7|8|9]{1}[0-9]{9}$";
		return Pattern.matches(accountNoRegEx, String.valueOf(accountNumber));
	}

	@Override
	public List<ReportBean> reportGeneration(long accountNumber) throws QGSException {
		
		return dao.reportGeneration(accountNumber);
	}

	@Override
	public long newAccount(AccountBean accountCreation) throws QGSException {
		// TODO Auto-generated method stub
		return dao.newAccount(accountCreation);
	}

	@Override
	public List<PolicyBean> viewPolicy(String username) throws QGSException {
		// TODO Auto-generated method stub
		return dao.viewPolicy(username);
	}

	@Override
	public boolean checkAccountCreation(String username) throws QGSException {
		
		return dao.checkAccountCreation(username);
	}

	@Override
	public long getInsertion(AccountBean accountBean) throws QGSException {
		// TODO Auto-generated method stub
		return dao.getInsertion(accountBean);
	}

	@Override
	public boolean checkingAccount(Long accountNumber) throws QGSException {
		// TODO Auto-generated method stub
		return dao.checkingAccount(accountNumber);
	}

	@Override
	public List<BusinessSegmentBean> viewBusinessSegment() throws QGSException {
		// TODO Auto-generated method stub
		return dao.viewBusinessSegment();
	}

	@Override
	public List<PolicyBean> viewPolicyDetails(String businessSegment) throws QGSException {
		// TODO Auto-generated method stub
		return dao.viewPolicyDetails(businessSegment);
	}

	@Override
	public int insertPolicy(PolicyBean questions) throws QGSException {
		// TODO Auto-generated method stub
		return dao.insertPolicy(questions);
	}

	@Override
	public int insertIntoPolicyDetails(List<PolicyBean> questions2) throws QGSException {
		// TODO Auto-generated method stub
	return dao.insertIntoPolicyDetails(questions2);	
	}

	@Override
	public List<PolicyBean> getViewPolicy(String username) throws QGSException {
		// TODO Auto-generated method stub
		return dao.getViewPolicy(username);
	}


}
