package com.example.earsensei

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.earsensei.EarSenseiDBHelper.TableInfo.DB_NAME
import com.example.earsensei.EarSenseiDBHelper.TableInfo.DB_VERSION
import java.util.*

class EarSenseiDBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    object TableInfo: BaseColumns{
        const val DB_NAME  = "EarSensei.db"
        const val DB_VERSION  = 1
        const val TABLE_NAME = "test_table"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }

    object BasicComand{
        const val SQL_CREATE_TABLE =
                "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${TableInfo.BASE_NOTE_COL} TEXT," +
                "${TableInfo.CORRECT_ANSWER_COL} TEXT, " +
                "${TableInfo.USER_ANSWER_COL} TEXT, " +
                "${TableInfo.DATE_COL} INTEGER);"
        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicComand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicComand.SQL_DELETE_TABLE)
        onCreate(db)
    }

    fun createContentValues(baseNote: String, correctAnswer: String, userAnswer: String, date: Int) : ContentValues{
        val contentValues : ContentValues = ContentValues()
        contentValues.put(TableInfo.BASE_NOTE_COL, baseNote)
        contentValues.put(TableInfo.CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(TableInfo.USER_ANSWER_COL, userAnswer)
        contentValues.put(TableInfo.DATE_COL, date)
        return contentValues
    }

    fun addContentValues(contentValues : ContentValues) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val insert : Long = db.insert(TableInfo.TABLE_NAME, null, contentValues)
        return insert != -1L

    }

}