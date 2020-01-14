package modelo;

public class Usuario {

	private int numLicencia;
	private String nombreUsuario;
	private String nombre;
	private String apellidos;
	private String password;
	
	public Usuario(int numLicencia, String nombreUsuario, String nombre, String apellidos, String password) {
		this.numLicencia = numLicencia;
		this.nombreUsuario = nombreUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
	}
	
	
	public Usuario(String nombre, String nombreUsuario, String apellidos, String password) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
	}
	
	
	public Usuario() {
	}
	
	public int getNumLicencia() {
		return numLicencia;
	}


	public void setNumLicencia(int numLicencia) {
		this.numLicencia = numLicencia;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	
	
}
