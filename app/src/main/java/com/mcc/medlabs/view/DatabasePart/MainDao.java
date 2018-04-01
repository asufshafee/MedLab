package com.mcc.medlabs.view.DatabasePart;





import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * @author Bayan
 *
 */
public class MainDao {
	
	protected Cursor queryByTableName(SQLiteDatabase database, String tableName) {
//		Log.v("database.isOpen() >>>>>>>>>>>>>> ","" + database.isOpen());
//		Log.v("database.isReadOnly() >>>>>>>>>>>>>> ","" + database.isReadOnly());
//		Log.v("database.getMaximumSize() >>>>>>>>>>>>>> ","" + database.getMaximumSize());
//		Log.v("database.getPageSize() >>>>>>>>>>>>>> ","" + database.getPageSize());
//		Log.v("database.getPath() >>>>>>>>>>>>>> ","" + database.getPath());
		
		return database.query(tableName, null, null, null, null, null, null);
	}
	
	protected Cursor queryByTableNameAndWhere(SQLiteDatabase database, String tableName, String[] cols , String[] values) {
		StringBuilder where = new StringBuilder(" WHERE ");
		for(int i=0; i<cols.length; i++) {
			where.append(cols[i] + " = ? ");
			if(i != cols.length-1)
				where.append("AND");
		}
		
		return database.rawQuery("select * from " + tableName + where, values);
	}
	
	protected Cursor queryByTableNameAndWhereWithLike(SQLiteDatabase database, String tableName, String cols , String values){
		
		return database.rawQuery("select * from " + tableName +" where " +cols +" like '%"+values+"%'", null);
	}
	
 	protected void insertToTable(SQLiteDatabase database, String tableName, String[] cols , String[] values){
		
		ContentValues cv = new ContentValues();
		try{
			for(int i=0 ; i<cols.length ; i++)
			{
				cv.put(cols[i], values[i]);
				Log.i("cols[i]",""+cols[i]);
				Log.i("values[i]",""+values[i]);
			}
			database.insert(tableName, null, cv);
//			database.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
//			database.close();
		}
		finally{
			Log.i("Finally","insertToTable");
			database.close();
		}
		
	}


	
	protected void UpdateTable(SQLiteDatabase database, String tableName , String[] cols , String[] values , String whereCluse) {
		ContentValues cv = new ContentValues();
		try{
			for(int i=0 ; i<cols.length ; i++)
			{
				cv.put(cols[i], values[i]);
				Log.i("cols[i]",""+cols[i]);
				Log.i("values[i]",""+values[i]);
			}
			database.update(tableName, cv, whereCluse, null);
//			database.close();
			
		}
		catch (Exception e) {
//			database.close();
		}
		finally{
			Log.i("Finally","UpdateTable");
			database.close();
		}
	}
	
	public void deleteAllRecords(SQLiteDatabase database, String tableName ){
		
		database.delete(tableName, null, null);
	}


}
