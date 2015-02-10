
import java.sql.Date;

public class TutorVO {
	private int idTutor;
	private int idAlumno;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String DNI;
	private Date fechaNac;
	private String parentesco;
	private String tlf;
	private String email;

	public TutorVO(int idTutor, int idAlumno, String nombre, String apellido1,
					String apellido2, String DNI, Date fechaNac, String parentesco,
					String tlf, String email) {
		
		this.idTutor = idTutor;
		this.idAlumno = idAlumno;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.DNI = DNI;
		this.fechaNac = fechaNac;
		this.parentesco = parentesco;
		this.tlf = tlf;
		this.email = email;
	}
	
	public TutorVO(){}
	
	public int getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(int idTutor) {
		this.idTutor = idTutor;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "TutoresVO [idTutor=" + idTutor + ", idAlumno=" + idAlumno
				+ ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", DNI=" + DNI + ", fechaNac="
				+ fechaNac + ", parentesco=" + parentesco + ", tlf=" + tlf
				+ ", email=" + email + "]";
	}



}
