package com.webmarke8.app.medlab.Session;

import android.content.Context;

public class MedlabsConstants {
	public static final String TAG ="MedLabs";
	public static final String AR_LANG_CODE ="ar";
	public static final String EN_LANG_CODE ="en";
	public static String APP_LANG_CODE = EN_LANG_CODE;
	public static String LONGTITUDE    = "0.0";
	public static String LATITUDE      = "0.0";
	public static final String MY_LOCATION = "My Location";
	public static final String APP_VERSION = "1.0";
	
	public static Context GLOBAL_CONTEXT = null;
	
	public static String RESOLUATION = "";
	public static String USER_ID ="";
	public static String COUNTERY_ID ="1";
	
	
	public static String NOTIFICATION_COUNT = "0";
	public static int LAST_NOTIFICATION_ID_VALUE = 0;
	public static int COUNT_OF_VISITS_VALUE = 0;
	
	//http://213.186.160.67:8086/MedlabsApp
	//http://213.186.160.67:8086/MedlabsApp
	
	public static final String REGISTER_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/RegistrationRequest";
	public static final String GIFT_VOUCHER_CALL_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/RequestGiftVoucher";
	public static final String SCHEDULE_HOUSE_CALL_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/ScheduleHouseCallRequest";
	public static final String PUBLIC_NOTIFICATIONS_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/GetNotification";
	public static final String NOTIFICATION_COUNT_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/GetNotificationNumber";
	public static final String LinkedUserLogin = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/LinkedUserLogin";
	public static final String GET_PATIENT_VISIT_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/GetPatientVisits";
	public static final String LOGOUT_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/Logout";
	public static final String FeedbackRating = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/FeedbackRating";
	public static final String FeedbackUserInformation = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/FeedbackUserInformation";
	public static final String POINTS = "http://www.medlabsgroup.com/admin/do/getPoints.php";

	
	public static final String SET_REGISTRATION_STATUS_URL = "http://213.186.160.67:8086/MedlabsApp/MedlabsApp.svc/SetRegistrationStatus";
	
	public static final String EN_EMAIL_SUBJECT= "Medlabs Test Result";
	public static final String EN_EMAIL_BODY = "";
	
	public static boolean isVibrate = true;
	public static String isPushEnabled = "true";
	
	
	public static String SHOW_HELP = "false";
	public static String SHOW_COUNTRY_DIALOG = "false";
	
	public static final float SCALE_X = 0.8f;
	public static final float SCALE_Y = 0.7f;
	
	public static  boolean COMES_AFTER_SPLASH = false;
	
	public static final String EN_FONT_NAME_BOLD  = "helvetica_neueltstd_bd.ttf";
	public static final String EN_FONT_NAME_PLAIN = "helvetica_neueltstd_lt.ttf";
	public static final String AR_FONT_NAME_BOLD  = "ge_ss_two_bold.ttf";
	public static final String AR_FONT_NAME_PLAIN = "ge_ss_two_light.ttf";
	
	
	

	
	
}
