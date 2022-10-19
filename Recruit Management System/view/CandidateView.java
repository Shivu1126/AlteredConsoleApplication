package view;

import java.util.Scanner;

import controller.*;

public class CandidateView {
	private Scanner scanner = new Scanner(System.in);
	protected void candidateView() 
	{
		while(true)
		{
			System.out.println("----Your options----");
			System.out.println("1. Jobs openings");
			System.out.println("2. Apply for a job");
			System.out.println("3. Applied jobs detail");
			System.out.println("4. Logout");
			System.out.println("Enter your option");
			int option = scanner.nextInt();
			switch(option)
			{
				case 1:
					CandidateController viewJobs = new CandidateController();
					viewJobs.viewOpeningsJobs();
					break;
				case 2:
					CandidateController apply = new CandidateController();
					apply.applyJob();
					break;
				case 3:
					CandidateController appliedJob = new CandidateController();
					appliedJob.appliedJobDetail();
					break;
				case 4:
					System.out.println("Thankyou for visiting ");
					return;
				default:
					System.out.println("Enter proper input");
			}
		}
	}
}
