package com.paxees.tcc.views.fragments.home;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandResponseCallBack;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;

import com.paxees.tcc.R;
import com.paxees.tcc.controllers.Dashboard;
import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoryCallBack;
import com.paxees.tcc.network.enums.RetrofitEnums;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoryResponse;
import com.paxees.tcc.network.networkmodels.response.models.Brand;
import com.paxees.tcc.network.store.TenGermsStore;
import com.paxees.tcc.utils.Constants;
import com.paxees.tcc.utils.ToastUtils;
import com.paxees.tcc.views.adapters.PopularBrandAdapter;
import com.paxees.tcc.views.adapters.RecyclerViewAdapter;
import com.paxees.tcc.views.adapters.NearestBrandsAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener, PopularBrandAdapter.ItemClickListener, View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {
    protected LocationRequest mlocationRequest;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    View root;
    private HomeViewModel homeViewModel;
    private RecyclerViewAdapter myRecyclerViewAdapter;
    private NearestBrandsAdapter nearestBrandsAdapter;
    private PopularBrandAdapter popularBrandAdapter;
    RecyclerView recyclerView, rvNearestBrands, rvPopular;
    LinearLayout map_open;
    EditText searchBrandEt;
    TextView locationSearchLbl, locationAddress, nrf_view1, nrf_view2, nrf_view3;
    private ArrayList<Brand> nearestBrandList,popularBrandList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
      /*  final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(tempReceiver, new IntentFilter(Constants.MAP_COORDINATES_ACTION));
        recyclerView = view.findViewById(R.id.rvButtons);
        searchBrandEt = view.findViewById(R.id.searchBrandEt);
        locationSearchLbl = view.findViewById(R.id.locationSearchLbl);
        locationAddress = view.findViewById(R.id.locationAddress);
        map_open = view.findViewById(R.id.map_open);
        nrf_view1 = view.findViewById(R.id.nrf_view1);
        nrf_view2 = view.findViewById(R.id.nrf_view2);
        nrf_view3 = view.findViewById(R.id.nrf_view3);
        rvNearestBrands = view.findViewById(R.id.rvSearchResults);
        rvPopular = view.findViewById(R.id.rvBrands);
        map_open.setOnClickListener(this);
        search();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        buttonRecyclerView();
    }

    private void search() {
        searchBrandEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterNearestBrand(s.toString());
                filterPopularBrand(s.toString());
            }
        });
    }

    private void filterNearestBrand(String text) {
        ArrayList<Brand> filteredList = new ArrayList<>();
        if(nearestBrandList.size()!=0) {
            for (Brand item : nearestBrandList) {
                if (item.getBrandName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                    nrf_view2.setVisibility(View.GONE);
                    rvNearestBrands.setVisibility(View.VISIBLE);
                }else{
                    nrf_view2.setVisibility(View.VISIBLE);
                    rvNearestBrands.setVisibility(View.GONE);
                }
            }
            nearestBrandsAdapter.filterList(filteredList);
        }
    }
    private void filterPopularBrand(String text) {
        ArrayList<Brand> filteredList = new ArrayList<>();
        if(popularBrandList.size()!=0) {
            for (Brand item : popularBrandList) {
                if (item.getBrandName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                    nrf_view3.setVisibility(View.GONE);
                    rvPopular.setVisibility(View.VISIBLE);
                }else{
                    nrf_view3.setVisibility(View.VISIBLE);
                    rvPopular.setVisibility(View.GONE);
                }
            }
            popularBrandAdapter.filterList(filteredList);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //Now lets connect to the API
        mGoogleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");
        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }

    /**
     * If connected get lat and long
     */
    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            getAddress(currentLatitude, currentLongitude);
            nearestBrandsRecyclers(currentLatitude, currentLongitude);
            popularBrandsRecyclers(currentLatitude, currentLongitude);
            Log.i("CurrentLocation", currentLatitude + " WORKS " + currentLongitude + "");
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    /**
     * If locationChanges change lat and long
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        getAddress(currentLatitude, currentLongitude);
        Log.i("CurrentLocation", currentLatitude + " WORKS " + currentLongitude + "");
    }


    public void getAddress(double lat, double lng) {
        Geocoder gCoder = new Geocoder(getActivity());
        List<Address> addresses = null;
        try {
            addresses = gCoder.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null && addresses.size() > 0) {
            Log.i("myAddresss", addresses.get(0).getFeatureName() + " = "
                    + addresses.get(0).getFeatureName() + " = "
                    + addresses.get(0).getCountryCode() + " = "
                    + addresses.get(0).getAdminArea() + " = "
                    + addresses.get(0).getLocality() + " = "
                    + addresses.get(0).getPremises() + " = "
                    + addresses.get(0).getCountryName() + " = "
                    + addresses.get(0).getPhone() + " = "
                    + addresses.get(0).getPostalCode() + " = "
                    + addresses.get(0).getAddressLine(0));
            locationSearchLbl.setText("Home " + addresses.get(0).getLocality() + " " + addresses.get(0).getAdminArea() + " " + addresses.get(0).getCountryCode());
            locationAddress.setText(addresses.get(0).getAddressLine(0));
        }
    }

    public void buttonRecyclerView() {
        ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
        TenGermsStore.getInstance().getCategories(RetrofitEnums.URL_HBL, new CategoryCallBack() {
            @Override
            public void CategorySuccess(CategoryResponse response) {
                if (response.getStatus()) {
                    nrf_view1.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    // set up the RecyclerView
                    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(horizontalLayoutManagaer);
                    myRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), response.getCategories());
                    recyclerView.setAdapter(myRecyclerViewAdapter);
                } else {
                    nrf_view1.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }

            @Override
            public void CategoryFailure(BaseResponse baseResponse) {
                ToastUtils.showToastWith(getActivity(), baseResponse.getMsg(), "");
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }
        });

    }


    public void nearestBrandsRecyclers(double lat, double lng) {
        ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
        DashboardRequest request = new DashboardRequest();
        request.setLat(Constants.LAT_REST + "");
        request.setLongi(Constants.LAT_REST + "");
        TenGermsStore.getInstance().getDashboard(RetrofitEnums.URL_HBL, request, new BrandResponseCallBack() {
            @Override
            public void BrandResponseSuccess(BrandByCategoryResponse response) {
                if (response.getStatus()) {
                    nrf_view2.setVisibility(View.GONE);
                    rvNearestBrands.setVisibility(View.VISIBLE);
                    // set up the RecyclerView
                    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    rvNearestBrands.setLayoutManager(horizontalLayoutManagaer);
                    nearestBrandList =new ArrayList<>();
                    nearestBrandList.addAll(response.getBrands());
                    nearestBrandsAdapter = new NearestBrandsAdapter(getActivity(), response.getBrands());
                    nearestBrandsAdapter.setClickListener(new NearestBrandsAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, Brand brand) {
                            ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((Dashboard) getActivity()).globalClass.hideLoader();
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(Constants.BRAND, brand);
                                    NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.home_to_brands, bundle);
                                }
                            }, 1500);
                        }
                    });
                    rvNearestBrands.setAdapter(nearestBrandsAdapter);
                } else {
                    nrf_view2.setVisibility(View.VISIBLE);
                    rvNearestBrands.setVisibility(View.GONE);
                }
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }

            @Override
            public void BrandResponseFailure(BaseResponse baseResponse) {
                ToastUtils.showToastWith(getActivity(), baseResponse.getMsg(), "");
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }
        });


    }


    public void popularBrandsRecyclers(double lat, double lng) {
        ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
        DashboardRequest request = new DashboardRequest();
        request.setLat(Constants.LAT + "");
        request.setLongi(Constants.LNG + "");
        TenGermsStore.getInstance().getDashboard(RetrofitEnums.URL_HBL, request, new BrandResponseCallBack() {
            @Override
            public void BrandResponseSuccess(BrandByCategoryResponse response) {
                if (response.getStatus()) {
                    nrf_view3.setVisibility(View.GONE);
                    rvPopular.setVisibility(View.VISIBLE);
                    // set up the RecyclerView
                    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    rvPopular.setLayoutManager(horizontalLayoutManagaer);
                    popularBrandList =new ArrayList<>();
                    popularBrandList.addAll(response.getBrands());
                    popularBrandAdapter = new PopularBrandAdapter(getActivity(), response.getBrands());
                    popularBrandAdapter.setClickListener(new PopularBrandAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, Brand brand) {
                            ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((Dashboard) getActivity()).globalClass.hideLoader();
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(Constants.BRAND, brand);
                                    NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.home_to_brands, bundle);
                                }
                            }, 1500);
                        }
                    });
                    rvPopular.setAdapter(popularBrandAdapter);
                } else {
                    nrf_view3.setVisibility(View.VISIBLE);
                    rvPopular.setVisibility(View.GONE);
                }
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }

            @Override
            public void BrandResponseFailure(BaseResponse baseResponse) {
                ToastUtils.showToastWith(getActivity(), baseResponse.getMsg(), "");
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }
        });


    }


    @Override
    public void onItemClick(View view, int position) {
        ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Dashboard) getActivity()).globalClass.hideLoader();
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.home_to_brands);
            }
        }, 1500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_open:
                openMap();
                break;
        }
    }

    private void openMap() {
        NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.home_to_maps);
    }

    private BroadcastReceiver tempReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // do some action
        }
    };


    @Override
    public void onItemClick(View view, int position, Brand brand) {

    }
}