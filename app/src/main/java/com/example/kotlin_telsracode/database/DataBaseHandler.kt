package com.example.kotlin_telsracode.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.kotlin_telsracode.model.Row

class DataBaseHandler (context: Context) : SQLiteOpenHelper(context ,
    DATABASE_NAME, null ,
    DATABASE_VERSION
) {

    companion object {
        val DATABASE_VERSION = 4
        val DATABASE_NAME = "ExploreVisitDatabase.db"


        //Row table
         val ROW_TABLE = "rows"
         val KEY_ROW_ID = "rowId"
         val KEY_ROW_TITLE ="title"
         val KEY_ROW_DESCRIPTION ="description"
         val KEY_ROW_IMAGE = "imageHref"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_ROW = (" CREATE TABLE " + ROW_TABLE + "(" + KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  + KEY_ROW_DESCRIPTION + " TEXT, " + KEY_ROW_TITLE + " TEXT, "+  KEY_ROW_IMAGE + " TEXT " + ")")
        db!!.execSQL(CREATE_ROW)
        Log.d("DATABASE", "CREATED")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int,  newVersion: Int) {
//        db!!.execSQL("DROP TABLE IF EXISTS " + EXPLOREDATA_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + ROW_TABLE)
        onCreate(db)
    }


//insert row
    fun insertRows(row: Row, count : Int ): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        val contentValues = ContentValues()
//        contentValues.put(KEY_ROW_ID, count)
        contentValues.put(KEY_ROW_TITLE, row.title)
        contentValues.put(KEY_ROW_DESCRIPTION, row.description)
        contentValues.put(KEY_ROW_IMAGE,row.imageHref)
        db.insert(ROW_TABLE, null, contentValues)

        db.close()

        return true
    }

//read all Row data from db
    fun getAll(): Cursor? {
        Log.d("DATABASE", "getAll called ")
        val rowList = ArrayList<Row>()
        var subtitle: String
        var description: String
        var imgeHref: String
        val db = this.readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT * FROM $ROW_TABLE ", null)
        Log.d("DATABASE", "DATABASE "+cursor.columnCount)
        Log.d("DATABASE", "DATABASE moveToFirst "+cursor.moveToFirst())
        if (cursor.moveToFirst()) {
            Log.d("DATABASE", "DATABASE isAfterLast "+cursor.isAfterLast())
            Log.d("DATABASE", "DATABASE isAfterLast "+cursor.getColumnIndex(KEY_ROW_TITLE))
            Log.d("DATABASE", "DATABASE isAfterLast "+KEY_ROW_TITLE)
            while (!cursor.isAfterLast()){
                if ( !cursor.isNull( cursor.getColumnIndex(KEY_ROW_TITLE) ) )
                {
                    subtitle = cursor.getString(cursor.getColumnIndex(KEY_ROW_TITLE))
                }
                else
                {
                    subtitle = ""
                }

                if ( !cursor.isNull( cursor.getColumnIndex(KEY_ROW_DESCRIPTION) ) )
                {
                    description = cursor.getString(cursor.getColumnIndex(KEY_ROW_DESCRIPTION))
                }
                else
                {
                    description = ""
                }

                if ( !cursor.isNull( cursor.getColumnIndex(KEY_ROW_IMAGE) ) )
                {
                    imgeHref = cursor.getString(cursor.getColumnIndex(KEY_ROW_IMAGE))
                }
                else
                {
                    imgeHref = ""
                }

                rowList.add(Row( subtitle, description ,imgeHref))
                Log.d("BINDU", "DATABASE subtitle "+subtitle)
                cursor.moveToNext()
            }
        }
        return cursor
    }


    fun viewData(): Cursor? {
        val rowList = ArrayList<Row>()
        var subtitle: String
        var description: String
        var imgeHref: String
        val db = this.readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT * FROM $ROW_TABLE ", null)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                if ( !cursor.isNull( cursor.getColumnIndex(KEY_ROW_TITLE) ) )
                {
                    subtitle = cursor.getString(cursor.getColumnIndex(KEY_ROW_TITLE))
                }
                else
                {
                    subtitle = ""
                }

                if ( !cursor.isNull( cursor.getColumnIndex(KEY_ROW_DESCRIPTION) ) )
                {
                    description = cursor.getString(cursor.getColumnIndex(KEY_ROW_DESCRIPTION))
                }
                else
                {
                    description = ""
                }

                if ( !cursor.isNull( cursor.getColumnIndex(KEY_ROW_IMAGE) ) )
                {
                    imgeHref = cursor.getString(cursor.getColumnIndex(KEY_ROW_IMAGE))
                }
                else
                {
                    imgeHref = ""
                }

                rowList.add(Row( subtitle, description ,imgeHref))
                Log.d("BINDU", "DATABASE View Data "+subtitle + description + imgeHref)
                cursor.moveToNext()
            }
        }
        return cursor
    }
}