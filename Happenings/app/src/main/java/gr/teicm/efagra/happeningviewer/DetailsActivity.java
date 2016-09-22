package gr.teicm.efagra.happeningviewer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import gr.teicm.efagra.happeningviewer.classes.DataStore;
import gr.teicm.efagra.happeningviewer.classes.ImageLoader;

public class DetailsActivity extends AppCompatActivity {

    HashMap<String, Object> event;
    ImageView imageViewEvent;
    ImageLoader imageLoader;
    TextView textViewDate;
    TextView textViewDuration;
    TextView textViewPrice;
    TextView textViewArtist;
    TextView textViewCategory;
    TextView textViewLocation;
    //TextView textViewSummary;
    Button buttonOpenMaps;

    private void findViews() {
        imageViewEvent = (ImageView)findViewById(R.id.imageViewEvent);
        textViewDate = (TextView)findViewById(R.id.event_item_date);
        textViewDuration = (TextView)findViewById(R.id.event_item_duration);
        textViewPrice = (TextView)findViewById(R.id.event_item_price);
        textViewArtist = (TextView)findViewById(R.id.event_item_artist);
        textViewCategory = (TextView)findViewById(R.id.event_item_category);
        textViewLocation = (TextView)findViewById(R.id.event_item_location);
        buttonOpenMaps = (Button)findViewById(R.id.buttonOpenMaps);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //TODO ACTIVATE
        //Animation when this Activity appears
        //overridePendingTransition(R.anim.pull_in_from_right, R.anim.hold);

        findViews();

        Intent intent = getIntent();
        int eventPosition = intent.getIntExtra(DataStore.KEY_EVENTPOSITION, -1);

        if (eventPosition < 0 || eventPosition >= DataStore.Events.size()){
            //TODO
            //show an error message
        }
        event = DataStore.Events.get(eventPosition);

        String eventDate = (String)event.get(DataStore.KEY_DATE);
        String eventTime = (String)event.get(DataStore.KEY_TIME);
        int eventDuration = (int) event.get(DataStore.KEY_DURATION);
        double eventPrice = (double)event.get(DataStore.KEY_PRICE);
        String eventCategory = (String)event.get(DataStore.KEY_CATEGORY);
        String eventArtist = (String)event.get(DataStore.KEY_ARTIST);
        String eventRegion = (String)event.get(DataStore.KEY_REGION);
        String eventCountry = (String)event.get(DataStore.KEY_COUNTRY);
        String eventImgUrl = (String)event.get(DataStore.KEY_IMGURL);
        final String eventGeoCoordinates = (String)event.get(DataStore.KEY_GEOCOORDINATES);

        imageLoader = new ImageLoader(getApplicationContext());
        imageLoader.DisplayImage(eventImgUrl,imageViewEvent);
        textViewDate.setText("Date: " + eventDate + " " + eventTime);
        textViewDuration.setText("Duration: " + eventDuration + "mins");
        textViewPrice.setText("Price: " + eventPrice + "euros");
        textViewArtist.setText("Artist: " + eventArtist);
        textViewCategory.setText("Category: " + eventCategory);
        textViewLocation.setText("Location: " + eventRegion + ", " + eventCountry);

        buttonOpenMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+eventGeoCoordinates);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

    }

//    @Override
//    protected void onPause() {
//        overridePendingTransition(R.anim.hold, R.anim.push_out_to_right);
//        super.onPause();
//    }
}
