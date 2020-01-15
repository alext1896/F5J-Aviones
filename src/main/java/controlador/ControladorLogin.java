package controlador;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.Usuario;
import sun.security.util.Password;
import util.Utilidades;

public class ControladorLogin {

	@FXML
	private TextField usuario;
	@FXML
	private PasswordField password;
	
	private Usuario usuarioObjeto;

	private boolean okClicked = false;
	
    private Stage dialogStage;


	private final static String SELECT_USUARIO = "SELECT numLicencia, nombreUsuario, nombre, apellidos, password FROM Usuario WHERE nombreUsuario = :nombreUsuario AND  password = :password";
	
	 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
	@FXML
    private void handleOk() {
		
		usuarioObjeto.setNombreUsuario(usuario.getText());
		usuarioObjeto.setPassword(password.getText());
		
        if (validarUsuario(usuarioObjeto)) {
        	
        	Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Valido");
            alert.setHeaderText("Has entrado");
            alert.setContentText("De puta madre tio");
            
            alert.showAndWait();
            
            okClicked = true;
            dialogStage.close();

        }
    }

	public Usuario altaUsuario(Usuario usuario) {

		Session sesion = Utilidades.getSessionFactory().openSession();
		sesion.beginTransaction();
		sesion.save(usuario);
		sesion.getTransaction().commit();
		sesion.close();
		return usuario;
	}

	public boolean validarUsuario(Usuario usuario) {

		Session sesion = Utilidades.getSessionFactory().openSession();
		sesion.beginTransaction();

		Query<Usuario> query = sesion.createNativeQuery(SELECT_USUARIO, Usuario.class);

		query.setParameter("nombreUsuario", usuario.getNombreUsuario());
		query.setParameter("password", usuario.getPassword());

		List<Usuario> competidor = query.list();

		// competidor = query.list();

		sesion.getTransaction().commit();

		sesion.close();

		boolean validar = false;

		for (int i = 0; i < competidor.size(); i++) {
			if (competidor.isEmpty()) {
				validar = false;
			} else {
				validar = true;

				/*
				 * Hicimos esto para ver si salian los datos correctos for (int j = 0; j <
				 * competidor.size(); j++) {
				 * System.out.println(competidor.get(j).getApellidos()); }
				 */

			}
		}

		return validar;
	}

}
