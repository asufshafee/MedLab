package com.webmarke8.app.medlab.DatabasePart;

import android.database.Cursor;

import java.io.Serializable;

public class LabwiseBean implements Serializable{
	public static final String TABLE_NAME = "LabwiseTest";
	private int id;
	private int charId;
	private String name;
	private String normalRange;
	private String description;
	private String indications;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the charId
	 */
	public int getCharId() {
		return charId;
	}
	/**
	 * @param charId the charId to set
	 */
	public void setCharId(int charId) {
		this.charId = charId;
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
	 * @return the normalRange
	 */
	public String getNormalRange() {
		return normalRange;
	}
	/**
	 * @param normalRange the normalRange to set
	 */
	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the indications
	 */
	public String getIndications() {
		return indications;
	}
	/**
	 * @param indications the indications to set
	 */
	public void setIndications(String indications) {
		this.indications = indications;
	}
	
	
	
	public void loadFromCursor(Cursor c) {
		if(!c.isAfterLast()) {
			this.id                 =  c.getInt(c.getColumnIndex("Id"));
			this.charId             =  c.getInt(c.getColumnIndex("CId"));
			this.name               =  c.getString(c.getColumnIndex("Name"));
			this.normalRange        =  c.getString(c.getColumnIndex("NormalRange"));
			this.description        =  c.getString(c.getColumnIndex("Description"));
			this.indications        =  c.getString(c.getColumnIndex("Indications"));
			
		}		
	}
	

}
