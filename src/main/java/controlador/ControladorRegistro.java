package controlador;

import org.hibernate.Session;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.Usuario;
import util.Utilidades;

public class ControladorRegistro {
	@FXML
	private TextField numLicencia;
	@FXML
	private TextField nombreUsuario;
	@FXML
	private TextField nombre;
	@FXML
	private TextField apellidos;
	@FXML
	private PasswordField password;

	private Stage dialogStage;
	private Usuario usuario;
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
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
	
	/**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
	@FXML
    private void handleOk() {
//        if (isInputValid()) {
        	usuario = new Usuario ();
            usuario.setNumLicencia(Integer.parseInt(numLicencia.getText()));
            usuario.setNombreUsuario(nombreUsuario.getText());
            usuario.setNombre(nombre.getText());
            usuario.setApellidos(apellidos.getText());
            usuario.setPassword(password.getText());
        	
            altaUsuario (usuario);
            
            okClicked = true;
            dialogStage.close();
//        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (numLicencia.getText() == null || numLicencia.getText().length() == 0) {
            errorMessage += "Número de licencia no valido!\n"; 
        }else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(numLicencia.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Número de Licencia no valido (Debe ser un numero)!\n"; 
            }
        }
        if (nombreUsuario.getText() == null || nombreUsuario.getText().length() == 0) {
            errorMessage += "Nombre de usuario no valido!\n"; 
        }
        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage += "Nombre no valida!\n"; 
        }

        if (apellidos.getText() == null || apellidos.getText().length() == 0) {
            errorMessage += "Apellidos no validos!\n"; 
        }

        if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "Password no valida!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campos no validos");
            alert.setHeaderText("Por favor corrija los campos incorrectos.");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
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


}
