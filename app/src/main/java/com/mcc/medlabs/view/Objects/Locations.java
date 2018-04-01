package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeeksEra on 3/24/2018.
 */

public class Locations implements Serializable {

    /**
     * BranchOb : [{"Address":"Aqaba, Third Commercial Area., behind Jordan Post Office, Walid Kawar Bldg.","AddressAr":"العقبة -المنطقة التجارية الثالثة - خلف البريد عمارة الوليد","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي - العقبة","Distance":"4953.10802610623","Id":"36","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"29.529529","Location":"Aqaba","LocationAr":"العقبة","Longitude":"35.004388","Telephone":"03 2033040"},{"Address":"Karak, Italian Hospital Str., Al Farouq Complex","AddressAr":"الكرك \u2013 شارع المستشفى الإيطالي - عمارة الإخوان المسلمين - مجمع الفاروق","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب  الطبي ","Distance":"5109.09835844188","Id":"35","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.1866109","Location":"Karak","LocationAr":"الكرك","Longitude":"35.6316374","Telephone":"03 2354204"},{"Address":"Ramallah, Al Ersal St. Al-Huneidi Complex","AddressAr":"رام الله - شارع الارسال , مجمع الهنيدي","Branch":"Al Hiba IVF Centre","BranchAr":"مركز الهبة للمساعدة على الحمل","Distance":"5124.00993647519","Id":"38","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.906174","Location":"Ramallah","LocationAr":"رام الله","Longitude":"35.205824","Telephone":"009702 298 6676"},{"Address":"Ramallah, Al Ersal St. Al-Huneidi Complex","AddressAr":"رام الله - شارع الارسال , مجمع الهنيدي","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب  الطبي ","Distance":"5124.00993647519","Id":"39","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.906174","Location":"Ramallah","LocationAr":"رام الله","Longitude":"35.205824","Telephone":"009702 2983125"},{"Address":"Nablus \u2013 Hitteen St., Al-Nabulsi building - 2nd ","AddressAr":"نابلس - شارع حطين, مجمع النابلسي الطابق الثاني","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب  الطبي ","Distance":"5149.2879700913","Id":"40","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.220144","Location":"Nablus","LocationAr":"نابلس","Longitude":"35.262944","Telephone":"0097092384074"},{"Address":"Madaba, Petra Str., Al Samaeen Complex No. 51","AddressAr":"مادبا - ش الملك عبدالله الثاني مجمع المصو","Branch":"Al Re'ayeh Medical Laboratory","BranchAr":"مختبر الرعاية الطبي ","Distance":"5156.55104723254","Id":"27","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.716001","Location":"Madaba","LocationAr":"مادبا","Longitude":"35.799388","Telephone":"05 3252122"},{"Address":"Marj El-Hamam, main street, near Al Neel Pharmacy","AddressAr":" مرج الحمام \u2013 الشارع الرئيسي عمارة البنك الاسلامي","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي - مرج الحمام","Distance":"5171.68401026491","Id":"19","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.898084","Location":"Amman","LocationAr":"عمان","Longitude":"35.841224","Telephone":"06 5731571"},{"Address":"7th circle, Abdallah ghosheh Str. Bldg. No. 47","AddressAr":"شارع عبدالله غوشة - مجمع الحسيني بناية 47","Branch":"Advanced Medical Solutions Laboratory","BranchAr":"مختبر الحلول المتقدمة الطبية ","Distance":"5171.93581427599","Id":"14","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.9657515","Location":"Amman","LocationAr":"عمان","Longitude":"35.7860893","Telephone":"06 5859004"},{"Address":"Fuheis, Shaker Circle, Rawhi Medical Bldg. ","AddressAr":"الفحيص -  دوار شاكر مجمع اجمل عكروش","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي - الفحيص","Distance":"5174.33568012659","Id":"25","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.004195","Location":"Fuheis","LocationAr":"الفحيص","Longitude":"35.784360","Telephone":"06 4729219"},{"Address":"Salt- Queen Rania Al-Abdullah Str., Wadi Al-Shajara, opposite Ya Hala Gas Station","AddressAr":"السلط - وادي الشجرة، مقابل محطة يا هلا","Branch":"National Consultancy Medical Laboratory","BranchAr":"المركز الوطني الاستشاري","Distance":"5174.45168848559","Id":"26","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.053229","Location":"Salt","LocationAr":"السلط","Longitude":"35.743301","Telephone":"05 3558600"},{"Address":"Daheyat Al Yasmeen, Jabal Arafat Str.","AddressAr":"ضاحية الياسمين - شارع جبل عرفات مجمع لاند مارك بناية رقم 60","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5176.52971785705","Id":"21","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.916005","Location":"Amman","LocationAr":"عمان","Longitude":"35.889381","Telephone":"06 4204859"},{"Address":"Mugablain, Darweesh Brothers Center","AddressAr":"المقابلين - شارع عائشة التيمورية مجمع درويش اخوان","Branch":"Al Zaytouna Medical Laboratory","BranchAr":"مختبر الزيتونة الطبي ","Distance":"5177.38807156797","Id":"24","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.903639","Location":"Salt","LocationAr":"عمان","Longitude":"35.911278","Telephone":"06 4207506"},{"Address":"Abdoun - near Taj Mall, Fitness First Platinum bldg., behind Pharmacy One ","AddressAr":" شارع سعد عبده شموط , FitnessFirst - عبدون بجانب تاج مول و بمبنى نادي  ","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5177.83714269189","Id":"6","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.941501","Location":"Amman","LocationAr":"عمان","Longitude":"35.884583","Telephone":"06 5921630"},{"Address":"5th Circle, Bader Medical Complex No. 9, 1st Floor","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5178.44403370099","Id":"5","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.960056","Location":"Amman","LocationAr":"عمان","Longitude":"35.876555","Telephone":"06 5922960"},{"Address":"5th  Circle, Bader Medical Complex (9)","AddressAr":"الدوار الخامس، مجمع بدر الطبي بناية رقم 9","Branch":"Abdoun PhysioHealth Center","BranchAr":"مختبر مدلاب  الطبي ","Distance":"5178.44971079004","Id":"37","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.960030","Location":"Amman","LocationAr":"عمان","Longitude":"35.876652","Telephone":"06 5921101"},{"Address":"Khilda, Wasfi al Tal St., opp. Arab Bank, Complex No. 281","AddressAr":"خلدا - شارع وصفي التل - إشارة البنك العربي مجمع ابن الهيثم الطبي بناية رقم 97 الطابق 1","Branch":"Ibn Al-Bitar Medical Laboratory","BranchAr":"مختبر ابن البيطار الطبي","Distance":"5178.68354924497","Id":"16","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.996702","Location":"Amman","LocationAr":"عمان","Longitude":"35.848052","Telephone":"06 5513480"},{"Address":"Um Uthaina- Saad bin Abi Waqqas Str. Abu Zeid complex No. 36","AddressAr":"ام اذينة، شارع سعد بن ابي وقاص- مقابل الطيران العماني مجمع ابو زيد الطابق الارضي","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5178.94115336041","Id":"7","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.966697","Location":"Amman","LocationAr":"عمان","Longitude":"35.877356","Telephone":"06 5533268"},{"Address":"3rd Circle, Ibn Khaldoun street, near pharmacy time, vitro complex","AddressAr":"الدوار الرابع، شارع ابن خلدون مجمع فيترو","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5179.68716621738","Id":"2","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.953925","Location":"Amman","LocationAr":"عمان","Longitude":"35.898167","Telephone":"06 4619387"},{"Address":"Sweileh, Princess Haya Bint AL Hussein Str., Maysalon Neighbor","AddressAr":"صويلح - شارع الأميرة هيا بنت الحسين - حي ميسلون","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5179.81963752386","Id":"18","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.023415","Location":"Amman","LocationAr":"عمان","Longitude":"35.839881","Telephone":"06- 5335824"},{"Address":"Wehdat, Madaba Str., Al Rahma Bldg. No.210","AddressAr":"الوحدات - شارع مادبا - مقابل المخفر عمارة الرحمة رقم 210","Branch":"Wehdat Medical Laboratory","BranchAr":"مختبر الوحدات الطبي","Distance":"5179.96499601414","Id":"22","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.911056","Location":"Amman","LocationAr":"عمان","Longitude":"35.938696","Telephone":"06 4734744"},{"Address":"3rd Circle, Ibn Khaldoun Street, Rawhi Medical Complex No. 30","AddressAr":"الدوار الثالث - شارع ابن خلدون  مجمع العتوم التجاري بناية رقم 98","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5179.9833321021","Id":"3","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.950036","Location":"Amman","LocationAr":"عمان","Longitude":"35.905406","Telephone":"06 4656089"},{"Address":"Al Rabieh, Abdullah Bin Rawaha, Abraj Al Rabieh Bldg. No. 4","AddressAr":"الرابية - شارع عبدالله بن رواحة بجانب Starbucks عمارة ابراج الرابية بناية 4","Branch":"Al Banan Medical Laboratory","BranchAr":"مختبر البنان الطبي","Distance":"5180.09045727506","Id":"12","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.974307","Location":"Amman","LocationAr":"عمان","Longitude":"35.885889","Telephone":"06 5560408"},{"Address":"3rd Circle, Ibn Khaldoun Street, beside Al Khalidi Hospital, Building No. 35","AddressAr":"الدوار الثالث - شارع ابن خلدون مبنى رقم 35","Branch":"Zahran Central Laboratory","BranchAr":"مختبر زهران المركزي ","Distance":"5180.11806835999","Id":"1","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.952606","Location":"Amman","LocationAr":"عمان","Longitude":"35.904961","Telephone":"06 4613031"},{"Address":"Gardens Street, Al Otoum Complex No. 98","AddressAr":"شارع وصفي التل - بجانب مطاعم جبري مجمع العتوم التجاري رقم 98","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5180.29793780824","Id":"10","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.987056","Location":"Amman","LocationAr":"عمان","Longitude":"35.877611","Telephone":"06 5522183"},{"Address":"3rd Circle, Opposite Farah Hospital, Al-Sakhaa Bldg. No. 21","AddressAr":"الدوار الثالث - مقابل مستشفى فرح، مجمع السخاء الطبي بناية رقم 21 الطابق الثاني","Branch":"QLab","BranchAr":"مختبر الجودة الطبي ","Distance":"5180.35541114637","Id":"4","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.951611","Location":"Amman","LocationAr":"عمان","Longitude":"35.908934","Telephone":"06 4640918"},{"Address":"Al Madina Al Munawara St, Al Sultan market, in front of Amin Shuqair drug store","AddressAr":"شارع المدينة المنورة - سوق السلطان - أمام مستودع أدوية أمين شقير","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5180.72032758269","Id":"13","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.99972222","Location":"Amman","LocationAr":"عمان","Longitude":"35.87222222","Telephone":"06 5661415"},{"Address":"Shmeisani, near Specialty Hospital, Omran Medical Complex No. 60","AddressAr":"الشميساني - شارع جابر بن حيان - بجانب م. التخصصي  مجمع عمران الطبي بناية رقم 60","Branch":"Al Re'ayeh Specialty Laboratory","BranchAr":"مختبر الرعاية التخصصي ","Distance":"5181.56387556421","Id":"11","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.978584","Location":"Amman","LocationAr":"عمان","Longitude":"35.901556","Telephone":"06 5666083"},{"Address":"Daheyat Al Rasheed, Akef Al Fayez Str. , opposite Umaima","AddressAr":"ضاحية الرشيد , سارع عاكف الفايز , مقابل أميمة","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب  الطبي ","Distance":"5181.81256115559","Id":"41","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.007622","Location":"Amman","LocationAr":"عمان","Longitude":"35.8797561","Telephone":"06 5153560"},{"Address":"Jbeiha, Yajouz St., Jbeiha traffic lights, Bilbesi Complex","AddressAr":"الجبيهه - شارع ياجوز- دوار اشارات الجبيهه - بجانب السيفوي مجمع البلبيسي","Branch":"Al Motamad Medical Laboratory","BranchAr":"مختبر المعتمد الطبي","Distance":"5181.92892720224","Id":"17","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.029446","Location":"Amman","LocationAr":"عمان","Longitude":"35.862414","Telephone":"06 5350884"},{"Address":"Jabal Al Hussein, Sukaina Circle, Al Farmawi Bldg. No. 2","AddressAr":"جبل الحسين - دوار سكينة- مجمع الفرماوي بناية رقم 2","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5181.95127608093","Id":"8","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.970278","Location":"Amman","LocationAr":"عمان","Longitude":"35.913805","Telephone":"06 5668092"},{"Address":"Jabal Al Hussein, Al Dakhlieh Circle, Al Amal Hospital","AddressAr":" جبل الحسين - مستشفى الامل ","Branch":"Al Amal Hospital Laboratory","BranchAr":"مختبر مستشفى الامل","Distance":"5182.10499730154","Id":"9","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.975972","Location":"Amman","LocationAr":"عمان","Longitude":"35.910916","Telephone":"06 5673155"},{"Address":"Jabal Al-Weibdeh, Luzmila Hospital","AddressAr":"جبل اللويبده - ش. عبد المنعم سمارة مبنى المستشفى","Branch":"Luzmila Hospital Laboratory","BranchAr":"مختبر مستشفى لوزميلا","Distance":"5182.256289373","Id":"15","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.955447","Location":"Amman","LocationAr":"عمان","Longitude":"35.930583","Telephone":"06 4624345"},{"Address":"Al Hashmi, Bathaa str, near Anabtawi Sweets, Akroush Complex","AddressAr":"الهاشمي - شارع البطحاء - قرب حلويات عنبتاوي","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي ","Distance":"5184.99516128829","Id":"23","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.976335","Location":"Amman","LocationAr":"عمان","Longitude":"35.948554","Telephone":"06 5052627"},{"Address":"Tabarbour  , Al Yafi St, beside Arab Bank in front of Nafisa sweets, Al Bari Medical Complex","AddressAr":"طبربور , شارع عمر اليافي , بجانب البنك العربي و مقابل حلويات نفيسة , مجمع الباري","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب  الطبي ","Distance":"5185.94281223954","Id":"42","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.00361111","Location":"Amman","LocationAr":"عمان","Longitude":"35.93750000","Telephone":"06 5058215"},{"Address":"Marka, opposite Arab Bank, Kibrilyan Bldg.","AddressAr":"ماركا - شارع الملك عبدالله الأول ، مقابل البنك العربي-  فوق صيدلية الشيماء بناية كبريليان","Branch":"Al-Aman Medical Laboratory","BranchAr":"مختبر الأمان الطبي ","Distance":"5188.12818041505","Id":"20","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"31.980640","Location":"Amman","LocationAr":"عمان","Longitude":"35.985972","Telephone":"06 4888823"},{"Address":"Zarqa Autostrad - Near Jabal al Zaytoun Hospital","AddressAr":" الزرقاء \u2013 الأتوستراد - اشارة مستشفى- بجانب مستشفى جبل الزيتونة ","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي - الزرقاء, الأتوستراد ","Distance":"5200.1016600101","Id":"30","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.039196","Location":"Zarqa","LocationAr":"الزرقاء","Longitude":"36.092777","Telephone":"05 3653263"},{"Address":"Jerash, Al Shaab Str., Jerash New Chamber of Commerce Bldg.","AddressAr":"جرش - ش الشعب مبنى غرفة تجارة جرش الجديدة","Branch":"Jarasa Medical Laboratory","BranchAr":"مختبر جراسا الطبي ","Distance":"5200.89919191894","Id":"34","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.278196","Location":"Jerash","LocationAr":"جرش","Longitude":"35.896416","Telephone":"02 6340650"},{"Address":"Zarqa, Saadeh Str., Al Saadeh Building","AddressAr":"الزرقاء - شارع السعادة - مجمع السعادة","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي - الزرقاء ","Distance":"5201.70868770554","Id":"28","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.062780","Location":"Zarqa","LocationAr":"الزرقاء","Longitude":"36.093613","Telephone":"05 3981904"},{"Address":"New Zarqa, Mecca Str (36) ., Building 61, Sameh Complex (1)","AddressAr":"الزرقاء الجديدة - شارع مكة المكرمة(36) بناية61 ","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي - الزرقاء الجديدة","Distance":"5203.61629374638","Id":"29","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.086203","Location":"Zarqa","LocationAr":"الزرقاء","Longitude":"36.098527","Telephone":"05 3866247"},{"Address":"Irbid, end of Al Huson Str., behind Al Day'aa Sweets, Al Hawi Complex","AddressAr":"اربد - أخر شارع الحصن - خلف حلويات الضيعة - مجمع الحاوي","Branch":"Irbid Medical Laboratory","BranchAr":"مختبر مدلاب الطبي - إربد، دوار الثقافة ","Distance":"5215.71263086179","Id":"32","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.554417","Location":"Irbid","LocationAr":"إربد","Longitude":"35.850307","Telephone":"02 7260620"},{"Address":"New-Irbid, Culture Circle, near Sameh Mall","AddressAr":"اربد - دوار الثقافة - بجانب سامح مول عمارة كتكت","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر إربد للتحاليل الطبية ","Distance":"5216.70335640675","Id":"33","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.538447","Location":"Irbid","LocationAr":"إربد","Longitude":"35.877581","Telephone":"02 7255029"},{"Address":"Al Mafraq, next to al Mafraq Health Directorate, al baladeyeh str downtown","AddressAr":"المفرق مقابل مديرية صحة المفرق شارع البلدية و سط البلد","Branch":"MedLabs Medical Laboratory","BranchAr":"مختبر مدلاب الطبي -المفرق ","Distance":"5228.94008617479","Id":"31","Image":"http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png","Latitude":"32.341339","Location":"Al Mafraq","LocationAr":"المفرق","Longitude":"36.211725","Telephone":"05 6232926"}]
     * Description : Success
     * ListCount : 42
     * ResultResponse : 0
     */

    private String Description;
    private int ListCount;
    private int ResultResponse;
    private List<BranchObObject> BranchOb;

    public static Locations objectFromData(String str) {

        return new Gson().fromJson(str, Locations.class);
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

    public List<BranchObObject> getBranchOb() {
        return BranchOb;
    }

    public void setBranchOb(List<BranchObObject> BranchOb) {
        this.BranchOb = BranchOb;
    }

    public static class BranchObObject implements Serializable {
        /**
         * Address : Aqaba, Third Commercial Area., behind Jordan Post Office, Walid Kawar Bldg.
         * AddressAr : العقبة -المنطقة التجارية الثالثة - خلف البريد عمارة الوليد
         * Branch : MedLabs Medical Laboratory
         * BranchAr : مختبر مدلاب الطبي - العقبة
         * Distance : 4953.10802610623
         * Id : 36
         * Image : http://213.186.160.67:8086/MedlabsAppV2/Labs/Labs.png
         * Latitude : 29.529529
         * Location : Aqaba
         * LocationAr : العقبة
         * Longitude : 35.004388
         * Telephone : 03 2033040
         */

        private String Address;
        private String AddressAr;
        private String Branch;
        private String BranchAr;
        private String Distance;
        private String Id;
        private String Image;
        private String Latitude;
        private String Location;
        private String LocationAr;
        private String Longitude;
        private String Telephone;

        public static BranchObObject objectFromData(String str) {

            return new Gson().fromJson(str, BranchObObject.class);
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

        public String getBranch() {
            return Branch;
        }

        public void setBranch(String Branch) {
            this.Branch = Branch;
        }

        public String getBranchAr() {
            return BranchAr;
        }

        public void setBranchAr(String BranchAr) {
            this.BranchAr = BranchAr;
        }

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String Distance) {
            this.Distance = Distance;
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

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public String getLocationAr() {
            return LocationAr;
        }

        public void setLocationAr(String LocationAr) {
            this.LocationAr = LocationAr;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getTelephone() {
            return Telephone;
        }

        public void setTelephone(String Telephone) {
            this.Telephone = Telephone;
        }
    }
}
