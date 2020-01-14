package modelo;

import java.time.LocalDate;

/**
 * @author jose.guapache
 * 
 */
public class Prueba {
	
	private int idPrueba;
	private String nombre;
	private LocalDate fechaPrueba;
	
	public Prueba () {
		
	}
	
	public Prueba (int idPrueba, String nombre, LocalDate fechaPrueba) {
		this.idPrueba = idPrueba;
		this.nombre = nombre;
		this.fechaPrueba = fechaPrueba;
	}
	
	public Prueba (String nombre, LocalDate fechaPrueba) {
		this.nombre = nombre;
		this.fechaPrueba = fechaPrueba;
	}
	
	public int getIdPrueba() {
		return idPrueba;
	}
	public void setIdPrueba(int idPrueba) {
		this.idPrueba = idPrueba;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaPrueba() {
		return fechaPrueba;
	}
	public void setFechaPrueba(LocalDate fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
	}

	@Override
	public String toString() {
		return "Prueba [idPrueba=" + idPrueba + ", nombre=" + nombre + ", fechaPrueba=" + fechaPrueba + "]";
	}
}
