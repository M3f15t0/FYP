package com.tgsdco.fyp10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Spinner;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.tgsdco.fyp10.Data.DbHelper;
import com.tgsdco.fyp10.Model.Crops;
import com.tgsdco.fyp10.Model.User_crops;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
     ExtendedFloatingActionButton fab;
    ListView cropList;
    RequestQueue queue;
    String url = "https://api.openweathermap.org/data/2.5/onecall?lat=52.4066&lon=-1.5122&units=metric&exclude=current,hourly,minutely&appid=561c265b627f97559fd11bb490c463f7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cropList = findViewById(R.id.cropsList_lv);
        fab = findViewById(R.id.main_fab);


        cropList.setOnItemClickListener(this);
        fab.setOnClickListener(this);

        DbHelper db = new DbHelper(this);



        //get the day of the month and the month int

        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        Log.d("TAG", "onCreate: " + dayOfMonth +" " + month);

        
//        loadListViewData();

        //API call
        getMinTemp();






//        Crops tomato = new Crops();
//
//        tomato.setName("Tomato");
//        tomato.setPlanting_start_day(15);
//        tomato.setPlanting_start_month(4);
//        tomato.setPlanting_end_day(30);
//        tomato.setPlanting_end_month(5);
//        tomato.setMin_planting_temp(10);
//        tomato.setGrowing_days(90);
//
//        Crops onion = new Crops();
//        onion.setGrowing_days(50);
//        onion.setName("onion");
//        onion.setMin_planting_temp(0);
//        onion.setPlanting_end_day(4);
//        onion.setPlanting_end_month(4);
//        onion.setPlanting_start_day(3);
//        onion.setPlanting_start_month(3);
//
//        db.addCrop(tomato);
//        db.addCrop(onion);


//
//        User_crops c = db.getUserCrop(1);
//        Log.d("TAG2", "onCreate: " + c.getCropName());
//        c.setGrowing_days(100);
//        db.deleteCrop(c);
//         loadListViewData ();

        List<User_crops> userCrops = db.getAllUserCrops();

        ArrayAdapter<User_crops> cropsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userCrops);
        cropList.setAdapter(cropsAdapter);





        for (User_crops userCrop : userCrops) {
            int cropId = userCrop.getCropid();
            Crops crop = db.getCrop(cropId);
            userCrop.setCrop(crop);
            Log.d("TAG1", "onCreate: " + crop + " " + userCrop);


        }



//        int updatedRow = db.updateCrop(c);
//        Log.d("TAG8", "onCreate: " + c.getGrowing_days());


//        for (Crops crops : cropsList) {
//            Log.d("TAG3", "onCreate: " + crops.getGrowing_days());
//        }






    }

    private void getMinTemp() {
        queue= Volley.newRequestQueue(this);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray jsonArray = (JSONArray) response.get("daily");
//                            Log.d("TAG6", "onResponse: " + jsonArray);
                            Double minTemperature = 100.12;
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //get data from nested object
                                Double minTemp = jsonObject.getJSONObject("temp").getDouble("min");

                                Log.d("TAG8", "onResponse: " + minTemp);
                                if (minTemp < minTemperature) {
                                    minTemperature = minTemp;
                                }
                            }

                            Log.d("TAG", "onResponse: " + minTemperature);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG2", "onErrorResponse: " + "Failed");

            }
        });
        queue.add(jsonObjectRequest);
        //Log.d("TAG10", "onResponse: " +minTemperature);

    }

    private void loadListViewData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String plants = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Clicked"+ plants, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddCrops.class);
        startActivity(intent);


    }
}
