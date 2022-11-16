package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.*;

public class CandidateController
{
	private Scanner scanner = new Scanner(System.in);
	public void applyJob() 
	{
		System.out.println("Enter job id");
		int jobId = scanner.nextInt();
		if(!searchJobId(jobId))
		{
			System.out.println("Job not available now..");
			return;
		}
		System.out.println("Enter your name");
		String name = scanner.next();
		System.out.println("Enter mobile number");
		String phoneNumber = scanner.next();
		while(true)
		{
			if(ValidationController.validPhoneNumber(phoneNumber))
				break;
			else
			{
				System.out.println("Enter valid mobile number");
				phoneNumber =scanner.next();
			}
		}
		if(checkAlreadyApplied(jobId,phoneNumber))
		{
			System.out.println("You already applied this job");
			return;
		}
		System.out.println("Enter mail Id");
		String mailId = scanner.next();
		while(true)
		{
			if(ValidationController.validMailId(mailId))
				break;
			else
			{
				System.out.println("Enter valid mail id");
				mailId = scanner.next();
			}
		}
		System.out.println("Enter your educational qualification");
		String education = scanner.next();
		System.out.println("Enter your experience");
		int experience = scanner.nextInt();
		System.out.println("Enter your skill");
		String skill = scanner.next();
		
		setCandidateDetail(name,phoneNumber,mailId,education,experience,skill,jobId);
	}
	
	public void appliedJobDetail() 
	{
		System.out.println("Enter your mobile number");
		String mobileNumber = scanner.next();
		while(true)
		{
			if(ValidationController.validPhoneNumber(mobileNumber))
				break;
			else
			{
				System.out.println("Enter valid mobile number");
				mobileNumber =scanner.next();
			}
		}
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			ResultSet statusDetail = statement.executeQuery("select candidate.candName,candidate.phoneNumber,jobdetail.jobRole,candidateStatus.candStatus,jobdetail.salary\r\n"
					+ "    from ((candidateStatus\r\n"
					+ "    inner join candidate on candidateStatus.candPhoneNumber=candidate.PhoneNumber)\r\n"
					+ "    inner join jobdetail on candidateStatus.candJobId=jobdetail.jobId) where phoneNumber = '"+mobileNumber+"';");
			if(!statusDetail.next())
			{
				System.out.println("You are not applied any jobs");
				return;
			}
			System.out.println("Your applied jobs details");
			System.out.println("YourName\tMobileNum\tJobRole\tYourStatus\tSalary");
			System.out.println("----------------------------------------------");
			do
			{
				CandidateStatus insert = setCandidateStatus(statusDetail, false);
				System.out.println(insert.getCandName()+"\t"+insert.getCandPhoneNo()+"\t"+insert.getJobRole()+"\t"+insert.getStatus()+"\t"+insert.getSalary());
			}while(statusDetail.next());
			System.out.println("----------------------------------------------");

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewOpeningsJobs() {
		
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			ResultSet jobDetail = statement.executeQuery("select *from jobdetail");
			int emptyOrNot=0;
			System.out.println("JobId\tJobRole\tExperience\tQualification\tSkill\tVacancy\tSalary");
			System.out.println("---------------------------------------------------------------------------");

			while(jobDetail.next())
			{
				
				Jobs viewJob = viewJobDetail(jobDetail);
				if(viewJob.getVacancy()!=viewJob.getSelectedCount())
				{
					emptyOrNot=1;
					System.out.println(viewJob.getJobId()+"\t"+viewJob.getRole()+"\t"+viewJob.getExperience()+"\t"+
									viewJob.getQualification()+"\t"+viewJob.getSkills()+"\t"+viewJob.getVacancy()+"\t"+viewJob.getSalary());
				}
			}
			System.out.println("---------------------------------------------------------------------------");

			if(emptyOrNot==0)
				System.out.println("No jobs yet");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setCandidateDetail(String name, String phoneNumber, String mailId, String education, int experience,
			String skill, int jobId) 
	{
		Candidate addCandidate = new Candidate();
		addCandidate.setName(name);
		addCandidate.setPhoneNum(phoneNumber);
		addCandidate.setMailId(mailId);
		addCandidate.setEducation(education);
		addCandidate.setExperience(experience);
		addCandidate.setSkills(skill);
		
		insertCandidate(addCandidate,jobId);
	}

	private void insertCandidate(Candidate addCandidate,int jobId)
	{
		String name = addCandidate.getName();
		String phoneNumber = addCandidate.getPhoneNum();
		String mailId = addCandidate.getMailId();
		String education = addCandidate.getEducation();
		int experience = addCandidate.getExperience();
		String skill = addCandidate.getSkills();
		
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			int updateInCandidate = statement.executeUpdate("insert ignore into candidate values('"+name+"','"+phoneNumber+"','"+mailId+"','"+education+"',"+experience+",'"+skill+"');");
			if(updateInCandidate==0)
				System.out.println("Your details already exist");
			int updateInStatus = statement.executeUpdate("insert into candidateStatus values('"+phoneNumber+"',"+jobId+",'Applied');");
			if(updateInStatus==1 || updateInCandidate==1)
				System.out.println("Job applied successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

	}

	private Jobs viewJobDetail(ResultSet jobDetail)
	{
		Jobs setJob = new Jobs();
		try {
			int jobId = jobDetail.getInt(1);
			String role = jobDetail.getString(2);
			int experience = jobDetail.getInt(3);
			String qualification = jobDetail.getString(4);
			String skills = jobDetail.getString(5);
			int vacancy = jobDetail.getInt(6);
			int selectedCount = jobDetail.getInt(7);
			int salary = jobDetail.getInt(8);
			setJob.setJobId(jobId);
			setJob.setRole(role);
			setJob.setExperience(experience);
			setJob.setQualification(qualification);
			setJob.setSkills(skills);
			setJob.setVacancy(vacancy);
			setJob.setSelectedCount(selectedCount);
			setJob.setSalary(salary);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return setJob;
	}
	
	private boolean searchJobId(int jobId) 
	{
		
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			ResultSet jobDetail = statement.executeQuery("select *from jobdetail");
			while(jobDetail.next())
			{
				
				Jobs viewJob = viewJobDetail(jobDetail);
				if(jobId==viewJob.getJobId() && viewJob.getVacancy()!=viewJob.getSelectedCount())
				{
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	private boolean checkAlreadyApplied(int jobId, String phoneNumber)
	{
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			ResultSet candidateInfo = statement.executeQuery("select *from candidatestatus");
			while(candidateInfo.next())
			{
				CandidateStatus applied = setCandidateStatus(candidateInfo,true);
				if(jobId==applied.getAppliedJobId() && applied.getCandPhoneNo().equals(phoneNumber))
				{
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	protected CandidateStatus setCandidateStatus(ResultSet candidateStatus,boolean checkOrGet) 
	{
		CandidateStatus candidateInfo = new CandidateStatus();
		
		try {
			if(checkOrGet)
			{
				String candPhoneNum = candidateStatus.getString(1);
				int jobId = candidateStatus.getInt(2);
				String status = candidateStatus.getString(3);
				
				candidateInfo.setCandPhoneNo(candPhoneNum);
				candidateInfo.setAppliedJobId(jobId);
				candidateInfo.setStatus(status);
			}
			else
			{
				//YourName\tMobileNum\tJobRole\tYourStatus\tSalary
				String name = candidateStatus.getString(1);
				String phoneNum = candidateStatus.getString(2);
				String jobRole = candidateStatus.getString(3);
				String status = candidateStatus.getString(4);
				int salary = candidateStatus.getInt(5);
				
				candidateInfo.setCandName(name);
				candidateInfo.setCandPhoneNo(phoneNum);
				candidateInfo.setJobRole(jobRole);
				candidateInfo.setStatus(status);
				candidateInfo.setSalary(salary);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return candidateInfo;
	}
}
