package com.example.ex11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnCreateContextMenuListener {
    Intent gi;
    double first, change, snval;
    boolean isGeo;
    double[] list = new double[20];
    String[] strList = new String[20];
    ListView lV;
    TextView x1, d, n, sn;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        lV = (ListView) findViewById(R.id.lv);
        x1 = (TextView) findViewById(R.id.firstText);
        d = (TextView) findViewById(R.id.dText);
        n = (TextView) findViewById(R.id.nText);
        gi = getIntent();
        first = gi.getDoubleExtra("first", 1);
        change = gi.getDoubleExtra("change", 1);
        isGeo = gi.getBooleanExtra("switch1", false);


        x1.setText("x1 = " + first);
        d.setText("d = " + change);

        if (isGeo) {
            calcGeo();
        } else {
            calcArith();
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, strList);
        lV.setAdapter(adp);
        lV.setOnItemLongClickListener(this);
        lV.setOnCreateContextMenuListener(this);


    }


    public void calcArith() {
        for (int i = 1; i < 21; i++) {
            list[i - 1] = first + ((i - 1) * change);
            strList[i - 1] = Double.toString(first + ((i - 1) * change));
        }
    }

    public void calcGeo() {
        for (int i = 1; i < 21; i++) {
            list[i - 1] = (first * Math.pow((change), (i - 1)));
            strList[i - 1] = Double.toString(first * Math.pow((change), (i - 1)));
        }
    }

    public void back(View view) {
        finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;


        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Get information about:");
        menu.add("index");
        menu.add("sum");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String oper=item.getTitle().toString();
        if (oper.equals("sum")) {
            if (isGeo) {
                snval = (int) (first * ((Math.pow(change, pos + 1) - 1) / (change - 1)));
                n.setText("sum:\n" + (snval));
            } else {
                snval = ((2 * first + (pos) * change) / 2) * (pos + 1);
                n.setText("sum:\n" + (snval));

            }
            return true;
        }
        else if (oper.equals("index")) {
            if (isGeo) {
                n.setText("index:\n" + (pos + 1));
            } else {
                n.setText("index:\n" + (pos + 1));
            }

            return true;
        }
        return super.onContextItemSelected(item);
    }

}