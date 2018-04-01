package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by GeeksEra on 3/26/2018.
 */

public class JsonParserLogin implements Serializable {

    /**
     * WSPassword : Medl@p$app17
     * WSUsername : Medlabs
     * device : {"Platform":"Android","Resolution":"XHigh","Version":"1.0"}
     * request : {"Countryid":1,"Password":"123456","RegId":"APA91bGo0XHQNPKbHd1-cO6jOs7Z9VnErFRhQH-JpUx8xB_GgA7uX8eQ31JBfES1vwToXqAhCt5AYdwtbrRcdMmZPhWNucLGczVFHmjM0hp5q3tpFkSECvy7lFDzJofWiAHDp9cdLwfx","UserName":"fahed_abuwardeh@hotmail.com"}
     * userId :
     */

    private String WSPassword;
    private String WSUsername;
    private DeviceObject device;
    private RequestObject request;
    private String userId;

    public static JsonParserLogin objectFromData(String str) {

        return new Gson().fromJson(str, JsonParserLogin.class);
    }

    public String getWSPassword() {
        return WSPassword;
    }

    public void setWSPassword(String WSPassword) {
        this.WSPassword = WSPassword;
    }

    public String getWSUsername() {
        return WSUsername;
    }

    public void setWSUsername(String WSUsername) {
        this.WSUsername = WSUsername;
    }

    public DeviceObject getDevice() {
        return device;
    }

    public void setDevice(DeviceObject device) {
        this.device = device;
    }

    public RequestObject getRequest() {
        return request;
    }

    public void setRequest(RequestObject request) {
        this.request = request;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class DeviceObject {
        /**
         * Platform : Android
         * Resolution : XHigh
         * Version : 1.0
         */

        private String Platform;
        private String Resolution;
        private String Version;

        public static DeviceObject objectFromData(String str) {

            return new Gson().fromJson(str, DeviceObject.class);
        }

        public String getPlatform() {
            return Platform;
        }

        public void setPlatform(String Platform) {
            this.Platform = Platform;
        }

        public String getResolution() {
            return Resolution;
        }

        public void setResolution(String Resolution) {
            this.Resolution = Resolution;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }
    }

    public static class RequestObject {
        /**
         * Countryid : 1
         * Password : 123456
         * RegId : APA91bGo0XHQNPKbHd1-cO6jOs7Z9VnErFRhQH-JpUx8xB_GgA7uX8eQ31JBfES1vwToXqAhCt5AYdwtbrRcdMmZPhWNucLGczVFHmjM0hp5q3tpFkSECvy7lFDzJofWiAHDp9cdLwfx
         * UserName : fahed_abuwardeh@hotmail.com
         */

        private int Countryid;
        private String Password;
        private String RegId;
        private String UserName;

        public static RequestObject objectFromData(String str) {

            return new Gson().fromJson(str, RequestObject.class);
        }

        public int getCountryid() {
            return Countryid;
        }

        public void setCountryid(int Countryid) {
            this.Countryid = Countryid;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getRegId() {
            return RegId;
        }

        public void setRegId(String RegId) {
            this.RegId = RegId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }
    }
}
