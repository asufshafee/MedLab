package com.mcc.medlabs.view.Session;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mcc.medlabs.view.Objects.JsonParserLogin;
import com.mcc.medlabs.view.Objects.Login_Object;

import br.vince.easysave.EasySave;
import io.fabric.sdk.android.Fabric;

/**
 * Created by GeeksEra on 20-Dec-17.
 */

public class MyApplication extends Application {


    public static final String TAG = "MedLabs";
    public static final String EN_LANG_CODE = "en";
    public static String APP_LANG_CODE = EN_LANG_CODE;
    public static final String APP_VERSION = "1.0";


    public static String RESOLUATION = "";
    public static String USER_ID = "1";
    public static String COUNTERY_ID = "1";
    public static final String REGISTER_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/RegistrationRequest";
    public static final String RequestGiftVoucher = " http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/RequestGiftVoucher";
    public static final String SCHEDULE_HOUSE_CALL_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/ScheduleHouseCallRequest";
    public static final String PUBLIC_NOTIFICATIONS_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetNotification";
    public static final String NOTIFICATION_COUNT_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetNotificationNumber";
    public static final String LinkedUserLogin = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/LinkedUserLogin";
    public static final String GET_PATIENT_VISIT_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetPatientVisits";
    public static final String LOGOUT_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/Logout";
    public static final String FeedbackRating = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/FeedbackRating";
    public static final String FeedbackUserInformation = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/FeedbackUserInformation";
    public static final String POINTS = "http://www.medlabsgroup.com/admin/do/getPoints.php";
    public static final String ScheduleHouseCallRequest = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/ScheduleHouseCallRequest";

    public static final String GetPinLocations = " http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetPinLocations";

    public static final String GetAllBranches = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetAllBranches";

    public static final String GetInsuranceCompanies = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetInsuranceCompanies";

    public static final String GetTips = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetTips";
    public static final String GetAllFeaturedTests = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetAllFeaturedTests";
    public static final String GetAllSahtakBilDenia = " http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetAllSahtakBilDenia";

    public static final String GetNews = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetNews";
    public static final String GetNotificationNumber = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetNotificationNumber";

    public static final String GetNotification = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetNotification";


    Context mContext;

    static final String MedLabs = "MedLabs";
    static final String MedLabsTimeUsed = "MedLabsTimeUsed";

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferencesMedLabsTimeUsed;
    public static SharedPreferences.Editor editorMedLabsTimeUsed;
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_NOTIFICATION = "IS_NOTIFICATION";
    private static final String LANGUAGE = "LANGUAGE";
    private static final String TIME = "TIME";
    private static final String DATE = "DATE";
    private static final String TODAY = "YESTERDAY";
    private static final String YESTERDAY = "YESTERDAY";


    private static final int GLASS = 0;
    private static MyApplication mInstance;

    public void onCreate() {
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mInstance = this;

        editor = getSharedPreferences(MedLabs, MODE_MULTI_PROCESS).edit();
        sharedPreferences = getSharedPreferences(MedLabs, MODE_MULTI_PROCESS);
        editorMedLabsTimeUsed = getSharedPreferences(MedLabsTimeUsed, MODE_MULTI_PROCESS).edit();
        sharedPreferencesMedLabsTimeUsed = getSharedPreferences(MedLabsTimeUsed, MODE_MULTI_PROCESS);

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public void clearUserPreference(Context mContext) {

        this.mContext = mContext;
        editor.clear();
        editor.commit();
    }


    public void logoutUser() {
        editor.putBoolean(IS_LOGIN, false);
        editor.commit();

    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


    public boolean isNotificationIn() {
        return sharedPreferences.getBoolean(IS_NOTIFICATION, true);
    }

    public void setIsNotification(Boolean isNotification) {
        editor.putBoolean(IS_NOTIFICATION, isNotification);
        editor.apply();
    }

    public String GetLanguage() {
        return sharedPreferences.getString(LANGUAGE, "en");
    }

    public void setLanguage(String language) {
        editor.putString(LANGUAGE, language);
        editor.apply();
    }


    public JsonParserLogin getLoginSessionJsonParserLogin() {
        return new EasySave(getApplicationContext()).retrieveModel("jsonParserVisited", JsonParserLogin.class);
    }

    public void setIslogged(Login_Object jsonParserLogin, JsonParserLogin jsonParserVisited) {
        editor.putBoolean(IS_LOGIN, true);
        new EasySave(getApplicationContext()).saveModel("Login_Object", jsonParserLogin);
        new EasySave(getApplicationContext()).saveModel("jsonParserVisited", jsonParserVisited);
        editor.apply();
    }

    public void setSaveToken(Login_Object jsonParserLogin) {
        new EasySave(getApplicationContext()).saveModel("Login_Object", jsonParserLogin);
        editor.apply();
    }

    //
    public Login_Object getLoginSessionLogin() {
        return new EasySave(getApplicationContext()).retrieveModel("Login_Object", Login_Object.class);
    }

    public static int getGLASS() {
        return sharedPreferences.getInt("Glass", 0);
    }

    public static void setGLASS(int Glass) {
        editor.putInt("Glass", Glass);
        editor.apply();
    }

    public static long getSECONDS() {

        return sharedPreferencesMedLabsTimeUsed.getLong("Secondsfortimeused", 0);
    }

    public static void setSECONDS(long Seconds) {
        long total = getSECONDS() + Seconds;
        editorMedLabsTimeUsed.putLong("Secondsfortimeused", total);
        editorMedLabsTimeUsed.apply();
        editorMedLabsTimeUsed.commit();
    }


    public static long getYesterdaytime() {
        return sharedPreferences.getLong("Yesterdaytime", 0);
    }

    public static void setyesterdaytime(Long Seconds) {
        editor.putLong("Yesterdaytime", Seconds);
        editor.apply();
    }

    public static String getGLASSDate() {
        return sharedPreferences.getString("DateGlass", "");
    }

    public static void setGLASSDate(String Date) {
        editor.putString("DateGlass", Date);
        editor.apply();
    }


    public static Long getTime() {
        return sharedPreferences.getLong(TIME, 0);
    }

    public static void setTime(Long time) {
        editor.putLong(TIME, time);
        editor.apply();
    }

    public static int getToday() {
        return sharedPreferences.getInt(TODAY, 0);
    }

    public static void setToday(int Glass) {
        editor.putInt(TODAY, Glass);
        editor.apply();
    }

    public static int getYesterday() {
        return sharedPreferences.getInt(YESTERDAY, 0);
    }

    public static void setyesterday(int Glass) {
        editor.putInt(YESTERDAY, Glass);
        editor.apply();
    }


    public boolean isSplashIn() {
        return sharedPreferences.getBoolean("SPLASH", true);
    }

    public void setIsSplash(Boolean isSPLASH) {
        editor.putBoolean("SPLASH", isSPLASH);
        editor.apply();
    }
}