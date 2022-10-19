package controller;

import java.util.Scanner;

import java.sql.*;

import model.*;

public class JobController {
	private Scanner scanner = new Scanner(System.in);
	public void postJob()
	{
		System.out.println("Enter a role");
		String role = scanner.next();
		System.out.println("Enter experience");
		int experience = scanner.nextInt();
		System.out.println("Enter qualification");
		String qualification = scanner.next();
		System.out.println("Enter specific skills");
		String skills = scanner.next();
		System.out.println("Enter total vacancy");
		int vacancy = scanner.nextInt();
		System.out.println("Enter monthly salary");
		int salary = scanner.nextInt();
		setJobDetail(role, experience, qualification, skills, vacancy,salary);
		
	}
	

	private void setJobDetail(String role, int experience, String qualification, String skills, int vacancy, int salary) 
	{
		Jobs addJob = new Jobs();
		addJob.setJobId(0);
		addJob.setRole(role);
		addJob.setExperience(experience);
		addJob.setQualification(qualification);
		addJob.setSkills(skills);
		addJob.setVacancy(vacancy);
		addJob.setSelectedCount(0);
		addJob.setSalary(salary);
		insertJobDetail(addJob);
	}
	private void insertJobDetail(Jobs addJob) 
	{
		int jobId = addJob.getJobId();
		String role = addJob.getRole();
		int experience = addJob.getExperience();
		String qualification = addJob.getQualification();
		String skills = addJob.getSkills();
		int vacancy = addJob.getVacancy();
		int selectedCount = addJob.getSelectedCount();
		int salary = addJob.getSalary();
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();			
			int update = statement.executeUpdate("insert into jobDetail values("+jobId+",'"+role+"',"+experience+",'"+qualification+"','"
												+skills+"',"+vacancy+","+selectedCount+","+salary+");");
			if(update==1)
				System.out.println("New job posted successfully");
			else
				System.err.println("Job posted unsuccessfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
