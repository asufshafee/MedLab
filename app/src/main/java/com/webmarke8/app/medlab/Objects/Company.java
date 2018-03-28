package com.webmarke8.app.medlab.Objects;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by GeeksEra on 3/25/2018.
 */

public class Company {

    /**
     * Description : Success
     * InsuranceCompanyOb : [{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"1","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"Euro Arab Insurance ","NameAr":"الأوربية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"2","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"للتأمين مدلاب","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"3","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"JOFICO Insurance ","NameAr":"الفرنسية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"Jabal Al-Weibdeh, Luzmila Hospital","AddressAr":"جبل اللويبده - ش. عبد المنعم سمارة مبنى المستشفى","Id":"4","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"مدلاب للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"5","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"Euro Arab Insurance ","NameAr":"الأوربية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"6","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"للتأمين مدلاب","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"7","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"JOFICO Insurance ","NameAr":"الفرنسية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"Jabal Al-Weibdeh, Luzmila Hospital","AddressAr":"جبل اللويبده - ش. عبد المنعم سمارة مبنى المستشفى","Id":"8","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"مدلاب للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"9","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"Euro Arab Insurance ","NameAr":"الأوربية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"10","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"للتأمين مدلاب","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"11","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"JOFICO Insurance ","NameAr":"الفرنسية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"Jabal Al-Weibdeh, Luzmila Hospital","AddressAr":"جبل اللويبده - ش. عبد المنعم سمارة مبنى المستشفى","Id":"12","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"مدلاب للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"13","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"Euro Arab Insurance ","NameAr":"الأوربية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"14","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"للتأمين مدلاب","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"15","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"JOFICO Insurance ","NameAr":"الفرنسية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"Jabal Al-Weibdeh, Luzmila Hospital","AddressAr":"جبل اللويبده - ش. عبد المنعم سمارة مبنى المستشفى","Id":"16","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"مدلاب للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"17","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"Euro Arab Insurance ","NameAr":"الأوربية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"18","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"للتأمين مدلاب","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"19","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"JOFICO Insurance ","NameAr":"الفرنسية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"Jabal Al-Weibdeh, Luzmila Hospital","AddressAr":"جبل اللويبده - ش. عبد المنعم سمارة مبنى المستشفى","Id":"20","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"مدلاب للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"21","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"Euro Arab Insurance ","NameAr":"الأوربية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"22","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"للتأمين مدلاب","PhoneNumber":"06 4613031"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Id":"23","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"JOFICO Insurance ","NameAr":"الفرنسية للتأمين ","PhoneNumber":"06 4613031"},{"Address":"Jabal Al-Weibdeh, Luzmila Hospital","AddressAr":"جبل اللويبده - ش. عبد المنعم سمارة مبنى المستشفى","Id":"24","Image":"http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png","Name":"medlabs Insurance ","NameAr":"مدلاب للتأمين ","PhoneNumber":"06 4613031"}]
     * ListCount : 24
     * ResultResponse : 0
     */

    private String Description;
    private int ListCount;
    private int ResultResponse;
    private List<InsuranceCompanyObObject> InsuranceCompanyOb;

    public static Company objectFromData(String str) {

        return new Gson().fromJson(str, Company.class);
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

    public List<InsuranceCompanyObObject> getInsuranceCompanyOb() {
        return InsuranceCompanyOb;
    }

    public void setInsuranceCompanyOb(List<InsuranceCompanyObObject> InsuranceCompanyOb) {
        this.InsuranceCompanyOb = InsuranceCompanyOb;
    }

    public static class InsuranceCompanyObObject {
        /**
         * Address : 5th Circle, Bader Medical Complex No. 9, 1st Floor
         * AddressAr : الدوار الخامس، مجمع بدر الطبي بناية رقم 9
         * Id : 1
         * Image : http://213.186.160.67:8086/MedlabsAppV2/InsuranceCompanies/Insurance.png
         * Name : Euro Arab Insurance
         * NameAr : الأوربية للتأمين
         * PhoneNumber : 06 4613031
         */

        private String Address;
        private String AddressAr;
        private String Id;
        private String Image;
        private String Name;
        private String NameAr;
        private String PhoneNumber;

        public static InsuranceCompanyObObject objectFromData(String str) {

            return new Gson().fromJson(str, InsuranceCompanyObObject.class);
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getAddressAr() {
            return AddressAr;
        }

        public void setAddressAr(String AddressAr) {
            this.AddressAr = AddressAr;
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

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }
    }
}
