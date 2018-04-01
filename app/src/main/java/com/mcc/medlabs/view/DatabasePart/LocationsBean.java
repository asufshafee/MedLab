package com.mcc.medlabs.view.DatabasePart;

import android.database.Cursor;

public class LocationsBean {
	
	public static final String TABLE_NAME = "Locations";
	
	private int id;
	private String arLocation;
	private String enLocation;
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
	 * @return the arLocation
	 */
	public String getArLocation() {
		return arLocation;
	}
	/**
	 * @param arLocation the arLocation to set
	 */
	public void setArLocation(String arLocation) {
		this.arLocation = arLocation;
	}
	/**
	 * @return the enLocation
	 */
	public String getEnLocation() {
		return enLocation;
	}
	/**
	 * @param enLocation the enLocation to set
	 */
	public void setEnLocation(String enLocation) {
		this.enLocation = enLocation;
	}
	
	
	public static String[] getColumns() {
		String[] cols = new String[] {"Id", "Location", "LocationAr"};
		return cols;
	}
	
	public void loadFromCursor(Cursor c) {
		if(!c.isAfterLast()) {
			this.id                 =  c.getInt(c.getColumnIndex("Id"));
			this.arLocation         =  c.getString(c.getColumnIndex("LocationAr"));
			this.enLocation         =  c.getString(c.getColumnIndex("Location"));
			

		}		
	}
	

}
