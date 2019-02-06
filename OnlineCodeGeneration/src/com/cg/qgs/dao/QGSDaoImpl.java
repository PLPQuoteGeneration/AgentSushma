package com.cg.qgs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.BusinessSegment;
import com.cg.qgs.model.PolicyQuestions;
import com.cg.qgs.utility.JdbcUtility;

public class QGSDaoImpl implements QGSDao {

	Connection connection = null;
	PreparedStatement preparestatement = null;
	ResultSet resultset = null;

	@Override
	public long getInsertion(Accounts accounts) throws QGSException {

		int result = 0;
		long account = 0l;
		connection = JdbcUtility.getConnection();
		try {
			preparestatement = connection
					.prepareStatement(QueryMapper.getinsertrecord);
			preparestatement.setString(1, accounts.getInsuredName());
			preparestatement.setString(2, accounts.getInsuredStreet());
			preparestatement.setString(3, accounts.getInsuredCity());
			preparestatement.setString(4, accounts.getInsuredState());
			preparestatement.setLong(5, accounts.getInsuredZip());
			preparestatement.setString(6, "agent");
			result = preparestatement.executeUpdate();

			preparestatement = connection
					.prepareStatement(QueryMapper.viewAccountNo);
			resultset = preparestatement.executeQuery();

			resultset.next();
			account = resultset.getLong(1);

		} catch (SQLException e) {
			throw new QGSException("Prepared statement not created ");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new QGSException("Connection not closed");
			}
			try {
				preparestatement.close();
			} catch (SQLException e) {
				throw new QGSException("statment not closed");
			}
		}

		return account;
	}

	@Override
	public List<BusinessSegment> viewBusinessSegment() throws QGSException {
		connection = JdbcUtility.getConnection();
		List<BusinessSegment> list = new ArrayList<BusinessSegment>();
		try {
			preparestatement = connection
					.prepareStatement(QueryMapper.viewBusinessSegment);
			resultset = preparestatement.executeQuery();
			while (resultset.next()) {
				String busSegName = resultset.getString("BUS_SEG_NAME");
				BusinessSegment businessSegment = new BusinessSegment();
				businessSegment.setBusSegName(busSegName);
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
			preparestatement = connection
					.prepareStatement(QueryMapper.displayAccountNo);
			resultset = preparestatement.executeQuery();

			while (resultset.next()) {
				if (resultset.getLong("account_number") == accountNumber) {
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
	public List<PolicyQuestions> viewPolicyDetails(String business_segment)
			throws QGSException {
		connection = JdbcUtility.getConnection();
		List<PolicyQuestions> list = null;
		try {
			preparestatement = connection
					.prepareStatement(QueryMapper.viewPolicyDetails);
			preparestatement.setString(1, business_segment );
			resultset = preparestatement.executeQuery();

			list = new ArrayList<>();
			while (resultset.next()) {

				String policyQuestId = resultset.getString(1);
				String question = resultset.getString(4);
				String answerOne = resultset.getString(5);
				int answerOneWeight = resultset.getInt(6);
				String answerTwo = resultset.getString(7);
				int answerTwoWeight = resultset.getInt(8);
				String answerThree = resultset.getString(9);
				int answerThreeWeight = resultset.getInt(10);

				PolicyQuestions policyquestions = new PolicyQuestions();
				//policyquestions.setBuisnessSegement("Business Auto");
				policyquestions.setPolQuesId(policyQuestId);
				policyquestions.setQuestion(question);
				policyquestions.setPolQuesAns1(answerOne);
				policyquestions.setPolQuesAns2(answerTwo);
				policyquestions.setPolQuesAns3(answerThree);
				policyquestions.setPolQuesAns1Weightage(answerOneWeight);
				policyquestions.setPolQuesAns2Weightage(answerTwoWeight);
				policyquestions.setPolQuesAns3Weightage(answerThreeWeight);

				list.add(policyquestions);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
