package evoter.server.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import evoter.server.dao.AnswerDAO;
import evoter.server.model.Answer;

public class AnswerRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowIndex) throws SQLException {

		Answer answer = new Answer();
		answer.setId(rs.getLong(AnswerDAO.ID));
		answer.setAnswerText(rs.getString(AnswerDAO.ANSWER_TEXT));
		answer.setQuestionId(rs.getLong(AnswerDAO.QUESTION_ID));
		return answer;
	}

}
