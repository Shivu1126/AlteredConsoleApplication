package controller;

public class ValidationController {
	protected static boolean validPhoneNumber(String phoneNumber)
	{
		String regex = "^[6-9]\\d{9}$";
		return phoneNumber.matches(regex);
	}
	protected static boolean validMailId(String mailId)
	{
		String regex = "^[A-Za-z][A-Za-z0-9+_.-]+@(.+)$";
		return mailId.matches(regex);
	}
}
