package com.cg.qgs.client;

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
	static QGSService service = null;
	static Scanner scanner = null;
	static Accounts accounts = null;
	static PolicyQuestions policy = null;

	public static void main(String[] args) {

		int choice;
		boolean flag = false;

		try {
			do {
				scanner = new Scanner(System.in);
				System.out.println("***********Agent*************");
				System.out.println("1.Account Creation");
				System.out.println("2.Policy Creation");
				System.out.println("3.View Policy");
				System.out.println("4.Exit");

				System.out.println("Enter your choice");

				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					System.out.println("\n==== Account Creation Screen ====");
					scanner.nextLine();
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
					break;
				case 2:
					service = new QGSServiceImpl();
					getPolicyCreation();
					break;
				case 3:
					break;
				case 4:

					break;

				default:
					System.err.println("Enter a Valid option");
				}

			} while (!flag);
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}

	}

	private static void getPolicyCreation() {

		double policyPremium = 0.0;
		String policyNumber = null;
		BusinessSegment businessSegment2 = new BusinessSegment();
		System.out.println("Enter Account No: ");
		Long accountNumber = scanner.nextLong();
		try {
			boolean result = service.checkingAccount(accountNumber);
			if (result) {
				List<BusinessSegment> list = new ArrayList<BusinessSegment>();
				List<PolicyQuestions> list2 = new ArrayList<PolicyQuestions>();
				QGSService service = new QGSServiceImpl();
				try {
					list = service.viewBusinessSegment();
					if (list.size() > 0) {
						System.out.println("\nBusiness Segment Name:\n");
						int choice = 1;
						for (BusinessSegment businessSegment : list) {
							System.out.println(choice++ + ". "
									+ businessSegment.getBusSegName());

							businessSegment2.setBusSegId(businessSegment
									.getBusSegId());
							businessSegment2.setBusSegName(businessSegment
									.getBusSegName());

						}
						System.out.println("please select policy");
						int choices = scanner.nextInt();
						String business_segment = null;
						switch (choices) {
						case 1:
							business_segment = "Business Auto";
							break;
							
						case 2:
							business_segment = "Restaurant";
							break;
							
						case 3:
							business_segment = "Apartment";
							break;
							
						case 4:
							business_segment = "General Merchant";
							break;

						default:
							break;
						}
						list2 = service.viewPolicyDetails(business_segment);
					
						int i=1;
						for (PolicyQuestions policyQuestions : list2) {
							System.out.println("Question " + i + ": " + policyQuestions.getQuestion() + "\n 1: "
									+ policyQuestions.getPolQuesAns1() + "\n 2: " + policyQuestions.getPolQuesAns2() + "\n 3: "
									+ policyQuestions.getPolQuesAns3() + "\n Enter your option :\n");
							i++;
							int choice1 = scanner.nextInt();
							
						}
					} else {
						System.out.println("No records found");
					}

				} catch (QGSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.err.println("Account number doesn't exists");
			}
		} catch (QGSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static Accounts getAccountCreation() {

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
