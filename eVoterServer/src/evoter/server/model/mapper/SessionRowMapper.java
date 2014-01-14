package evoter.server.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import evoter.share.dao.*;
import evoter.share.model.*;

public class SessionRowMapper implements RowMapper<Session> {


	@Override
	public Session mapRow(ResultSet rs, int rowIndex) throws SQLException {
		Session session = new Session();
		session.setCreationDate(rs.getDate(SessionDAO.CREATION_DATE));
		session.setId(rs.getLong(SessionDAO.ID));
		session.setName(rs.getString(SessionDAO.NAME));
		session.setSubjectId(rs.getLong(SessionDAO.SUBJECT_ID));
		session.setActive(rs.getBoolean(SessionDAO.IS_ACTIVE));
		return session;
	}

}
