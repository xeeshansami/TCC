package com.paxees.tcc.controllers

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.paxees.tcc.R
import java.util.*

class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, PlaceSelectionListener, OnMapClickListener, View.OnClickListener {
    private var done: ImageView? = null
    private var googleMap: GoogleMap? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var currentLatitude = 0.0
    private var currentLongitude = 0.0
    private var latLng: LatLng? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        init()
        placeAddress()
    }

    private fun placeAddress() {
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, resources.getString(R.string.google_places_key))
        }

        /*Biiling Address City*/
        // Initialize the AutocompleteSupportFragment.
        val billingAddressCity = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        billingAddressCity!!.setOnPlaceSelectedListener(this)
        billingAddressCity.setHint(resources.getString(R.string.search_address))
        billingAddressCity.setPlaceFields(Arrays.asList(Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS, Place.Field.LAT_LNG))
        billingAddressCity.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
//                Log.i("Places123", "Place: " + place.getName() + ", " + place.getId());
                Log.i("Places123", "Place: " + place.latLng!!.latitude + ", " + place.id)
                //                Log.i("Places123", "Place: " + place.getLatLng().latitude + ", " + place.getId());
                latLng = place.latLng
                addMarker(place.latLng, place.name, true)
                //                lat = 26.00021;
//                lng = 67.264792;
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i("Places", "An error occurred: $status")
            }
        })
    }

    private fun init() {
        done = findViewById(R.id.done)
        done!!.setOnClickListener(this)
        mGoogleApiClient = GoogleApiClient.Builder(this) // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this) //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build()

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval((10 * 1000).toLong()) // 10 seconds, in milliseconds
                .setFastestInterval((1 * 1000).toLong()) // 1 second, in milliseconds

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapview) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        this.googleMap!!.setOnMapClickListener(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        this.googleMap!!.isMyLocationEnabled = true
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))
        if (latLng != null) {
            addMarker(latLng, resources.getString(R.string.imhere), true)
        }
    }

    override fun onResume() {
        super.onResume()
        //Now lets connect to the API
        mGoogleApiClient!!.connect()
    }

    override fun onPause() {
        super.onPause()
        Log.v(this.javaClass.simpleName, "onPause()")
        //Disconnect from API onPause()
        if (mGoogleApiClient!!.isConnected) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
            mGoogleApiClient!!.disconnect()
        }
    }

    /**
     * If connected get lat and long
     *
     */
    override fun onConnected(bundle: Bundle?) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.latitude
            currentLongitude = location.longitude
            //            addMarker(new LatLng(currentLatitude, currentLongitude), getResources().getString(R.string.imhere), true);
            Log.i("CurrentLocation", "$currentLatitude WORKS $currentLongitude")
        }
    }

    override fun onConnectionSuspended(i: Int) {}
    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST)
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (e: IntentSender.SendIntentException) {
                // Log the error
                e.printStackTrace()
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.e("Error", "Location services connection failed with code " + connectionResult.errorCode)
        }
    }

    /**
     * If locationChanges change lat and long
     *
     *
     * @param location
     */
    override fun onLocationChanged(location: Location) {
        currentLatitude = location.latitude
        currentLongitude = location.longitude
        Log.i("CurrentLocation", "$currentLatitude WORKS $currentLongitude")
    }

    override fun onPlaceSelected(place: Place) {
        latLng = place.latLng
        googleMap!!.clear()
        addMarker(place.latLng, place.name.toString(), true)
    }

    fun addMarker(latLng: LatLng?, title: String?, move: Boolean) {
        googleMap!!.clear()
        val mp = MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin))
        mp.position(latLng!!)
        mp.snippet(title)
        if (googleMap != null) {
            googleMap!!.addMarker(mp)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            googleMap!!.isMyLocationEnabled = true
            if (move) {
                val cameraPosition = CameraPosition.Builder()
                        .target(latLng) // Sets the center of the map to Mountain View
                        .zoom(13f) // Sets the zoom
                        .bearing(0f) // Sets the orientation of the camera to east
                        .tilt(10f) // Sets the tilt of the camera to 30 degrees
                        .build()
                googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                googleMap!!.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        }
    }

    override fun onError(status: Status) {
        Log.i("PlacessError", """
     ${status.statusMessage}
     ${status.status}
     """.trimIndent())
    }

    override fun onMapClick(latLng: LatLng) {
        try {
            this.latLng = latLng
            googleMap!!.clear()
            addMarker(latLng, resources.getString(R.string.imhere), false)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: NumberFormatException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: IllegalArgumentException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: ActivityNotFoundException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: SecurityException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: IllegalStateException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: NullPointerException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: OutOfMemoryError) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: RuntimeException) {
            Log.e("ExceptionError", " = " + e.message)
        } catch (e: Exception) {
            Log.e("ExceptionError", " = " + e.message)
        } finally {
            Log.e("ExceptionError", " = Finally")
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.done -> getLatLng()
        }
    }

    private fun getLatLng() {}
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        startActivity(Intent(this, CIFRootActivity::class.java))
    }

    companion object {
        //Define a request code to send to Google Play services
        private const val CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000
    }
}