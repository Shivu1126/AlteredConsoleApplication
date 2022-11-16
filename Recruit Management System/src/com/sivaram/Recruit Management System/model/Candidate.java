package model;

public class Candidate {
	private String name;
	private String mailId;
	private String phoneNum;
	private String education;
	private int experience;
	private String skills;
		
	public String getName() {
		return name;
	}
	public String getMailId() {
		return mailId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public String getEducation() {
		return education;
	}
	public int getExperience() {
		return experience;
	}
	public String getSkills() {
		return skills;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
}
