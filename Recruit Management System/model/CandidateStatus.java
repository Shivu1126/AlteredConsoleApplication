package model;

public class CandidateStatus 
{
	private int appliedJobId;
	private String jobRole;
	private int salary;
	private String candName;
	private String candPhoneNo;
	private String status;
	
	public int getAppliedJobId() {
		return appliedJobId;
	}
	public void setAppliedJobId(int appliedJobId) {
		this.appliedJobId = appliedJobId;
	}

	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
	}
	public String getCandPhoneNo() {
		return candPhoneNo;
	}
	public void setCandPhoneNo(String candPhoneNo) {
		this.candPhoneNo = candPhoneNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
