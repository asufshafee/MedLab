package com.webmarke8.app.medlab.DatabasePart;

/***
 * 
 * @author Bayan
 * 
 */

/*
 * Contains the implementation of the daos creation methods
 */
public class DaoFactory implements DaoModel {
    private static DaoFactory daoFactory;

    public static DaoFactory getDaoFactoryInstance() {
	if (daoFactory == null)
	    daoFactory = new DaoFactory();
	return daoFactory;
    }

	@Override
	public AllDaoes getAllDaoInstance() {
		// TODO Auto-generated method stub
		return new AllDaoes();
	}







}