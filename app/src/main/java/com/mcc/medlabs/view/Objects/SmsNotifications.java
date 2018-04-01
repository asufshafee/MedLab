package com.mcc.medlabs.view.Objects;

import com.google.gson.Gson;

/**
 * Created by GeeksEra on 3/30/2018.
 */

public class SmsNotifications {

    /**
     * NewVisitNo : 2147483647
     * NotifcationNo : 2147483647
     * SumOfNotifcation : 2147483647
     */

    private int NewVisitNo;
    private int NotifcationNo;
    private int SumOfNotifcation;

    public static SmsNotifications objectFromData(String str) {

        return new Gson().fromJson(str, SmsNotifications.class);
    }

    public int getNewVisitNo() {
        return NewVisitNo;
    }

    public void setNewVisitNo(int NewVisitNo) {
        this.NewVisitNo = NewVisitNo;
    }

    public int getNotifcationNo() {
        return NotifcationNo;
    }

    public void setNotifcationNo(int NotifcationNo) {
        this.NotifcationNo = NotifcationNo;
    }

    public int getSumOfNotifcation() {
        return SumOfNotifcation;
    }

    public void setSumOfNotifcation(int SumOfNotifcation) {
        this.SumOfNotifcation = SumOfNotifcation;
    }
}
