package com.tgsdco.fyp10.Util;

public class Util {
    //database related utils
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME= "fyp_db";
    //Crop Table
    public static final String TABLE_NAME= "crop";

    public static final String KEY_ID= "id";
    public static final String KEY_NAME="name";
    public static final String KEY_PLANTING_END_DAY="planting_end_day";
    public static final String KEY_PLANTING_START_MONTH="planting_start_month";
    public static final String KEY_PLANTING_END_MONTH="planting_end_month";
    public static final String KEY_GROWING_DAYS="growing_days";
    public static final String KEY_MIN_PLANTING_TEMP="min_planting_temp";
    public static final String KEY_PLANTING_START_DAY="planting_start_day";
    //User_Crops Table
    public static final String TABLE2_NAME= "user_crops";

    public static final String KEY2_ID= "id";
    public static final String KEY2_CROP_ID= "cropid";
    public static final String KEY2_DATE_PLANTED= "date_planted";
    //User Table
    public static final String TABLE3_NAME= "user";

    public static final String KEY3_ID= "id";
    public static final String KEY3_USER_CROPS_ID= "user_cropsid";
    public static final String KEY3_LOCATION= "location";
}
