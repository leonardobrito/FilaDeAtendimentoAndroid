package Util;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

/**
 * Created by Roanderson on 17/03/2016.
 */
public class UTF8ParseJson extends JsonArrayRequest {


    public UTF8ParseJson(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }



    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        Response<JSONArray> array =null;
        try {
            String string = new String(response.data,"UTF8");
            array  = Response.success(new JSONArray(string), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;

    }
}
