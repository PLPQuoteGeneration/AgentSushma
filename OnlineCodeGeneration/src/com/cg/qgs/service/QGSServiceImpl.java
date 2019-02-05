package com.cg.qgs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.qgs.dao.QGSDao;
import com.cg.qgs.dao.QGSDaoImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.BusinessSegment;

public class QGSServiceImpl implements QGSService {
	QGSDao dao = new QGSDaoImpl(); 
	List<String> list=new ArrayList<String>();
	@Override
	public boolean getValidations(Accounts accounts) throws QGSException {
		
		boolean validateflag=false;
		if(!checkinsuredname(accounts.getInsuredName()))
		{
				list.add("Name should start with capital letters........");
		}
		if(!checkinsuredZip(accounts.getInsuredZip()))
		{
				list.add("Zip code must be numbers and then 5 digits only");
		}
		
		if(list.isEmpty()){
			validateflag=true;
		}else{
			
			System.out.println(list+"");
		}
		return validateflag;
	}

	private boolean checkinsuredZip(Long long1) {
		String zipRegEx = "[0-9]{5}$";
		return Pattern.matches(zipRegEx, long1.toString());
	}


	private boolean checkinsuredname(String insuredName) {
		 String nameRegEx = "[A-Z]{1}[a-z]{4,19}$";
		return Pattern.matches(nameRegEx, insuredName);
	}

	@Override
	public long getInsertion(Accounts accounts) throws QGSException {
		// TODO Auto-generated method stub
		return dao.getInsertion(accounts);
	}

	@Override
	public List<BusinessSegment> viewBusinessSegment() throws QGSException {
		
		return dao.viewBusinessSegment();
	}

}
