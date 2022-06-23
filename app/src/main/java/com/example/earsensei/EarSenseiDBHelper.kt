package com.example.earsensei

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.earsensei.dbmodels.*


import kotlin.collections.ArrayList

class EarSenseiDBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        const val DB_VERSION  = 4
        const val DB_NAME  = "EarSensei.db"
    }


    object IntervalsTable{
        const val TABLE_NAME = "intervals_table"
        const val KEY_ID = "_id"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }

    object ChordsTable{
        const val TABLE_NAME = "chords_table"
        const val KEY_ID = "_id"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }

    object ScalesTable{
        const val TABLE_NAME = "scales_table"
        const val KEY_ID = "_id"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }

    object PerfectPitchTable{
        const val TABLE_NAME = "perfect_pitch_table"
        const val KEY_ID = "_id"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }


    object BasicComand{
        const val SQL_CREATE_INTERVALS_TABLE =
            "CREATE TABLE ${IntervalsTable.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${IntervalsTable.BASE_NOTE_COL} TEXT," +
                    "${IntervalsTable.CORRECT_ANSWER_COL}  TEXT, " +
                    "${IntervalsTable.USER_ANSWER_COL}  TEXT, " +
                    "${IntervalsTable.DATE_COL} INTEGER);"
        const val SQL_DELETE_INTERVALS_TABLE = "DROP TABLE IF EXISTS ${IntervalsTable.TABLE_NAME}"

        const val SQL_CREATE_CHORDS_TABLE =
            "CREATE TABLE ${ChordsTable.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${ChordsTable.BASE_NOTE_COL} TEXT," +
                    "${ChordsTable.CORRECT_ANSWER_COL} TEXT, " +
                    "${ChordsTable.USER_ANSWER_COL} TEXT, " +
                    "${ChordsTable.DATE_COL} INTEGER);"
        const val SQL_DELETE_CHORDS_TABLE = "DROP TABLE IF EXISTS ${ChordsTable.TABLE_NAME}"

        const val SQL_CREATE_SCALES_TABLE =
            "CREATE TABLE ${ScalesTable.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${ScalesTable.BASE_NOTE_COL} TEXT," +
                    "${ScalesTable.CORRECT_ANSWER_COL} TEXT, " +
                    "${ScalesTable.USER_ANSWER_COL} TEXT, " +
                    "${ScalesTable.DATE_COL} INTEGER);"
        const val SQL_DELETE_SCALES_TABLE = "DROP TABLE IF EXISTS ${ScalesTable.TABLE_NAME}"

        const val SQL_CREATE_PERFECT_PITCH_TABLE =
            "CREATE TABLE ${PerfectPitchTable.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${PerfectPitchTable.BASE_NOTE_COL} TEXT," +
                    "${PerfectPitchTable.CORRECT_ANSWER_COL} TEXT, " +
                    "${PerfectPitchTable.USER_ANSWER_COL} TEXT, " +
                    "${PerfectPitchTable.DATE_COL} INTEGER);"
        const val SQL_DELETE_PERFECT_PITCH_TABLE = "DROP TABLE IF EXISTS ${PerfectPitchTable.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicComand.SQL_CREATE_INTERVALS_TABLE)
        db?.execSQL(BasicComand.SQL_CREATE_CHORDS_TABLE)
        db?.execSQL(BasicComand.SQL_CREATE_SCALES_TABLE)
        db?.execSQL(BasicComand.SQL_CREATE_PERFECT_PITCH_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicComand.SQL_DELETE_INTERVALS_TABLE)
        db?.execSQL(BasicComand.SQL_DELETE_CHORDS_TABLE)
        db?.execSQL(BasicComand.SQL_DELETE_SCALES_TABLE)
        db?.execSQL(BasicComand.SQL_DELETE_PERFECT_PITCH_TABLE)
        onCreate(db)
    }


    fun createIntervalsContentValues(baseNote: String, correctAnswer: String, userAnswer: String, date: Long) : ContentValues{
        val contentValues : ContentValues = ContentValues()
        contentValues.put(IntervalsTable.BASE_NOTE_COL, baseNote)
        contentValues.put(IntervalsTable.CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(IntervalsTable.USER_ANSWER_COL, userAnswer)
        contentValues.put(IntervalsTable.DATE_COL, date)
        return contentValues
    }

    fun createChordsContentValues(baseNote: String, correctAnswer: String, userAnswer: String, date: Long) : ContentValues{
        val contentValues : ContentValues = ContentValues()
        contentValues.put(ChordsTable.BASE_NOTE_COL, baseNote)
        contentValues.put(ChordsTable.CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(ChordsTable.USER_ANSWER_COL, userAnswer)
        contentValues.put(ChordsTable.DATE_COL, date)
        return contentValues
    }

    fun createScalesContentValues(baseNote: String, correctAnswer: String, userAnswer: String, date: Long) : ContentValues{
        val contentValues : ContentValues = ContentValues()
        contentValues.put(ScalesTable.BASE_NOTE_COL, baseNote)
        contentValues.put(ScalesTable.CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(ScalesTable.USER_ANSWER_COL, userAnswer)
        contentValues.put(ScalesTable.DATE_COL, date)
        return contentValues
    }

    fun createPerfectPitchContentValues(baseNote: String, correctAnswer: String, userAnswer: String, date: Long) : ContentValues{
        val contentValues : ContentValues = ContentValues()
        contentValues.put(PerfectPitchTable.BASE_NOTE_COL, baseNote)
        contentValues.put(PerfectPitchTable.CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(PerfectPitchTable.USER_ANSWER_COL, userAnswer)
        contentValues.put(PerfectPitchTable.DATE_COL, date)
        return contentValues
    }



    fun addIntervalsContentValues(contentValues : ContentValues) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val insert : Long = db.insert(IntervalsTable.TABLE_NAME, null, contentValues)
        db.close()
        return insert != -1L
    }

    fun addChordsContentValues(contentValues : ContentValues) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val insert : Long = db.insert(ChordsTable.TABLE_NAME, null, contentValues)
        db.close()
        return insert != -1L
    }

    fun addScalesContentValues(contentValues : ContentValues) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val insert : Long = db.insert(ScalesTable.TABLE_NAME, null, contentValues)
        db.close()
        return insert != -1L
    }

    fun addPerfectPitchContentValues(contentValues : ContentValues) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val insert : Long = db.insert(PerfectPitchTable.TABLE_NAME, null, contentValues)
        db.close()
        return insert != -1L
    }


    @SuppressLint("Range")
    fun readAllIntervalsData() : ArrayList<IntervalModel>{
        val intervalModelList : ArrayList<IntervalModel> = ArrayList<IntervalModel>()
        val selectQuery : String = "SELECT * FROM " + IntervalsTable.TABLE_NAME
        val db : SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db?.rawQuery(selectQuery, null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var baseNote: String
        var correctAnswer : String
        var userAnswer: String
        var date: Int

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(IntervalsTable.KEY_ID))
                baseNote = cursor.getString(cursor.getColumnIndex(IntervalsTable.BASE_NOTE_COL))
                correctAnswer = cursor.getString(cursor.getColumnIndex(IntervalsTable.CORRECT_ANSWER_COL))
                userAnswer = cursor.getString(cursor.getColumnIndex(IntervalsTable.USER_ANSWER_COL))
                date = cursor.getInt(cursor.getColumnIndex(IntervalsTable.DATE_COL))
                val intervalModel : IntervalModel = IntervalModel(id, baseNote, correctAnswer, userAnswer, date)
                intervalModelList.add(intervalModel)
            } while (cursor.moveToNext())
        }
        return intervalModelList
    }

    @SuppressLint("Range")
    fun readAllChordsData() : ArrayList<ChordModel>{
        val chordModelList : ArrayList<ChordModel> = ArrayList<ChordModel>()
        val selectQuery : String = "SELECT * FROM " + ChordsTable.TABLE_NAME
        val db : SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db?.rawQuery(selectQuery, null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var baseNote: String
        var correctAnswer : String
        var userAnswer: String
        var date: Int

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(ChordsTable.KEY_ID))
                baseNote = cursor.getString(cursor.getColumnIndex(ChordsTable.BASE_NOTE_COL))
                correctAnswer = cursor.getString(cursor.getColumnIndex(ChordsTable.CORRECT_ANSWER_COL))
                userAnswer = cursor.getString(cursor.getColumnIndex(ChordsTable.USER_ANSWER_COL))
                date = cursor.getInt(cursor.getColumnIndex(ChordsTable.DATE_COL))
                val chordModel : ChordModel = ChordModel(id, baseNote, correctAnswer, userAnswer, date)
                chordModelList.add(chordModel)
            } while (cursor.moveToNext())
        }
        return chordModelList
    }

    @SuppressLint("Range")
    fun readAllScalesData() : ArrayList<ScaleModel>{
        val scaleModelList : ArrayList<ScaleModel> = ArrayList<ScaleModel>()
        val selectQuery : String = "SELECT * FROM " + ScalesTable.TABLE_NAME
        val db : SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db?.rawQuery(selectQuery, null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var baseNote: String
        var correctAnswer : String
        var userAnswer: String
        var date: Int

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(ScalesTable.KEY_ID))
                baseNote = cursor.getString(cursor.getColumnIndex(ScalesTable.BASE_NOTE_COL))
                correctAnswer = cursor.getString(cursor.getColumnIndex(ScalesTable.CORRECT_ANSWER_COL))
                userAnswer = cursor.getString(cursor.getColumnIndex(ScalesTable.USER_ANSWER_COL))
                date = cursor.getInt(cursor.getColumnIndex(ScalesTable.DATE_COL))
                val scaleModel : ScaleModel = ScaleModel(id, baseNote, correctAnswer, userAnswer, date)
                scaleModelList.add(scaleModel)
            } while (cursor.moveToNext())
        }
        return scaleModelList
    }


    @SuppressLint("Range")
    fun readAllPerfectPitchData() : ArrayList<PerfectPitchModel>{
        val perfectPitchModelList : ArrayList<PerfectPitchModel> = ArrayList<PerfectPitchModel>()
        val selectQuery : String = "SELECT * FROM " + PerfectPitchTable.TABLE_NAME
        val db : SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db?.rawQuery(selectQuery, null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var baseNote: String
        var correctAnswer : String
        var userAnswer: String
        var date: Int

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(PerfectPitchTable.KEY_ID))
                baseNote = cursor.getString(cursor.getColumnIndex(PerfectPitchTable.BASE_NOTE_COL))
                correctAnswer = cursor.getString(cursor.getColumnIndex(PerfectPitchTable.CORRECT_ANSWER_COL))
                userAnswer = cursor.getString(cursor.getColumnIndex(PerfectPitchTable.USER_ANSWER_COL))
                date = cursor.getInt(cursor.getColumnIndex(PerfectPitchTable.DATE_COL))
                val perfectPitchModel : PerfectPitchModel = PerfectPitchModel(id, baseNote, correctAnswer, userAnswer, date)
                perfectPitchModelList.add(perfectPitchModel)
            } while (cursor.moveToNext())
        }
        return perfectPitchModelList
    }

}