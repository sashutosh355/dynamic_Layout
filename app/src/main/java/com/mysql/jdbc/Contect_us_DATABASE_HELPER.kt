package com.infinity_loop.dunamiclayout

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.infinity_loop.dunamiclayout.Contect_us_INFO


class Contect_us_DATABASE_HELPER(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        val DATABASE_NAME = "MYDatabase.db"
        val TABLE_NAME = "accounts"
        val ID = "ID"
        val DISCRIPTION = "DISCRIPTION"
        val PHONE = "PHONE"
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHONE TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    /**
     * insert data
     */
    fun insertData(name: String,  phone: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DISCRIPTION, name)
        contentValues.put(PHONE, phone)
        db.insert(TABLE_NAME, null, contentValues)
    }

    /**
     * list of user data
     */
    fun listOfUserInfo(): ArrayList<Contect_us_INFO>  {
        val db = this.writableDatabase
        val res = db.rawQuery("select * from " + TABLE_NAME, null)
        val useList = ArrayList<Contect_us_INFO>()
        while (res.moveToNext()) {
            var userInfo = Contect_us_INFO()
            userInfo.id = Integer.valueOf(res.getString(0))
            userInfo.Discription= res.getString(1)
            userInfo.phone = res.getString(2)
            useList.add(userInfo)
        }
        return useList
    }

    //Getting all user list
    fun getAllUserData(): ArrayList<Contect_us_INFO> {
        val stuList: ArrayList<Contect_us_INFO> = arrayListOf<Contect_us_INFO>()
        val cursor: Cursor = getReadableDatabase().query(TABLE_NAME, arrayOf(ID, DISCRIPTION,  PHONE), null, null, null, null, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val id : Int = cursor.getInt(cursor.getColumnIndex(ID))
                        val name: String = cursor.getString(cursor.getColumnIndex(DISCRIPTION))
                        val phone: Int = cursor.getInt(cursor.getColumnIndex(PHONE))
                        var userInfo = Contect_us_INFO()
                        userInfo.id = id
                        userInfo.Discription = name
                        userInfo.phone = phone.toString()
                        stuList.add(userInfo)
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }

        return stuList
    }

    fun getParticularUserData(id: String): Contect_us_INFO {
        var userInfo  = Contect_us_INFO()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + ID + " = '" + id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        userInfo.id = cursor.getInt(cursor.getColumnIndex(ID))
                        userInfo.Discription = cursor.getString(cursor.getColumnIndex(DISCRIPTION))
                        userInfo.phone = cursor.getString(cursor.getColumnIndex(PHONE))
                    } while ((cursor.moveToNext()));
                }
            }
        } finally {
            cursor.close();
        }
        return userInfo
    }

    /**
     * update the userdata
     */
    fun updateData(id: String, name: String, phone: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        contentValues.put(DISCRIPTION, name)
        contentValues.put(PHONE, phone)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    /**
     * delete the userData
     */
    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"ID = ?", arrayOf(id))

    }


}