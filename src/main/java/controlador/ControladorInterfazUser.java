package controlador;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelo.Prueba;
import util.DateUtil;
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
    
    
    
}
