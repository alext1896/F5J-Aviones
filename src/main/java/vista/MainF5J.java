package vista;

import controlador.ControladorLogin;
import modelo.Usuario;

public class MainF5J {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario (252, "alex", "alejandro", "guapache", "x");
		
		ControladorLogin controlador = new ControladorLogin ();
		System.out.println(controlador.validarUsuario(usuario));
		
		
	}

}
