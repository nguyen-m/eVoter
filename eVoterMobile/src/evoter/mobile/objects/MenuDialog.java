/**
 * 
 */
package evoter.mobile.objects;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import evoter.mobile.main.R;
import evoter.share.model.UserType;

/**
 * Created by @author luongnv89 on 19-Jan-2014:<br>
 * Manage main menu of eVoterMobile.
 */
public class MenuDialog extends Dialog {
	
	LinearLayout lo_mainMenu;
	Button btExit;
	Button btLogout;
	
	LinearLayout lo_subjectActivityMenu;
	Button btListUsers;
	
	LinearLayout lo_sessionActivityMenu;
	Button btNewSession;
	Button btAcceptUsers;
	
	LinearLayout lo_questionActivityMenu;
	Button btNewQuestion;
	
	LinearLayout lo_questionDetailActivityMenu;

	Button btAllQuestion;
	
	/**
	 * @param context
	 */
	public MenuDialog(Context context) {
		super(context);
		this.setTitle("MAIN MENU");
		setContentView(R.layout.icon_menu);
		WindowManager.LayoutParams layoutParameters = new WindowManager.LayoutParams();
		layoutParameters.copyFrom(this.getWindow().getAttributes());
		layoutParameters.width = WindowManager.LayoutParams.MATCH_PARENT;
		layoutParameters.height = WindowManager.LayoutParams.WRAP_CONTENT;
		this.getWindow().setAttributes(layoutParameters);
		
		lo_mainMenu = (LinearLayout) findViewById(R.id.mainMenuLayout);
		btExit = (Button) findViewById(R.id.btExit);
		btLogout = (Button) findViewById(R.id.btLogout);
		
		lo_subjectActivityMenu = (LinearLayout) findViewById(R.id.subjectMenuLayout);
		btListUsers = (Button) findViewById(R.id.btSubjectUser);
		
		lo_sessionActivityMenu = (LinearLayout) findViewById(R.id.sessionMenuLayout);
		btNewSession = (Button) findViewById(R.id.btNewSession);
		btNewSession.setVisibility(View.GONE);
		btAcceptUsers = (Button) findViewById(R.id.btAcceptedUser);
		
		lo_questionActivityMenu = (LinearLayout) findViewById(R.id.questionMenuLayout);
		btNewQuestion = (Button) findViewById(R.id.btNewQuestion);
		btNewQuestion.setVisibility(View.GONE);
		
		lo_questionDetailActivityMenu = (LinearLayout) findViewById(R.id.questionDetailMenuLayout);
		
		btAllQuestion = (Button) findViewById(R.id.btAllQuestion);
		lo_subjectActivityMenu.setVisibility(View.GONE);
		lo_sessionActivityMenu.setVisibility(View.GONE);
		lo_questionActivityMenu.setVisibility(View.GONE);
		lo_questionDetailActivityMenu.setVisibility(View.GONE);
		btAllQuestion.setVisibility(View.GONE);
		if(RuntimeEVoterManager.getCurrentUserType()==UserType.TEACHER){
			btAllQuestion.setVisibility(View.VISIBLE);
			btNewSession.setVisibility(View.VISIBLE);
			btNewQuestion.setVisibility(View.VISIBLE);
		}
	}
	
	public void setMenuSubjectActivity(){
		lo_subjectActivityMenu.setVisibility(View.VISIBLE);
		lo_sessionActivityMenu.setVisibility(View.GONE);
		lo_questionActivityMenu.setVisibility(View.GONE);
		lo_questionDetailActivityMenu.setVisibility(View.GONE);
	}
	
	public void setMenuSessionActivity(){
		lo_sessionActivityMenu.setVisibility(View.VISIBLE);
		lo_subjectActivityMenu.setVisibility(View.GONE);
		lo_questionActivityMenu.setVisibility(View.GONE);
		lo_questionDetailActivityMenu.setVisibility(View.GONE);
	}
	
	public void setMenuQuestionActivity(){
		lo_questionActivityMenu.setVisibility(View.VISIBLE);
		lo_subjectActivityMenu.setVisibility(View.GONE);
		lo_sessionActivityMenu.setVisibility(View.GONE);
		lo_questionDetailActivityMenu.setVisibility(View.GONE);
	}
	
	public void setMenuQuestionDetailActivity(){
		lo_questionDetailActivityMenu.setVisibility(View.VISIBLE);
		lo_subjectActivityMenu.setVisibility(View.GONE);
		lo_sessionActivityMenu.setVisibility(View.GONE);
		lo_questionActivityMenu.setVisibility(View.GONE);
	}
	
	/**
	 * @return the mainMenu
	 */
	public LinearLayout getMainMenu() {
		return lo_mainMenu;
	}

	/**
	 * @return the btExit
	 */
	public Button getBtExit() {
		return btExit;
	}

	/**
	 * @return the btLogout
	 */
	public Button getBtLogout() {
		return btLogout;
	}

	/**
	 * @return the subjectActivityMenu
	 */
	public LinearLayout getSubjectActivityMenu() {
		return lo_subjectActivityMenu;
	}

	/**
	 * @return the btListUsers
	 */
	public Button getBtListUsers() {
		return btListUsers;
	}

	/**
	 * @return the sessionActivityMenu
	 */
	public LinearLayout getSessionActivityMenu() {
		return lo_sessionActivityMenu;
	}

	/**
	 * @return the btNewSession
	 */
	public Button getBtNewSession() {
		return btNewSession;
	}

	/**
	 * @return the btAcceptUsers
	 */
	public Button getBtAcceptUsers() {
		return btAcceptUsers;
	}

	/**
	 * @return the questionActivityMenu
	 */
	public LinearLayout getQuestionActivityMenu() {
		return lo_questionActivityMenu;
	}

	/**
	 * @return the btNewQuestion
	 */
	public Button getBtNewQuestion() {
		return btNewQuestion;
	}

	/**
	 * @return the questionDetailActivityMenu
	 */
	public LinearLayout getQuestionDetailActivityMenu() {
		return lo_questionDetailActivityMenu;
	}

	/**
	 * @return the btAllQuestion
	 */
	public Button getBtAllQuestion() {
		return btAllQuestion;
	}

}