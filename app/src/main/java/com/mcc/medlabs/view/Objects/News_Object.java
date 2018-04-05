package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeeksEra on 3/25/2018.
 */

public class News_Object implements Serializable {


    private String Description;
    private int ListCount;
    private int ResultResponse;
    private List<NewsObObject> NewsOb;

    public static News_Object objectFromData(String str) {

        return new Gson().fromJson(str, News_Object.class);
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getListCount() {
        return ListCount;
    }

    public void setListCount(int ListCount) {
        this.ListCount = ListCount;
    }

    public int getResultResponse() {
        return ResultResponse;
    }

    public void setResultResponse(int ResultResponse) {
        this.ResultResponse = ResultResponse;
    }

    public List<NewsObObject> getNewsOb() {
        return NewsOb;
    }

    public void setNewsOb(List<NewsObObject> NewsOb) {
        this.NewsOb = NewsOb;
    }

    public static class NewsObObject implements Serializable {

        private String Date;
        private String Description;
        private String DescriptionAr;
        private String Id;
        private String Image;
        private String Name;
        private String NameAr;

        public static NewsObObject objectFromData(String str) {

            return new Gson().fromJson(str, NewsObObject.class);
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

        public String getDescriptionAr() {
            return DescriptionAr;
        }

        public void setDescriptionAr(String DescriptionAr) {
            this.DescriptionAr = DescriptionAr;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getNameAr() {
            return NameAr;
        }

        public void setNameAr(String NameAr) {
            this.NameAr = NameAr;
        }
    }
}
