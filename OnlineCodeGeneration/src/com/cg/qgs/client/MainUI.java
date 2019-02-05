package com.cg.qgs.client;

import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.BusinessSegment;
import com.cg.qgs.model.PolicyQuestions;
import com.cg.qgs.service.QGSService;
import com.cg.qgs.service.QGSServiceImpl;

public class MainUI {

	public static void main(String[] args) {

		Scanner scan = null;
		Accounts accounts = null;
		PolicyQuestions policy = null;
		int choice;
		boolean flag = false;
		QGSService service = null;
		try {
			do {
				scan = new Scanner(System.in);
				System.out.println("***********Agent*************");
				System.out.println("1.Account Creation");
				System.out.println("2.Policy Creation");
				System.out.println("3.View Policy");
				System.out.println("4.Exit");

				System.out.println("Enter your choice");

				choice = scan.nextInt();

				switch (choice) {
				case 1:
					System.out.println("\n==== Account Creation Screen ====");
					scan.nextLine();
					accounts = getAccountCreation();
					boolean validateflag = false;
					service = new QGSServiceImpl();
					try {
						validateflag = service.getValidations(accounts);
						if (validateflag) {
							long insertRecords = service.getInsertion(accounts);

							System.out.println("Your Account Number is..."
									+ insertRecords);
						}

					} catch (QGSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					flag = true;

				case 2:
					policy = getPolicyCreation();
					
					
				case 3:

				case 4:
					return;
				default:
					System.err.println("Enter a Valid option");
				}

			} while (!flag);
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}

	}

	private static PolicyQuestions getPolicyCreation() {

		List<BusinessSegment> list = new ArrayList<BusinessSegment>();
		QGSService service = new QGSServiceImpl();
		try {
			list=service.viewBusinessSegment();
			
		} catch (QGSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Accounts getAccountCreation() {
		Scanner scanner = new Scanner(System.in);

		Accounts accounts = new Accounts();
		System.out.println("Enter Insured Name : ");
		String insuredName = scanner.nextLine();
		System.out.println("Enter Insured Street : ");
		String insuredStreet = scanner.nextLine();
		System.out.println("Enter Insured City : ");
		String insuredCity = scanner.nextLine();
		System.out.println("Enter Insured State :");
		String insuredState = scanner.nextLine();
		System.out.println("Enter Insured Zip");
		Long insuredZip = scanner.nextLong();

		accounts.setInsuredName(insuredName);
		accounts.setInsuredStreet(insuredStreet);
		accounts.setInsuredCity(insuredCity);
		accounts.setInsuredState(insuredState);
		accounts.setInsuredZip(insuredZip);

		return accounts;
	}

}
