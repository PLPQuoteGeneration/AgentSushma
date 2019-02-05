package com.cg.qgs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.BusinessSegment;
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
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
