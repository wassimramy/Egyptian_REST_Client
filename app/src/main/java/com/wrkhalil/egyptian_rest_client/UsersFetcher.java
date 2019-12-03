package com.wrkhalil.egyptian_rest_client;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UsersFetcher {
    private int index = 1;
    private int usersMaxBound = 0;
    private TextView usersDownloadStatusTextView;
    private MainActivity mainActivity;

    UsersFetcher(Cache cache, TextView usersDownloadStatusTextView, MainActivity mainActivity){
        this.usersDownloadStatusTextView = usersDownloadStatusTextView;
        this.mainActivity = mainActivity;
        fetchData(cache);
    }

    private void fetchData(Cache cache){
        RequestQueue requestQueue;

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        String url= "https://jsonplaceholder.typicode.com/users/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, response -> {
                    Log.d("Response" , "Response:\n" + response);
                    String value = "null";
                    try {
                        value = response.getString("id");
                        UsersFetcher.this.usersMaxBound = Integer.parseInt(value);
                        UsersFetcher.this.usersDownloadStatusTextView.setText("Downloading " + value + " users");
                        Log.d("Response" , "# of users: " + value);
                        UsersFetcher.this.fetchUser(cache);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // TODO: Handle error
                    Log.d("Response" , "Failed");
                    UsersFetcher.this.fetchData(cache);
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }

    private void fetchUser(Cache cache){
        RequestQueue requestQueue;

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        if (index < usersMaxBound){
            String url= "https://jsonplaceholder.typicode.com/users/"+index;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response" , "Response:\n" + response);
                            String name, username, email, phone, website = "null";
                            Address address;
                            Geography geo;
                            Company company;
                            int id, userId = 0;
                            try {
                                id = response.getInt("id");
                                name = response.getString("name");
                                username = response.getString("username");
                                email = response.getString("email");

                                JSONObject addressResponse = response.getJSONObject("address");
                                JSONObject geoResponse = addressResponse.getJSONObject("geo");
                                geo = new Geography(geoResponse.getDouble("lat"),
                                        geoResponse.getDouble("lng"));
                                address = new Address(addressResponse.getString("street"),
                                        addressResponse.getString("suite"),
                                        addressResponse.getString("city"),
                                        addressResponse.getString("zipcode"),
                                        geo);

                                phone = response.getString("phone");
                                website = response.getString("website");

                                JSONObject companyResponse = response.getJSONObject("company");
                                company = new Company(companyResponse.getString("name"),
                                        companyResponse.getString("catchPhrase"),
                                        companyResponse.getString("bs"));

                                BaseApplication.usersList.add(new User (id, name, username, email,
                                        address, phone, website, company));
                                UsersFetcher.this.usersDownloadStatusTextView.setText("Downloading post # " + index + " UserID " + userId);
                                Log.d("Response" , "post ID: " + index);
                                index ++;
                                UsersFetcher.this.fetchUser(cache);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            UsersFetcher.this.fetchUser(cache);
                            Log.d("Response" , "Failed");
                        }
                    });

            // Add the request to the RequestQueue.
            requestQueue.add(jsonObjectRequest);
        }
        else{
            UsersFetcher.this.usersDownloadStatusTextView.setText(BaseApplication.usersList.size() + " users were downloaded successfully");
            Log.d("usersList.size()", BaseApplication.usersList.size() + "");
            mainActivity.setUsersFetched(true);
            mainActivity.showPostsActivity();
        }
    }

}
