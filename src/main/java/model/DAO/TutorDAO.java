package model.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.VO.TutorVO;

@ManagedBean
@SessionScoped
public class TutorDAO implements TutorDAOInterface {

	// @Resource(name="jdbc/myoracle")
	private DataSource ds;

	public TutorDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/opensigad");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	// Devuelve una lista con todos los tutores
	public ArrayList<TutorVO> getlistaTutor() {

		ArrayList<TutorVO> listaTutores = new ArrayList<TutorVO>();

		try {
			if (ds == null)
				throw new SQLException("Can't get data source");
			// get database connection
			Connection con = ds.getConnection();

			if (con == null)
				throw new SQLException("Can't get database connection");

			PreparedStatement st = con
					.prepareStatement("SELECT * FROM TUTORES");
			// PreparedStatement st =
			// conexion.prepareStatement("SELECT * FROM EQ4_BICI");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				listaTutores.add(new TutorVO(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getString(5), rs.getDate(6), rs.getString(7), rs
						.getString(8), rs.getString(9)));
			}

		} catch (Exception e) {

		}
		return listaTutores;

	}

	// recibe 1 idTutor y devuelve ese tutor
	public TutorVO getTutor(int id) {
		TutorVO tutor = null;

		try{
			if (ds == null)
				throw new SQLException("Can't get data source");
			// get database connection
			Connection con = ds.getConnection();
	
			if (con == null)
				throw new SQLException("Can't get database connection");
	
			PreparedStatement st = con
					.prepareStatement("SELECT * FROM tutores WHERE idtutores = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				tutor = new TutorVO(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getString(5), rs.getDate(6), rs.getString(7), rs
						.getString(8), rs.getString(9));
			}				
		} catch (Exception e) {

		}
		return tutor;
	}

	// borra 1 tutor con el idTutor recibido
	public void deleteTutor(int id) {

		try{
			if (ds == null)
				throw new SQLException("Can't get data source");
			// get database connection
			Connection con = ds.getConnection();
	
			if (con == null)
				throw new SQLException("Can't get database connection");
	
			PreparedStatement st = con
					.prepareStatement("DELETE FROM tutores WHERE idTutor = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {

		}
	}

	// modifica un tutor con el id recibido
	public void updateTutor(int idTutor, String nombre, String apellido1,
			String apellido2, String DNI, Date fechaNac, String parentesco,
			String telefono, String email) {
		
		try{
			if (ds == null)
				throw new SQLException("Can't get data source");
			// get database connection
			Connection con = ds.getConnection();
	
			if (con == null)
				throw new SQLException("Can't get database connection");
	
			PreparedStatement st = con
					.prepareStatement("UPDATE tutores SET nombre = ? , apellido1 = ? , apellido2 = ?, dni = ?, fechaNac = ?, tel = ?,email = ? WHERE idTutor = ?");
			st.setString(1, nombre);
			st.setString(2, apellido1);
			st.setString(3, apellido2);
			st.setString(4, DNI);
			st.setDate(5, fechaNac);
			st.setString(6, telefono);
			st.setString(7, email);
			st.executeUpdate();					
		} catch (Exception e) {
		}		
	}

	public boolean insertarTutor(int idAlumno, String nombre, String apellido1,
			String apellido2, String DNI, Date fechaNac, String parentesco,
			String telefono, String email) {
		
		
		try{
			if (ds == null)
				throw new SQLException("Can't get data source");
			// get database connection
			Connection con = ds.getConnection();
	
			if (con == null)
				throw new SQLException("Can't get database connection");
			
			/*Validamos si el tutor a insertar ya existe*/		
			PreparedStatement val = con.prepareStatement("SELECT idTutor FROM tutor WHERE idAlumno = ?");
			val.setInt(1, idAlumno);		
			ResultSet rs = val.executeQuery();
			
			/*Si no existe en la base de datos lo insertamos en la tabla*/
			if (!rs.next()){
				PreparedStatement st = con
						.prepareStatement("INSERT INTO tutor (idAlumno,nombre,apellido1,apellido2,DNI,fechaNac,parentesco,tlf,email) values ('1',?,?,?,?,?,?,?,?)");			
				st.setString(1, nombre);
				st.setString(2, apellido1);
				st.setString(3, apellido2);
				st.setString(4, DNI);
				java.sql.Date fecha = new java.sql.Date (fechaNac.getTime());
				st.setDate(5,fecha);
				st.setString(6, parentesco);
				st.setString(7, telefono);
				st.setString(8, email);
				st.executeUpdate();
				
				/*Recupero el idTutor que acabamos de crear*/
				PreparedStatement st2 = con.prepareStatement("SELECT idTutor FROM tutor WHERE DNI LIKE ?");
				st2.setString(1, DNI);
				ResultSet rs2 = st2.executeQuery();
					if (rs.next()){
						//inserto el tutor en la tabla de relaciones
						PreparedStatement st3 = con.prepareStatement("INSERT INTO relacionAlumnoTutor(idAlumno, idTutor) values ('1',?) ");
						st3.setInt(1, rs.getInt("idTutor"));		
					}
			}else{
				/*Si existe solo insertamos la relacion*/
				PreparedStatement st = con.prepareStatement("INSERT INTO relacionAlumnoTutor(idAlumno, idTutor) values ('1',?) ");
				st.setInt(1, rs.getInt("idTutor"));		
			}
			
			return true;
		}
		catch (Exception e) 
		{
				Logger.getLogger(getClass().getName()).log(Level.SEVERE,
					"Error en TutorDAO.insertarTutor:"
							+ e.getMessage());
			
		}
		return false;
	}

		
}
