package com.mcc.medlabs.view.DatabasePart;

import android.database.Cursor;

public class CountryListBean {
	
public static final String TABLE_NAME = "countries_list";
	
	private String id;
	private String name;
	private String arName;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the arName
	 */
	public String getArName() {
		return arName;
	}
	/**
	 * @param arName the arName to set
	 */
	public void setArName(String arName) {
		this.arName = arName;
	}
	
	public void loadFromCursor(Cursor c) {
		if(!c.isAfterLast()) {
			this.id     =  c.getString(c.getColumnIndex("Cid"));
			this.name   =  c.getString(c.getColumnIndex("name"));
			this.arName =  c.getString(c.getColumnIndex("ar_name"));
		}		
	}
	
	

}
