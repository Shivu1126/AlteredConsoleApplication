package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.*;

public class AdminController {
	private Scanner scanner = new Scanner(System.in);
	public boolean loginCheck(String name, String password)
	{
		if(name.equals("Shiva") && password.equals("Shiv123"))
		{
			return true;
		}
		return false;
	}

	public void selectionProcess()
	{
		int inOrNOt = candidateLevel("applied");
		if(inOrNOt==0)
		{
			System.out.println("No applicants");
			return;
		}
		System.out.println("Enter candidate mobile number");
		String mobileNum = scanner.next();
		while(true) {
			if(ValidationController.validPhoneNumber(mobileNum))
				break;
			else
			{
				System.err.println("Enter proper mobile number");
				mobileNum = scanner.next();
			}
		}
		ArrayList<HashMap<String, Object>> storedCandData = new ArrayList<HashMap<String, Object>>();

		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			ResultSet infoOfCand = statement.executeQuery("select candidate.phoneNumber,jobdetail.jobId,jobdetail.skills,jobdetail.experience,jobdetail.qualification,jobdetail.vacancy,jobdetail.selectedCount,candidate.candSkills,candidate.candExperience,candidate.candEducation\r\n"
					+ "    from ((candidateStatus\r\n"
					+ "    inner join candidate on candidateStatus.candPhoneNumber=candidate.PhoneNumber)\r\n"
					+ "    inner join jobdetail on candidateStatus.candJobId=jobdetail.jobId) where candStatus = 'applied' and candPhoneNumber = '"+mobileNum+"'");
			if(!infoOfCand.next())
			{
				System.out.println("Mobile number doesn't exist");
				return;
			}
			do
			{
				storedCandData = insertList(infoOfCand);			
				updateSelectOrNot(storedCandData);

			}while(infoOfCand.next());
			System.out.println("Status update successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void updateSelectOrNot(ArrayList<HashMap<String, Object>> storedCandData) 
	{
		Statement statement;
		try {
			statement = JdbcConnection.getInstance().createStatement();
			
			for(int i=0;i<storedCandData.size();i++)
			{
				int jobId = (int)storedCandData.get(i).get("JobId");
				String mobileNum = (String)storedCandData.get(i).get("mobileNum");
				String status = (String)storedCandData.get(i).get("status");
				int selectedCount = (int)storedCandData.get(i).get("selectedCount");
				statement.executeUpdate("update candidateStatus set candStatus='"+status+"' where candPhoneNumber = '"+mobileNum+"' and candJobId = "+jobId+";");
				int updateJobDetail=0;
				if(status.equals("qualified"))
				{
					updateJobDetail = statement.executeUpdate("update jobdetail set selectedCount="+(selectedCount+1)+" where jobId = "+jobId+";");
				}
				if(updateJobDetail==1)
					System.out.println("One candidate selected(JobId = "+jobId+")");
				else
					System.out.println("One candidate rejected(JobId = "+jobId+")");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<HashMap<String, Object>> insertList(ResultSet infoOfCand) 
	{
		ArrayList<HashMap<String, Object>> returnedCandInfo = new ArrayList<HashMap<String, Object>>();

		try {
			String mobileNum = infoOfCand.getString(1);
			int jobId = infoOfCand.getInt(2);
			String jobSkill = infoOfCand.getString(3);
			int jobExp = infoOfCand.getInt(4);
			String jobEdu = infoOfCand.getString(5);
			int vacancy = infoOfCand.getInt(6);
			int selectedCount = infoOfCand.getInt(7);
			String candSkill = infoOfCand.getString(8);
			int candExp = infoOfCand.getInt(9);
			String candEdu = infoOfCand.getString(10);

			int count = 0;
			if(jobSkill.equals(candSkill))
				count++;
			if(jobExp<=candExp)
				count++;
			if(jobEdu.equals(candEdu))
				count++;
			HashMap<String, Object> candHash = new HashMap<>();
			candHash.put("JobId",jobId );
			candHash.put("mobileNum", mobileNum);
			candHash.put("selectedCount", selectedCount);
			if(count>=2 && vacancy>selectedCount)
			{
				candHash.put("status", "qualified");
			}
			else
			{
				candHash.put("status", "disqualified");
			}
			returnedCandInfo.add(candHash);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return returnedCandInfo;
	}

	public int candidateLevel(String status) 
	{
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			ResultSet statusDetail = statement.executeQuery("select candidate.candName,candidate.phoneNumber,jobdetail.jobRole,candidateStatus.candStatus,jobdetail.salary\r\n"
					+ "    from ((candidateStatus\r\n"
					+ "    inner join candidate on candidateStatus.candPhoneNumber=candidate.PhoneNumber)\r\n"
					+ "    inner join jobdetail on candidateStatus.candJobId=jobdetail.jobId) where candStatus = '"+status+"'");
			if(!statusDetail.next())
			{
				//System.out.println("NO one selected");
				return 0;
			}
			System.out.println("Selected candidate details");
			System.out.println("Name\tMobileNum\tJobRole\tStatus\tSalary");
			System.out.println("----------------------------------------------");
			do
			{
				CandidateController candidateDet = new CandidateController();
				CandidateStatus insert = candidateDet.setCandidateStatus(statusDetail, false);
				System.out.println(insert.getCandName()+"\t"+insert.getCandPhoneNo()+"\t"+insert.getJobRole()+"\t"+insert.getStatus()+"\t"+insert.getSalary());
			}while(statusDetail.next());
			System.out.println("----------------------------------------------");

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public void viewPostedJobs()
	{
		try {
			Statement statement = JdbcConnection.getInstance().createStatement();
			ResultSet jobDetail = statement.executeQuery("select *from jobdetail");
			int emptyOrNot=0;
			System.out.println("JobId\tJobRole\tExperience\tQualification\tSkill\tVacancy\tSelectedCount\tSalary");
			System.out.println("---------------------------------------------------------------------------");

			while(jobDetail.next())
			{
				emptyOrNot=1;
				Jobs viewJob = setViewJobs(jobDetail);
				System.out.println(viewJob.getJobId()+"\t"+viewJob.getRole()+"\t"+viewJob.getExperience()+"\t"+
						viewJob.getQualification()+"\t"+viewJob.getSkills()+"\t"+viewJob.getVacancy()+"\t"+viewJob.getSelectedCount()+"\t"+viewJob.getSalary());

			}
			System.out.println("---------------------------------------------------------------------------");

			if(emptyOrNot==0)
				System.out.println("You do not yet post job");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Jobs setViewJobs(ResultSet jobDetail) {
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
}
