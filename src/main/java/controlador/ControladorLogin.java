package controlador;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import modelo.Usuario;
import util.Utilidades;

public class ControladorLogin {
	private  final static String SELECT_USUARIO = "SELECT numLicencia, nombreUsuario, nombre, apellidos, password FROM Usuario WHERE nombreUsuario = :nombreUsuario AND  password = :password";

	public boolean validarUsuario(Usuario usuario) {

		Session sesion = Utilidades.getSessionFactory().openSession();
		sesion.beginTransaction();
		
		Query<Usuario> query = sesion.createNativeQuery(SELECT_USUARIO, Usuario.class);

		query.setParameter("nombreUsuario", usuario.getNombreUsuario() );
		query.setParameter("password", usuario.getPassword());

		
		List<Usuario> competidor = query.list();
		
		//competidor = query.list();
		
		sesion.getTransaction().commit();
			
		sesion.close();
		
		boolean validar = false;
		
		for (int i = 0; i < competidor.size(); i++) {
			if (competidor.isEmpty()) {
				validar = false;
			}else {
				validar = true;
				for (int j = 0; j < competidor.size(); j++) {
					System.out.println(competidor.get(j).getApellidos());
				}
			}
		}
		
		return validar;
	}
	
	

}
