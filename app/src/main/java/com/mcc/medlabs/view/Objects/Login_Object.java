package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeeksEra on 3/26/2018.
 */

public class Login_Object implements Serializable {

    /**
     * Description : Success
     * Patients : [{"FileNo":"20154030411;1845909","P_Name":"ايات تيسير محمود الجابري","RegistrationStatus":"1"},{"FileNo":"20153141127;2023335","P_Name":"تولين فهد ابو ورده","RegistrationStatus":"1"},{"FileNo":"20153826502;2129361","P_Name":"عزيزه محمود عبدالله الجابري","RegistrationStatus":"1"},{"FileNo":"20164714255;2297616","P_Name":"ايات بشير الجابري","RegistrationStatus":"1"},{"FileNo":"20165280124;2373744","P_Name":"فهد محمد محمود ابو ورده","RegistrationStatus":"1"}]
     * PatientsCount : 5
     * RegistrationStatus : null
     * Result : 0
     * Token : imo6-XMQDgkfmTKOr5v9kEJdGxxIb8GPL9IDp0e3GtaG0AlsQ_5hnyTPyIGjvqXPKigMd3Aehn-I0JxRg7mD0PIn6GhdOG6J_ntU38OirKQ-MYz8CK4Bt94-gGYftheiGLKGksRUZ1x1WC7FDsvwlvIogfIFkrThmpzW452V37SpbEJ80UCY7WPzhRVwpzUBs8qO80bUpwO8-SFodJ5VcFQPdngzKe_omcR4WWBvCPSsHMXvLm3IJALtWaFxZchb
     * userId : null
     */

    private String Description;
    private int PatientsCount;
    private Object RegistrationStatus;
    private int Result;
    private String Token;
    private Object userId;
    private List<PatientsObject> Patients;

    public static Login_Object objectFromData(String str) {

        return new Gson().fromJson(str, Login_Object.class);
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getPatientsCount() {
        return PatientsCount;
    }

    public void setPatientsCount(int PatientsCount) {
        this.PatientsCount = PatientsCount;
    }

    public Object getRegistrationStatus() {
        return RegistrationStatus;
    }

    public void setRegistrationStatus(Object RegistrationStatus) {
        this.RegistrationStatus = RegistrationStatus;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int Result) {
        this.Result = Result;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public List<PatientsObject> getPatients() {
        return Patients;
    }

    public void setPatients(List<PatientsObject> Patients) {
        this.Patients = Patients;
    }

    public static class PatientsObject {
        /**
         * FileNo : 20154030411;1845909
         * P_Name : ايات تيسير محمود الجابري
         * RegistrationStatus : 1
         */

        private String FileNo;
        private String P_Name;
        private String RegistrationStatus;

        public static PatientsObject objectFromData(String str) {

            return new Gson().fromJson(str, PatientsObject.class);
        }

        public String getFileNo() {
            return FileNo;
        }

        public void setFileNo(String FileNo) {
            this.FileNo = FileNo;
        }

        public String getP_Name() {
            return P_Name;
        }

        public void setP_Name(String P_Name) {
            this.P_Name = P_Name;
        }

        public String getRegistrationStatus() {
            return RegistrationStatus;
        }

        public void setRegistrationStatus(String RegistrationStatus) {
            this.RegistrationStatus = RegistrationStatus;
        }
    }
}
