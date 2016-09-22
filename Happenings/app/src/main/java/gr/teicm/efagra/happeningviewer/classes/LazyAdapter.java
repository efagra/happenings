package gr.teicm.efagra.happeningviewer.classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import gr.teicm.efagra.happeningviewer.R;

/**
 * Created by efagra on 7/2/16.
 */
public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, Object>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<HashMap<String, Object>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_item, null);

        TextView genre = (TextView)vi.findViewById(R.id.event_item_category);
        TextView artist = (TextView)vi.findViewById(R.id.event_item_artist);
        TextView location = (TextView)vi.findViewById(R.id.event_item_location);
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.event_item_img);

        HashMap<String, Object> event = new HashMap<String, Object>();
        event = data.get(position);

        // Setting all values in listview
        genre.setText((String)event.get(DataStore.KEY_CATEGORY));
        artist.setText((String)event.get(DataStore.KEY_ARTIST));
        location.setText((String)event.get(DataStore.KEY_REGION) + ", " + (String)event.get(DataStore.KEY_COUNTRY));
        imageLoader.DisplayImage((String)event.get(DataStore.KEY_IMGURL), thumb_image);
        return vi;
    }

}
