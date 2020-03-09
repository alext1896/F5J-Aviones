package controlador;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	@FXML
	private Label numLicencia;
	@FXML
	private Label nombreUsuario;

	ControladorLogin controlador = new ControladorLogin();
	private int idPrueba1;
	private int licencia = controlador.getNewUsuario().getNumLicencia();

	Prueba prueba = new Prueba();
	Usuario usuario = new Usuario();

	private final static String SELECT_PRUEBA = "SELECT * FROM Prueba WHERE idPrueba = :idPrueba";

	private final static String COUNT_PILOTOS = "SELECT * FROM Competicion WHERE idPrueba = :idPrueba";

	private final static String VALIDAR_PILOTOS = "SELECT * FROM Competicion WHERE idPrueba = :idPrueba AND numLicencia = :numLicencia";

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		pruebaColumna.setCellValueFactory(cellData -> cellData.getValue().getPropiedadNombre());
		fechaColumna.setCellValueFactory(cellData -> cellData.getValue().getPropiedadFecha());
		// Clear person details.
		showPruebaDetails(null);

		// Listen for selection changes and show the person details when changed.
		pruebaTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPruebaDetails(newValue));

		numLicencia.setText(Integer.toString(controlador.getNewUsuario().getNumLicencia()));
		nombreUsuario.setText(controlador.getNewUsuario().getNombreUsuario());

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
	 * Es llamado por la aplicación principal para devolverse una referencia a sí
	 * mismo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainF5JApp mainApp) {
		this.mainApp = mainApp;
		pruebaTable.setItems(mainApp.getPruebaData());

	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
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
			numPilotosEtiqueta.setText(contarPilotosInscritos());

			// numPilotosEtiqueta.setText(prueba.get);
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

		prueba.setIdPrueba(idPrueba1);

		if (validar(idPrueba1)) {
			Session sesion = Utilidades.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query<Prueba> query = sesion.createNativeQuery(SELECT_PRUEBA, Prueba.class);
			query.setParameter("idPrueba", prueba.getIdPrueba());
			List<Prueba> pruebaLis = query.list();
			sesion.getTransaction().commit();
			sesion.close();

			ControladorLogin controlador = new ControladorLogin();
			usuario = controlador.getNewUsuario();
			Competicion altaPrueba = new Competicion(prueba, usuario);
			Date d = new Date();
			LocalDate diaHoy = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate diaPrueba = pruebaLis.get(0).getFechaPrueba();
			LocalDate diaLimite = pruebaLis.get(0).getLimitePrueba();

			if ((diaPrueba.compareTo(diaHoy) < 0) && (diaLimite.compareTo(diaHoy) > 0)) {
				Session seesion = Utilidades.getSessionFactory().openSession();

				seesion.beginTransaction();
				seesion.save(altaPrueba);
				seesion.getTransaction().commit();
				seesion.close();

				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Valido");
				alert.setHeaderText("Has entrado en la prueba");
				alert.setContentText("Suerte");

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Invalido");
				alert.setHeaderText("No has podido entrar en la prueba");
				alert.setContentText("Aun no es la competición o se ha pasado la fecha límite");

				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR");
			alert.setHeaderText("Ya estás inscrito a esta prueba");
			alert.setContentText("Gilipollas");

			alert.showAndWait();
		}

	}

	public void bajaPrueba() {
		prueba.setIdPrueba(idPrueba1);

		if (validar(idPrueba1) == false) {
			Session sesion = Utilidades.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query<Competicion> query = sesion.createNativeQuery(VALIDAR_PILOTOS, Competicion.class);
			query.setParameter("idPrueba", prueba.getIdPrueba());
			query.setParameter("numLicencia", licencia);
			List<Competicion> competicionLis = query.list();
			sesion.getTransaction().commit();
			sesion.close();

			Session seesion = Utilidades.getSessionFactory().openSession();

			seesion.beginTransaction();
			seesion.delete(competicionLis.get(0));
			seesion.getTransaction().commit();
			seesion.close();

			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("¡Exito!");
			alert.setHeaderText("Te has elimidado de esta prueba");
			alert.setContentText("Tonto.");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR");
			alert.setHeaderText("No estás inscrito a esta prueba");
			alert.setContentText("Gilipollas");

			alert.showAndWait();
		}
	}

	public String contarPilotosInscritos() {
		Session sesion = Utilidades.getSessionFactory().openSession();
		sesion.beginTransaction();
		Query<Competicion> query = sesion.createNativeQuery(COUNT_PILOTOS, Competicion.class);
		prueba.setIdPrueba(idPrueba1);
		query.setParameter("idPrueba", prueba.getIdPrueba());
		List<Competicion> competicion = query.list();
		sesion.getTransaction().commit();
		sesion.close();

		return Integer.toString(competicion.size());

	}

	public boolean validar(int idPrueba) {

		Session sesion = Utilidades.getSessionFactory().openSession();
		sesion.beginTransaction();
		Query<Competicion> query = sesion.createNativeQuery(VALIDAR_PILOTOS, Competicion.class);

		query.setParameter("idPrueba", idPrueba);
		query.setParameter("numLicencia", licencia);
		List<Competicion> competicion = query.list();
		sesion.getTransaction().commit();
		sesion.close();

		if (competicion.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
