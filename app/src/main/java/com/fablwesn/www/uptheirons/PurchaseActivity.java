package com.fablwesn.www.uptheirons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * displays a spinner containing every available album to select from, after clicking purchase, displays a Toast
 */

public class PurchaseActivity extends AppCompatActivity {

    //globals
    Spinner _albumSpinner;
    Button _orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        //fill the Spinner and set the listener for the Button
        if (_albumSpinner == null)
            fillSpinner();
        if (_orderBtn == null)
            setOrderButtonListener();
    }

    /**
     * fills the spinner using an array
     */
    private void fillSpinner(){
        _albumSpinner = (Spinner) findViewById(R.id.purchase_act_spinner);

        // Create an ArrayAdapter using the string array and a default _spinGender layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.purchase_act_spinner_albums, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the _spinGender
        _albumSpinner.setAdapter(adapter);
    }

    /**
     * sets onClickListener for the Purchase Button
     */
    private void setOrderButtonListener(){
        _orderBtn = (Button) findViewById(R.id.purchase_act_order_btn);

        _orderBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),R.string.purchase_act_toast,Toast.LENGTH_LONG).show();
            }
        });
    }
}
