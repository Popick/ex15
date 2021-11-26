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
    /**
     * @author        Etay Sabag
     * @version       2.0
     * @since         18/10/2021
     *
     * MainActivity2 class, the second screen, it shows you the series based of your input on the
     * last screen.
     */


    Intent gi;
    double first, change, snval;
    boolean is_geometric;
    double[] list = new double[20];
    String[] strList = new String[20];
    ListView lV;
    TextView x1, d, information_text, sn;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        lV = (ListView) findViewById(R.id.lv);
        x1 = (TextView) findViewById(R.id.firstText);
        d = (TextView) findViewById(R.id.dText);
        information_text = (TextView) findViewById(R.id.nText);
        gi = getIntent();
        first = gi.getDoubleExtra("first", 1);
        change = gi.getDoubleExtra("change", 1);
        is_geometric = gi.getBooleanExtra("switch1", false);


        x1.setText("x1 = " + first);
        d.setText("d = " + change);

        if (is_geometric) {
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
        /**
         * Calculates the Arithmetic series.
         * <p> (<----- albert, what is the purpose of that?)
         *
         * @param     list      Description:    The list of the Arithmetic as doubles
         *
         * @param     strList   Description:    Same list but aas string
         *
         * @return   None.
         */
        for (int i = 1; i < 21; i++) {
            list[i - 1] = first + ((i - 1) * change);
            strList[i - 1] = Double.toString(first + ((i - 1) * change));
        }
    }

    public void calcGeo() {
        /**
         * Calculates the Geometric series.
         * <p>
         *
         * @param     list      Description:    The list of the Geometric as doubles
         *
         * @param     strList   Description:    Same list but aas string
         *
         * @return  None.
         */
        for (int i = 1; i < 21; i++) {
            list[i - 1] = (first * Math.pow((change), (i - 1)));
            strList[i - 1] = Double.toString(first * Math.pow((change), (i - 1)));
        }
    }

    public void back(View view) {
        /**
         * Sends you back to the first screen.
         * <p>
         *
         * @return  None.
         */
        finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        /**
         * Saves the position of the clicked item.
         * <p>
         *
         * @param   pos   Description:    the position of the selected item in the list
         *
         * @return  Description:   False(by default).
         */

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
        /**
         * Shows the selected information of the clicked item.
         * <p>
         *
         * @param   is_geometric Description:    boolean that is true if the series is geometric
         *                                       if arithmetic than it will be equals false.
         *
         * @param   first        Description:    the object that linked to the input box of the
         *                                       first item the user wants to enter in the series.
         *
         * @param   change       Description:    the object that linked to the input box of the
         *                                       difference or the multiplier in the series.
         *
         * @param   pos          Description:    the position of the selected item in the list
         *
         *
         * @return  None.
         */

        String title_of_item=item.getTitle().toString();
        if (title_of_item.equals("sum")) {
            if (is_geometric) {
                information_text.setText("sum:\n" + (first * ((Math.pow(change, pos + 1) - 1) / (change - 1))));
            } else {
                information_text.setText("sum:\n" + ((2 * first + (pos) * change) / 2) * (pos + 1));

            }
            return true;
        }
        else if (title_of_item.equals("index")) {
            information_text.setText("index:\n" + (pos + 1));
            return true;
        }
        return super.onContextItemSelected(item);
    }

}