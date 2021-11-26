package com.example.ex11_2;


import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {
    /**
     * @author        Etay Sabag
     * @version       1.0
     * @since         18/10/2021
     *
     * MainActivity class, the start screen of the app that receive the
     * input and sends it to the second screen.
     */

    EditText firstText,changeText;
    Intent si;
    Switch sW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstText = (EditText) findViewById(R.id.item1);
        changeText = (EditText) findViewById(R.id.change);
        sW = (Switch) findViewById(R.id.switch1);

        si = new Intent(this,MainActivity2.class);

    }


    public void go(View view) {

        /**
         * Go function, sends you to the second screen with the input of the user and checking if the
         * input is valid.
         * <p> (<----- albert, what is the purpose of that?)
         *
         * @param     firstText  Description:    the object that linked to the input box of the
         *                                       first item the user wants to enter in the series.
         *
         * @param     changeText Description:    the object that linked to the input box of the
         *                                       difference or the multiplier in the series.
         *
         * @param     si         Description:    the intent.
         *
         * @return               Description:    None.
         */

        if (isEmpty(firstText.getText().toString())){
            firstText.setError("Invalid input");
        }else{
            if (isEmpty(changeText.getText().toString())){
                changeText.setError("Invalid input");
            }else{
                si.putExtra("first",Double.parseDouble(firstText.getText().toString()));
                si.putExtra("change",Double.parseDouble(changeText.getText().toString()));
                si.putExtra("switch1",sW.isChecked());
                startActivityForResult(si, 1);
            }
        }
    }


}