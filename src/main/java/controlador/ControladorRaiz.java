package controlador;

import vista.MainF5JApp;

public class ControladorRaiz {
	// Reference to the main application
	private MainF5JApp mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainF5JApp mainApp) {
		this.mainApp = mainApp;
	}
}
