package evoter.mobile.main;

import evoter.mobile.main.R;
import evoter.mobile.utils.UserAccountValidation;
import evoter.mobile.utils.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by luongnv89 on 05/12/13.
 */
public class ResetPassword extends EVoterActivity {

	String validEmail = "luongnv89@gmail.com";
	EditText etEmail;
	Button btReset;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_reset);

		this.tvTitleBarContent.setText("Reset password");

		etEmail = (EditText) findViewById(R.id.etEmail);
		btReset = (Button) findViewById(R.id.btResetPassword);

		btReset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String i_email = etEmail.getText().toString();
				if (i_email.equals("")) {
					Utils.showeVoterToast(ResetPassword.this,
							"Input your email!");
				} else if (!UserAccountValidation.isValidEmail(i_email)) {
					Utils.showeVoterToast(ResetPassword.this,
							"Input email is invalid. Try again!");
				} else {

					// TODO: Send request to sever: email to change the password
					Utils.showeVoterToast(ResetPassword.this,
							"You will receive an email confirm to reset your password!");
					Intent intent = new Intent(ResetPassword.this, Login.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
			}

		});
	}
}