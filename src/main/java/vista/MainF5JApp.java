package vista;

import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import controlador.ControladorInterfazUser;
import controlador.ControladorLogin;
import controlador.ControladorRaiz;
import controlador.ControladorRegistro;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Prueba;
import modelo.Usuario;
import util.Utilidades;

public class MainF5JApp extends Application  {
	 private Stage primaryStage;
	 private BorderPane rootLayout;
	 private ObservableList<Prueba> pruebaData = FXCollections.observableArrayList();
	 private Session session;
	 private static String obtenerPruebas = "FROM Prueba";

	
	/**
     * Constructor
     */
    public MainF5JApp() {
    	
    }
    
    public void empezar() {
		session = Utilidades.getSessionFactory().openSession();
		session.beginTransaction();
	}
	
	private void terminar() {
		session.getTransaction().commit();
		session.close();
	}
    
    public ObservableList<Prueba> getPruebaData() {
		 return pruebaData;
	 }
    

	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AgendaApp");

        initRootLayout();

        showLoginOverview();
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Initializes the root layout and tries to load the last opened person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainF5JApp.class
                    .getResource("../vista/Raiz.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            ControladorRaiz controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showLoginOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainF5JApp.class.getResource("../vista/Login.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            ControladorLogin controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean mostrarVentanaRegistro(Usuario usuario) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainF5JApp.class.getResource("../vista/Registro.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ControladorRegistro controller = loader.getController();
            controller.setDialogStage(dialogStage);
//            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void mostrarVentanaInterfaz() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainF5JApp.class.getResource("../vista/InterfazUser.fxml"));
            AnchorPane interfazOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(interfazOverview);

            // Give the controller access to the main app.
            ControladorInterfazUser controller = loader.getController();
            controller.setMainApp(this);
             loadPruebaFromSQL();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void loadPruebaFromSQL() {
			empezar();
			@SuppressWarnings("unchecked")
			Query<Prueba> cu = session.createQuery(obtenerPruebas);
			List<Prueba> pruebas = cu.list();
			terminar();		
		
		try {
			pruebaData.clear();
			for(int i = 0; i < pruebas.size(); i++) {
				Prueba prueba = (Prueba) pruebas.get(i);
				pruebaData.add(prueba);
			}
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No se encuentran datos");
		}
	}

}
