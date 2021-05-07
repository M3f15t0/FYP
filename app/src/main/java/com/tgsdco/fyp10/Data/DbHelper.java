package com.tgsdco.fyp10.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tgsdco.fyp10.Model.Crops;
import com.tgsdco.fyp10.Model.User_crops;
import com.tgsdco.fyp10.R;
import com.tgsdco.fyp10.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CROP_TABLE = "CREATE TABLE Crop (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name varchar(30) NOT NULL UNIQUE, planting_start_month tinyint(3) NOT NULL, planting_start_day tinyint(3) NOT NULL, planting_end_month tinyint(3) NOT NULL, planting_end_day tinyint(3) NOT NULL, growing_days integer(10) NOT NULL, min_planting_temp tinyint(3) NOT NULL);";

        String CREATE_USER_CROPS_TABLE = "CREATE TABLE User_Crops (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, cropid integer(10) NOT NULL, date_planted date)";
        String CREATE_USER_TABLE= "CREATE TABLE User (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, User_Cropsid integer(200) NOT NULL, location varchar(8) NOT NULL)";
        try {
            db.execSQL(CREATE_CROP_TABLE);
            db.execSQL(CREATE_USER_CROPS_TABLE);
            db.execSQL(CREATE_USER_TABLE);

        }
        catch (Exception e) {
            System.out.println("Oops!");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME, Util.TABLE_NAME});
        db.execSQL(DROP_TABLE,new String[] {Util.DATABASE_NAME, Util.TABLE2_NAME});
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME, Util.TABLE3_NAME});

        //create a table again
        onCreate(db);

    }
    //CRUD

    //Add Crop to crop table
    public void addCrop(Crops crops){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, crops.getName());
        values.put(Util.KEY_GROWING_DAYS, crops.getGrowing_days());
        values.put(Util.KEY_MIN_PLANTING_TEMP, crops.getMin_planting_temp());
        values.put(Util.KEY_PLANTING_END_MONTH,crops.getPlanting_end_month());
        values.put(Util.KEY_PLANTING_END_DAY,crops.getPlanting_end_day());
        values.put(Util.KEY_PLANTING_START_DAY,crops.getPlanting_start_day());
        values.put(Util.KEY_PLANTING_START_MONTH,crops.getPlanting_start_month());

        //insert to row
        db.insert(Util.TABLE_NAME,null, values);
        db.close();
    }

    //get a crop from crop table
    public Crops getCrop(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_GROWING_DAYS,Util.KEY_MIN_PLANTING_TEMP,
                Util.KEY_NAME,Util.KEY_PLANTING_END_DAY,Util.KEY_PLANTING_END_MONTH,Util.KEY_PLANTING_START_DAY,Util.KEY_PLANTING_START_MONTH},
                Util.KEY_ID +"=?", new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        Crops crops = new Crops();
        crops.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
        crops.setName(cursor.getString(cursor.getColumnIndex("name")));
        crops.setPlanting_start_day(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_start_day"))));
        crops.setPlanting_start_month(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_start_month"))));
        crops.setPlanting_end_day(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_end_day"))));
        crops.setPlanting_end_month(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_end_month"))));
        crops.setGrowing_days(Integer.parseInt(cursor.getString(cursor.getColumnIndex("growing_days"))));
        crops.setMin_planting_temp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("min_planting_temp"))));
        return crops;



    }

    //get all crops crop table

    public List<Crops> getAllCrops(){
        List<Crops> cropsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //select all contacts
        String selectAll= "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //loop through data
        if(cursor.moveToFirst()){
            do {
                Crops crops = new Crops();
                crops.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                crops.setName(cursor.getString(cursor.getColumnIndex("name")));
                crops.setPlanting_start_day(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_start_day"))));
                crops.setPlanting_start_month(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_start_month"))));
                crops.setPlanting_end_day(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_end_day"))));
                crops.setPlanting_end_month(Integer.parseInt(cursor.getString(cursor.getColumnIndex("planting_end_month"))));
                crops.setGrowing_days(Integer.parseInt(cursor.getString(cursor.getColumnIndex("growing_days"))));
                crops.setMin_planting_temp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("min_planting_temp"))));

                //add crops to list
                cropsList.add(crops);


            }while (cursor.moveToNext());

        }
        return  cropsList;

    }
    //update record crop Table
    public int updateCrop (Crops crops) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, crops.getName());
        values.put(Util.KEY_GROWING_DAYS, crops.getGrowing_days());
        values.put(Util.KEY_MIN_PLANTING_TEMP, crops.getMin_planting_temp());
        values.put(Util.KEY_PLANTING_START_DAY, crops.getPlanting_start_day());
        values.put(Util.KEY_PLANTING_START_MONTH, crops.getPlanting_start_month());
        values.put(Util.KEY_PLANTING_END_DAY, crops.getPlanting_end_day());
        values.put(Util.KEY_PLANTING_END_MONTH, crops.getPlanting_end_month());
        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?", new String[]{String.valueOf(crops.getId())});
    }
    //delete single crop

    public void deleteCrop(Crops crops) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(crops.getId())});
        db.close();
    }



    public void addUserCrops(User_crops user_crops){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY2_CROP_ID, user_crops.getCropid());
        //possible problem ****
        values.put(Util.KEY2_DATE_PLANTED, String.valueOf(user_crops.getDate_planted()));


        //insert to row
        db.insert(Util.TABLE2_NAME,null, values);
        db.close();
    }

    public  User_crops getUserCrop(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT user_crops.*, crop.name AS cropName FROM user_crops JOIN crop ON crop.id= user_crops.cropid WHERE user_crops.id = " + id;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor!=null)
            cursor.moveToFirst();

        User_crops user_crops = new User_crops();
        user_crops.setCropName(cursor.getString(cursor.getColumnIndex("cropName")));
        user_crops.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
        user_crops.setCropid(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cropid"))));
        try {
            user_crops.setDate_planted(new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                    Locale.ENGLISH).parse(cursor.getString(cursor.getColumnIndex("date_planted"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return user_crops;

    }
    //TODO display user_crops on the main screen
    public List<User_crops> getAllUserCrops(){
        List<User_crops> userCropsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //select all contacts
        String selectAll= "SELECT * FROM " + Util.TABLE2_NAME + "JOIN crop ON crop.id= user_crops.cropid";
        Cursor cursor = db.rawQuery("SELECT user_crops.*, crop.name AS cropName FROM user_crops JOIN crop ON crop.id= user_crops.cropid", null);

        //loop through data
        if(cursor.moveToFirst()){
            do {
                User_crops userCrops = new User_crops();
                userCrops.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                userCrops.setCropid(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cropid"))));
                userCrops.setCropName(cursor.getString(cursor.getColumnIndex("cropName")));
                try {
                    userCrops.setDate_planted(new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                            Locale.ENGLISH).parse(cursor.getString(cursor.getColumnIndex("date_planted"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                //add crops to list
                userCropsList.add(userCrops);


            }while (cursor.moveToNext());

        }
        return  userCropsList;

    }

    public void deleteUserCrop(User_crops user_crops) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE2_NAME, Util.KEY2_ID + "=?",
                new String[]{String.valueOf(user_crops.getId())});
        db.close();
    }



}
