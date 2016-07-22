package com.ihesen.think.utils.db.update;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ihesen.think.utils.LogUtil;
import com.ihesen.think.utils.db.DaoMaster;
import com.ihesen.think.utils.db.daos.ActivityDao;

import org.greenrobot.greendao.database.Database;

/**
 * 创建DaoMaster使用
 * author: ihesen on 2016/7/21 17:36
 * email: hesen@ichsy.com
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        LogUtil.hLog().d("GreenDao oldVersion: " + oldVersion + " newVersion: " + newVersion);
        MigrationHelper.migrate(db, ActivityDao.class);
    }
}
