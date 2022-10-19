package view;

import java.util.Scanner;

public class HomePage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartView admin = new StartView();
		admin.dashboard();
	}

}
class StartView
{
	private Scanner scanner = new Scanner(System.in);
	protected void dashboard() {
		System.out.println("-----Welcome----");
		while(true)
		{
			System.out.println("--Your options--");
			System.out.println("1. Admin Login");
			System.out.println("2. Candidate Page");
			System.out.println("3. Exit");
			System.out.println("Enter your option");
			int option = scanner.nextInt();
			switch(option)
			{
			case 1:
				AdminView adminLogin = new AdminView();
				adminLogin.adminView();
				break;
			case 2:
				CandidateView candidateLogin = new CandidateView();
				candidateLogin.candidateView();
				break;
			case 3:
				System.out.println("Thank you");
				return;
			default:
					System.out.println("Enter proper input");
			}
		}
	}
}