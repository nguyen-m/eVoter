/**
 * 
 */
package web.gui.secretary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import evoter.share.model.User;

import web.gui.secretary.AddTeacher;
import web.gui.secretary.ViewTeacher;

/**
 * @author maint
 *
 */
public class ViewTeacher_Test {

	public static void main(String[] args)
	{
		User usr = new User();
		ViewTeacher teacher = new ViewTeacher(usr);
	}

}
