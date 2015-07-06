package com.christianjandl.phototask.phototask.model;

import java.util.ArrayList;
import com.christianjandl.phototask.phototask.model.Log;
import com.christianjandl.phototask.phototask.model.Picture;

public class Task {

	private String name, plate, staff;

	private int id, date, jobnumber;

	private ArrayList<Picture> pictures;
	private ArrayList<Log> logs;

	public Task() {
	}

	private Task(String name, String plate, String staff, int id, int date, int jobnumber, ArrayList<Picture> pictures, ArrayList<Log>logs) {
		this.name = name;
		this.plate= plate;
		this.staff = staff;
		this.id = id;
		this.date = date;
		this.jobnumber = jobnumber;

		this.pictures = pictures;
		this.logs = logs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int jobnumber) {
		this.date = date;
	}

	public int getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(int jobnumber) {
		this.jobnumber = jobnumber;
	}



	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}

	public ArrayList<Log> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<Log> logs) {
		this.logs = logs;
	}


}
