package com.example.apppeluquera.model;

public class Cita {

  String id;
  String fecha;
  String hora;
  String idPeluqueria;
  String idServicio;
  String idUsuario;
  String nombrePeluqueria;
  String nombreServicio;
  String nombreCliente;

  public Cita(){}

  public Cita(String fecha, String hora, String idPeluqueria, String idServicio, String idUsuario, String nombrePeluqueria, String nombreServicio, String nombreCliente) {
    this.fecha = fecha;
    this.hora = hora;
    this.idPeluqueria = idPeluqueria;
    this.idServicio = idServicio;
    this.idUsuario = idUsuario;
    this.nombrePeluqueria = nombrePeluqueria;
    this.nombreServicio = nombreServicio;
    this.nombreCliente = nombreCliente;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getIdPeluqueria() {
    return idPeluqueria;
  }

  public void setIdPeluqueria(String idPeluqueria) {
    this.idPeluqueria = idPeluqueria;
  }

  public String getIdServicio() {
    return idServicio;
  }

  public void setIdServicio(String idServicio) {
    this.idServicio = idServicio;
  }

  public String getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(String idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNombrePeluqueria() {
    return nombrePeluqueria;
  }

  public void setNombrePeluqueria(String nombrePeluqueria) {
    this.nombrePeluqueria = nombrePeluqueria;
  }

  public String getNombreServicio() {
    return nombreServicio;
  }

  public void setNombreServicio(String nombreServicio) {
    this.nombreServicio = nombreServicio;
  }

  public String getNombreCliente() {
    return nombreCliente;
  }

  public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
  }

  @Override
  public String toString() {
    return "Cita{" +
            "id='" + id + '\'' +
            ", fecha='" + fecha + '\'' +
            ", idPeluqueria='" + idPeluqueria + '\'' +
            ", idServicio='" + idServicio + '\'' +
            ", idUsuario='" + idUsuario + '\'' +
            ", nombrePeluqueria='" + nombrePeluqueria + '\'' +
            ", nombreServicio='" + nombreServicio + '\'' +
            ", nombreCliente='" + nombreCliente + '\'' +
            '}';
  }
}
