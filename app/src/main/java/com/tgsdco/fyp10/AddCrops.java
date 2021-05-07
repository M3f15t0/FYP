package com.tgsdco.fyp10;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tgsdco.fyp10.Data.DbHelper;
import com.tgsdco.fyp10.Model.Crops;
import com.tgsdco.fyp10.Model.User_crops;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class AddCrops extends AppCompatActivity {

    private Spinner spinner1, spinner2, spinner3;
    private Button btnSubmit;
    private List<Crops> crops;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crops);
        spinner1= findViewById(R.id.spinner1);
        spinner2= findViewById(R.id.spinner2);
        btnSubmit = findViewById(R.id.btnSubmit);
        final EditText date_txt = findViewById(R.id.editTextDate);
        final TextView when_planted_txt = findViewById(R.id.when_planted_txt);
        DbHelper db = new DbHelper(getApplicationContext());
        this.crops = db.getAllCrops();






        loadSpinnerData();


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = spinner2.getSelectedItem().toString();
                Log.d("TAG", "onItemSelected: "  + itemSelected);
                if (itemSelected.equals("Yes")){
                    date_txt.setVisibility(View.VISIBLE);
                    when_planted_txt.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                String cropSelected = spinner1.getSelectedItem().toString();
//                Log.d("TAG", "onItemSelected: " + cropSelected);
//                String itemSelected = spinner2.getSelectedItem().toString();
//                Log.d("TAG", "onItemSelected: "+ itemSelected);
                String dateSelected = date_txt.getText().toString();

                DbHelper db = new DbHelper(getApplicationContext());
                Crops selectedCrop = crops.get(spinner1.getSelectedItemPosition());
                User_crops userCrops = new User_crops();


                userCrops.setCropid(selectedCrop.getId());
                try {

                    userCrops.setDate_planted(new SimpleDateFormat("yyyy-MM-dd").parse(dateSelected));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                db.addUserCrops(userCrops);
                Log.d("TAG", "onClick: "+ userCrops.getCropid()+ ","+userCrops.getDate_planted());


            }

        });

    }

    private void loadSpinnerData() {

//
        ArrayAdapter<Crops> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,this.crops);
        spinner1.setAdapter(adapter);


    }
}
