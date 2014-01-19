package evoter.server.dao.interfaces;

import java.util.List;

import evoter.server.model.Session;

public interface SessionDAO {

	public static final String ID = "ID";
	public static final String NAME = "NAME";
	public static final String SUBJECT_ID = "SUBJECT_ID";
	public static final String CREATION_DATE = "CREATION_DATE";
	public static final String IS_ACTIVE = "IS_ACTIVE";
	public static final String USER_ID = "USER_ID";
	
	public static final String TABLE_NAME = "SESSION";
	public static final String BEAN_NAME = "sessionDAO";
	
	public long insert (Session session);
	public List<Session> findAll();
	public int update(Session session);
	/**
	 * Search {@link Session} object in the database by input properties and their values </br> 
	 * The length of property name array should have the same length with array of property value </br>
	 * @param propertyName array of property name </br>
	 * @param propertyValue array of property value </br>
	 * @return {@link List} of {@link Session} </br>
	 */
	public List<Session> findByProperty(String[] propertyName, Object[] propertyValue);
	public List<Session> findById(long id);
	public List<Session> findBySubjectId(long subjectId);
	public List<Session> findByName(String name);
	public List<Session> findByCreationDate(String date);
	public List<Session> findBySessionIsActive(boolean isActive);
	public List<Session> findBySessionUserId(long userId);
	/**
	 * 
	 * Delete {@link Session} objects in the database by input properties and their values </br> 
	 * The length of property name array should have the same length with array of property value </br>
	 * @param propertyName array of property name </br>
	 * @param propertyValue array of property value </br>
	 * @return {@link List} of {@link Session} </br>
	 */
	public void deleteByProperty(String[] propertyNames, Object [] propertyValues);
	public void deleteById(long id);
	public void deleteBySubjectId(long subjectId);
	public void deleteByName(String name);
	public void deleteByCreationDate(String date);
	public void deleteBySessionIsActive(boolean isActive);
	public void deleteByUserId(long userId);
}
