package model.DAO;

import java.util.List;

import model.VO.TutorVO;
import junit.framework.TestCase;

public class TutorDAOTest extends TestCase {
	
	public void testGetListaTutor() 
	{
		TutorDAOPoolDB tutorDAO = new TutorDAOPoolDB();
		List <TutorVO> tutores = tutorDAO.getlistaTutor();
		assertTrue (tutores.size() > 0);
	}
}
