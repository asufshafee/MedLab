package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;

/**
 * Created by GeeksEra on 3/29/2018.
 */

public class JsonParserSchedule {

    /**
     * WSPassword : String content
     * WSUsername : String content
     * device : {"Platform":"String content","Resolution":"String content","Version":"String content"}
     * request : {"Area":"String content","BuildingNo":"String content","City":"String content","Countryid":2147483647,"DateTime":"String content","FloorNo":"String content","HourTime":"String content","Name":"String content","PhoneNumber":"String content","StreetName":"String content"}
     * userId : String content
     */

    private String WSPassword;
    private String WSUsername;
    private DeviceObject device;
    private RequestObject request;
    private String userId;

    public static JsonParserSchedule objectFromData(String str) {

        return new Gson().fromJson(str, JsonParserSchedule.class);
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
         * Platform : String content
         * Resolution : String content
         * Version : String content
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
         * Area : String content
         * BuildingNo : String content
         * City : String content
         * Countryid : 2147483647
         * DateTime : String content
         * FloorNo : String content
         * HourTime : String content
         * Name : String content
         * PhoneNumber : String content
         * StreetName : String content
         */

        private String Area;
        private String BuildingNo;
        private String City;
        private int Countryid;
        private String DateTime;
        private String FloorNo;
        private String HourTime;
        private String Name;
        private String PhoneNumber;
        private String StreetName;

        public static RequestObject objectFromData(String str) {

            return new Gson().fromJson(str, RequestObject.class);
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String Area) {
            this.Area = Area;
        }

        public String getBuildingNo() {
            return BuildingNo;
        }

        public void setBuildingNo(String BuildingNo) {
            this.BuildingNo = BuildingNo;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public int getCountryid() {
            return Countryid;
        }

        public void setCountryid(int Countryid) {
            this.Countryid = Countryid;
        }

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String DateTime) {
            this.DateTime = DateTime;
        }

        public String getFloorNo() {
            return FloorNo;
        }

        public void setFloorNo(String FloorNo) {
            this.FloorNo = FloorNo;
        }

        public String getHourTime() {
            return HourTime;
        }

        public void setHourTime(String HourTime) {
            this.HourTime = HourTime;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getStreetName() {
            return StreetName;
        }

        public void setStreetName(String StreetName) {
            this.StreetName = StreetName;
        }
    }
}

