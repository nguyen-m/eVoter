package evoter.mobile.main;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import evoter.mobile.subject.SubjectActivity;
import evoter.mobile.utils.UserAccountValidation;
import evoter.mobile.utils.Utils;
import evoter.server.dao.UserDAO;

/**
 * Created by luongnv89 on 05/12/13 </br> Updated by @author btdiem on
 * 08-Jan-2014:</br> </li> parse response and store user key sent by server to
 * verify next time
 * 
 */
public class Login extends EVoterActivity {

	EditText etUsrName;
	EditText etPassword;

	Button btLogin;

	TextView tvRegister;
	TextView tvResetPassword;

	CheckBox cbRemember;

	EVoterSessionManager eVoterSessionManager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);

		this.tvTitleBarContent.setText("Login");
		this.ivTitleBarRefresh.setVisibility(View.INVISIBLE);

		eVoterSessionManager = new EVoterSessionManager(this);

		etUsrName = (EditText) findViewById(R.id.usrname);
		etPassword = (EditText) findViewById(R.id.password);

		btLogin = (Button) findViewById(R.id.btLogin);

		cbRemember = (CheckBox) findViewById(R.id.cb_remember);

		btLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// GetData getData = new GetData();
				AsyncHttpClient client = new AsyncHttpClient(1000);
				final String i_Usrname = etUsrName.getText().toString();
				final String i_Password = etPassword.getText().toString();
				RequestParams params = new RequestParams();
				params.add(UserDAO.USER_NAME, i_Usrname);
				params.add(UserDAO.PASSWORD, i_Password);

				if (i_Usrname.equals("")) {
					Utils.showeVoterToast(Login.this,
							"Please input your username");
				} else if (i_Password.equals("")) {
					Utils.showeVoterToast(Login.this,
							"Please input your password");
				} else if (!UserAccountValidation.isValidUserName(i_Usrname)) {
					Utils.showeVoterToast(Login.this,
							"Input username is not valid");
				} else if (!UserAccountValidation.isValidPassword(i_Password)) {
					Utils.showeVoterToast(Login.this,
							"Input password is not valid");
				} else

				{
					// Send request to login
					client.post(Configuration.get_urlLogin(), params,
							new AsyncHttpResponseHandler() {
								// Request successfully
								@Override
								public void onSuccess(String response) {

									String userKey = null;
									try {

										JSONObject object = new JSONObject(
												response);
										userKey = object
												.getString(UserDAO.USER_KEY);

									} catch (JSONException e) {
										e.printStackTrace();
									}

									if (userKey != null || userKey != "null") {

										if (cbRemember.isChecked()) {
											eVoterSessionManager
													.rememberCurrentUser(
															i_Usrname,
															i_Password);
										}

										EVoterSessionManager
												.setCurrentSubjectName(i_Usrname);
										EVoterSessionManager
												.setUSER_KEY(userKey);
										Utils.showeVoterToast(Login.this,
												"Welcome " + i_Usrname
														+ " to eVoter!");

										Intent subjectIntent = new Intent(
												Login.this,
												SubjectActivity.class);
										subjectIntent
												.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
										subjectIntent
												.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
										startActivity(subjectIntent);

									}
								}

								@Override
								public void onFailure(Throwable error,
										String content) {
									Utils.showeVoterToast(
											Login.this,
											"onFailure error : "
													+ error.toString()
													+ "content : " + content);
									Log.e("LoginTest", "onFailure error : "
											+ error.toString() + "content : "
											+ content);
								}
							});
				}

			}
		});

		tvRegister = (TextView) findViewById(R.id.tvSignUp);
		tvRegister.setPaintFlags(tvRegister.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);
		tvRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.REGISTER"));
			}
		});

		tvResetPassword = (TextView) findViewById(R.id.tvForgotPassword);
		tvResetPassword.setPaintFlags(tvResetPassword.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);
		tvResetPassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(
						"android.intent.action.RECOVERPASSWORD"));
			}
		});

	}

	/**
	 * Called when the activity has detected the user's press of the back key.
	 * The default implementation simply finishes the current activity, but you
	 * can override this to do whatever you want.
	 */
	@Override
	public void onBackPressed() {
		Intent exitIntent = new Intent(this, Splash.class);
		exitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		exitIntent.putExtra("Exit application", true);
		startActivity(exitIntent);
		finish();
	}
}