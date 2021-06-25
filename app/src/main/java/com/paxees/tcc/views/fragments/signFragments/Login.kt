package com.paxees.tcc.views.fragments.signFragments

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.paxees.tcc.R
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoriesCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.LoginCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.LoginRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoriesResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailEt
import kotlinx.android.synthetic.main.fragment_login.etPassword
import kotlinx.android.synthetic.main.fragment_login.mainLoginLayout

class Login : Fragment(), View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private var callbackManager: CallbackManager? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    var mGoogleSignInClient: GoogleSignInClient? = null
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View) {
        sessionManager = SessionManager(activity)
        facebookBtn!!.setOnClickListener(this)
        gmailBtn!!.setOnClickListener(this)
        facebookBtn!!.setOnClickListener(this)
        bt_login!!.setOnClickListener(this)
        signupBtn!!.setOnClickListener(this)
        forgetPwd!!.setOnClickListener(this)
        signupBtn.paintFlags = signupBtn.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        checkBackground()
        gmailConnect()
        setupGoogleClient()
        facebook()
    }

    @SuppressLint("WrongConstant")
    private fun checkBackground() {
        if (AppCompatDelegate.getDefaultNightMode() == 2) {
            mainLoginLayout.background = resources.getDrawable(R.color.colorPrimary)
        } else {
            mainLoginLayout.background = resources.getDrawable(R.drawable.loginbg)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_login -> if (validation()) {
                login()
            }
            R.id.gmailBtn -> signIn()
            R.id.forgetPwd -> forgetPwd()
            R.id.facebookBtn -> facebookBtnLogin!!.performClick()
            R.id.signupBtn -> register()
            R.id.backBtn -> {
                switchFragment(R.id.login)
            }
        }
    }

    private fun switchFragment(startDestId: Int) {
//        val fragmentContainer = view?.findViewById<View>(R.id.nav_host)
//        val navController = Navigation.findNavController(fragmentContainer!!)
        val navController = findNavController()
        val inflater = navController.navInflater
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    private fun register() {
        NavHostFragment.findNavController(this@Login).navigate(R.id.login_to_register)
    }

    private fun forgetPwd() {
        NavHostFragment.findNavController(this@Login).navigate(R.id.login_to_forget)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
        gmailConnect()
    }

    fun gmailConnect() {
        if (mGoogleApiClient == null || !mGoogleApiClient!!.isConnected) {
            try {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build()
                mGoogleApiClient = GoogleApiClient.Builder(requireActivity())
                        .enableAutoManage(requireActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build()
                mGoogleApiClient!!.connect()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (mGoogleApiClient != null && mGoogleApiClient!!.isConnected) {
            mGoogleApiClient!!.stopAutoManage(requireActivity())
            mGoogleApiClient!!.disconnect()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mGoogleApiClient != null && mGoogleApiClient!!.isConnected) {
            mGoogleApiClient!!.stopAutoManage(requireActivity())
            mGoogleApiClient!!.disconnect()
        }
    }

    fun validation(): Boolean {
        val email = emailEt!!.text.toString().trim { it <= ' ' }
        val pwd = etPassword!!.text.toString().trim { it <= ' ' }
        return if (TextUtils.isEmpty(email)) {
            emailEt!!.error = "Email should not be empty"
            emailEt!!.requestFocus()
            false
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            emailEt!!.error = "Email should be valid"
            emailEt!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(pwd)) {
            etPassword!!.error = "Password should not be empty"
            etPassword!!.requestFocus()
            false
        } else {
            true
        }
    }

    /*facebookLogin*/
    fun facebook() {
        callbackManager = CallbackManager.Factory.create()
        facebookBtnLogin!!.setReadPermissions("email", "public_profile")
        facebookBtnLogin!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()) { `object`, response ->
                    (activity as launcher?)!!.globalClass!!.hideLoader()
                    Log.i("Facebook", """
     facebook response$response
     ${loginResult.accessToken}
     """.trimIndent())
                    //                        getFacebookDetails(loginResult.getAccessToken());
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                (activity as launcher?)!!.globalClass!!.hideLoader()
                Log.i("cancel", "cancel")
            }

            override fun onError(error: FacebookException) {
                Log.i("FacebookException", error.message!!)
                Log.i("FacebookException", error.cause.toString() + "")
            }
        })
    }

    private fun login() {
        val email = emailEt!!.text.toString().trim { it <= ' ' }
        val pwd = etPassword!!.text.toString().trim { it <= ' ' }
        (activity as launcher?)!!.globalClass!!.showDialog(activity)
        var request = LoginRequest()
        request.username = email
        request.password = pwd
        TCCStore.getInstance().getLogin(RetrofitEnums.URL_HBL, request, object : LoginCallBack {
            override fun LoginSuccess(response: LoginResponse) {
                (activity as launcher).sharedPreferenceManager.loginData = response
                ToastUtils.showToastWith(activity, "Login successfully...")
                NavHostFragment.findNavController(this@Login).navigate(R.id.login_to_dashboard)
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }

            override fun LoginFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.msg, "")
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setupGoogleClient() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .build()
        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext())
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun signIn() {
        (activity as launcher?)!!.globalClass!!.showDialog(activity)
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == Constants.RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            (activity as launcher?)!!.globalClass!!.hideLoader()
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            getGmailDetails(result)
        }
    }

    /*Gmail login*/
    private fun getGmailDetails(completedTask: GoogleSignInResult) {
        try {
            val account = completedTask.signInAccount
            val email = account!!.email
            val first_name = account.displayName
            val last_name = account.familyName
            //            String fcm = SharedPreferenceManager.getInstance(LoginActivity.this).getFcmToken();
            val app = "consumer"
            val password = "Dusky123"
            //            String gmailToken = account.getId();
            val gmail = "google"
            Log.i("GoogleMail", " gmail login email success fully$email")


            // Signed in successfully, show authenticated UI.
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

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        (activity as launcher?)!!.globalClass!!.hideLoader()
        ToastUtils.showToastWith(activity, connectionResult.errorMessage, "")
    }

    /*Facebook login*/
    private fun getFacebookDetails(token: AccessToken) {
        try {
            val request = GraphRequest.newMeRequest(token) { `object`, response ->
                try {
                    val email = `object`.optString("email")
                    val first_name = `object`.optString("first_name")
                    val last_name = `object`.optString("last_name")
                    //                        String fcm = SharedPreferenceManager.getInstance(LoginActivity.this).getFcmToken();
                    val password = "Dusky123"
                    val app = "consumer"
                    val fbToken = token.token
                    val facebook = "facebook"
                    Log.i("Facebook", token.token + "\\n"
                            + email + "\\n"
                            + last_name + "\\n" //                                + fcm + "\\n"
                            + password + "\\n"
                            + app + "\\n"
                            + fbToken)
                } catch (e: Exception) {
                    ToastUtils.showToastWith(activity, resources.getString(R.string.somethingWentWrong), "")
                    Log.e("error", e.message!!)
                }
            }
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
}

private fun String.matches(regex: String): Boolean {
    return regex == "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"
}
