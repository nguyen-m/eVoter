package evoter.server.model;

import java.io.Serializable;

import org.json.simple.JSONObject;

import evoter.server.dao.QuestionDAO;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5892190903011268048L;

	private long id;
	private long questionTypeId;
	private long sessionId;
	private String questionText;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", questionTypeId=" + questionTypeId
				+ ", sessionId=" + sessionId + ", questionText=" + questionText
				+ "]";
	}
	@SuppressWarnings("unchecked")
	public String toJSONString() {
		JSONObject object = new JSONObject();
		object.put(QuestionDAO.ID, id);
		object.put(QuestionDAO.QUESTION_TEXT, questionText);
		object.put(QuestionDAO.QUESTION_TYPE_ID, questionTypeId);
		object.put(QuestionDAO.SESSION_ID, sessionId);
		return object.toJSONString();
	}
	
	
}