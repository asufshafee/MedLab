package com.webmarke8.app.medlab.Objects;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by GeeksEra on 3/26/2018.
 */

public class JsonParserVisited implements Serializable {


    /**
     * Token : String content
     * WSPassword : String content
     * WSUsername : String content
     * device : {"Platform":"String content","Resolution":"String content","Version":"String content"}
     * userId : String content
     */

    private String Token;
    private String WSPassword;
    private String WSUsername;
    private DeviceObject device;
    private String userId;

    public static JsonParserVisited objectFromData(String str) {

        return new Gson().fromJson(str, JsonParserVisited.class);
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
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
}
