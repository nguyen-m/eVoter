/**
 * 
 */
package evoter.mobile.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import evoter.mobile.main.R;
import evoter.mobile.objects.EVoterShareMemory;
import evoter.mobile.utils.CallBackMessage;
import evoter.share.dao.UserDAO;
import evoter.share.model.UserType;

/**
 * <br>
 * Updated by @author luongnv89 on 12-Feb-2014:<br>
 * <li>Completed loadListItemData() method <br>
 * Created by @author luongnv89
 */
public class SubjectUserActivity extends EVoterActivity {
	
	ArrayList<String> listTeachers;
	ArrayList<String> listStudents;
	ListView lvTeacher;
	ListView lvStudent;
	ArrayAdapter<String> teacherAdapter;
	ArrayAdapter<String> studentAdapter;
	
	/*
	 * (non-Javadoc)
	 * @see evoter.mobile.activities.EVoterActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_users);
		this.ivTitleBarRefresh.setVisibility(View.VISIBLE);
		// When the refresh icon is click, the data of listview will be reloaded
		this.ivTitleBarRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listStudents.clear();
				listTeachers.clear();
				refreshData();
			}
			
		});
		
		//Setup dialog which is show the loading process
		dialogLoading = new Dialog(this);
		dialogLoading.setContentView(R.layout.dialog_loading);
		dialogLoading.setTitle("Refresh");
		WindowManager.LayoutParams layoutParameters = new WindowManager.LayoutParams();
		layoutParameters.copyFrom(dialogLoading.getWindow().getAttributes());
		layoutParameters.width = WindowManager.LayoutParams.MATCH_PARENT;
		layoutParameters.height = WindowManager.LayoutParams.WRAP_CONTENT;
		dialogLoading.getWindow().setAttributes(layoutParameters);
		
		tvLoadingStatus = (TextView) dialogLoading
				.findViewById(R.id.tvLoadingStatus);
		internetProcessBar = (ProgressBar) dialogLoading
				.findViewById(R.id.progressRefresh);
		internetProcessBar.setProgress(0);
		this.tvTitleBarContent.setText(EVoterShareMemory
				.getCurrentUserName());
		
		//Set menu
		mainMenu.setSessionActivityMenu();
		mainMenu.getBtListUsers().setVisibility(View.GONE);
		listTeachers = new ArrayList<String>();
		listStudents = new ArrayList<String>();
		
		lvTeacher = (ListView) findViewById(R.id.lvSubjectProfessor);
		teacherAdapter = new ArrayAdapter<String>(SubjectUserActivity.this, R.layout.user_item, listTeachers);
		lvTeacher.setAdapter(teacherAdapter);
		lvStudent = (ListView) findViewById(R.id.lvSubjectStudents);
		studentAdapter = new ArrayAdapter<String>(SubjectUserActivity.this, R.layout.user_item, listStudents);
		lvStudent.setAdapter(studentAdapter);
		refreshData();
	}
	
	public void refreshData() {
		EVoterRequestManager.getUserOfSubject(this, EVoterShareMemory.getCurrentSubject().getId());
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * evoter.mobile.activities.EVoterActivity#updateRequestCallBack(java.lang
	 * .String)
	 */
	@Override
	public void updateRequestCallBack(String response, String callBackMessage) {
		if (callBackMessage.equals(CallBackMessage.GET_USER_OF_SUBJECT_EVOTER_REQUEST)) {
			try {
				JSONArray array = new JSONArray(response);
				for (int i = 0; i < array.length(); i++) {
					JSONObject ob = array.getJSONObject(i);
					if (ob.getLong(UserDAO.USER_TYPE_ID) == UserType.TEACHER) {
						listTeachers.add((listTeachers.size() + 1) + ". " + ob.getString(UserDAO.FULL_NAME) + ": " + ob.getString(UserDAO.EMAIL));
					} else if (ob.getLong(UserDAO.USER_TYPE_ID) == UserType.STUDENT) {
						listStudents.add((listStudents.size() + 1) + ". " + ob.getString(UserDAO.FULL_NAME) + ": " + ob.getString(UserDAO.EMAIL));
					}
				}
				Log.i("Total teachers: ", String.valueOf(listTeachers.size()));
				Log.i("Total students: ", String.valueOf(listStudents.size()));
				studentAdapter.notifyDataSetChanged();
				teacherAdapter.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			super.updateRequestCallBack(response, callBackMessage);
		}
	}
	
}
