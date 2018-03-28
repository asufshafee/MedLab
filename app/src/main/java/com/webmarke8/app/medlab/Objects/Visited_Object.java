package com.webmarke8.app.medlab.Objects;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeeksEra on 3/26/2018.
 */

public class Visited_Object implements Serializable {

    /**
     * Description : null
     * RespOBJ : [{"IsPaid":true,"Referral":"","Status":"Finalized","VisitDate":"10/24/2015 8:52:21 AM","VisitId":"ASL1585687"},{"IsPaid":true,"Referral":"","Status":"Finalized","VisitDate":"10/13/2015 11:46:16 AM","VisitId":"ASL1573413"}]
     * ResultResponse : 0
     * count : 2
     * userId : 20154030411;1845909
     */

    private Object Description;
    private int ResultResponse;
    private int count;
    private String userId;
    private List<RespOBJObject> RespOBJ;
    private String FileNo;
    private String VisitedName;

    public String getFileNo() {
        return FileNo;
    }

    public void setFileNo(String fileNo) {
        FileNo = fileNo;
    }

    public String getVisitedName() {
        return VisitedName;
    }

    public void setVisitedName(String visitedName) {
        VisitedName = visitedName;
    }

    public static Visited_Object objectFromData(String str) {

        return new Gson().fromJson(str, Visited_Object.class);
    }

    public Object getDescription() {
        return Description;
    }

    public void setDescription(Object Description) {
        this.Description = Description;
    }

    public int getResultResponse() {
        return ResultResponse;
    }

    public void setResultResponse(int ResultResponse) {
        this.ResultResponse = ResultResponse;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<RespOBJObject> getRespOBJ() {
        return RespOBJ;
    }

    public void setRespOBJ(List<RespOBJObject> RespOBJ) {
        this.RespOBJ = RespOBJ;
    }

    public static class RespOBJObject  implements  Serializable{
        /**
         * IsPaid : true
         * Referral :
         * Status : Finalized
         * VisitDate : 10/24/2015 8:52:21 AM
         * VisitId : ASL1585687
         */

        private boolean IsPaid;
        private String Referral;
        private String Status;
        private String VisitDate;
        private String VisitId;

        public static RespOBJObject objectFromData(String str) {

            return new Gson().fromJson(str, RespOBJObject.class);
        }

        public boolean isIsPaid() {
            return IsPaid;
        }

        public void setIsPaid(boolean IsPaid) {
            this.IsPaid = IsPaid;
        }

        public String getReferral() {
            return Referral;
        }

        public void setReferral(String Referral) {
            this.Referral = Referral;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getVisitDate() {
            return VisitDate;
        }

        public void setVisitDate(String VisitDate) {
            this.VisitDate = VisitDate;
        }

        public String getVisitId() {
            return VisitId;
        }

        public void setVisitId(String VisitId) {
            this.VisitId = VisitId;
        }
    }
}

