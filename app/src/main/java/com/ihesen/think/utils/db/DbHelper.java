package com.ihesen.think.utils.db;

import com.ihesen.think.utils.db.daos.ActivityDao;
import com.ihesen.think.utils.UIUtils;

/**
 * author: ihesen on 2016/4/26 10:54
 * email: hesen@ichsy.com
 */
public class DbHelper {

    private static DaoMaster master;
    private static DaoSession daoSession;

    public static final String DB_NAME = "think_db";


    public static DaoMaster getDaoMasterInstance() {
        if (master == null) {
            master = new DaoMaster(new DaoMaster.DevOpenHelper(UIUtils.getContext(), DB_NAME, null).getWritableDatabase());
        }
        return master;
    }

    public static DaoSession getDaoSessionInstance() {
        if (daoSession == null) {
            daoSession = getDaoMasterInstance().newSession();
        }
        return daoSession;
    }

    public static ActivityDao getActivityDao() {
        return getDaoSessionInstance().getActivityDao();
    }

}
