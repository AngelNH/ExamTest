package com.iteso.examtest;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {
    Button btnAdd;
    boolean added;
    RadioButton radioPink;
    RadioButton radioBlack;
    RadioButton radioBrown;
    RadioButton radioGray;
    Button btnSmall;
    Button btnMedium;
    Button btnLarge;
    Button btnExtra;
    boolean btnSmallState;
    boolean btnMediumState;
    boolean btnLargeState;
    boolean btnExtraState;

    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.activity_main_coordinator);
        btnSmall = findViewById(R.id.button_small);
        btnMedium = findViewById(R.id.button_medium);
        btnLarge = findViewById(R.id.button_large);
        btnExtra = findViewById(R.id.button_extralarge);
        btnAdd = findViewById(R.id.button_add);
        radioBlack = findViewById(R.id.radio_black);
        radioBrown = findViewById(R.id.radio_brown);
        radioPink = findViewById(R.id.radio_pink);
        radioGray = findViewById(R.id.radio_gray);
        //Button to add cart.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!added) {
                    added = true;
                    btnAdd.setText(R.string.button_added_to_cart);
                    Snackbar.make(layout, R.string.added_to_cart_text, Snackbar.LENGTH_INDEFINITE).setAction(R.string.undo, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btnAdd.setText(R.string.button_add_to_cart);
                            added = false;
                        }
                    }).show();
                }
                else
                    Toast.makeText(ActivityMain.this,R.string.item_added,Toast.LENGTH_SHORT).show();
            }
        });

        selectBtn();

    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        int color = 0;
        int size = 0;
        int itemAdded = 0;
        if (radioGray.isChecked())
            color = R.id.radio_gray;
        else if (radioPink.isChecked())
            color = R.id.radio_pink;
        else if (radioBrown.isChecked())
            color = R.id.radio_brown;
        else if (radioBlack.isChecked())
            color = R.id.radio_black;

        if (btnExtraState)
            size = btnExtra.getId();
        else if (btnLargeState)
            size = btnExtra.getId();
        else if (btnMediumState)
            size = btnMedium.getId();
        else if (btnSmallState)
            size = btnSmall.getId();

        if (added)
            itemAdded = 1;
        else
            itemAdded = 0;
        outState.putInt("COLOR",color);
        outState.putInt("SIZE",size);
        outState.putInt("ADDED",itemAdded);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        int color = savedInstanceState.getInt("COLOR");
        int size = savedInstanceState.getInt("SIZE");
        int itemAdded = savedInstanceState.getInt("ADDED");

        activateButton(size);
        RadioButton r1 = findViewById(color);
        r1.setChecked(true);
        if (itemAdded == 0) {
            btnAdd.setText(R.string.button_add_to_cart);
            added = false;
        }else {
            btnAdd.setText(R.string.button_added_to_cart);
            added = true;
        }

    }

    public void onLike(View view){
        Toast.makeText(this,R.string.plus_like,Toast.LENGTH_SHORT).show();
    }

    public void onSizeConfig(View view){
        int btn = view.getId();
        activateButton(btn);
    }
    public void activateButton(int buttonNo){
        btnSmallState=false;
        btnMediumState=false;
        btnLargeState=false;
        btnExtraState=false;
        switch (buttonNo){
            case R.id.button_small:
                btnSmallState=true;
                break;
            case R.id.button_medium:
                btnMediumState=true;
                break;
            case R.id.button_large:
                btnLargeState=true;
                break;
            case R.id.button_extralarge:
                btnExtraState=true;
                break;
        }
        selectBtn();
    }

    public void selectBtn(){
        if (btnSmallState){
            btnSmall.setBackground(getDrawable(R.color.colorPrimary));
            btnSmall.setTextColor(getResources().getColor(R.color.white,null));
        }else {
            btnSmall.setBackground(getDrawable(R.drawable.button_img));
            btnSmall.setTextColor(getResources().getColor(R.color.black,null));
        }

        if (btnMediumState){
            btnMedium.setBackground(getDrawable(R.color.colorPrimary));
            btnMedium.setTextColor(getResources().getColor(R.color.white,null));
        }else {
            btnMedium.setBackground(getDrawable(R.drawable.button_img));
            btnMedium.setTextColor(getResources().getColor(R.color.black,null));
        }

        if (btnLargeState){
            btnLarge.setBackground(getDrawable(R.color.colorPrimary));
            btnLarge.setTextColor(getResources().getColor(R.color.white,null));
        }else {
            btnLarge.setBackground(getDrawable(R.drawable.button_img));
            btnLarge.setTextColor(getResources().getColor(R.color.black,null));
        }

        if (btnExtraState){
            btnExtra.setBackground(getDrawable(R.color.colorPrimary));
            btnExtra.setTextColor(getResources().getColor(R.color.white,null));
        }else {
            btnExtra.setBackground(getDrawable(R.drawable.button_img));
            btnExtra.setTextColor(getResources().getColor(R.color.black,null));
        }
    }
}
