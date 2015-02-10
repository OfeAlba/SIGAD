package model.DAO.copy;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.VO.TutorVO;

@ManagedBean
@SessionScoped
public class TutorDAO implements TutorDAOInterface, Serializable {

	// @Resource(name="jdbc/myoracle")
	private DataSource ds;

	public TutorDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	// Devuelve una lista con todos los tutores
	public ArrayList<TutorVO> getlistaTutor() {
		ArrayList<TutorVO> listaTutores = new ArrayList<TutorVO>();

		if (ds == null)
			throw new SQLException("Can't get data source");
		// get database connection
		Connection con = ds.getConnection();

		if (con == null)
			throw new SQLException("Can't get database connection");

		PreparedStatement st = con.prepareStatement("SELECT * FROM TUTORES");
		// PreparedStatement st =
		// conexion.prepareStatement("SELECT * FROM EQ4_BICI");
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			listaTutores.add(new TutorVO(rs.getInt(1), rs.getInt(2), rs
					.getString(3), rs.getString(4), rs.getString(5), rs
					.getString(6), rs.getDate(7), rs.getString(8), rs
					.getString(9), rs.getString(10)));
		}
		return listaTutores;

	}

	// recibe 1 idTutor y devuelve ese tutor
	public TutorVO getTutor(int id) {
		TutorVO tutor = null;

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
			tutor = new TutorVO(rs.getInt(1), rs.getInt(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6),
					rs.getDate(7), rs.getString(8), rs.getString(9),
					rs.getString(10));
		}
		return tutor;
	}

	// borra 1 tutor con el idTutor recibido
	public void deleteTutor(int id) {

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
	}

	// modifica un tutor con el id recibido
	public int updateTutor(int idTutor, String nombre, String apellido1,
			String apellido2, String DNI, Date fechaNac, String parentesco,
			String telefono, String email) {

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
		return 4;
	}

	public void insertarTutor(int idAlumno, String nombre, String apellido1,
			String apellido2, String DNI, Date fechaNac, String parentesco,
			String telefono, String email) {

		if (ds == null)
			throw new SQLException("Can't get data source");
		// get database connection
		Connection con = ds.getConnection();

		if (con == null)
			throw new SQLException("Can't get database connection");

		String sql = "insert into venta(codigo,fecha,info) values (?,?,?)";
		PreparedStatement st = con
				.prepareStatement("INSERT INTO tutores(nombre,apellido1,apellido2,dni,fechaNac,tel,email,idAlumno)VALUES (?,?,?,?,?,?,?,?)");
		st.setString(1, nombre);
		st.setString(2, apellido1);
		st.setString(3, apellido2);
		st.setString(4, DNI);
		st.setDate(5, fechaNac);
		st.setString(6, telefono);
		st.setString(7, email);
		st.setInt(8, idAlumno);
		st.executeUpdate();
		st.executeUpdate();
	}

}
