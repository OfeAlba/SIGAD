package model.DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.VO.TutorVO;

public interface TutorDAOInterface {

	// Devuelve una lista con todos los tutores
	public abstract ArrayList<TutorVO> getlistaTutor();

	// recibe 1 idtutor y devuelve ese tutor
	public abstract TutorVO getTutor(int id);

	// borra 1 tutor con el id recibido
	public abstract void deleteTutor(int id);

	// modifica un tutor con el id recibido
	public abstract void updateTutor(int idTutor, String nombre,
			String apellido1, String apellido2, String DNI, Date fechaNac,
			String parentesco, String telefono, String email);

    public abstract boolean insertarTutor(int idAlumno, String nombre,
			String apellido1, String apellido2, String DNI, Date fechaNac,
			String parentesco, String telefono, String email);
	
}
