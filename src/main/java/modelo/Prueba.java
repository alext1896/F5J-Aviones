package modelo;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.AdaptadorFecha;

/**
 * @author jose.guapache
 * 
 */
public class Prueba {
	
	private int idPrueba;
	private String nombre;
	private LocalDate fechaPrueba;
	private LocalDate limitePrueba;
	private String ciudad;
	
    private StringProperty propiedadNombre;
    private ObjectProperty<LocalDate> propiedadFecha;
    
	
	public Prueba () {
		this(null, null);
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
	
	public StringProperty getPropiedadNombre() {
		Object v = this.getNombre();
		StringProperty var = new SimpleStringProperty((String) v);
		return var;
	}

	@XmlJavaTypeAdapter(AdaptadorFecha.class)
	public ObjectProperty<LocalDate> getPropiedadFecha() {
		Object v = this.getFechaPrueba();
		ObjectProperty<LocalDate> var = new SimpleObjectProperty<LocalDate>((LocalDate) v);
		return var;
	}
	
	public LocalDate getLimitePrueba() {
		return limitePrueba;
	}

	public void setLimitePrueba(LocalDate limitePrueba) {
		this.limitePrueba = limitePrueba;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Prueba [idPrueba=" + idPrueba + ", nombre=" + nombre + ", fechaPrueba=" + fechaPrueba + "]";
	}
}
