package com.sumo.traffic;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Generator extends AppCompatActivity {
    EditText travellers, budget;
    TextView rbudget;
    Button Generate;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);


        travellers = (EditText) findViewById(R.id.travellers);

        budget = (EditText) findViewById(R.id.budget);


        rbudget = (TextView) findViewById(R.id.rbudget);
        Generate = (Button) findViewById(R.id.Generate);


        travellers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sTextFromET = travellers.getText().toString();
          /*      int travlrs = new Integer(sTextFromET).intValue();*/
                if (sTextFromET.equals("1"))
                {
                    rbudget.setText("₱2850");
                }
                else if (sTextFromET.equals(""))
                {
                    Toast.makeText(Generator.this, "Please enter number of travellers", Toast.LENGTH_SHORT).show();
                }
              else  if (sTextFromET.equals("2"))
                {
                    rbudget.setText("₱2850");
                }
                else    if (sTextFromET.equals("3"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("4"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("5"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("6"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("7"))
                {
                    rbudget.setText("₱2850");
                }
                else   if (sTextFromET.equals("8"))
                {
                    rbudget.setText("₱2850");
                }
               else  if (sTextFromET.equals("9"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("10"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("11"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("12"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("13"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("14"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("15"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("16"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("17"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("18"))
                {
                    rbudget.setText("₱2850");
                }
                else  if (sTextFromET.equals("19"))
                {
                    rbudget.setText("₱2850");
                }

                else  if (sTextFromET.equals("20"))
                {
                    rbudget.setText("₱2850");
                }

                else  if (sTextFromET.equals("21"))
                {
                    rbudget.setText("₱2850");
                }

                else  if (sTextFromET.equals("21"))
                {
                    rbudget.setText("₱2850");
                }

                else  if (sTextFromET.equals("22"))
                {
                    rbudget.setText("₱2850");
                }

                else  if (sTextFromET.equals("23"))
                {
                    rbudget.setText("₱2850");
                }

                else  if (sTextFromET.equals("24"))
                {
                    rbudget.setText("₱2850");
                }

                else  if (sTextFromET.equals("25"))
                {
                    rbudget.setText("₱2850");
                }





            }
        });

        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sTextFromET = travellers.getText().toString();
                int travlr = new Integer(sTextFromET).intValue();

                String budgetz = budget.getText().toString();
                int budget = new Integer(budgetz).intValue();

                if (travlr == 1) {

                    if (budget < 2500) {
                        Toast.makeText(Generator.this, "Budget Not Met", Toast.LENGTH_SHORT).show();
                    } else if ((budget >= 2850) && (budget <= 12500)) {
                        Toast.makeText(Generator.this, "Budget on the spot", Toast.LENGTH_SHORT).show();
                    } else if (budget > 15000) {
                        Toast.makeText(Generator.this, "TOO MUCH", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Generator Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.sumo.traffic/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Generator Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.sumo.traffic/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
