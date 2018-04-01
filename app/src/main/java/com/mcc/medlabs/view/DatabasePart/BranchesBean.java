package com.mcc.medlabs.view.DatabasePart;

import android.database.Cursor;

public class BranchesBean {
	public static final String TABLE_NAME = "Branches";
	
	private int id;
	private int locationId;
	private String arBranchName;
	private String enBranchName;
	private String phone;
	private String fax;
	private String arLocation;
	private String enLocation;
	private String arAddress;
	private String enAddress;
	private String latitude;
	private String longtitude;
	private String distance;
	
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
	 * @return the arBranchName
	 */
	public String getArBranchName() {
		return arBranchName;
	}
	/**
	 * @param arBranchName the arBranchName to set
	 */
	public void setArBranchName(String arBranchName) {
		this.arBranchName = arBranchName;
	}
	/**
	 * @return the enBranchName
	 */
	public String getEnBranchName() {
		return enBranchName;
	}
	/**
	 * @param enBranchName the enBranchName to set
	 */
	public void setEnBranchName(String enBranchName) {
		this.enBranchName = enBranchName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
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
	/**
	 * @return the arAddress
	 */
	public String getArAddress() {
		return arAddress;
	}
	/**
	 * @param arAddress the arAddress to set
	 */
	public void setArAddress(String arAddress) {
		this.arAddress = arAddress;
	}
	/**
	 * @return the enAddress
	 */
	public String getEnAddress() {
		return enAddress;
	}
	/**
	 * @param enAddress the enAddress to set
	 */
	public void setEnAddress(String enAddress) {
		this.enAddress = enAddress;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longtitude
	 */
	public String getLongtitude() {
		return longtitude;
	}
	/**
	 * @param longtitude the longtitude to set
	 */
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	
	
	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	
	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public static String[] getColumns() {
		String[] cols = new String[] {"Id", "Name", "Name_Ar" , "Telephone", "Fax" , "LID" ,"Location" ,"LocationAr" ,"Address" ,"AddressAr" ,"Latitude","Longitude"};
		return cols;
	}
	
	public void loadFromCursor(Cursor c) {
		if(!c.isAfterLast()) {
			this.id                 =  c.getInt(c.getColumnIndex("Id"));
			this.arBranchName       =  c.getString(c.getColumnIndex("Name_Ar"));
			this.enBranchName       =  c.getString(c.getColumnIndex("Name"));
			this.arLocation         =  c.getString(c.getColumnIndex("LocationAr"));
			this.enLocation         =  c.getString(c.getColumnIndex("Location"));
			this.arAddress          =  c.getString(c.getColumnIndex("AddressAr"));
			this.enAddress          =  c.getString(c.getColumnIndex("Address"));
			this.phone              =  c.getString(c.getColumnIndex("Telephone"));
			this.fax                =  c.getString(c.getColumnIndex("Fax"));
			this.latitude           =  c.getString(c.getColumnIndex("Latitude"));
			this.longtitude         =  c.getString(c.getColumnIndex("Longitude"));
			this.locationId         =  c.getInt(c.getColumnIndex("LID"));

		}		
	}
	
 
}
