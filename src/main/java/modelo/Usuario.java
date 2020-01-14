package modelo;

public class Usuario {

	private int id;
	private String nombre;
	private String apellidos;
	private String contraseña;
	
	public Usuario(int id, String nombre, String apellidos, String contraseña) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
	}
	
	
	public Usuario(String nombre, String apellidos, String contraseña) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
	}
	
	
	public Usuario() {
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	
}
