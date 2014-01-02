package evoter.server.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import evoter.server.dao.SessionUserDAO;
import evoter.server.model.SessionUser;
import evoter.server.model.mapper.SessionUserRowMapper;


public class SessionUserDAOImpl extends JdbcDaoSupport implements SessionUserDAO {

	@Override
	public int insert(SessionUser sessionUser) {
		
		String sql = "INSERT INTO " + TABLE_NAME + "(" 
		+ USER_ID + "," + SESSION_ID + "," + DELETE_INDICATOR + "," + ACCEPT_SESSION 
		+ ") VALUES(?,?,?,?)";
		
		return getJdbcTemplate().update(sql, 
				new Object[]{sessionUser.getUserId(), sessionUser.getSessionId(), sessionUser.isDeleteIndicator(), sessionUser.isAcceptSession()});

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SessionUser> findAll() {
		
		String sql = "SELECT * FROM " + TABLE_NAME ;
		return getJdbcTemplate().query(sql, new SessionUserRowMapper());

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SessionUser> findByProperty(String[] propertyNames,
			Object[] propertyValues) {
		
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ";
		int len = propertyNames.length;
		for (int i=0; i<len; i++){
			sql += propertyNames[i] + "=? ";
			
			if (i<len-1)
				sql += " AND ";
		}
		return getJdbcTemplate().query(sql, propertyValues, new SessionUserRowMapper());

	}

	@Override
	public List<SessionUser> findByUserId(long userId) {
		
		return findByProperty(new String[]{USER_ID}, new Long[]{userId});
	}

	@Override
	public List<SessionUser> findBySessionId(long sessionId) {
		
		return findByProperty(new String[]{SESSION_ID}, new Long[]{sessionId});
	}

	@Override
	public List<SessionUser> findByDeleteIndicator(boolean deleteIndicator) {
		
		return findByProperty(new String[]{DELETE_INDICATOR}, new Boolean[]{deleteIndicator});
	}

	@Override
	public List<SessionUser> findByAcceptSession(boolean acceptSession) {
	
		return findByProperty(new String[]{ACCEPT_SESSION}, new Boolean[]{acceptSession});
		
	}

	@Override
	public void deleteByProperty(String[] propertyNames, Object[] propertyValues) {
		
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE ";
		int len = propertyNames.length;
		for (int i=0; i<len; i++){
			sql += propertyNames[i] + "=? ";
			
			if (i<len-1)
				sql += " AND ";
		}
		getJdbcTemplate().update(sql, propertyValues);
		
	}

	@Override
	public void deleteByUserId(long userId) {
		
		deleteByProperty(new String[]{USER_ID}, new Long[]{userId});
		
	}

	@Override
	public void deleteBySessionId(long sessionId) {
		
		deleteByProperty(new String[]{SESSION_ID}, new Long[]{sessionId});
		
	}

	@Override
	public void deleteByDeleteIndicator(boolean deleteIndicator) {
		
		deleteByProperty(new String[]{DELETE_INDICATOR}, new Boolean[]{deleteIndicator});
		
	}

	@Override
	public void deleteByAcceptSession(boolean acceptSession) {
		
		deleteByProperty(new String[]{ACCEPT_SESSION}, new Boolean[]{acceptSession});
		
	}

}
