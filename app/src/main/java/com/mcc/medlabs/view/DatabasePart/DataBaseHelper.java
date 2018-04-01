package com.mcc.medlabs.view.DatabasePart;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.mcc.medlabs.view/databases/";
    private static String DB_NAME = "Medlabs5Mar2017.bak.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    public static boolean isDbExist = false;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();
        SQLiteDatabase db_Read = null;
        if (dbExist) {
            //do nothing - database already exist

        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.

            db_Read = this.getReadableDatabase();

            db_Read.close();

            CopyTask task = new CopyTask();
            task.execute();

//        	try { 
//    			copyDataBase();
// 
//    		} catch (IOException e) {
////    			e.printStackTrace();
//    			
//    			Log.d(MedlabsConstants.TAG , ""+e.getMessage() );
////        		throw new Error("Error copying database");
// 
//        	}
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        boolean checkDB = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        } catch (SQLiteException e) {
        }
        return checkDB;
    }


    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {
        try {
            //Open your local db as the input stream
            InputStream myInput = myContext.getAssets().open(DB_NAME);

            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            int bufferCount = 0;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
                bufferCount++;
            }


            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {

        }

    }

    public SQLiteDatabase openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDataBase;

    }


    @Override
    public synchronized void close() {
        super.close();
        Log.i("--DataBaseHelper--", "Closing database");
        if (myDataBase != null)
            myDataBase.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    public class CopyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                copyDataBase();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.v("DATABASE", "" + e.getMessage());
            }
            return null;
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}