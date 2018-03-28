package com.webmarke8.app.medlab.DatabasePart;

import android.database.Cursor;

public class SDBBean {

    public static final String TABLE_NAME = "SehtalBelDeniaProgram";
    private String countryId;
    private String checkupsEn;
    private String checkupsAr;
    private String checkupsPDF;
    private String hairLossEn;
    private String hairLossAr;
    private String hairLossPDF;
    private String checkupsShapeEn;
    private String checkupsShapeAr;
    private String checkupsShapePDF;
    private String checkupsKidsEn;
    private String checkupsKidsAr;
    private String checkupsKidsPDF;

    public String getCheckupsShapeEn() {
        return checkupsShapeEn;
    }

    public void setCheckupsShapeEn(String checkupsShapeEn) {
        this.checkupsShapeEn = checkupsShapeEn;
    }

    public String getCheckupsShapeAr() {
        return checkupsShapeAr;
    }

    public void setCheckupsShapeAr(String checkupsShapeAr) {
        this.checkupsShapeAr = checkupsShapeAr;
    }

    public String getCheckupsShapePDF() {
        return checkupsShapePDF;
    }

    public void setCheckupsShapePDF(String checkupsShapePDF) {
        this.checkupsShapePDF = checkupsShapePDF;
    }

    public String getCheckupsKidsEn() {
        return checkupsKidsEn;
    }

    public void setCheckupsKidsEn(String checkupsKidsEn) {
        this.checkupsKidsEn = checkupsKidsEn;
    }

    public String getCheckupsKidsAr() {
        return checkupsKidsAr;
    }

    public void setCheckupsKidsAr(String checkupsKidsAr) {
        this.checkupsKidsAr = checkupsKidsAr;
    }

    public String getCheckupsKidsPDF() {
        return checkupsKidsPDF;
    }

    public void setCheckupsKidsPDF(String checkupsKidsPDF) {
        this.checkupsKidsPDF = checkupsKidsPDF;
    }


    private String loyaltyEn;
    private String loyaltyAr;
    private String loyaltyPDF;
    private String understandingEn;
    private String understandingAr;
    private String understandingPDF;
    private String voucherEn;
    private String voucherAr;
    private String voucherPDF;

    /**
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the checkupsEn
     */
    public String getCheckupsEn() {
        return checkupsEn;
    }

    public String getHairLossEn() {
        return hairLossEn;
    }

    /**
     * @param checkupsEn the checkupsEn to set
     */
    public void setCheckupsEn(String checkupsEn) {
        this.checkupsEn = checkupsEn;
    }

    /**
     * @return the checkupsAr
     */
    public String getCheckupsAr() {
        return checkupsAr;
    }

    public String getHairLossAr() {
        return hairLossAr;
    }

    /**
     * @param checkupsAr the checkupsAr to set
     */
    public void setCheckupsAr(String checkupsAr) {
        this.checkupsAr = checkupsAr;
    }

    /**
     * @return the checkupsPDF
     */
    public String getCheckupsPDF() {
        return checkupsPDF;
    }

    public String getHairLossPDF() {
        return hairLossPDF;
    }

    /**
     * @param checkupsPDF the checkupsPDF to set
     */
    public void setCheckupsPDF(String checkupsPDF) {
        this.checkupsPDF = checkupsPDF;
    }

    /**
     * @return the loyaltyEn
     */
    public String getLoyaltyEn() {
        return loyaltyEn;
    }

    /**
     * @param loyaltyEn the loyaltyEn to set
     */
    public void setLoyaltyEn(String loyaltyEn) {
        this.loyaltyEn = loyaltyEn;
    }

    /**
     * @return the loyaltyAr
     */
    public String getLoyaltyAr() {
        return loyaltyAr;
    }

    /**
     * @param loyaltyAr the loyaltyAr to set
     */
    public void setLoyaltyAr(String loyaltyAr) {
        this.loyaltyAr = loyaltyAr;
    }

    /**
     * @return the loyaltyPDF
     */
    public String getLoyaltyPDF() {
        return loyaltyPDF;
    }

    /**
     * @param loyaltyPDF the loyaltyPDF to set
     */
    public void setLoyaltyPDF(String loyaltyPDF) {
        this.loyaltyPDF = loyaltyPDF;
    }

    /**
     * @return the understandingEn
     */
    public String getUnderstandingEn() {
        return understandingEn;
    }

    /**
     * @param understandingEn the understandingEn to set
     */
    public void setUnderstandingEn(String understandingEn) {
        this.understandingEn = understandingEn;
    }

    /**
     * @return the understandingAr
     */
    public String getUnderstandingAr() {
        return understandingAr;
    }

    /**
     * @param understandingAr the understandingAr to set
     */
    public void setUnderstandingAr(String understandingAr) {
        this.understandingAr = understandingAr;
    }

    /**
     * @return the understandingPDF
     */
    public String getUnderstandingPDF() {
        return understandingPDF;
    }

    /**
     * @param understandingPDF the understandingPDF to set
     */
    public void setUnderstandingPDF(String understandingPDF) {
        this.understandingPDF = understandingPDF;
    }

    /**
     * @return the voucherEn
     */
    public String getVoucherEn() {
        return voucherEn;
    }

    /**
     * @param voucherEn the voucherEn to set
     */
    public void setVoucherEn(String voucherEn) {
        this.voucherEn = voucherEn;
    }

    /**
     * @return the voucherAr
     */
    public String getVoucherAr() {
        return voucherAr;
    }

    /**
     * @param voucherAr the voucherAr to set
     */
    public void setVoucherAr(String voucherAr) {
        this.voucherAr = voucherAr;
    }

    /**
     * @return the voucherPDF
     */
    public String getVoucherPDF() {
        return voucherPDF;
    }

    /**
     * @param voucherPDF the voucherPDF to set
     */
    public void setVoucherPDF(String voucherPDF) {
        this.voucherPDF = voucherPDF;
    }


    public void loadFromCursor(Cursor c) {
        if (!c.isAfterLast()) {
            this.countryId = c.getString(c.getColumnIndex("Countryid"));
            this.checkupsAr = c.getString(c.getColumnIndex("CheckupsAr"));
            this.checkupsEn = c.getString(c.getColumnIndex("Checkups"));
            this.checkupsPDF = c.getString(c.getColumnIndex("CheckupsPDF"));

            this.hairLossAr = c.getString(c.getColumnIndex("CheckupsHairLossAr"));
            this.hairLossEn = c.getString(c.getColumnIndex("CheckupsHairLoss"));
            this.hairLossPDF = c.getString(c.getColumnIndex("CheckupsHairLossPDF"));

            this.loyaltyAr = c.getString(c.getColumnIndex("LoyaltyProgramAr"));
            this.loyaltyEn = c.getString(c.getColumnIndex("LoyaltyProgram"));
            this.loyaltyPDF = c.getString(c.getColumnIndex("LoyaltyProgramPDF"));
            this.understandingAr = c.getString(c.getColumnIndex("UnderstandingSBDAr"));
            this.understandingEn = c.getString(c.getColumnIndex("UnderstandingSBD"));
            this.understandingPDF = c.getString(c.getColumnIndex("UnderstandingSBDPDF"));
            this.voucherAr = c.getString(c.getColumnIndex("GetVoucherAr"));
            this.voucherEn = c.getString(c.getColumnIndex("GetVoucher"));
            this.voucherPDF = c.getString(c.getColumnIndex("GetVoucherPDF"));
            this.checkupsShapeEn = c.getString(c.getColumnIndex("CheckupsShape"));
            this.checkupsShapeAr = c.getString(c.getColumnIndex("CheckupsShapeAr"));
            this.checkupsShapePDF = c.getString(c.getColumnIndex("CheckupsShapePDF"));
            this.checkupsKidsEn = c.getString(c.getColumnIndex("CheckupsKids"));
            this.checkupsKidsAr = c.getString(c.getColumnIndex("CheckupsKidsAr"));
            this.checkupsKidsPDF = c.getString(c.getColumnIndex("CheckupsKidsPDF"));

        }
    }


}
