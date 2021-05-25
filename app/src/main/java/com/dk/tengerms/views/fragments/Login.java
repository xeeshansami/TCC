package com.dk.tengerms.views.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.tengerms.R;
import com.dk.tengerms.controllers.launcher;
import com.dk.tengerms.network.ResponseHandlers.callbacks.LoginCallBack;
import com.dk.tengerms.network.enums.RetrofitEnums;
import com.dk.tengerms.network.networkmodels.request.LoginRequest;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.LoginResponse;
import com.dk.tengerms.network.networkmodels.response.models.Brand;
import com.dk.tengerms.network.store.TenGermsStore;
import com.dk.tengerms.utils.Constants;
import com.dk.tengerms.utils.SessionManager;
import com.dk.tengerms.utils.ToastUtils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Login extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    Button bt_login;
    TextView register;
    LoginRequest request;
    EditText emailEt, etPassword;
    LoginButton facebook_login_btn;
    private CallbackManager callbackManager;
    FrameLayout facebookBtn;
    private GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    ImageView gmail;
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void init(View view) {
        sessionManager=new SessionManager(getActivity());
        bt_login = view.findViewById(R.id.bt_login);
        facebookBtn = view.findViewById(R.id.facebookBtn);
        register = view.findViewById(R.id.register);
        emailEt = view.findViewById(R.id.emailEt);
        gmail = view.findViewById(R.id.gmail);
        facebook_login_btn = (LoginButton) view.findViewById(R.id.facebook_login_btn);
        etPassword = view.findViewById(R.id.etPassword);
        facebookBtn.setOnClickListener(this);
        register.setOnClickListener(this);
        gmail.setOnClickListener(this);
        facebook_login_btn.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        gmailConnect();
        setupGoogleClient();
        facebook();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                if (validation()) {
                    login();
                }
                break;
            case R.id.gmail:
                signIn();
                break;
            case R.id.facebookBtn:
                facebook_login_btn.performClick();
                break;
            case R.id.register:
                register();
                break;

        }
    }

    private void register() {
        NavHostFragment.findNavController(Login.this).navigate(R.id.login_to_register);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        gmailConnect();
    }

    public void gmailConnect(){
        if(mGoogleApiClient == null || !mGoogleApiClient.isConnected()){
            try {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                        .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();
                mGoogleApiClient.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    public boolean validation() {
        String email = emailEt.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            emailEt.setError("Email should not be empty");
            emailEt.requestFocus();
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            emailEt.setError("Email should be valid");
            emailEt.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(pwd)) {
            etPassword.setError("Password should not be empty");
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    /*facebookLogin*/
    public void facebook() {
        callbackManager = CallbackManager.Factory.create();
        facebook_login_btn.setReadPermissions("email", "public_profile");
        facebook_login_btn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        ((launcher) getActivity()).globalClass.hideLoader();
                        Log.i("Facebook", "facebook response" + response.toString() + "\n" + loginResult.getAccessToken());
//                        getFacebookDetails(loginResult.getAccessToken());
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                ((launcher) getActivity()).globalClass.hideLoader();
                Log.i("cancel", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("FacebookException", error.getMessage());
                Log.i("FacebookException", error.getCause() + "");
            }
        });
    }

    private void login() {
        String email = emailEt.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();
        ((launcher) getActivity()).globalClass.showDialog(getActivity());
        request = new LoginRequest();
        request.setEmail(email);
        request.setPassword(pwd);
        TenGermsStore.getInstance().getLogin(RetrofitEnums.URL_HBL, request, new LoginCallBack() {
            @Override
            public void LoginSuccess(LoginResponse response) {
//                if (response.getStatus()) {
                getActivity().finish();
                sessionManager.setLogin(true);
                Brand getBundle = getActivity().getIntent().getExtras().getParcelable(Constants.BRAND);
                Bundle bundle=new Bundle();
                bundle.putParcelable(Constants.BRAND,getBundle);
                bundle.putInt(Constants.BRAND_OPEN,1);
                NavHostFragment.findNavController(Login.this).navigate(R.id.login_to_dashboard,bundle);
//                }
                ToastUtils.showToastWith(getActivity(), response.getMsg());
                ((launcher) getActivity()).globalClass.hideLoader();
            }

            @Override
            public void LoginFailure(BaseResponse baseResponse) {
                ToastUtils.showToastWith(getActivity(), baseResponse.getMsg(), "");
                ((launcher) getActivity()).globalClass.hideLoader();
            }
        });
    }

    private void setupGoogleClient() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .build();
        FacebookSdk.sdkInitialize(getApplicationContext());
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }

    private void signIn() {
        ((launcher) getActivity()).globalClass.showDialog(getActivity());
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == Constants.RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            ((launcher) getActivity()).globalClass.hideLoader();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            getGmailDetails(result);
        }
    }

    /*Gmail login*/
    private void getGmailDetails(GoogleSignInResult completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getSignInAccount();
            String email = account.getEmail();
            String first_name = account.getDisplayName();
            String last_name = account.getFamilyName();
//            String fcm = SharedPreferenceManager.getInstance(LoginActivity.this).getFcmToken();
            String app = "consumer";
            String password = "Dusky123";
//            String gmailToken = account.getId();
            String gmail = "google";
            Log.i("GoogleMail", " gmail login email success fully" + email);


            // Signed in successfully, show authenticated UI.
        } catch (IndexOutOfBoundsException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (NumberFormatException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (ActivityNotFoundException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (SecurityException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (IllegalStateException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (OutOfMemoryError e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (RuntimeException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (Exception e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } finally {
            Log.e("ExceptionError", " = Finally");
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        ((launcher) getActivity()).globalClass.hideLoader();
        ToastUtils.showToastWith(getActivity(), connectionResult.getErrorMessage(), "");
    }

    /*Facebook login*/
    private void getFacebookDetails(AccessToken token) {
        try {
            GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        String email = object.optString("email");
                        String first_name = object.optString("first_name");
                        String last_name = object.optString("last_name");
//                        String fcm = SharedPreferenceManager.getInstance(LoginActivity.this).getFcmToken();
                        String password = "Dusky123";
                        String app = "consumer";
                        String fbToken = token.getToken();
                        String facebook = "facebook";

                        Log.i("Facebook", token.getToken() + "\\n"
                                + email + "\\n"
                                + last_name + "\\n"
//                                + fcm + "\\n"
                                + password + "\\n"
                                + app + "\\n"
                                + fbToken);

                    } catch (Exception e) {
                        ToastUtils.showToastWith(getActivity(), getResources().getString(R.string.somethingWentWrong), "");
                        Log.e("error", e.getMessage());
                    }
                }
            });
        } catch (IndexOutOfBoundsException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (NumberFormatException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (ActivityNotFoundException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (SecurityException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (IllegalStateException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (OutOfMemoryError e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (RuntimeException e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } catch (Exception e) {
            Log.e("ExceptionError", " = " + e.getMessage());
        } finally {
            Log.e("ExceptionError", " = Finally");
        }
    }
}