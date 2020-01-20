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
import util.Utilidades;
import vista.MainF5JApp;

public class ControladorLogin {

	@FXML
	private TextField usuario;
	@FXML
	private PasswordField password;
	
	private Usuario usuarioObjeto;

	private boolean okClicked = false;
	
    private Stage dialogStage;
    
    private MainF5JApp mainApp;

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
    
    public boolean pulsarOk() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
	@FXML
    private void funcionOk() {
		
		usuarioObjeto = new Usuario();
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
	
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person. 
     */
    @FXML
    private void funcionNuevoUsuario() {
        Usuario usuario = new Usuario();
        boolean okClicked = mainApp.mostrarVentanaRegistro(usuario);
//        if (okClicked) {
//            mainApp.getPersonData().add(tempUsuario);
//        }
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
	
	 /**
     * 
	 * Es llamado por la aplicación principal para devolverse una referencia a sí mismo.
     * @param mainApp
     */
    public void setMainApp(MainF5JApp mainApp) {
        this.mainApp = mainApp;
    }

}
