package com.mcc.medlabs.view.DatabasePart;


import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;


public class MedlabsDelegate {
    private static MedlabsDelegate medlabsDelegate;

    public static MedlabsDelegate getMedLabsDelegateInstance() {
        if (medlabsDelegate == null)
            medlabsDelegate = new MedlabsDelegate();
        return medlabsDelegate;
    }


    public void createDatabase(Context ctx) throws IOException {
        AllDaoes d = (AllDaoes) getDao(ctx);
        d.createDatabase(ctx);
    }


    public ArrayList<LocationsBean> getLocationsList(Context ctx) throws Exception {
        try {
            AllDaoes d = (AllDaoes) getDao(ctx);
            return d.getLocationsList(ctx);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<BranchesBean> getBranchesList(Context ctx, String cID) throws Exception {
        try {
            AllDaoes d = (AllDaoes) getDao(ctx);
            return d.getBranchesList(ctx, cID);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public BranchesBean getBranch(Context ctx, int brId) throws IOException {

        AllDaoes d = (AllDaoes) getDao(ctx);
        try {
            return d.getBranchItem(ctx, brId);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }


    public ArrayList<LabwiseBean> getLabwiseList(Context ctx) throws IOException {
        AllDaoes d = (AllDaoes) getDao(ctx);
        return d.getLabwiseList(ctx);
    }

    public CountryBean getCountryId(Context ctx, String counteryName) throws IOException {
        AllDaoes d = (AllDaoes) getDao(ctx);
        return d.getCountryId(ctx, counteryName);
    }

    public SDBBean getSDBContent(Context ctx, String countryId) throws IOException {
        AllDaoes d = (AllDaoes) getDao(ctx);
        return d.getSDBContent(ctx, countryId);
    }


    public ArrayList<CountryListBean> getCountriesList(Context ctx) throws IOException {
        AllDaoes d = (AllDaoes) getDao(ctx);
        return d.getCountriesList(ctx);
    }

    public CountryListBean getCountryName(Context ctx, String id) throws IOException {
        AllDaoes d = (AllDaoes) getDao(ctx);
        return d.getCountryName(ctx, id);
    }


    public ArrayList<LabwiseBean> getLabwiseListAfterSearch(Context ctx, String keyword) throws IOException {
        AllDaoes d = (AllDaoes) getDao(ctx);
        return d.getLabwiseListAfterSearch(ctx, keyword);

    }

    //-------------------------------------------------------------------------------------------------------------------------

    /**
     * @param ctx
     * @return the appropriate dao depending on the context
     */
    private MainDao getDao(Context ctx) {
        MainDao dao = new MainDao();
        DaoFactory daoFactory = DaoFactory.getDaoFactoryInstance();


        dao = daoFactory.getAllDaoInstance();

        return dao;
    }
}
