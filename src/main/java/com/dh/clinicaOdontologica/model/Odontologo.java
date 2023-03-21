package com.dh.clinicaOdontologica.model;

public class Odontologo {
  private Integer id;
  private String numeroMatricula;
  private String nombre;
  private String apellido;
  
  public Odontologo(Integer id, String numeroMatricula, String nombre, String apellido) {
    this.id = id;
    this.numeroMatricula = numeroMatricula;
    this.nombre = nombre;
    this.apellido = apellido;
  }
  
  public Odontologo(String numeroMatricula, String nombre, String apellido) {
    this.numeroMatricula = numeroMatricula;
    this.nombre = nombre;
    this.apellido = apellido;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getNumeroMatricula() {
    return numeroMatricula;
  }
  
  public void setNumeroMatricula(String numeroMatricula) {
    this.numeroMatricula = numeroMatricula;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getApellido() {
    return apellido;
  }
  
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  
  @Override
  public String toString() {
    return "🧑🏻‍⚕️️ Odontologo{" +
            "id=" + id +
            ", numeroMatricula='" + numeroMatricula + '\'' +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            '}';
  }
}
