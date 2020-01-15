package controlador;

import org.hibernate.Session;
import org.hibernate.query.Query;

import modelo.Usuario;
import util.Utilidades;

public class ControladorLogin {
	private  final static String SELECT_USUARIO = "SELECT numLicencia, nombreUsuario, nombre, apellidos, password FROM Usuario WHERE nombreUsuario = :nombreUsuario AND  password = :password";

	public boolean validarUsuario(Usuario usuario) {

		Session sesion = Utilidades.getSessionFactory().openSession();
		sesion.beginTransaction();
		@SuppressWarnings("unchecked")
		
		Query<Usuario> query = sesion.createQuery(SELECT_USUARIO);
		query.setParameter("nombreUsuario", usuario.getNombreUsuario() );
		query.setParameter("password", usuario.getPassword());

		Usuario competidor = (Usuario) query.uniqueResult();
		sesion.getTransaction().commit();
			
		sesion.close();

		if (competidor == null) {
			return false;
		}else {
			return true;
		}

	}

}
