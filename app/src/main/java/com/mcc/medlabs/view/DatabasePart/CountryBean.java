package com.mcc.medlabs.view.DatabasePart;

import android.database.Cursor;

public class CountryBean {
	public static final String TABLE_NAME = "Countries";
	
	private String id;
	private String name;
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
	
	public void loadFromCursor(Cursor c) {
		if(!c.isAfterLast()) {
			this.id   =  c.getString(c.getColumnIndex("Cid"));
			this.name =  c.getString(c.getColumnIndex("CountryName"));
			

		}		
	}
	
}
