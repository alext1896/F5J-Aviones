package controlador;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelo.Competicion;
import modelo.Prueba;
import modelo.Usuario;
import util.DateUtil;
import util.Utilidades;
import vista.MainF5JApp;

public class ControladorInterfazUser {

    private Stage dialogStage;
    private MainF5JApp mainApp;
    
    @FXML
    private TableView<Prueba> pruebaTable;
    @FXML
    private TableColumn<Prueba, String> pruebaColumna;
    @FXML
    private TableColumn<Prueba, LocalDate> fechaColumna;
    @FXML
    private Label idEtiqueta;
    @FXML
    private Label nombreEtiqueta;
    @FXML
    private Label limiteEtiqueta;
    @FXML
    private Label ciudadEtiqueta;
    @FXML
    private Label numPilotosEtiqueta;
    
    private int idPrueba1; 
    
//    private final static String COUNT_PILOTOS = "SELECT COUNT(idCompeticion) FROM Competicion WHERE idPrueba = :idPrueba";
    
//    private String contarPilotos = "SELECT COUNT(idPru"

	 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        pruebaColumna.setCellValueFactory(cellData -> cellData.getValue().getPropiedadNombre());
        fechaColumna.setCellValueFactory(cellData -> cellData.getValue().getPropiedadFecha());
     // Clear person details.
        showPruebaDetails(null);

        // Listen for selection changes and show the person details when changed.
        pruebaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPruebaDetails(newValue));
        
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
     * 
	 * Es llamado por la aplicación principal para devolverse una referencia a sí mismo.
     * @param mainApp
     */
    public void setMainApp(MainF5JApp mainApp) {
        this.mainApp = mainApp;
        pruebaTable.setItems(mainApp.getPruebaData());

    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPruebaDetails(Prueba prueba) {
        if (prueba != null) {
            // Fill the labels with info from the person object.
        	idEtiqueta.setText(Integer.toString(prueba.getIdPrueba()));
        	nombreEtiqueta.setText(prueba.getNombre());
            limiteEtiqueta.setText(DateUtil.format(prueba.getLimitePrueba()));
            ciudadEtiqueta.setText(prueba.getCiudad());
            
            idPrueba1 = prueba.getIdPrueba();
            //numPilotosEtiqueta.setText(prueba.get);
            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
        	idEtiqueta.setText("");
        	nombreEtiqueta.setText("");
        	limiteEtiqueta.setText("");
        	ciudadEtiqueta.setText("");
        	numPilotosEtiqueta.setText("");
        }
    }
    
    public void altaPrueba() {

		Session sesion = Utilidades.getSessionFactory().openSession();
		sesion.beginTransaction();
		Prueba prueba = new Prueba();
		Usuario usuario = new Usuario ();
		prueba.setIdPrueba(idPrueba1);
		usuario.setNumLicencia(12);
		Competicion altaPrueba = new Competicion (prueba, usuario);
		sesion.save(altaPrueba);
		sesion.getTransaction().commit();
		sesion.close();
	}
    
//    public void contarPilotosInscritos (Competicion idCompeticion) {
//    	Session sesion = Utilidades.getSessionFactory().openSession();
//		sesion.beginTransaction();
//		Query<Usuario> query = sesion.createNativeQuery(COUNT_PILOTOS, Competicion.class);
//
//		query.setParameter("idCompeticion", idPrueba.getIdCompeticion());
//
//		List<Usuario> competicion = query.list();
//
//		// competicion = query.list();
//
//		sesion.getTransaction().commit();
//
//		sesion.close();
//    	
//    }
    
    
    
    
    
    
}
