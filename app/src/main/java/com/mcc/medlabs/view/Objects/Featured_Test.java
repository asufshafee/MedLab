package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeeksEra on 3/28/2018.
 */

public class Featured_Test implements Serializable {

    @SerializedName("Description")
    private String _$Description17; // FIXME check this code
    private int ListCount;
    private int ResultResponse;
    private List<SahtakBilDeniaObObject> SahtakBilDeniaOb;

    public static Featured_Test objectFromData(String str) {

        return new Gson().fromJson(str, Featured_Test.class);
    }

    public String get_$Description17() {
        return _$Description17;
    }

    public void set_$Description17(String _$Description17) {
        this._$Description17 = _$Description17;
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

    public List<SahtakBilDeniaObObject> getSahtakBilDeniaOb() {
        return SahtakBilDeniaOb;
    }

    public void setSahtakBilDeniaOb(List<SahtakBilDeniaObObject> SahtakBilDeniaOb) {
        this.SahtakBilDeniaOb = SahtakBilDeniaOb;
    }

    public static class SahtakBilDeniaObObject implements Serializable{
        /**
         * Description : <!doctype html>
         <html>
         <head>
         <meta charset="utf-8">
         <title>Untitled Document</title>
         <style type="text/css">
         body,td,th {
         color: #393939;
         font-size: 14px;
         font-family: HelveticaNeueLT Std Lt;
         }
         h1 {
         color: #ee2e24;
         }
         h1,h2,h3,h4,h5,h6 {
         font-family: HelveticaNeueLT Std Lt;
         }
         </style>
         </head>

         <body>

         <p>These check-ups include four groups  of tests to check on your health and the health of your loved ones. They are  carefully designed to highlight how &lsquo;well&rsquo; the most important organs of your  body are faring, and with each of the four check ups, more and more tests are  added to get a more comprehensive picture of the status of your bodily  functions.</p>
         <h1 style="margin-bottom:0;">1
         </h1>
         <p style="margin-top:0;">CBC <br>
         FBS<br>
         Creatinine<br>
         Uric Acid<br>
         Triglycerides<br>
         Cholesterol<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         SGPT<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 53JD<br>
         Profile Price JD35</strong></p>
         <h1 style="margin-bottom:0;">2
         </h1>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c <br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         TSH<br>
         Vitamin B12<br>
         Urine Analysis <br>
         <strong>Ministry Of Health Price 125JD<br>
         Profile Price JD69</strong></p>

         <h1 style="margin-bottom:0;">3 </h1><h3 style="margin-top:0;"> Females</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Calcium<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Vitamin B12<br>
         TSH<br>
         CRP<br>
         CA 125<br>
         CA 15.3<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 205JD<br>
         Profile Price JD109</strong></p>

         <h1 style="margin-bottom:0;">3 </h1><h3 style="margin-top:0;"> Males</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Calcium<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Vitamin B12<br>
         TSH<br>
         CRP<br>
         PSA Total<br>
         PSA Free<br>
         Free PSA/Total PSA Ratio<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 195JD<br>
         Profile Price JD109</strong></p>

         <h1 style="margin-bottom:0;">4 </h1>
         <h3 style="margin-top:0;"> Females</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Urea<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Direct Bilirubin<br>
         Calcium<br>
         Amylase<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Homocysteine<br>
         Ferritin<br>
         Vitamin B12<br>
         Vitamin D<br>
         TSH<br>
         CRP<br>
         CEA<br>
         CA 19.9<br>
         CA 125<br>
         CA 15.3<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 339JD<br>
         Profile Price JD185</strong></p>

         <h1 style="margin-bottom:0;">4 </h1><h3 style="margin-top:0;"> Males</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Urea<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Direct Bilirubin<br>
         Calcium<br>
         Amylase<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Homocysteine<br>
         Ferritin<br>
         Vitamin B12<br>
         Vitamin D<br>
         TSH<br>
         CRP<br>
         CEA<br>
         CA 19.9<br>
         PSA Total<br>
         PSA Free<br>
         Free PSA/Total PSA Ratio<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 329JD<br>
         Profile Price JD185</strong></p>
         <p></p>
         </body>
         </html>

         * DescriptionAr : <!doctype html>
         <html>
         <head>
         <meta charset="utf-8">
         <title>Untitled Document</title>
         <style type="text/css">
         body,td,th {
         color: #393939;
         font-size: 14px;
         font-family: HelveticaNeueLT Std Lt;
         }
         h1 {
         color: #ee2e24;
         }
         h1,h2,h3,h4,h5,h6 {
         font-family: HelveticaNeueLT Std Lt;
         }
         .FP {
         font-family: GE SS Two Light;
         text-align: right;
         font-size: 16px;
         }
         </style>
         </head>

         <body>

         <p class="FP" dir="RTL">والتي  تشمل أربع مجموعات من الفحوصات الطبية للاطمئنان على صحتك وصحة أحبائك. تم تصميم  هذه الفحوصات بعناية لتسليط الضوء على أهم أجهزة الجسم وكيفية القيام بوظائفها،  ومع كل من الفحوصات الأربعة، يتم إضافة المزيد والمزيد من الاختبارات للحصول على  صورة أكثر شمولا لحالة وظائف جسمك. </p>
         <p>&nbsp;</p>
         <h1 style="margin-bottom:0;">1
         </h1>
         <p style="margin-top:0;">CBC <br>
         FBS<br>
         Creatinine<br>
         Uric Acid<br>
         Triglycerides<br>
         Cholesterol<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         SGPT<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 53JD<br>
         Profile Price JD35</strong></p>
         <h1 style="margin-bottom:0;">2
         </h1>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c <br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         TSH<br>
         Vitamin B12<br>
         Urine Analysis <br>
         <strong>Ministry Of Health Price 125JD<br>
         Profile Price JD69</strong></p>

         <h1 style="margin-bottom:0;">3 </h1><h3 style="margin-top:0;"> Females</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Calcium<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Vitamin B12<br>
         TSH<br>
         CRP<br>
         CA 125<br>
         CA 15.3<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 205JD<br>
         Profile Price JD109</strong></p>

         <h1 style="margin-bottom:0;">3 </h1><h3 style="margin-top:0;"> Males</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Calcium<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Vitamin B12<br>
         TSH<br>
         CRP<br>
         PSA Total<br>
         PSA Free<br>
         Free PSA/Total PSA Ratio<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 195JD<br>
         Profile Price JD109</strong></p>

         <h1 style="margin-bottom:0;">4 </h1>
         <h3 style="margin-top:0;"> Females</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Urea<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Direct Bilirubin<br>
         Calcium<br>
         Amylase<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Homocysteine<br>
         Ferritin<br>
         Vitamin B12<br>
         Vitamin D<br>
         TSH<br>
         CRP<br>
         CEA<br>
         CA 19.9<br>
         CA 125<br>
         CA 15.3<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 339JD<br>
         Profile Price JD185</strong></p>

         <h1 style="margin-bottom:0;">4 </h1><h3 style="margin-top:0;"> Males</h3>
         <p style="margin-top:0;">CBC<br>
         ESR<br>
         FBS<br>
         HbA1c<br>
         Urea<br>
         Creatinine<br>
         Uric Acid<br>
         Cholesterol<br>
         Triglycerides<br>
         HDL<br>
         Cholesterol/HDL Ratio<br>
         LDL<br>
         Alkaline Phosphatase<br>
         SGPT<br>
         SGOT<br>
         GGT<br>
         CPK<br>
         Total Protein<br>
         Albumin<br>
         Globulin<br>
         Albumin/Globulin Ratio<br>
         Total Bilirubin<br>
         Direct Bilirubin<br>
         Calcium<br>
         Amylase<br>
         Sodium<br>
         Potassium<br>
         Chloride<br>
         Homocysteine<br>
         Ferritin<br>
         Vitamin B12<br>
         Vitamin D<br>
         TSH<br>
         CRP<br>
         CEA<br>
         CA 19.9<br>
         PSA Total<br>
         PSA Free<br>
         Free PSA/Total PSA Ratio<br>
         Urine Analysis<br>
         <strong>Ministry Of Health Price 329JD<br>
         Profile Price JD185</strong></p>
         <p></p>
         </body>
         </html>

         * Id : 1
         * Image :
         * PDFLink :
         * ProgramName : Sehtak Bil Denia - Checkups
         * ProgramNameAr : صحتك بالدنيا - فحوصات
         */

        private String Description;
        private String DescriptionAr;
        private String Id;
        private String Image;
        private String PDFLink;
        private String ProgramName;
        private String ProgramNameAr;

        public static SahtakBilDeniaObObject objectFromData(String str) {

            return new Gson().fromJson(str, SahtakBilDeniaObObject.class);
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

        public String getPDFLink() {
            return PDFLink;
        }

        public void setPDFLink(String PDFLink) {
            this.PDFLink = PDFLink;
        }

        public String getProgramName() {
            return ProgramName;
        }

        public void setProgramName(String ProgramName) {
            this.ProgramName = ProgramName;
        }

        public String getProgramNameAr() {
            return ProgramNameAr;
        }

        public void setProgramNameAr(String ProgramNameAr) {
            this.ProgramNameAr = ProgramNameAr;
        }
    }
}
