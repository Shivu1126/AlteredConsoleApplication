package view;

import java.util.Scanner;

import controller.AdminController;
import controller.JobController;
//import controller.*;

public class AdminView {
	private Scanner scanner = new Scanner(System.in);

	protected void adminView() 
	{
		System.out.println("Enter admin name");
		String name = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();
		AdminController loginAdmin = new AdminController();
		
		if(loginAdmin.loginCheck(name, password))
		{
			while(true)
			{
				System.out.println("----Welcome "+name+"----");
				System.out.println("1. Post Job ");
				System.out.println("2. Applicant details & Selection process");
				System.out.println("3. Selected Candidate");
				System.out.println("4. Posted jobs");
				System.out.println("5. Logout");
				System.out.println("Enter your option");
				int option = scanner.nextInt();
				
				switch(option)
				{
					case 1:
						JobController jobDetail = new JobController();
						jobDetail.postJob();
						break;
					case 2:
						AdminController process = new AdminController();
						process.selectionProcess();
						break;
					case 3:
						AdminController candidate = new AdminController();
						candidate.candidateLevel("qualified");
						
						break;
					case 4:
						AdminController postedJob = new AdminController();
						postedJob.viewPostedJobs();
						break;
					case 5: 
						System.out.println("Thank You "+name);
						return;
					default:
						System.err.println("Enter proper input");
				}
			}
		}
		else
			System.err.println("Name or password invalid");
	}
}
