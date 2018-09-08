package xartateam.phppost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String data_iot_str= "iki sing jadi isine datamu json soko iot mu ko, cmn lek keakean, hrusne pake async disik, biar ga ngefreeze app e";


        // url dari server
        String requestUrl = "http://192.168.0.3/phppost/receiver_data.php";

        // untuk tentukan mw post apa put
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // cek apakah server respon / sudah terima data
                // bs print sesuatu biar detect klo emgn server sudah receive
                Log.d("Volley-Result", ""+response); //the response contains the result from the server, a json string or any other object returned by your server

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // print error
                error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                // Set up nama variable post dan setup value yang akan dikirim datanya ke server
                postMap.put("data_iot", data_iot_str);
                //            postMap.put("param2", value2);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };

        // panggil fungsinya
        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }

}
