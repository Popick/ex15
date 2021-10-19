package com.example.ex11_2;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    EditText firstText,changeText;
    Intent si;
    Switch sW;

    public static boolean isNumeric(String str)
    {
        if (isEmpty(str)){
            return false;
        }
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

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