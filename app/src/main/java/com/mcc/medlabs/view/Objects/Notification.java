package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeeksEra on 3/25/2018.
 */

public class Notification implements Serializable {

    /**
     * RespOBJ : [{"Date":"String content","Description":"String content","Image":"String content","Nid":"String content","Text":"String content","Type":"String content","isread":true}]
     * count : 2147483647
     */

    private int count;
    private List<RespOBJObject> RespOBJ;

    public static Notification objectFromData(String str) {

        return new Gson().fromJson(str, Notification.class);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RespOBJObject> getRespOBJ() {
        return RespOBJ;
    }

    public void setRespOBJ(List<RespOBJObject> RespOBJ) {
        this.RespOBJ = RespOBJ;
    }

    public static class RespOBJObject implements Serializable {
        /**
         * Date : String content
         * Description : String content
         * Image : String content
         * Nid : String content
         * Text : String content
         * Type : String content
         * isread : true
         */

        private String Date;
        private String Description;
        private String Image;
        private String Nid;
        private String Text;
        private String Type;
        private boolean isread;

        public static RespOBJObject objectFromData(String str) {

            return new Gson().fromJson(str, RespOBJObject.class);
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getNid() {
            return Nid;
        }

        public void setNid(String Nid) {
            this.Nid = Nid;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public boolean isIsread() {
            return isread;
        }

        public void setIsread(boolean isread) {
            this.isread = isread;
        }
    }
}
