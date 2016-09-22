package gr.teicm.efagra.happeningviewer.classes;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by efagra on 7/2/16.
 */
public class AssetsUtils {

    public static String getFileContentsFromAssets(Context ctx, String filename) {
        String contents = null;

        try {
            AssetManager am = ctx.getAssets();
            InputStream st = am.open(filename);

            InputStreamReader isr = new InputStreamReader(st);

            StringBuilder sb=new StringBuilder();

            BufferedReader br = new BufferedReader(isr);
            String line;

            while((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            isr.close();
            st.close();

            contents = sb.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        // return contents
        return contents;
    }


}
