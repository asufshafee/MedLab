package com.webmarke8.app.medlab.Session;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.webmarke8.app.medlab.Objects.JsonParserLogin;
import com.webmarke8.app.medlab.Objects.JsonParserVisited;
import com.webmarke8.app.medlab.Objects.Login_Object;

import br.vince.easysave.EasySave;

/**
 * Created by GeeksEra on 20-Dec-17.
 */

public class MyApplication extends Application {


    public static final String TAG = "MedLabs";
    public static final String AR_LANG_CODE = "ar";
    public static final String EN_LANG_CODE = "en";
    public static String APP_LANG_CODE = EN_LANG_CODE;
    public static String LONGTITUDE = "0.0";
    public static String LATITUDE = "0.0";
    public static final String MY_LOCATION = "My Location";
    public static final String APP_VERSION = "1.0";

    public static Context GLOBAL_CONTEXT = null;

    public static String RESOLUATION = "";
    public static String USER_ID = "1";
    public static String COUNTERY_ID = "1";


    public static String NOTIFICATION_COUNT = "0";
    public static int LAST_NOTIFICATION_ID_VALUE = 0;
    public static int COUNT_OF_VISITS_VALUE = 0;

    //http://213.186.160.67:8086/MedlabsApp
    //http://213.186.160.67:8086/MedlabsApp

    public static final String REGISTER_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/RegistrationRequest";
    public static final String GIFT_VOUCHER_CALL_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/RequestGiftVoucher";
    public static final String SCHEDULE_HOUSE_CALL_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/ScheduleHouseCallRequest";
    public static final String PUBLIC_NOTIFICATIONS_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetNotification";
    public static final String NOTIFICATION_COUNT_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetNotificationNumber";
    public static final String LinkedUserLogin = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/LinkedUserLogin";
    public static final String GET_PATIENT_VISIT_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetPatientVisits";
    public static final String LOGOUT_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/Logout";
    public static final String FeedbackRating = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/FeedbackRating";
    public static final String FeedbackUserInformation = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/FeedbackUserInformation";
    public static final String POINTS = "http://www.medlabsgroup.com/admin/do/getPoints.php";

    public static final String GetAllBranches = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetAllBranches";

    public static final String GetInsuranceCompanies = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetInsuranceCompanies";

    public static final String GetTips = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetTips";
    public static final String GetAllFeaturedTests = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetAllFeaturedTests";
    public static final String GetAllSahtakBilDenia = " http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/GetAllSahtakBilDenia";


    public static final String SET_REGISTRATION_STATUS_URL = "http://213.186.160.67:8086/MedlabsAppV2/MedlabsApp.svc/SetRegistrationStatus";

    public static final String EN_EMAIL_SUBJECT = "Medlabs Test Result";
    public static final String EN_EMAIL_BODY = "";

    public static boolean isVibrate = true;
    public static String isPushEnabled = "true";


    public static String SHOW_HELP = "false";
    public static String SHOW_COUNTRY_DIALOG = "false";

    public static final float SCALE_X = 0.8f;
    public static final float SCALE_Y = 0.7f;

    public static boolean COMES_AFTER_SPLASH = false;

    public static final String EN_FONT_NAME_BOLD = "helvetica_neueltstd_bd.ttf";
    public static final String EN_FONT_NAME_PLAIN = "helvetica_neueltstd_lt.ttf";
    public static final String AR_FONT_NAME_BOLD = "ge_ss_two_bold.ttf";
    public static final String AR_FONT_NAME_PLAIN = "ge_ss_two_light.ttf";


    Context mContext;
    static final String MedLabs = "MedLabs";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_NOTIFICATION = "IS_NOTIFICATION";
    private static final String LANGUAGE = "LANGUAGE";


    private static MyApplication mInstance;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        editor = getSharedPreferences(MedLabs, MODE_PRIVATE).edit();
        sharedPreferences = getSharedPreferences(MedLabs, MODE_PRIVATE);

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
        // Clearing all data from Shared Preferences
//        new EasySave(getApplicationContext()).saveModel("Driver", null);
        editor.clear();
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

}