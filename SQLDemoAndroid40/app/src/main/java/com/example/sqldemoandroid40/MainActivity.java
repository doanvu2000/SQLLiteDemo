package com.example.sqldemoandroid40;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqldemoandroid40.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SQLHelper sqlHelper;
    List<Foods> foods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding  = DataBindingUtil.setContentView( this,R.layout.activity_main );
        sqlHelper = new SQLHelper( this );
        foods = sqlHelper.getAllFoods();
        binding.btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etName.getText().toString();
                float price = Float.parseFloat( binding.etPrice.getText().toString() );
                int number = Integer.parseInt( binding.etNumber.getText().toString() );
                String description = binding.etDescription.getText().toString();
                Foods foods = new Foods( name,price,number,description );
                sqlHelper.insertFoods( foods );
                Toast.makeText( getBaseContext(),"Thêm thành công",Toast.LENGTH_LONG ).show();
                clearData();
            }
        } );
        binding.btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etName.getText().toString();
                float price = Float.parseFloat( binding.etPrice.getText().toString() );
                int number = Integer.parseInt( binding.etNumber.getText().toString() );
                String description = binding.etDescription.getText().toString();
                Foods foods = new Foods( name,price,number,description );
                sqlHelper.updateFoods("2", foods );
                Toast.makeText( getBaseContext(),"Update thành công",Toast.LENGTH_LONG ).show();
                clearData();
            }
        } );
        binding.btnDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean kt = sqlHelper.deleteFoods( "1" );
                Toast.makeText( getBaseContext(),"Xoas thành công",Toast.LENGTH_LONG ).show();

            }
        } );
    }
    private void clearData(){
        binding.etName.setText( "" );
        binding.etPrice.setText( "" );
        binding.etNumber.setText( "" );
        binding.etDescription.setText( "" );
    }
}