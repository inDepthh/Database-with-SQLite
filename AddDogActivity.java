package com.example.it_lab_local.petlabdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDogActivity extends AppCompatActivity {

    Button addDog;
    EditText editTextName;
    EditText editTextAge;
    EditText editTextBreed;
    int age;
    String name;
    String breed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        editTextAge = findViewById(R.id.editTextAge);
        editTextName = findViewById(R.id.editTextName);
        editTextBreed = findViewById(R.id.editTextBreed);

        addDog = findViewById(R.id.btnAdd);
        addDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = Integer.parseInt(editTextAge.getText().toString());
                name = editTextName.getText().toString();
                breed = editTextBreed.getText().toString();

                DatabaseConnector dc = new DatabaseConnector(AddDogActivity.this);
                dc.open();
                dc.insertDog(breed, name, age);
                finish();   // go back to previous activity
            }
        });
    }
}
