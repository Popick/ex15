package com.example.ex11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener{
    Intent gi;
    int first,change,snval;
    boolean isGeo;
    Integer[] list = new Integer[20];
    ListView lV;
    TextView x1,d,n,sn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        lV = (ListView) findViewById(R.id.lv);
        x1 = (TextView) findViewById(R.id.firstText);
        d = (TextView) findViewById(R.id.dText);
        n = (TextView) findViewById(R.id.nText);
        sn = (TextView) findViewById(R.id.snText);
        gi = getIntent();
        first = gi.getIntExtra("first",1);
        change = gi.getIntExtra("change",1);
        isGeo = gi.getBooleanExtra("switch1",false);

        x1.setText("x1 = "+first);
        d.setText("d = "+change);

        if (isGeo){
            calcGeo();
        }else{
            calcArith();
        }
        ArrayAdapter<Integer> adp = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item,list);
        lV.setAdapter(adp);
        lV.setOnItemClickListener(this);
    }


    public void calcArith(){
        for(int i = 1; i < 21;i++){
            list[i-1] = first+((i-1)*change);
        }
    }
    public void calcGeo(){
        for(int i = 1; i < 21;i++){
            list[i-1] = (int)(first*Math.pow((change),(i-1)));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(isGeo){
            snval = (int)(first*((Math.pow(change,position+1)-1)/(change-1)));
            n.setText("n = "+(position+1));
            sn.setText("Sn = "+(snval));
        }
        else{
            snval = ((2 * first + (position) * change) / 2) * (position+1);
            n.setText("n = "+(position+1));
            sn.setText("Sn = "+(snval));
        }

    }
}