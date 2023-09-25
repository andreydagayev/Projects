package application;

import java.io.Serializable;
import java.util.Arrays;

public class Person implements Serializable {
	
	private final int towerCaptainYear = 5;
	//how many years on beach to be considered for tower captain
	private String firstName;
	private String lastName;
	private String fullName;
	private int[] scheduleArray = {3,3,3,3,3,3,3};
	private boolean[] availability = new boolean[7];
	private int yearsOnBeach;
	private boolean isTowerCaptain;
	
	public Person(String firstName, String lastName, int yearsOnBeach) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = firstName + " " + lastName;
		this.yearsOnBeach = yearsOnBeach;
		this.isTowerCaptain = yearsOnBeach >= towerCaptainYear;
	}
	
	public Person(String fullName, int yearsOnBeach) {
		String[] stringArr = fullName.split(" ");
		this.firstName = stringArr[0];
		this.lastName = stringArr[1];
		this.fullName = fullName;
		this.yearsOnBeach = yearsOnBeach;
		this.isTowerCaptain = yearsOnBeach >= towerCaptainYear;
	}
	
	public void incrementYear() {
		this.yearsOnBeach++;
		if(yearsOnBeach >= towerCaptainYear) {
			isTowerCaptain = true;
		}
	}
	
	public void decrementYear() {
		this.yearsOnBeach--;
		if(yearsOnBeach < towerCaptainYear) {
			isTowerCaptain = false;
		}
	}

	public Person(String fullName) {
		
		this.fullName = fullName;
		
	}
	
	public void setScheduleDay(int day, int location) {
		this.scheduleArray[day] = location;
	}
	
	public void resetDays() {
		for(int i = 0; i < scheduleArray.length;i++) {
			scheduleArray[i] = 3;
		}
	}
	
	public int getScheduleDay(int day2Get) {
		return scheduleArray[day2Get];
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int[] getScheduleArray() {
		return scheduleArray;
	}

	public void setScheduleArray(int[] scheduleArray) {
		this.scheduleArray = scheduleArray;
	}

	public int getYearsOnBeach() {
		return yearsOnBeach;
	}

	public void setYearsOnBeach(int yearsOnBeach) {
		this.yearsOnBeach = yearsOnBeach;
	}

	public boolean isTowerCaptain() {
		return isTowerCaptain;
	}

	public void setTowerCaptain(boolean isTowerCaptain) {
		this.isTowerCaptain = isTowerCaptain;
	}

	@Override
	public String toString() {
		return "Person [fullName=" + fullName + ", scheduleArray=" + Arrays.toString(scheduleArray) + ", yearsOnBeach="
				+ yearsOnBeach + ", isTowerCaptain=" + isTowerCaptain + "]";
	}
	
}
