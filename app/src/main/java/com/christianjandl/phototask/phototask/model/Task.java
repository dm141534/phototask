package com.christianjandl.phototask.phototask.model;

import java.util.ArrayList;
import com.christianjandl.phototask.phototask.model.Log;
import com.christianjandl.phototask.phototask.model.Picture;

public class Task {

	private String name, plate, staff, jobnumber, id;
	private int date;

	private ArrayList<Picture> pictures;
	private ArrayList<Log> logs;

	private Picture previewpic;

	public Task() {
	}




	private Task(String name, String plate, String staff, String id, int date, String jobnumber, ArrayList<Picture> pictures, ArrayList<Log>logs, Picture previewpic) {
		this.name = name;
		this.plate= plate;
		this.staff = staff;
		this.id = id;
		this.date = date;
		this.jobnumber = jobnumber;

		this.pictures = pictures;
		this.logs = logs;
		this.previewpic = previewpic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setDate(int date) {
		this.date = date;
	}

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
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


	public Picture getPreviewpic() {
		return previewpic;
	}

	public void setPreviewpic(Picture previewpic) {
		this.previewpic = previewpic;
	}

}
