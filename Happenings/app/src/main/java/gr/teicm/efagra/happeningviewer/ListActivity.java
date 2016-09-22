package gr.teicm.efagra.happeningviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import gr.teicm.efagra.happeningviewer.classes.DataStore;
import gr.teicm.efagra.happeningviewer.classes.LazyAdapter;

public class ListActivity extends AppCompatActivity {

    ListView listViewFilteredEvents;

    private void findViews() {
        listViewFilteredEvents = (ListView)findViewById(R.id.listViewFilteredEvents);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //TODO ACTIVATE
        //Animation when this Activity appears
        //overridePendingTransition(R.anim.pull_in_from_right,R.anim.hold);

        findViews();

        Intent intent = getIntent();
        String filterFromDate = intent.getStringExtra(DataStore.KEY_FROMDATE);
        String filterToDate = intent.getStringExtra(DataStore.KEY_TODATE);
        String filterGenre = intent.getStringExtra(DataStore.KEY_CATEGORY);
        int filterGenreID = intent.getIntExtra(DataStore.KEY_CATEGORYID,0);
        String filterArtist = intent.getStringExtra(DataStore.KEY_ARTIST);
        String filterRegion = intent.getStringExtra(DataStore.KEY_REGION);
        String filterCountry = intent.getStringExtra(DataStore.KEY_COUNTRY);
        int filterCountryID = intent.getIntExtra(DataStore.KEY_COUNTRYID, 0);
        //String message = String.format("from: %s, to: %s, genre: %s, genreid: %d, artist: %s, region: %s, country: %s, countryid: %d", filterFromDate, filterToDate, filterGenre, filterGenreID, filterArtist, filterRegion, filterCountry, filterCountryID);
        //Toast.makeText(ListActivity.this, message, Toast.LENGTH_LONG).show();

        DataStore.LoadEvents(filterFromDate, filterToDate, filterGenreID, filterArtist, filterRegion, filterCountryID);
/*
        ListAdapter eventsAdapter = new SimpleAdapter(
                this,
                DataStore.Events,
                R.layout.list_item,
                new String[] {
                        DataStore.KEY_CATEGORY,
                        DataStore.KEY_ARTIST,
                        DataStore.KEY_REGION,
                        DataStore.KEY_COUNTRY,
                        DataStore.KEY_IMGURL
                },
                new int[] {
                        R.id.event_item_genre,
                        R.id.event_item_artist,
                       R.id.event_item_location
                }
        );
*/
        LazyAdapter eventsAdapter = new LazyAdapter(this, DataStore.Events);

        listViewFilteredEvents.setAdapter(eventsAdapter);

        listViewFilteredEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(ListActivity.this, DetailsActivity.class);
                detailsIntent.putExtra(DataStore.KEY_EVENTPOSITION, position);
                startActivity(detailsIntent);
            }
        });
    }
}
