package com.cg.qgs.dao.implementation;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.qgs.dao.QueryMapper;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.utility.JdbcUtility;

public class QGSDaoImplementationTest {

	QGSDaoImplementationTest object = null;

	@Before
	public void setUp() throws Exception {
		object = new QGSDaoImplementationTest();
	}

	@After
	public void tearDown() throws Exception {
		object = null;
	}

	@Test
	public void testAddProfile() {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = JdbcUtility.getConnection();
		} catch (QGSException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			preparedStatement = connection.prepareStatement(QueryMapper.addProfile);
			preparedStatement.setString(1,"indira");
			preparedStatement.setString(2, "indira123");
			preparedStatement.setString(3, "ADMIN123");

			result = preparedStatement.executeUpdate();
			connection.commit();
			assertEquals(result, 1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}

	@Test
	public void testGeneratePolicy() {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = JdbcUtility.getConnection();
		} catch (QGSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.generatePolicy);
			preparedStatement.setDouble(1, 1800.0);
			preparedStatement.setLong(2, 1000000004);
			preparedStatement.setString(3, "Restaurant");

			result = preparedStatement.executeUpdate();
			assertEquals(result, 1);
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
	}

	@Test
	public void testPolicyDetails() {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		connection = JdbcUtility.getConnection();

		try {
			preparedStatement = connection.prepareStatement(QueryMapper.insertPolicyDetails);
			preparedStatement.setLong(1, beans.getPolicyNumber());
			preparedStatement.setString(2, beans.getQuestionDetails());
			preparedStatement.setString(3, beans.getAnswerDetails());

			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAddAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInsertion() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertPolicy() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertIntoPolicyDetails() {
		fail("Not yet implemented");
	}

}
