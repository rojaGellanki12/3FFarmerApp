package com.calibrage.a3ffarmerapp.Model;

import java.io.Serializable;

public class QuickPayModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String netWeight;
	private String date;

	private String cc;

	private boolean isSelected;


	public QuickPayModel(String name, String netWeight,String date, String cc,boolean isSelected) {

		this.name = name;
		this.netWeight = netWeight;
		this.date = date;
		this.cc = cc;
		this.isSelected = isSelected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}*/

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
}
