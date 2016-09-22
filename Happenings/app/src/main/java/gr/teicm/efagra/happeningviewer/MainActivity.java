package gr.teicm.efagra.happeningviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import gr.teicm.efagra.happeningviewer.classes.DataStore;

public class MainActivity extends AppCompatActivity {

    EditText filterEditFromDate;
    EditText filterEditToDate;
    Spinner spinnerCategory;
    EditText filterEditArtist;
    EditText filterEditRegion;
    Spinner spinnerCountry;
    Button buttonSearch;

    private void findViews() {
        filterEditFromDate = (EditText) findViewById(R.id.filterEditFromDate);
        filterEditToDate = (EditText) findViewById(R.id.filterEditToDate);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        filterEditArtist = (EditText) findViewById(R.id.filterEditArtist);
        filterEditRegion = (EditText) findViewById(R.id.filterEditRegion);
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        DataStore.Init(getApplicationContext());

        //set-up an ArrayAdapter of CharSequences and connect it to the Model (categories.xml)
        ArrayAdapter<CharSequence> genresAdapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        genresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Connect the ArrayAdapter to the spinner
        spinnerCategory.setAdapter(genresAdapter);

        //set-up an ArrayAdapter of CharSequences and connect it to the Model (countries.xml)
        ArrayAdapter<CharSequence> countriesAdapter = ArrayAdapter.createFromResource(this, R.array.countries_array, android.R.layout.simple_spinner_item);
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Connect the ArrayAdapter to the spinner
        spinnerCountry.setAdapter(countriesAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //collect user input
                String fromDate = filterEditFromDate.getText().toString();
                String toDate = filterEditToDate.getText().toString();
                String category = (String) spinnerCategory.getSelectedItem();
                int categoryID = spinnerCategory.getSelectedItemPosition();
                String artist = filterEditArtist.getText().toString();
                String area = filterEditRegion.getText().toString();
                String country = (String) spinnerCountry.getSelectedItem();
                int countryID = spinnerCountry.getSelectedItemPosition();

                //String message = String.format("from: %s, to: %s, category: %s, categoryid: %d, artist: %s, region: %s, country: %s, countryid: %d", fromDate, toDate, category, categoryID, artist, area, country, countryID);
                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                //Show ListActivity
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(DataStore.KEY_FROMDATE,fromDate);
                intent.putExtra(DataStore.KEY_TODATE,toDate);
                intent.putExtra(DataStore.KEY_CATEGORY,category);
                intent.putExtra(DataStore.KEY_CATEGORYID,categoryID);
                intent.putExtra(DataStore.KEY_ARTIST,artist);
                intent.putExtra(DataStore.KEY_REGION,area);
                intent.putExtra(DataStore.KEY_COUNTRY,country);
                intent.putExtra(DataStore.KEY_COUNTRYID,countryID);
                startActivity(intent);

            }
        });
    }
}
