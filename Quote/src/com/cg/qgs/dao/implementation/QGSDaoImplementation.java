package com.cg.qgs.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.qgs.dao.QGSDao;
import com.cg.qgs.dao.QueryMapper;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.AccountBean;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;
import com.cg.qgs.model.ReportBean;
import com.cg.qgs.utility.JdbcUtility;

public class QGSDaoImplementation implements QGSDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Long policyNumber = 0l;
	static Logger logger = Logger.getLogger(QGSDaoImplementation.class);

	/**
	 * Method : Logging into LogIn Module Argument : it's taking username and
	 * password as an argument return type : this method returns the list of
	 * Login Bean Object Author : Capgemini Date : 08-Feb-2019
	 */
	@Override
	public List<LoginBean> loginValid(String username, String password)
			throws QGSException {

		ResultSet resultSet = null;
		List<LoginBean> list = null;
		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		try {
			logger.info("Inside LoginValid 1st try block");
			list = new ArrayList<>();
			preparedStatement = connection
					.prepareStatement(QueryMapper.loginValidation);
			logger.info("Query Executed..");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			logger.info("ResultSet Executed");
			while (resultSet.next()) {
				logger.debug("Inside loginValid While loop 1");
				String userName = resultSet.getString(1);
				String roleCode = resultSet.getString(3);

				LoginBean bean = new LoginBean();
				bean.setUsername(userName);
				bean.setRoleCode(roleCode);

				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			logger.info("Inside LoginValid 2nd try block");
			resultSet.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			logger.info("Inside LoginValid 3rd try block");
			preparedStatement.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			logger.info("Inside LoginValid 4th try block");
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Method : Validating Username. Argument : it's taking UserName as an
	 * argument return type : this method returns the Boolean Value. Author :
	 * Capgemini Date : 08-Feb-2019
	 */

	@Override
	public boolean getValidUsername(String userName) throws QGSException {
		boolean userFlag = false;
		List<LoginBean> list1 = null;
		connection = JdbcUtility.getConnection();
		list1 = new ArrayList<>();
		try {
			logger.info("Inside GetValidUsername 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.userValidation);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				logger.debug("Inside GetValidUsername While loop 1");
				String profUserName = resultSet.getString(1);
				LoginBean bean = new LoginBean();
				bean.setUsername(profUserName);
				list1.add(bean);
			}
			if (list1.isEmpty()) {
				logger.debug("Inside GetValidUsername If block 1");
				userFlag = true;

			} else {
				logger.debug("Inside GetValidUsername Else block 1");
				userFlag = false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {

			logger.info("Inside GetValidUsername Finally block");
			try {
				logger.info("Inside GetValidUsername 2nd try block");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			try {
				logger.info("Inside GetValidUsername 3rd try block");
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			try {
				logger.info("Inside GetValidUsername 4th try block");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return userFlag;
	}

	/**
	 * Method : Inserting into Userrole. Argument : it's taking LoginBean object
	 * as an argument return type : this method returns the result Object Author
	 * : Capgemini Date : 08-Feb-2019
	 */

	@Override
	public int addProfile(LoginBean bean) throws QGSException {
		int result = 0;
		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		try {
			logger.info("Inside AddProfile 1st Try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.addProfile);
			logger.info("Query Executed..");
			preparedStatement.setString(1, bean.getUsername());
			preparedStatement.setString(2, bean.getPassword());
			preparedStatement.setString(3, bean.getRoleCode());

			result = preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				logger.info("Inside AddProfile 2nd Try block");
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			logger.info("Inside AddProfile Finally block");
			try {
				logger.info("Inside AddProfile 3rd Try block");
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				try {
					logger.info("Inside AddProfile 4th Try block");
					connection.rollback();
				} catch (SQLException e1) {
					logger.error(e.getMessage());
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			try {
				logger.info("Inside AddProfile 5th Try block");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				try {
					logger.info("Inside AddProfile 6th Try block");
					connection.rollback();
				} catch (SQLException e1) {
					logger.error(e.getMessage());
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	/**
	 * Method : Validating AccountNumbers. Argument : it's taking AccountNumber
	 * as an argument return type : this method returns the boolean value.
	 * Author : Capgemini Date : 08-Feb-2019
	 */
	@Override
	public boolean validAccountNumber(Long accountNumber) throws QGSException {
		boolean validationFlag = false;
		Long accountNo = 0l;
		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		List<PolicyBean> list = new ArrayList<>();
		try {
			logger.info("Inside ValidAccountNumber 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.validateAccountNumber);
			logger.info("Query Executed..");
			preparedStatement.setLong(1, accountNumber);

			resultSet = preparedStatement.executeQuery();
			logger.info("ResultSet Executed");
			while (resultSet.next()) {
				logger.debug("Inside GetValidUsername While loop 1");
				accountNo = resultSet.getLong(1);
				PolicyBean bean = new PolicyBean();

				bean.setAccountNumber(accountNo);
				list.add(bean);
			}
			if (!list.isEmpty()) {
				logger.debug("Inside GetValidUsername If Block 1");
				validationFlag = true;
			} else {
				logger.debug("Inside GetValidUsername Else Block 1");
				validationFlag = false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Inside ValidAccountNumber Finally block");
			try {
				logger.info("Inside ValidAccountNumber 2nd try block");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			try {
				logger.info("Inside ValidAccountNumber 3rd try block");
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			try {
				logger.info("Inside ValidAccountNumber 4th try block");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return validationFlag;
	}

	/**
	 * Method : Displaying BusinessSegment to User. Argument : it's not taking
	 * anything as an argument return type : this method returns the list of
	 * BusinessSegmentBean Object Author : Capgemini Date : 08-Feb-2019
	 */
	@Override
	public List<BusinessSegmentBean> viewBusinessName() throws QGSException {
		List<BusinessSegmentBean> list = new ArrayList<>();

		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		try {
			logger.info("Inside viewBusinessName 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.getBusinessName);
			logger.info("Query Executed..");
			resultSet = preparedStatement.executeQuery();
			logger.info("ResultSet Executed");

			while (resultSet.next()) {
				logger.debug("Inside ViewBusinessName While loop 1");
				String busId = resultSet.getString(1);
				String busName = resultSet.getString(3);

				BusinessSegmentBean bean = new BusinessSegmentBean();
				bean.setBusinessName(busName);
				bean.setBusinessId(busId);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Method : Displaying PolicyQuestions to user. Argument : it's taking
	 * BusinessSegment as an argument return type : this method returns the list
	 * of Policy Bean Object Author : Capgemini Date : 08-Feb-2019
	 */
	@Override
	public List<PolicyBean> getPolicyQuestions(String businessSegment)
			throws QGSException {
		List<PolicyBean> list = null;

		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		try {
			logger.info("Inside GetPolicyQuestions 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.getPolicyQuestions);
			logger.info("Query Executed..");
			preparedStatement.setString(1, businessSegment);
			resultSet = preparedStatement.executeQuery();
			logger.info("ResultSet Executed");
			list = new ArrayList<>();
			while (resultSet.next()) {
				logger.debug("Inside GetPolicyQuestions While loop 1");
				String policyQuesId = resultSet.getString(1);
				String question = resultSet.getString(4);
				String answerOne = resultSet.getString(5);
				int answerOneWeightage = resultSet.getInt(6);
				String answerTwo = resultSet.getString(7);
				int answerTwoWeightage = resultSet.getInt(8);
				String answerThree = resultSet.getString(9);
				int answerThreeWeightage = resultSet.getInt(10);

				PolicyBean bean = new PolicyBean();
				bean.setPolicyQuestionId(policyQuesId);
				bean.setQuestion(question);
				bean.setAnswerOne(answerOne);
				bean.setAnswerTwo(answerTwo);
				bean.setAnswerThree(answerThree);
				bean.setAnsOneWeightage(answerOneWeightage);
				bean.setAnsTwoWeightage(answerTwoWeightage);
				bean.setAnsThreeWeightage(answerThreeWeightage);

				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Method : Generating policy using Policy Number. Argument : it's taking
	 * PolicyBean Object as an argument return type : this method returns the
	 * Generated Policy Author : Capgemini Date : 08-Feb-2019
	 */
	@Override
	public long generatePolicy(PolicyBean bean) throws QGSException {
		long result = 0l;

		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		try {
			logger.info("Inside GeneratePolicy 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.generatePolicy);
			logger.info("Query Executed..");
			preparedStatement.setDouble(1, bean.getPolicyPremium());
			preparedStatement.setLong(2, bean.getAccountNumber());
			preparedStatement.setString(3, bean.getBusinessId());

			preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement(QueryMapper.getPolicyNumber);
			logger.info("Query Executed..");
			resultSet = preparedStatement.executeQuery();
			logger.info("ResultSet Executed");
			while (resultSet.next()) {
				logger.debug("Inside GeneratePolicy While loop 1");
				result = resultSet.getLong(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Inside GeneratePolicy Finally block");
			try {
				logger.info("Inside GeneratePolicy 2nd try block");
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			try {
				logger.info("Inside GeneratePolicy 1st try block");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Method : Inserting into PolicyDetails. Argument : it's taking PolicyBean
	 * Object as an argument return type : this method returns the list of
	 * Policy Bean Object Author : Capgemini Date : 08-Feb-2019
	 */

	@Override
	public int policyDetails(PolicyBean beans) throws QGSException {
		int result = 0;
		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		try {
			logger.info("Inside PolicyDetails 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.insertPolicyDetails);
			logger.info("Query Executed..");
			preparedStatement.setLong(1, beans.getPolicyNumber());
			preparedStatement.setString(2, beans.getQuestionDetails());
			preparedStatement.setString(3, beans.getAnswerDetails());

			result = preparedStatement.executeUpdate();
			logger.info("ResultSet Executed");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Method : Inserting into Accounts Table. Argument : it's taking
	 * AccountBeans Object as an argument return type : this method returns the
	 * list of AccountBean Object Author : Capgemini Date : 08-Feb-2019
	 */

	@Override
	public long addAccount(AccountBean accountBean) throws QGSException {
		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		int result = 0;
		long accountNumber = 0;
		try {
			logger.info("Inside LoginValid 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.addAccount);
			logger.info("Query Executed..");
			preparedStatement.setString(1, accountBean.getInsuredName());
			preparedStatement.setString(2, accountBean.getInsuredStreet());
			preparedStatement.setString(3, accountBean.getInsuredCity());
			preparedStatement.setString(4, accountBean.getInsuredState());
			preparedStatement.setLong(5, accountBean.getInsuredZip());
			preparedStatement.setString(6, accountBean.getUserName());

			result = preparedStatement.executeUpdate();
			logger.info("ResultSet Executed");
			if (result == 1) {
				logger.info("Inside GetValidUsername If block");
				preparedStatement = connection
						.prepareStatement(QueryMapper.getAccountNumber);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				accountNumber = resultSet.getLong(1);
			} else {
				logger.info("Inside GetValidUsername Else block");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Inside LoginValid Finally block");
			try {
				logger.info("Inside LoginValid 2nd try block");
				preparedStatement.close();

			} catch (SQLException e) {
				logger.error(e.getMessage());
				try {
					logger.info("Inside LoginValid 3rd try block");
					connection.rollback();
				} catch (SQLException e1) {
					logger.error(e.getMessage());
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			try {
				logger.info("Inside LoginValid 4th try block");
				connection.close();

			} catch (SQLException e) {
				logger.error(e.getMessage());
				try {
					logger.info("Inside LoginValid 5th try block");
					connection.rollback();
				} catch (SQLException e1) {
					logger.error(e.getMessage());
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		}

		return accountNumber;
	}

	/**
	 * Method : Displaying Policy details of specific User based on
	 * AccountNumber. Argument : it's taking AccountNumber as an argument return
	 * type : this method returns the list of Policy Bean Object Author :
	 * Capgemini Date : 08-Feb-2019
	 */

	@Override
	public List<PolicyBean> getDetails(long accountNumber) throws QGSException {
		List<PolicyBean> list = new ArrayList<>();

		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		try {
			logger.info("Inside GetDetails 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.getDetails);
			logger.info("Query Executed..");
			preparedStatement.setLong(1, accountNumber);
			resultSet = preparedStatement.executeQuery();
			logger.info("ResultSet Executed");
			while (resultSet.next()) {
				logger.debug("Inside GetDetails While loop 1");
				long policyNumber = resultSet.getLong(1);
				double policyPremium = resultSet.getLong(2);
				long accountNumber1 = resultSet.getLong(3);
				String businessSegment = resultSet.getString(4);
				PolicyBean policyModel = new PolicyBean();
				policyModel.setPolicyNumber(policyNumber);
				policyModel.setPolicyPremium(policyPremium);
				policyModel.setAccountNumber(accountNumber1);
				policyModel.setBusinessId(businessSegment);
				list.add(policyModel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			logger.info("Inside GetDetails Finally block");
				try {
					logger.info("Inside GetDetails 2nd try block");
					preparedStatement.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					try {
						logger.info("Inside GetDetails 3rd try block");
						connection.rollback();
					} catch (SQLException e1) {
						logger.error(e.getMessage());
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				try {
					logger.info("Inside GetDetails 4th try block");
					connection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					try {
						logger.info("Inside GetDetails 5th try block");
						connection.rollback();
					} catch (SQLException e1) {
	
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	
			}
		return list;
	}

	/**
	 * Method : Genarate Report for Policy Argument : it's taking accountNumber
	 * as an argument return type : this method returns list of ReportBean
	 * Author : Capgemini Date : 08-Feb-2019
	 */

	@Override
	public List<ReportBean> reportGeneration(long accountNumber)
			throws QGSException {
		connection = JdbcUtility.getConnection();
		logger.info("Connection is established");
		ResultSet resultSet = null;
		List<ReportBean> list = new ArrayList<>();
		try {
			logger.info("Inside reportGeneration 1st try block");
			preparedStatement = connection
					.prepareStatement(QueryMapper.detailedReport);
			logger.info("Query Executed..");
			preparedStatement.setLong(1, accountNumber);
			resultSet = preparedStatement.executeQuery();
			logger.info("ResultSet Executed");
			while (resultSet.next()) {
				logger.debug("Inside reportGeneration While loop 1");
				String insuredName = resultSet.getString(1);
				String insuredStreet = resultSet.getString(2);
				String insuredCity = resultSet.getString(3);
				String insuredState = resultSet.getString(4);
				Long insuredZip = resultSet.getLong(5);
				String businessName = resultSet.getString(6);
				Double policyPremium = resultSet.getDouble(7);
				String question = resultSet.getString(8);
				String answerDetails = resultSet.getString(9);

				ReportBean reportBean = new ReportBean();
				reportBean.setInsuredName(insuredName);
				reportBean.setInsuredStreet(insuredStreet);
				reportBean.setInsuredCity(insuredCity);
				reportBean.setInsuredState(insuredState);
				reportBean.setInsuredZip(insuredZip);
				reportBean.setBusinessName(businessName);
				reportBean.setPolicyPremium(policyPremium);
				reportBean.setQuestion(question);
				reportBean.setAnswerDetails(answerDetails);
				list.add(reportBean);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	// ---------------------------------------------------USER--------------------------------------------------------------------------

	@Override
	public long newAccount(AccountBean accountCreation) throws QGSException {

		connection = JdbcUtility.getConnection();
		
		long accountNum = 0l;

		try {

			preparedStatement = connection
					.prepareStatement(QueryMapper.insertQuery);

			preparedStatement.setString(1, accountCreation.getInsuredName());
			preparedStatement.setString(2, accountCreation.getInsuredStreet());
			preparedStatement.setString(3, accountCreation.getInsuredCity());
			preparedStatement.setString(4, accountCreation.getInsuredState());
			preparedStatement.setLong(5, accountCreation.getInsuredZip());
			preparedStatement.setString(6, accountCreation.getUserName());
			preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement(QueryMapper.viewAccountNum);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				accountNum = resultSet.getLong(1);
			}
		} catch (SQLException e) {

			// throw new InsuranceException("Not Inserted");
			e.printStackTrace();
		}
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountNum;
	}

	@Override
	public List<PolicyBean> viewPolicy(String username) throws QGSException {
		List<PolicyBean> list = new ArrayList<>();
		connection = JdbcUtility.getConnection();
		try {

			preparedStatement = connection
					.prepareStatement(QueryMapper.viewPolicy);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				String businessSegment = resultSet.getString(4);
				long policyNumber = resultSet.getLong(1);
				double policyPremium = resultSet.getDouble(2);
				long accountNumber = resultSet.getLong(3);

				PolicyBean policy = new PolicyBean();

				policy.setBusinessId(businessSegment);
				policy.setPolicyNumber(policyNumber);
				policy.setPolicyPremium(policyPremium);
				policy.setAccountNumber(accountNumber);

				list.add(policy);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean checkAccountCreation(String username) throws QGSException {

		boolean result = false;
		connection = JdbcUtility.getConnection();
		List<AccountBean> list = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.checkAccount);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AccountBean accountBean = new AccountBean();
				accountBean.setUserName(resultSet.getString(1));

				list.add(accountBean);
			}
			if (!list.isEmpty()) {
				result = false;
			} else {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// ---------------------------------------------------------------------AGENT-----------------------------------------------------------------
	@Override
	public long getInsertion(AccountBean accountBean) throws QGSException {
		long account = 0l;
		connection = JdbcUtility.getConnection();

		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.getinsertrecord);
			preparedStatement.setString(1, accountBean.getInsuredName());
			preparedStatement.setString(2, accountBean.getInsuredStreet());
			preparedStatement.setString(3, accountBean.getInsuredCity());
			preparedStatement.setString(4, accountBean.getInsuredState());
			preparedStatement.setLong(5, accountBean.getInsuredZip());
			preparedStatement.setString(6, accountBean.getUserName());

			preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement(QueryMapper.viewAccountNo);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			account = resultSet.getLong(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new QGSException("Result Set not closed");
			}
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new QGSException("statment not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new QGSException("Connection not closed");
			}
		}

		return account;
	}

	@Override
	public List<BusinessSegmentBean> viewBusinessSegment() throws QGSException {
		connection = JdbcUtility.getConnection();
		List<BusinessSegmentBean> list = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.viewBusinessSegment);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String busSegName = resultSet.getString(1);
				BusinessSegmentBean businessSegment = new BusinessSegmentBean();
				businessSegment.setBusinessName(busSegName);
				list.add(businessSegment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean checkingAccount(Long accountNumber) throws QGSException {
		connection = JdbcUtility.getConnection();
		boolean flag = false;
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.displayAccountNo);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getLong(1) == accountNumber) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<PolicyBean> viewPolicyDetails(String businessSegment)
			throws QGSException {
		connection = JdbcUtility.getConnection();
		List<PolicyBean> list = null;
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.viewPolicyDetails);
			preparedStatement.setString(1, businessSegment);
			resultSet = preparedStatement.executeQuery();

			list = new ArrayList<>();
			while (resultSet.next()) {

				String policyQuestId = resultSet.getString(1);
				String question = resultSet.getString(4);
				String answerOne = resultSet.getString(5);
				int answerOneWeight = resultSet.getInt(6);
				String answerTwo = resultSet.getString(7);
				int answerTwoWeight = resultSet.getInt(8);
				String answerThree = resultSet.getString(9);
				int answerThreeWeight = resultSet.getInt(10);

				PolicyBean policyquestions = new PolicyBean();
				// policyquestions.setBuisnessSegement("Business Auto");
				policyquestions.setPolicyQuestionId(policyQuestId);
				policyquestions.setQuestion(question);
				policyquestions.setAnswerOne(answerOne);
				policyquestions.setAnswerTwo(answerTwo);
				policyquestions.setAnswerThree(answerThree);
				policyquestions.setAnsOneWeightage(answerOneWeight);
				policyquestions.setAnsTwoWeightage(answerTwoWeight);
				policyquestions.setAnsThreeWeightage(answerThreeWeight);

				list.add(policyquestions);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertPolicy(PolicyBean questions) throws QGSException {
		List<PolicyBean> list2 = null;
		int result = 0;

		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.getinsertPolicy);
			preparedStatement.setDouble(1, questions.getPolicyPremium());
			preparedStatement.setLong(2, questions.getAccountNumber());
			preparedStatement.setString(3, questions.getBusinessId());

			result = preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement(QueryMapper.displayPolicyNo);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			policyNumber = resultSet.getLong(1);

		} catch (SQLException e) {
			e.printStackTrace();
			/* throw new QGSException("Prepared statement not created "); */
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new QGSException("Connection not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new QGSException("statment not closed");
			}
		}

		return result;
	}

	@Override
	public int insertIntoPolicyDetails(List<PolicyBean> questions2)
			throws QGSException {
		int result = 0;
		connection = JdbcUtility.getConnection();
		try {

			for (PolicyBean details : questions2) {
				preparedStatement = connection
						.prepareStatement(QueryMapper.insertIntoPolicyDetails);
				preparedStatement.setLong(1, policyNumber);
				preparedStatement.setString(2, details.getPolicyQuestionId());
				preparedStatement.setString(3, details.getAnswerDetails());
				result = preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {

			throw new QGSException("Prepared statement not created ");
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new QGSException("Connection not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new QGSException("statment not closed");
			}
		}
		return result;
	}

	@Override
	public List<PolicyBean> getViewPolicy(String username) throws QGSException {
		List<PolicyBean> list = new ArrayList<>();
		connection = JdbcUtility.getConnection();

		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.selectPolicy);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String businessSegement = resultSet
						.getString("BUSINESS_SEGMENT");
				Long accountNumber = resultSet.getLong("ACCOUNT_NUMBER");
				Long policyNumber = resultSet.getLong("POLICY_NUMBER");
				Double policyPremium = resultSet.getDouble("POLICY_PREMIUM");

				PolicyBean policy = new PolicyBean();

				policy.setBusinessId(businessSegement);
				policy.setAccountNumber(accountNumber);
				policy.setPolicyNumber(policyNumber);
				policy.setPolicyPremium(policyPremium);

				list.add(policy);
			}

		} catch (SQLException e) {
			throw new QGSException("Connection is not closed");
		}

		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new QGSException("Statement not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new QGSException("Connection not closed");
			}
		}
		return list;
	}
}
