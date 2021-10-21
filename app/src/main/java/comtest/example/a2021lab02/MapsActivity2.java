package comtest.example.a2021lab02;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity2";
    private static final float DEFAULT_ZOOM = 15f;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //searchbar
        mSearchText = (EditText) findViewById(R.id.input_search);


        //init();
    }
    //widgets
    private EditText mSearchText;

    //initializing searchMethod
    private void init(){
        Log.d(TAG, "init: initializing");


     //enabling search actions with actionID to enter the search and register the search

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER ||
                keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    //execute searching
                    geoLocate();

                }


                return false;
            }

           /* private void moveCamera(LatLng latLng, float zoom, String title) {
                Log.d(TAG, "moveCamera: moving camera to, lat" + latLng.latitude + "long "+ latLng.longitude );
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(title);
                mMap.addMarker(options);



            }*/

            //search for one location

            private void geoLocate(){
                Log.d(TAG, "geoLocate: geolocating");

                String searchString = mSearchText.getText().toString();

                //we search for one, and if we have one result, we show one address
                Geocoder geocoder = new Geocoder(MapsActivity2.this);
                List<Address> list = new ArrayList<>();
                try{
                    list = geocoder.getFromLocationName(searchString,1);
                }catch (IOException e){
                    Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
                }

                if (list.size() > 0){
                    Address address = list.get(0);

                    Log.d(TAG, "geoLocate: found location" + address.toString());

                    moveCamera(new LatLng(address.getLatitude(), address.getLongitude()),
                            DEFAULT_ZOOM, address.getAddressLine(0));


                }
            }
        });
    }

    public void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving camera to, lat" + latLng.latitude + "long "+ latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title(title);
        mMap.addMarker(options);



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       /* // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        init();
    }


}