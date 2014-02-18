/**
 * 
 */
package evoter.mobile.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import evoter.mobile.main.R;
import evoter.mobile.objects.EVoterShareMemory;
import evoter.mobile.utils.CallBackMessage;
import evoter.mobile.utils.EVoterMobileUtils;
import evoter.share.model.Question;

/**
 * @author luongnv89
 */
public class QuestionStatisticActivity extends EVoterActivity {
	LinearLayout layout;
	TextView tv;
	
	/*
	 * (non-Javadoc)
	 * @see evoter.mobile.activities.EVoterActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_statistic);
		mainMenu.setQuestionActivityMenu();
		tvTitleBarContent.setText(EVoterShareMemory.getCurrentQuestion().getTitle());
		layout = (LinearLayout) findViewById(R.id.layout);
		tv = new TextView(this);
		tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		layout.addView(tv);
		ivTitleBarRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EVoterRequestManager.updateQuestion(QuestionStatisticActivity.this);
			}
		});
		ivTitleBarRefresh.setVisibility(View.VISIBLE);
		drawStatistic();
	}
	
	/**
	 * 
	 */
	public void drawStatistic() {
		EVoterRequestManager.getStatistic(EVoterShareMemory.getCurrentQuestion().getId(), QuestionStatisticActivity.this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * evoter.mobile.activities.EVoterActivity#updateRequestCallBack(java.lang
	 * .String)
	 */
	@Override
	public void updateRequestCallBack(String response, String callBackMessage) {
		if (callBackMessage.equals(CallBackMessage.GET_STATISTIC_EVOTER_REQUEST)) {
			layout.removeAllViews();
			ArrayList<String> textToView = EVoterMobileUtils.drawStatistic(response, EVoterShareMemory.getCurrentQuestion());
			for (int i = 0; i < textToView.size(); i++) {
				TextView tvShow = new TextView(this);
				tvShow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				tvShow.setText(textToView.get(i));
				layout.addView(tvShow);
			}
		} else if (callBackMessage.equals(CallBackMessage.UPDATE_QUESTION_EVOTER_REQUEST)) {
			try {
				JSONArray array = new JSONArray(response);
				Question question = null;
				if (array != null)
					question = EVoterMobileUtils.parserToQuestion(array.getJSONObject(0));
				if (question != null) {
					EVoterShareMemory.setCurrentQuestion(question);
					drawStatistic();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			super.updateRequestCallBack(response, callBackMessage);
		}
	}
	
}
