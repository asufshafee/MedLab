package com.webmarke8.app.medlab.Objects;

import com.google.gson.Gson;

/**
 * Created by GeeksEra on 3/28/2018.
 */

public class JsonParserLocation {


    /**
     * Lang : en
     * PageIndex : 1
     * PageSize : 100
     * LocationLatitude : 1.26743237E15
     * LocationLongitude : 1.26743237E15
     * UserId : 1
     * WSPassword : Medl@p$app17
     * WSUsername : Medlabs
     * device : {"Platform":"Android","Resolution":"XHigh","Version":"1.0"}
     */

    private String Lang;
    private int PageIndex;
    private int PageSize;
    private double LocationLatitude;
    private double LocationLongitude;
    private int UserId;
    private String WSPassword;
    private String WSUsername;
    private DeviceObject device;

    public static JsonParserLocation objectFromData(String str) {

        return new Gson().fromJson(str, JsonParserLocation.class);
    }

    public String getLang() {
        return Lang;
    }

    public void setLang(String Lang) {
        this.Lang = Lang;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public double getLocationLatitude() {
        return LocationLatitude;
    }

    public void setLocationLatitude(double LocationLatitude) {
        this.LocationLatitude = LocationLatitude;
    }

    public double getLocationLongitude() {
        return LocationLongitude;
    }

    public void setLocationLongitude(double LocationLongitude) {
        this.LocationLongitude = LocationLongitude;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
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
}
