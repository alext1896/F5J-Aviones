package modelo;

public class Competicion {
	
	private int idCompeticion;
	private Prueba idPrueba;
	private Usuario numLicencia;
	
	public Competicion () {
		
	}
	

	public Competicion(int idCompeticion, Prueba idPrueba, Usuario numLicencia) {
		this.idCompeticion = idCompeticion;
		this.idPrueba = idPrueba;
		this.numLicencia = numLicencia;
	}

	public Competicion(Prueba idPrueba, Usuario numLicencia) {
		this.idPrueba = idPrueba;
		this.numLicencia = numLicencia;
	}
	
	public int getIdCompeticion() {
		return idCompeticion;
	}

	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}

	public Prueba getIdPrueba() {
		return idPrueba;
	}

	public void setIdPrueba(Prueba idPrueba) {
		this.idPrueba = idPrueba;
	}


	public Usuario getnumLicencia() {
		return numLicencia;
	}


	public void setnumLicencia(Usuario numLicencia) {
		this.numLicencia = numLicencia;
	}
	
}
