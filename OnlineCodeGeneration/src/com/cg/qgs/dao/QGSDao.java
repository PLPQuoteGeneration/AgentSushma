package com.cg.qgs.dao;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.BusinessSegment;

public interface QGSDao {

	long getInsertion(Accounts accounts) throws QGSException;

	List<BusinessSegment> viewBusinessSegment() throws QGSException;

	
	
	
	
	
}
