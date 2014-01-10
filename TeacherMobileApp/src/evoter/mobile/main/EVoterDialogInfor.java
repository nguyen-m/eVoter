/**
 * 
 */
package evoter.mobile.main;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

/**
 * Present an information dialog in eVoter application <br>
 * Include a TextView {@link EVoterDialogInfor#tvMessage} content the message of
 * application <br>
 * a Button {@link EVoterDialogInfor#btOK} to process an action like: OK, Agree,
 * Retry,... <br>
 * a Button {@link EVoterDialogInfor#btKO} to process an action which is close
 * application or cancel,....
 * 
 * @author luongnv89
 * 
 */
public class EVoterDialogInfor extends Dialog {

	private TextView tvMessage;
	private Button btOK;
	private Button btKO;

	/**
	 * @param context
	 */
	public EVoterDialogInfor(Context context, String title) {
		super(context);
		setTitle(title);
		initialDialog();
	}

	/**
	 * 
	 */
	private void initialDialog() {
		setContentView(R.layout.dialog_info);
		tvMessage = (TextView) findViewById(R.id.tvDialogMessage);
		btOK = (Button) findViewById(R.id.btDialogOK);
		btKO = (Button) findViewById(R.id.btDialogExit);
	}

	/**
	 * @param context
	 * @param cancelable
	 * @param cancelListener
	 */
	public EVoterDialogInfor(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		initialDialog();
	}

	/**
	 * @param context
	 * @param theme
	 */
	public EVoterDialogInfor(Context context, int theme) {
		super(context, theme);
		initialDialog();
	}

	/**
	 * @return the tvMessage
	 */
	public TextView getTvMessage() {
		return tvMessage;
	}

	/**
	 * @param tvMessage
	 *            the tvMessage to set
	 */
	public void setMessageContent(String message) {
		this.tvMessage.setText(message);
	}

	/**
	 * @return the btOK
	 */
	public Button getBtOK() {
		return btOK;
	}

	/**
	 * @return the btKO
	 */
	public Button getBtKO() {
		return btKO;
	}

}
