package com.cg.qgs.client;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.AccountBean;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;
import com.cg.qgs.model.ReportBean;
import com.cg.qgs.service.QGSService;
import com.cg.qgs.service.implementation.QGSServiceImpl;

public class Index1 {
	static Logger logger = Logger.getLogger(Index1.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("resources/log4j.properties");

		LoginBean bean = login();
		boolean continueFlag = true;
		boolean wrongFlag = false;
		if (bean.getRoleCode().equalsIgnoreCase("ADMIN123")) {
			logger.debug("Inside If..Else block 1");
			System.out.println(bean.getRoleCode());
			continueFlag = true;
			do {
				adminMenu(bean.getUsername());
				logger.debug("Inside Do..While loop 1");
				System.out.println("Do you want to continue");
				System.out.println("1. Yes \n2. No");
				Scanner scanner = new Scanner(System.in);
				try {
					int cont = scanner.nextInt();
					logger.info("Inside 1st try block");
					switch (cont) {
					case 1:
						logger.info("Inside Switch Case 1");
						continueFlag = false;
						break;
					case 2:
						logger.info("Inside Switch Case 2");
						scanner.close();
						System.exit(0);
						break;
					default:
						break;
					}

				} catch (InputMismatchException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			} while (!continueFlag);

		} else if (bean.getRoleCode().equalsIgnoreCase("AGENT123")) {
			logger.debug("Inside Else..If block 1");
			do {
				logger.debug("Inside Do..While loop 2");
				agentMenu(bean.getUsername());
				continueFlag = true;
				System.out.println("Do you want to continue");
				System.out.println("1. Yes \n2. No");
				Scanner scanner = new Scanner(System.in);
				try {
					int cont = scanner.nextInt();
					logger.info("Inside 2st try block");
					if (cont >= 1 && cont <= 2) {
						logger.debug("Inside If block 2");
						switch (cont) {
						case 1:
							logger.info("Inside Switch Case 1");
							continueFlag = false;
							break;
						case 2:
							logger.info("Inside Switch Case 2");
							scanner.close();
							System.exit(0);
							break;
						default:
							break;
						}
					} else {
						logger.debug("Inside Else block 2");
						System.err.println("Enter a valid choice 1-2");
						continueFlag = false;
					}
				} catch (InputMismatchException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}

			} while (!continueFlag);
		}

		else {
			logger.debug("Inside Else block 1");
			System.out.println(bean.getRoleCode());
			continueFlag = true;
			wrongFlag = false;
			do {
				logger.debug("Inside Do..While loop 3");
				userMenu(bean.getUsername());

				System.out.println("Do you want to continue");
				System.out.println("1. Yes \n2. No");
				Scanner scanner = new Scanner(System.in);
				do {
					logger.debug("Inside Do..While loop 4");
					try {
						scanner = new Scanner(System.in);
						int cont = scanner.nextInt();
						if (cont >= 1 && cont <= 2) {
							logger.debug("Inside If block 3");
							wrongFlag = true;
						} else {
							logger.debug("Inside If block 3`");
							wrongFlag = false;
						}
						switch (cont) {
						case 1:
							logger.info("Inside Switch Case 1");
							continueFlag = false;
							break;
						case 2:
							logger.info("Inside Switch Case 2");
							scanner.close();
							System.exit(0);
							break;
						default:
							break;
						}
					} catch (InputMismatchException e) {
						logger.error(e.getMessage());
						e.printStackTrace();
					}
				} while (!wrongFlag);
			} while (!continueFlag);
		}
	}

	// ----------------------------------------ADMIN------------------------------------------------
	
	@SuppressWarnings("resource")
	private static void adminMenu(String username) {
		Scanner scanner = new Scanner(System.in);
		int menuChoice = 0;
		boolean mainMenuFlag = false;

		do {
			logger.debug("Inside Admin Do..While loop 1");
			scanner = new Scanner(System.in);
			System.out.println("Main Menu: ");
			System.out.println("1. Create New Profile");
			System.out.println("2. Create New Account");
			System.out.println("3. Create New Policy");
			System.out.println("4. View Policy");
			System.out.println("5. Generate Report");
			System.out.println("6. Exit");
			System.out.println("Select an option");
			try {
				logger.info("Inside 1st try block in admin");
				menuChoice = scanner.nextInt();

				if (menuChoice >= 1 && menuChoice <= 6) {
					logger.debug("Inside Admin If block 1");
					mainMenuFlag = true;

					switch (menuChoice) {
					case 1:
						logger.info("Inside Switch Case 1");
						System.out.println("****** Profile Creation ******");
						ProfileCreation();
						break;
					case 2:
						logger.info("Inside Switch Case 2");
						System.out.println("****** Account Creation ******");
						AccountCreation(username);
						break;
					case 3:
						logger.info("Inside Switch Case 3");
						System.out.println("****** Policy Creation ******");
						PolicyCreation();
						break;
					case 4:
						logger.info("Inside Switch Case 4");
						System.out.println("****** View Policy ******");
						ViewPolicy();
						break;
					case 5:
						logger.info("Inside Switch Case 5");
						System.out.println("****** Generate Report ******");
						ReportGeneration();
						break;
					case 6:
						System.exit(0);
						break;

					default:
						break;
					}

				} else {
					logger.debug("Inside Admin else block 1");

					mainMenuFlag = false;
					System.err.println("Enter options in the range of 1-6 only");
				}

			} catch (InputMismatchException e) {
				logger.error(e.getMessage());

				mainMenuFlag = false;
				System.err.println("Please enter only digits 0-6");
			}

		} while (!mainMenuFlag);
	}

	@SuppressWarnings("resource")
	public static void PolicyCreation() {
		boolean validAccount = false;
		boolean validBusName = false;
		boolean questionFlag = false;
		double policyPremium = 0.0;
		boolean optionFlag = false;
		Long accountNumber = 0l;
		Long policyNumber = 0l;
		int result = 0;

		Scanner scanner = new Scanner(System.in);

		do {
			logger.debug("Inside Policy Creation Do..While loop 1");
			scanner = new Scanner(System.in);
			System.out.println("Enter the account number: ");

			try {
				logger.info("Inside Policy Creation 1st try block");
				accountNumber = scanner.nextLong();

				QGSService service = new QGSServiceImpl();
				validAccount = service.validAccountNumber(accountNumber);
				if (validAccount) {
					logger.debug("Inside Policy Creation If block 1");
					validAccount = true;

				} else {
					logger.debug("Inside Policy Creation else block 1");
					System.err.println("Enter a valid account number");
					validAccount = false;
				}

			} catch (InputMismatchException e) {
				logger.error(e.getMessage());
				validAccount = false;
				System.err.println("Enter only digits");
			} catch (QGSException e) {
				logger.error(e.getMessage());
				validAccount = false;
				// TODO Auto-generated catch block
				System.err.println(e.getStackTrace());
			}

		} while (!validAccount);

		List<BusinessSegmentBean> list = new ArrayList<>();

		QGSService service = new QGSServiceImpl();

		try {
			System.out.println("List of Business Segments");
			list = service.viewBusinessName();
			int i = 1;
			for (BusinessSegmentBean businessSegmentBean : list) {
				System.out.println(i++ + " - " + businessSegmentBean.getBusinessName());
			}

		} catch (QGSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Line Of Business(LOB)");
			String businessSegment = scanner.nextLine();

			String[] detailsQues = null;
			String[] detailsAns = null;
			int listSize = 0;
			int i = 1;
			int j = 0;
			List<PolicyBean> list2 = null;
			list2 = new ArrayList<>();
			try {
				list2 = service.getPolicyQuestions(businessSegment);
				validBusName = true;

				if (!list2.isEmpty()) {

					do {
						listSize = list2.size();
						detailsQues = new String[list2.size()];
						detailsAns = new String[list2.size()];

						for (PolicyBean policyBean : list2) {

							do {

								detailsQues[j] = policyBean.getPolicyQuestionId();

								System.out.println("Question " + i + ": " + policyBean.getQuestion() + "\n 1: "
										+ policyBean.getAnswerOne() + "\n 2: " + policyBean.getAnswerTwo() + "\n 3: "
										+ policyBean.getAnswerThree() + "\n Enter your option");

								try {

									scanner = new Scanner(System.in);

									int option = scanner.nextInt();

									if (option <= 3 && option >= 1) {

										switch (option) {
										case 1:

											policyPremium = policyPremium + policyBean.getAnsOneWeightage();
											detailsAns[j] = policyBean.getAnswerOne();
											optionFlag = true;
											questionFlag = true;
											break;

										case 2:
											policyPremium = policyPremium + policyBean.getAnsTwoWeightage();
											detailsAns[j] = policyBean.getAnswerTwo();
											optionFlag = true;
											questionFlag = true;
											break;
										case 3:
											policyPremium = policyPremium + policyBean.getAnsThreeWeightage();
											detailsAns[j] = policyBean.getAnswerThree();
											optionFlag = true;
											questionFlag = true;
											break;
										default:
											break;
										}
									} else {

										System.err.println("Enter a valid option 1-3");
										optionFlag = false;
										questionFlag = false;
									}
								} catch (InputMismatchException e) {

									System.err.println("Please enter only digits");
									questionFlag = false;
									optionFlag = false;
								}
							} while (!optionFlag);
							j++;
							i++;
						}

						PolicyBean bean = new PolicyBean();
						bean.setAccountNumber(accountNumber);
						bean.setPolicyPremium(policyPremium);
						bean.setBusinessId(businessSegment);

						policyNumber = service.generatePolicy(bean);

						System.out.println(policyNumber + " Policy Added: " + "\n1.Business Name: "
								+ bean.getBusinessId() + "\n2. Policy Preminum: " + bean.getPolicyPremium()
								+ "\n3. Account Number: " + bean.getAccountNumber());

						j = 0;
						for (i = 0; i < listSize; i++) {
							PolicyBean beans = new PolicyBean();
							beans.setPolicyNumber(policyNumber);
							beans.setQuestionDetails(detailsQues[j]);
							beans.setAnswerDetails(detailsAns[j]);

							result = service.policyDetails(beans);
							System.out.println("Policy Details Added");

							if (result > 0) {
								j++;
							}
						}

					} while (!questionFlag);

				} else {
					System.err.println("Enter a valid Line of Business");
					validBusName = false;
				}
			} catch (QGSException e) {
				validBusName = false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!validBusName);
	}

	
	@SuppressWarnings("resource")
	private static void ViewPolicy() {
		QGSService service = new QGSServiceImpl();
		boolean flag = false;
		Scanner scanner = null;
		do {
			System.out.println("********Enter Account Number********");
			scanner = new Scanner(System.in);
			try {
				long accountNumber = scanner.nextLong();
				List<PolicyBean> list = new ArrayList<>();
				Boolean validAcccountNo;
				flag = true;
				try {
					validAcccountNo = service.accountValidation(accountNumber);
					if (validAcccountNo) {
						list = service.getDetails(accountNumber);
						flag = true;
						if (list.isEmpty()) {
							System.err.println("sorry!, we couldnt find any account on your acount number");
							System.err.println("enter your account number again ");
							flag = false;
						} else {

							for (PolicyBean policyBean : list) {
								System.out.println(policyBean.getPolicyNumber() + " " + policyBean.getPolicyPremium()
										+ " " + policyBean.getAccountNumber() + " " + policyBean.getBusinessId());
							}
						}

					} else {
						flag = false;
						System.err.println("enter a 10 digit account number");
						System.err.println("enter your account number again ");

					}
				} catch (QGSException e) {
					flag = false;
					e.printStackTrace();
				}
			} catch (InputMismatchException e) {
				System.err.println("enter only digits");
				System.err.println("enter your account number again ");
				flag = false;
			}
		} while (!flag);
	}

	@SuppressWarnings("resource")
	private static void AccountCreation(String userName) {
		QGSService service = new QGSServiceImpl();
		String insuredName, insuredStreet, insuredCity, insuredState;
		Scanner scanner = null;
		boolean flag = false;
		long insuredZip = 0;
		do {
			scanner = new Scanner(System.in);
			System.out.println("enter insured name : ");
			insuredName = scanner.nextLine();
			try {
				flag = service.validName(insuredName);
				if (!flag) {
					System.err.println("Use Only Characters minimum 3 and maxiumum 30");
					
					flag = false;
				}
			} catch (QGSException e) {
				e.printStackTrace();
			}

		} while (!flag);
		do {
			scanner = new Scanner(System.in);
			System.out.println("enter insured street : ");
			insuredStreet = scanner.nextLine();
			try {
				flag = service.validStreet(insuredStreet);
				if (!flag) {
					System.err.println("Enter more than 3 characters and less than 40 numerics included");
					
					flag = false;
				}
			} catch (QGSException e) {
				e.printStackTrace();
			}

		} while (!flag);
		do {
			scanner = new Scanner(System.in);
			System.out.println("enter insured city : ");
			insuredCity = scanner.nextLine();
			try {
				flag = service.validCity(insuredCity);
				if (!flag) {
					System.err.println("Use Only Characters minimum 3 and maxiumum 15");
					
					flag = false;
				}
			} catch (QGSException e) {
				e.printStackTrace();
			}

		} while (!flag);
		do {
			scanner = new Scanner(System.in);
			System.out.println("enter insured state : ");
			insuredState = scanner.nextLine();
			try {
				flag = service.validState(insuredState);
				if (!flag) {
					System.err.println("Use Only Characters minimum 3 and maxiumum 15");
					
					flag = false;
				}
			} catch (QGSException e) {
				e.printStackTrace();
			}

		} while (!flag);
		do {
			scanner = new Scanner(System.in);
			System.out.println("enter zip : ");

			try {
				flag = true;
				insuredZip = scanner.nextLong();
			} catch (InputMismatchException e) {
				System.err.println("enter 5 digits only");
				
				flag = false;
			}
			if (flag) {
				try {
					flag = service.validZip(insuredZip);
					if (!flag) {
						System.err.println("enter 5 digits only");
						
						flag = false;
					}
				} catch (QGSException e) {
					e.printStackTrace();
				}
			}

		} while (!flag);

		long accountNumber;
		AccountBean accountBean = new AccountBean();
		accountBean.setInsuredName(insuredName);
		accountBean.setInsuredStreet(insuredStreet);
		accountBean.setInsuredCity(insuredCity);
		accountBean.setInsuredState(insuredState);
		accountBean.setInsuredZip(insuredZip);
		accountBean.setUserName(userName);

		try {
			accountNumber = service.addAccount(accountBean);
			System.out.println("your account number : " + accountNumber);

		} catch (QGSException e) {

			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	private static void ReportGeneration() {
		QGSService service = new QGSServiceImpl();
		boolean flag = false;
		Scanner scanner = null;
		do {
			System.out.println("********Enter Account Number********");
			scanner = new Scanner(System.in);
			try {
				long accountNumber = scanner.nextLong();
				List<ReportBean> list = new ArrayList<>();
				Boolean validAcccountNo;
				flag = true;
				try {
					validAcccountNo = service.accountValidation(accountNumber);
					if (validAcccountNo) {
						list = service.reportGeneration(accountNumber);
						flag = true;
						if (list.isEmpty()) {
							System.err.println("sorry!, we couldnt find any account on your acount number");
							System.err.println("enter your account number again ");
							flag = false;
						} else {

							for (ReportBean reportBean : list) {
								System.out.println(reportBean.getInsuredName() + "   " + reportBean.getInsuredStreet()
										+ "     " + reportBean.getInsuredCity() + "    " + reportBean.getInsuredState()
										+ "    " + reportBean.getInsuredZip() + "    " + reportBean.getBusinessName()
										+ "        " + reportBean.getPolicyPremium() + "     "
										+ reportBean.getQuestion() + "    " + reportBean.getAnswerDetails());

							}
						}

					} else {
						flag = false;
						System.err.println("enter a 10 digit account number");
						System.err.println("enter your account number again ");

					}
				} catch (QGSException e) {
					flag = false;
					e.printStackTrace();
				}
			} catch (InputMismatchException e) {
				System.err.println("enter only digits");
				System.err.println("enter your account number again ");
				flag = false;
			}
		} while (!flag);
	}

	@SuppressWarnings("resource")
	public static void ProfileCreation() {
		boolean usernameFlag = false;
		boolean roleCodeFlag = false;
		boolean inserFlag = false;
		String roleCode = null;
		String userName = null;
		int roleChoice = 0;
		int result = 0;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter the username: ");
			scanner = new Scanner(System.in);

			userName = scanner.nextLine();
			QGSService service = new QGSServiceImpl();

			try {
				usernameFlag = service.getValidUsername(userName);
				if (!usernameFlag) {
					System.err.println("Username already in user");
				}

			} catch (QGSException e) {
				usernameFlag = false;
				System.err.println(e.getStackTrace());
			}

		} while (!usernameFlag);

		System.out.println("Enter the password: ");
		String pass = scanner.nextLine();

		do {
			scanner = new Scanner(System.in);
			System.out.println("Select the role code: ");
			System.out.println("1. ADMIN123");
			System.out.println("2. AGENT123");
			System.out.println("3. USER123");

			try {
				roleChoice = scanner.nextInt();
			} catch (InputMismatchException e) {
				roleCodeFlag = false;
				System.err.println("Please enter only digits");
			}
			if (roleChoice == 1) {
				roleCode = "ADMIN123";
				roleCodeFlag = true;
				System.out.println(roleCode);
			} else if (roleChoice == 2) {
				roleCodeFlag = true;
				roleCode = "AGENT123";
				System.out.println(roleCode);
			} else if (roleChoice == 3) {
				roleCodeFlag = true;
				roleCode = "USER123";
				System.out.println(roleCode);
			} else {
				roleCodeFlag = false;
				System.err.println("Please enter 1-3 only");
			}
		} while (!roleCodeFlag);
		do {
			try {
				LoginBean bean = new LoginBean();
				bean.setUsername(userName);
				bean.setPassword(pass);
				bean.setRoleCode(roleCode);

				QGSService service = new QGSServiceImpl();
				result = service.addProfile(bean);
				inserFlag = true;
				System.out.println(result + " Profile Created");
				roleCodeFlag = true;
			} catch (QGSException e) {
				roleCodeFlag = false;
				// TODO Auto-generated catch block
				inserFlag = false;
				System.err.println(e.getStackTrace());
			}
		} while (!inserFlag);
	}

	@SuppressWarnings("resource")
	private static LoginBean login() {

		Scanner scanner = new Scanner(System.in);
		String userName = null;
		String roleCode = null;
		LoginBean bean = new LoginBean();
		boolean loginValidation = false;

		do {
			scanner = new Scanner(System.in);
			System.out.println("****** Online Quote Generation System ******");
			System.out.println("Enter Username");
			String username = scanner.nextLine();
			System.out.println("Enter Password");
			String password = scanner.nextLine();

			QGSService service = new QGSServiceImpl();

			try {
				List<LoginBean> list = service.loginValid(username, password);
				if (!list.isEmpty()) {
					for (LoginBean loginBean : list) {
						userName = loginBean.getUsername();
						roleCode = loginBean.getRoleCode();
					}

					bean.setRoleCode(roleCode);
					bean.setUsername(userName);

					loginValidation = true;
					System.out.println("Logged in successfully");
				} else {
					System.err.println("Invalid Username and Password");
					loginValidation = false;

				}
			} catch (QGSException e) {

				System.err.println("Login failed please try again later");

			}
		} while (!loginValidation);

		return bean;
	}

	// -----------------------------------------USER---------------------------------------------------

	@SuppressWarnings("resource")
	private static void userMenu(String username) {

		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		boolean flag = false;
		System.out.println("***** User Role *****");
		System.out.println("1. Account Creation");
		System.out.println("2. View the list of Policies available");
		do {
			scanner = new Scanner(System.in);
			try {
				System.out.println("1. Account Creation");
				System.out.println("2. View the list of Policies available");
				choice = scanner.nextInt();
				scanner.nextLine();
				if (choice == 1 || choice == 2) {
					flag = true;
					switch (choice) {
					case 1:
						QGSService service = new QGSServiceImpl();
						boolean checkAccountFlag;
						try {
							checkAccountFlag = service.checkAccountCreation(username);
							if (checkAccountFlag) {
								userAccountCreation(username);
							} else {
								System.err.println("User cant create more than one account");
							}
						} catch (QGSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;

					case 2:
						viewPolicy(username);
						break;

					}
				} else {
					flag = false;
					System.err.println("Enter only digits 1-2");
				}
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Enter only digits");

			}
		} while (!flag);

	}

	@SuppressWarnings("resource")
	public static void userAccountCreation(String username) {

		boolean pattern = false;
		long insuredZip = 0l;
		long accountNumber = 0l;
		String insuredName, insuredStreet, insuredCity, insuredState;
		Scanner scanner = new Scanner(System.in);

		do {
			scanner = new Scanner(System.in);
			System.out.println(" Enter your Name ");
			insuredName = scanner.nextLine();
			String regexName = "[A-Za-z\\s]{1,30}$";
			pattern = Pattern.matches(regexName, insuredName);
			if (!pattern) {
				System.out.println("Enter max 30 characters");
			}
		} while (!pattern);
		System.out.println("Address: ");
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter the Street");
			insuredStreet = scanner.nextLine();
			String regexStreet = "[A-Za-z0-9\\s]{1,40}$";
			pattern = Pattern.matches(regexStreet, insuredStreet);
			if (!pattern) {
				System.out.println("Enter max 40 characters");
			}
		} while (!pattern);
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter the City");
			insuredCity = scanner.nextLine();
			String regexCity = "[A-Za-z\\s]{1,15}$";
			pattern = Pattern.matches(regexCity, insuredCity);
			if (!pattern) {
				System.out.println("Enter max 15 characters");
			}
		} while (!pattern);
		do {
			scanner = new Scanner(System.in);
			System.out.println(" Enter the State");
			insuredState = scanner.nextLine();
			String regexState = "[A-Za-z\\s]{1,15}$";
			pattern = Pattern.matches(regexState, insuredState);
			if (!pattern) {
				System.out.println("Enter max 15 characters");
			}
		} while (!pattern);

		do {
			try {
				scanner = new Scanner(System.in);
				System.out.println(" Enter the Zip");
				insuredZip = scanner.nextLong();
				String zipRegEx = "[0-9]{5}";
				pattern = Pattern.matches(zipRegEx, String.valueOf(insuredZip));
				if (!pattern) {
					System.err.println("Invalid Zip");
				}
			} catch (InputMismatchException e) {
				pattern = false;
				System.err.println("Enter integer only");

			}
		} while (!pattern);

		AccountBean accountCreation = new AccountBean();
		accountCreation.setInsuredName(insuredName);
		accountCreation.setInsuredStreet(insuredStreet);
		accountCreation.setInsuredCity(insuredCity);
		accountCreation.setInsuredState(insuredState);
		accountCreation.setInsuredZip(insuredZip);
		accountCreation.setUserName(username);
		QGSService service = new QGSServiceImpl();
		try {
			accountNumber = service.newAccount(accountCreation);
			System.out.println(accountNumber + " is your new Account Number");
		} catch (QGSException e) {
			e.printStackTrace();
		}
	}

	public static void viewPolicy(String username) {

		QGSService service = new QGSServiceImpl();
		System.out.println("List of Policies Available");
		List<PolicyBean> list = new ArrayList<>();
		;
		try {
			list = service.viewPolicy(username);
			if (!list.isEmpty()) {

				System.out.println("BUSINESS SEGMENT" + " --- " + "POLICY NUMBER" + " --- " + "POLICY PREMIUM" + " --- "
						+ "ACCOUNT NUMBER");
			} else {
				System.err.println("No Policy Found");

			}
			for (PolicyBean policy : list) {
				System.out.println(policy.getBusinessId() + "---" + policy.getPolicyNumber() + " --- "
						+ policy.getPolicyPremium() + " --- " + policy.getAccountNumber());
			}
		} catch (QGSException e) {
			e.printStackTrace();
		}
	}

	// -----------------------------------------AGENT---------------------------------------------------
	
	@SuppressWarnings("resource")
	private static void agentMenu(String username) {
		int choice = 0;
		boolean choiceFlag = false;

		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("***********Agent*************");
			System.out.println("1.Account Creation");
			System.out.println("2.Policy Creation");
			System.out.println("3.View Policy");
			System.out.println("4.Exit");

			System.out.println("Enter your choice");
			scanner = new Scanner(System.in);
			try {
				choice = scanner.nextInt();

				if (choice >= 1 && choice <= 4) {
					choiceFlag = true;
				} else {
					System.err.println("Enter valid options 1-4");

				}
			} catch (InputMismatchException e) {
				choiceFlag = false;
				System.err.println("Enter valid options in digits 1-4");
			}
		} while (!choiceFlag);
		switch (choice) {
		case 1:
			getAccountCreation(username);

			break;
		case 2:
			getPolicyCreation();
			break;

		case 3:
			getViewPolicy(username);
			break;
		case 4:
			System.exit(0);
			break;

		default:
			break;
		}

	}

	private static void getViewPolicy(String username) {
		List<PolicyBean> list3 = new ArrayList<>();
		QGSService service = new QGSServiceImpl();
		String userName = username;
		try {
			list3 = service.getViewPolicy(userName);
		} catch (QGSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (list3.size() > 0) {
			System.out.println("PolicyNumber       PolicyPremium       AccountNumber       BusinessSegment");

			for (PolicyBean policy : list3) {

				System.out.println(policy.getPolicyNumber() + "       " + policy.getPolicyPremium() + "       "
						+ policy.getAccountNumber() + "       " + policy.getBusinessId());
			}
		} else {
			System.out.println("Policy Records are not found:");
		}
	}

	@SuppressWarnings({ "resource", "unused" })
	private static void getPolicyCreation() {

		int resultPolicy = 0;
		double policyPremium = 0.0;
		int choices = 0;
		boolean accountFlag = false;
		boolean result = false;
		Scanner scanner = new Scanner(System.in);
		// BusinessSegmentBean businessSegment2 = new BusinessSegmentBean();

		PolicyBean questions = new PolicyBean();

		QGSService service = new QGSServiceImpl();
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Account No: ");
			try {
				Long accountNumber = scanner.nextLong();
				try {
					result = service.checkingAccount(accountNumber);
				} catch (QGSException e) {
					accountFlag = false;
					System.err.println("No account present");
				}
				if (result) {
					accountFlag = true;

					List<BusinessSegmentBean> list = new ArrayList<BusinessSegmentBean>();

					try {
						list = service.viewBusinessSegment();
						if (list.size() > 0) {
							System.out.println("\nBusiness Segment Name:\n");
							int choice = 1;
							for (BusinessSegmentBean businessSegment : list) {
								System.out.println(choice++ + " --- " + businessSegment.getBusinessName());
							}
							System.out.println("please select policy");

							do {
								try {
									scanner = new Scanner(System.in);
									choices = scanner.nextInt();
									if (choices >= 1 && choices <= 4) {
										accountFlag = true;
									} else {
										accountFlag = false;
										System.err.println("Enter only digits from 1-4");
									}
								} catch (InputMismatchException e) {
									System.err.println("Enter only digits ranges from 1-4");
									accountFlag = false;
								}
							} while (!accountFlag);

							String business = null;
							switch (choices) {
							case 1:
								business = "Business Auto";
								break;

							case 2:
								business = "Restaurant";
								break;

							case 3:
								business = "Apartment";
								break;

							case 4:
								business = "General Merchant";
								break;

							default:
								break;
							}
							System.out.println("Choosen business "+choices);
							
							List<PolicyBean> list2 = new ArrayList<>();
							list2 = service.viewPolicyDetails(business);

							int i = 1;
							List<PolicyBean> questions2 = new ArrayList<>();
							for (PolicyBean policyQuestions : list2) {

								System.out.println("Question " + i + ": " + policyQuestions.getQuestion() + "\n 1: "
										+ policyQuestions.getAnswerOne() + "\n 2: " + policyQuestions.getAnswerTwo()
										+ "\n 3: " + policyQuestions.getAnswerThree() + "\n Enter your option :\n" + policyQuestions.getAnsOneWeightage() + policyQuestions.getAnsTwoWeightage() + policyQuestions.getAnsThreeWeightage());

								/* String questionAnswer; */
								PolicyBean details = new PolicyBean();

								details.setPolicyQuestionId(policyQuestions.getPolicyQuestionId());
								int choice1 = 0;
								do {
									scanner = new Scanner(System.in);
									try {
										choice1 = scanner.nextInt();

										if (choice1 >= 1 && choice1 <= 3) {
											System.out.println("True");
											accountFlag = true;
										} else {
											accountFlag = false;
											System.err.println("Enter only Digits from 1-3");
										}

									} catch (InputMismatchException e) {
										accountFlag = false;
										System.err.println("Enter only Digits 1-3");
									}
									System.out.println("prtins");
								} while (!accountFlag);

								switch (choice1) {
								
								case 1:
									System.out.println("Option 1 is chosen");
									policyPremium += policyQuestions.getAnsOneWeightage();
									System.out.println(policyPremium);
									details.setAnswerDetails(policyQuestions.getAnswerOne());

									break;

								case 2:
									System.out.println("Option 2 is chosen");
									policyPremium += policyQuestions.getAnsOneWeightage();
									System.out.println(policyPremium);
									details.setAnswerDetails(policyQuestions.getAnswerTwo());

									break;

								case 3:
									System.out.println("Option 3 is chosen");
									policyPremium += policyQuestions.getAnsThreeWeightage();
									System.out.println(policyPremium);
									details.setAnswerDetails(policyQuestions.getAnswerThree());

									break;

								default:
									break;
								}
								questions2.add(details);
								i++;
							}
							System.out.println("Your premium is...." + policyPremium);

							questions.setPolicyPremium(policyPremium);
							questions.setAccountNumber(accountNumber);
							questions.setBusinessId(business);

							resultPolicy = service.insertPolicy(questions);

							int rows = service.insertIntoPolicyDetails(questions2);
							System.out.println("Inserted\n	" + rows);

						} else {
							System.out.println("No records found");
						}

					} catch (QGSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					accountFlag = false;
					System.err.println("Account number doesn't exists");
				}
			} catch (InputMismatchException e1) {
				accountFlag = false;
				System.err.println("Please enter only digits");
			}
		} while (!accountFlag);

	}

	@SuppressWarnings("resource")
	private static void getAccountCreation(String username) {
		boolean flag = false;
		long accountNumber = 0l;
		String insuredName, insuredStreet, insuredCity, insuredState;
		long insuredZip = 0l;
		QGSService service = new QGSServiceImpl();

		Scanner scanner = new Scanner(System.in);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Insured Name : ");
			insuredName = scanner.nextLine();
			try {
				flag = service.validName(insuredName);
				if (!flag) {
					System.err.println("Use Only Characters minimum 3 and maxiumum 30");
					System.out.println("enter insured name again");
					flag = false;
				}
			} catch (QGSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!flag);
		do {
			scanner = new Scanner(System.in);
			System.out.println("Address: ");

			System.out.println("\nEnter Insured Street : ");

			insuredStreet = scanner.nextLine();
			try {
				flag = service.validStreet(insuredStreet);
				if (!flag) {
					System.err.println("Enter more than 3 characters and less than 40 numerics included");
					System.out.println("enter insured street again");
					flag = false;
				}
			} catch (QGSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!flag);
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Insured City : ");
			insuredCity = scanner.nextLine();
			try {
				flag = service.validCity(insuredCity);
				if (!flag) {
					System.err.println("Use Only Characters minimum 3 and maxiumum 15");
					System.out.println("enter insured city again");
					flag = false;
				}
			} catch (QGSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!flag);
		do {
			System.out.println("Enter Insured State :");
			insuredState = scanner.nextLine();
			try {
				flag = service.validState(insuredState);
				if (!flag) {
					System.err.println("Use Only Characters minimum 3 and maxiumum 30");

					flag = false;
				}
			} catch (QGSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!flag);
		do {
			scanner = new Scanner(System.in);
			try {
				System.out.println("Enter Insured Zip");
				insuredZip = scanner.nextLong();
				try {
					flag = service.validZip(insuredZip);
					if (!flag) {
						System.err.println("Enter only 5 digit number");
						flag = false;
					} else {

						flag = true;
					}
				} catch (QGSException e) {
					flag = false;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Enter only 5 digit number");
			}

		} while (!flag);

		AccountBean accountBean = new AccountBean();
		accountBean.setInsuredName(insuredName);
		accountBean.setInsuredStreet(insuredStreet);
		accountBean.setInsuredCity(insuredCity);
		accountBean.setInsuredState(insuredState);
		accountBean.setInsuredZip(insuredZip);
		accountBean.setUserName(username);

		try {
			accountNumber = service.getInsertion(accountBean);

		} catch (QGSException e) {
			
			e.printStackTrace();
		}

		System.out.println("Your Account Number is..." + accountNumber);

	}

}