package com.paxees.tcc.views.fragments.signFragments

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.facebook.*
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.paxees.tcc.R
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_create_account.etPassword
import kotlinx.android.synthetic.main.fragment_create_account.mainLoginLayout
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class CreateAccount : Fragment(), View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

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
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        checkBackground()
        header!!.text = getText(R.string.create_account)
        bt_create_account!!.setOnClickListener(this)
        signInBtn!!.setOnClickListener(this)
        signInBtn!!.paintFlags = signInBtn.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_create_account -> if (validation()) {
            }
            R.id.gmailBtn -> signIn()
            R.id.signInBtn -> register()
        }
    }

    private fun register() {
        switchFragment(R.id.login)
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


    @SuppressLint("WrongConstant")
    private fun checkBackground() {
        if(AppCompatDelegate.getDefaultNightMode()==2){
            mainLoginLayout.background=resources.getDrawable(R.color.colorPrimary)
        }else{
            mainLoginLayout.background=resources.getDrawable(R.drawable.loginbg)
        }
    }


    fun validation(): Boolean {
        val email = firstNameEt!!.text.toString().trim { it <= ' ' }
        val pwd = etPassword!!.text.toString().trim { it <= ' ' }
        return if (TextUtils.isEmpty(email)) {
            firstNameEt!!.error = "Email should not be empty"
            firstNameEt!!.requestFocus()
            false
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            firstNameEt!!.error = "Email should be valid"
            firstNameEt!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(pwd)) {
            etPassword!!.error = "Password should not be empty"
            etPassword!!.requestFocus()
            false
        } else {
            true
        }
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
        return regex=="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"
}
