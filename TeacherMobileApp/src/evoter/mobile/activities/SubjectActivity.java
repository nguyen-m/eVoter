package evoter.mobile.activities;

import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import evoter.mobile.adapters.SubjectAdapter;
import evoter.mobile.main.R;
import evoter.mobile.objects.Configuration;
import evoter.mobile.objects.RuntimeEVoterManager;
import evoter.mobile.utils.EVoterMobileUtils;
import evoter.share.dao.SubjectDAO;
import evoter.share.dao.UserDAO;
import evoter.share.model.ItemData;
import evoter.share.model.Subject;
import evoter.share.model.UserType;

/**
 * Created by @author nvluong on 05-Dec-2013</br> Updated by @author btdiem on
 * 08-Jan-2014 : </br> </li>update loadListSubjects() method: </li>remove
 * parameters </li>add userKey to parameter map when sending request to server
 */
public class SubjectActivity extends ItemDataActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set content for title bar is the username
		this.tvTitleBarContent.setText(RuntimeEVoterManager
				.getCurrentUserName());
		
		this.ivTitleBarIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO: Show
				offlineEVoterManager.logoutUser();
			}
		});
		
		adapter = new SubjectAdapter(SubjectActivity.this);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Subject currentSubject = ((Subject) parent
						.getItemAtPosition(position));
				
				RuntimeEVoterManager.setCurrentSubjectID(currentSubject.getId());
				RuntimeEVoterManager.setCurrentSubjectName(currentSubject
						.getTitle());
				Intent sessionIntent = new Intent(SubjectActivity.this,
						SessionActivity.class);
				startActivity(sessionIntent);
			}
		});
		
		//Only teacher can deleted subject
		if (RuntimeEVoterManager.getCurrentUserType() == UserType.TEACHER)
		{
			listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					final Dialog dialog = new Dialog(SubjectActivity.this);
					final Subject subject = (Subject) parent
							.getItemAtPosition(position);
					dialog.setContentView(R.layout.subject_dialog);
					dialog.setTitle("Subject Action");
					
					Button btSbjDelete = (Button) dialog
							.findViewById(R.id.btSubjectDeleting);
					
					btSbjDelete.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							EVoterMobileUtils.showeVoterToast(SubjectActivity.this,
									"Deleted subject: " + subject.getTitle());
							deleteSubject(subject.getId());
							adapter.deleteItem(subject.getId());
							adapter.notifyDataSetChanged();
							dialog.dismiss();
						}
						
					});
					
					Button btSbjDetail = (Button) dialog
							.findViewById(R.id.btSubjectDetail);
					btSbjDetail.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							Toast.makeText(SubjectActivity.this,
									"Not implemented yet!!!" + subject.getTitle(),
									Toast.LENGTH_LONG).show();
							dialog.dismiss();
						}
					});
					dialog.show();
					
					return true;
				}
			});
		}
	}
	
	/**
	 * Deleted a subject by id of subject
	 * @param id
	 */
	private void deleteSubject(long id) {
		RequestParams params = new RequestParams();
		params.add(RuntimeEVoterManager.getUSER_KEY(), String.valueOf(id));
		client.post(Configuration.get_urlDeleteSubject(), params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Log.i("Delete Subject Test", "response : " + response);
			}
			
			@Override
			public void onFailure(Throwable error, String content)
			{
				Log.e("Delete Subject Test", "onFailure error : " + error.toString() + "content : " + content);
			}
		});
		
	}
	
	protected void loadListItemData() {
		RequestParams params = new RequestParams();
		params.put(UserDAO.USER_KEY, RuntimeEVoterManager.getUSER_KEY());
		client.post(Configuration.get_urlGetAllSubject(), params,
				new AsyncHttpResponseHandler() {
					
					/*
					 * (non-Javadoc)
					 * @see
					 * com.loopj.android.http.AsyncHttpResponseHandler#onStart()
					 */
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						tvLoadingStatus.setText("Loading...");
						dialogLoading.show();
					}
					
					/*
					 * (non-Javadoc)
					 * @see
					 * com.loopj.android.http.AsyncHttpResponseHandler#onFinish
					 * ()
					 */
					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						tvLoadingStatus.setText("Finished");
						dialogLoading.dismiss();
					}
					
					@Override
					public void onSuccess(String response) {
						
						try {
							ArrayList<ItemData> newList = new ArrayList<ItemData>();
							JSONArray array = EVoterMobileUtils.getJSONArray(response);
							
							for (int i = 0; i < array.length(); i++) {
								progressBar.setProgress((i + 1) * 100
										/ array.length());
								tvLoadingStatus.setText("Loading..." + (i + 1)
										* 100 / array.length());
								String sItem = array.get(i).toString();
								JSONObject item = new JSONObject(sItem);
								Log.i("JSON TEST: ", item.toString());
								Subject subject = null;
								try {
									subject = new Subject(
											Long.parseLong(item
													.getString(SubjectDAO.ID)),
											item.getString(SubjectDAO.TITLE),
											EVoterMobileUtils.convertToDate(item
													.getString(SubjectDAO.CREATION_DATE)));
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								newList.add(subject);
							}
							if (newList.isEmpty()) {
								EVoterMobileUtils.showeVoterToast(SubjectActivity.this,
										"There isn't any subject!");
							}
							adapter.updateList(newList);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					
					@Override
					public void onFailure(Throwable error, String content) {
						Log.e("Get All Subject Test", "onFailure error : "
								+ error.toString() + "content : " + content);
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
		Intent exitIntent = new Intent(this, StartActivity.class);
		exitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		exitIntent.putExtra("Exit application", true);
		startActivity(exitIntent);
		finish();
	}
	
}