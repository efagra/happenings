package gr.teicm.efagra.happeningviewer.classes;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import gr.teicm.efagra.happeningviewer.R;

/**
 * Created by efagra on 7/2/16.
 */
public class DataStore {

    public static String KEY_FROMDATE = "FROMDATE";
    public static String KEY_TODATE = "TODATE";
    public static String KEY_DATE = "DATE";
    public static String KEY_TIME = "TIME";
    public static String KEY_CATEGORY = "CATEGORY";
    public static String KEY_CATEGORYID = "CATEGORYID";
    public static String KEY_DURATION = "DURATION";
    public static String KEY_PRICE = "PRICE";
    public static String KEY_ARTIST = "ARTIST";
    public static String KEY_REGION = "REGION";
    public static String KEY_COUNTRY = "COUNTRY";
    public static String KEY_COUNTRYID = "COUNTRYID";
    public static String KEY_GEOCOORDINATES = "GEOCOORDINATES";
    public static String KEY_IMGURL = "IMGURL";
    public static String KEY_EVENTPOSITION = "POSITION";
    public static String KEY_EVENTID = "ID";

    public static String[] Genres;
    public static String[] Countries;
    public static ArrayList<HashMap<String, Object>> Events;
    public static Context AppContext;
    public static Resources AppResources;

    public static void Init(Context ctx) {
        AppContext = ctx;
        AppResources = ctx.getResources();
        Events = new ArrayList<HashMap<String, Object>>();
        Genres = AppResources.getStringArray(R.array.categories_array);
        Countries = AppResources.getStringArray(R.array.countries_array);
    }

    public static void LoadEvents( String filterFromDate,
                                   String filterToDate,
                                   int filterCategoryID,
                                   String filterArtist,
                                   String filterRegion,
                                   int filterCountryID)
    {
        DataStore.Events.clear();

        //Read from file in Assets
        //String contents = AssetsUtils.getFileContentsFromAssets(AppContext, "events.json");

        //Read from URL
        filterArtist = NetworkUtils.UrlEncode(filterArtist);
        String urlString = String.format("http://83.212.97.234/happenings_service.php?fromdate=%s&todate=%s&categoryid=%d&artist=%s&region=%s&countryid=%d", filterFromDate, filterToDate, filterCategoryID, filterArtist, filterRegion, filterCountryID);
        String contents = NetworkUtils.getFileContentsFromUrl(urlString);

        JSONObject json = JSONParser.getJsonObject(contents);
        JSONArray jEvents = json.optJSONArray("Events");
        if (jEvents == null) return;
        int nEvents = jEvents.length();
        for (int i=0; i<nEvents; i++) {
            JSONObject jCurEvent = jEvents.optJSONObject(i);
            int eventID = jCurEvent.optInt(DataStore.KEY_EVENTID,0);
            String eventDate = jCurEvent.optString(DataStore.KEY_DATE);
            String eventTime = jCurEvent.optString(DataStore.KEY_TIME);
            int eventDuration = jCurEvent.optInt(DataStore.KEY_DURATION,0);
            double eventPrice = jCurEvent.optDouble(DataStore.KEY_PRICE,0.0);
            int eventGenreID = jCurEvent.optInt(DataStore.KEY_CATEGORYID,0);
            String eventArtist = jCurEvent.optString(DataStore.KEY_ARTIST);
            String eventRegion = jCurEvent.optString(DataStore.KEY_REGION);
            int eventCountryID = jCurEvent.optInt(DataStore.KEY_COUNTRYID,0);
            String eventImgUrl = jCurEvent.optString(DataStore.KEY_IMGURL);
            String eventGeoCoordinates = jCurEvent.optString(DataStore.KEY_GEOCOORDINATES);

            //get Genre name by ID
            String eventGenre = DataStore.Genres[eventGenreID];

            //get Country name by ID
            String eventCountry = DataStore.Countries[eventCountryID];

            // hold each event in a HashMap (Associative Array)
            HashMap<String, Object> event = new HashMap<String, Object>();
            event.put(DataStore.KEY_EVENTID, eventID);
            event.put(DataStore.KEY_DATE, eventDate);
            event.put(DataStore.KEY_TIME, eventTime);
            event.put(DataStore.KEY_DURATION, eventDuration);
            event.put(DataStore.KEY_PRICE, eventPrice);
            //event.put(DataStore.KEY_CATEGORYID, eventGenreID);
            event.put(DataStore.KEY_CATEGORY, eventGenre);
            event.put(DataStore.KEY_ARTIST, eventArtist);
            event.put(DataStore.KEY_REGION, eventRegion);;
            //event.put(DataStore.KEY_COUNTRYID, eventCountryID);
            event.put(DataStore.KEY_COUNTRY, eventCountry);
            event.put(DataStore.KEY_IMGURL, eventImgUrl);
            event.put(DataStore.KEY_GEOCOORDINATES, eventGeoCoordinates);

            Events.add(event);
        }
    }

}
