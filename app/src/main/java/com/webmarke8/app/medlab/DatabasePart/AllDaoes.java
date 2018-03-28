package com.webmarke8.app.medlab.DatabasePart;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class AllDaoes extends MainDao{
	
	
	public void createDatabase(Context ctx) throws IOException {
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
	}
	
	public ArrayList<LocationsBean> getLocationsList(Context ctx) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getLocationsList(databaseHelper);
		ArrayList<LocationsBean> bean = new ArrayList<LocationsBean>();

		for(int i=0; i<cursor.getCount();i++)
		{
			cursor.moveToPosition(i);
			bean.add( new LocationsBean());
			bean.get(i).loadFromCursor(cursor);
		}
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	private Cursor getLocationsList(DataBaseHelper databaseHelper){
		SQLiteDatabase database = databaseHelper.openDataBase();
		return queryByTableName(database, LocationsBean.TABLE_NAME);
	}
	
	
	
	public ArrayList<BranchesBean> getBranchesList(Context ctx , String cID) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getBranchesList(databaseHelper , cID);
		ArrayList<BranchesBean> bean = new ArrayList<BranchesBean>();

		for(int i=0; i<cursor.getCount();i++)
		{
			cursor.moveToPosition(i);
			bean.add( new BranchesBean());
			bean.get(i).loadFromCursor(cursor);
		}
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	private Cursor getBranchesList(DataBaseHelper databaseHelper  , String cID){
		SQLiteDatabase database = databaseHelper.openDataBase();
		String cols[] = {"LID"};
		String values[] = {cID};
		return queryByTableNameAndWhere(database, BranchesBean.TABLE_NAME , cols , values);
	}
	
	
	
	public BranchesBean getBranchItem(Context ctx , int branchID) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getBranchItem(databaseHelper , branchID);
		BranchesBean bean = new BranchesBean();
		cursor.moveToPosition(0);
		bean.loadFromCursor(cursor);
		
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	
	
	
	private Cursor getBranchItem(DataBaseHelper databaseHelper , int branchID){
		SQLiteDatabase database = databaseHelper.openDataBase();
		String cols[] = {"Id"};
		String values[] = {""+branchID};
		return queryByTableNameAndWhere(database, BranchesBean.TABLE_NAME, cols,values);
		
	}
	
	
	//--------------------------------------------------------------------------------------------------
	
	
	public ArrayList<LabwiseBean> getLabwiseList(Context ctx) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getLabwiseList(databaseHelper);
		ArrayList<LabwiseBean> bean = new ArrayList<LabwiseBean>();

		for(int i=0; i<cursor.getCount();i++)
		{
			cursor.moveToPosition(i);
			bean.add( new LabwiseBean());
			bean.get(i).loadFromCursor(cursor);
		}
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	private Cursor getLabwiseList(DataBaseHelper databaseHelper){
		SQLiteDatabase database = databaseHelper.openDataBase();
		return queryByTableName(database, LabwiseBean.TABLE_NAME);
	}
	
	


	

	//--------------------------------------------------------------------------------------------------
	
	public CountryBean getCountryId(Context ctx , String countryName) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getCountryId(databaseHelper , countryName);
		CountryBean bean = new CountryBean();
		cursor.moveToPosition(0);
		bean.loadFromCursor(cursor);
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	
	private Cursor getCountryId(DataBaseHelper databaseHelper , String countryName){
		SQLiteDatabase database = databaseHelper.openDataBase();
		String cols[] = {"CountryName"};
		String values[] = {countryName};
		return queryByTableNameAndWhere(database, CountryBean.TABLE_NAME, cols,values);
		
	}
	//----------------------------------------------------------------------------------
	
	public SDBBean getSDBContent(Context ctx , String countryId) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getSDBContent(databaseHelper , countryId);
		SDBBean bean = new SDBBean();
		cursor.moveToPosition(0);
		bean.loadFromCursor(cursor);
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	
	private Cursor getSDBContent(DataBaseHelper databaseHelper , String countryId){
		SQLiteDatabase database = databaseHelper.openDataBase();
		String cols[] = {"Countryid"};
		String values[] = {countryId};
		return queryByTableNameAndWhere(database, SDBBean.TABLE_NAME, cols,values);	
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	public ArrayList<CountryListBean> getCountriesList(Context ctx) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getCountriesList(databaseHelper);
		ArrayList<CountryListBean> bean = new ArrayList<CountryListBean>();

		for(int i=0; i<cursor.getCount();i++)
		{
			cursor.moveToPosition(i);
			bean.add( new CountryListBean());
			bean.get(i).loadFromCursor(cursor);
		}
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	private Cursor getCountriesList(DataBaseHelper databaseHelper){
		SQLiteDatabase database = databaseHelper.openDataBase();
		return queryByTableName(database, CountryListBean.TABLE_NAME);
	}
	
	//-----------------------------------------------------------------------------------------------------
	
	public CountryListBean getCountryName(Context ctx , String id) throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getCountryName(databaseHelper , id);
		CountryListBean bean = new CountryListBean();
		cursor.moveToPosition(0);
		bean.loadFromCursor(cursor);
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	
	private Cursor getCountryName(DataBaseHelper databaseHelper , String id){
		SQLiteDatabase database = databaseHelper.openDataBase();
		String cols[] = {"Cid"};
		String values[] = {id};
		return queryByTableNameAndWhere(database, CountryListBean.TABLE_NAME, cols,values);
		
	}
	
	
	//-------------------------------------------------------------------------------------------------------
	
	
	public ArrayList<LabwiseBean> getLabwiseListAfterSearch(Context ctx , String keyword)throws IOException {
		Cursor cursor = null;
		DataBaseHelper databaseHelper =new DataBaseHelper(ctx);
		if(! DataBaseHelper.isDbExist){
			databaseHelper.createDataBase();
		}
		cursor = getLabwiseListAfterSearch(databaseHelper , keyword);
		ArrayList<LabwiseBean> bean = new ArrayList<LabwiseBean>();

		for(int i=0; i<cursor.getCount();i++)
		{
			cursor.moveToPosition(i);
			bean.add( new LabwiseBean());
			bean.get(i).loadFromCursor(cursor);
		}
		cursor.close();
		databaseHelper.close();
		return bean;
	}
	
	
	private Cursor getLabwiseListAfterSearch(DataBaseHelper databaseHelper , String keyword){
		SQLiteDatabase database = databaseHelper.openDataBase();
		return queryByTableNameAndWhereWithLike(database, LabwiseBean.TABLE_NAME, "Name", keyword);
	}
}
