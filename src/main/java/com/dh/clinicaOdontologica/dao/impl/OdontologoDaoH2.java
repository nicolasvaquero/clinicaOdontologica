package com.dh.clinicaOdontologica.dao.impl;

import com.dh.clinicaOdontologica.dao.IDao;
import com.dh.clinicaOdontologica.model.Odontologo;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
  private final static String DB_JDBC_DRIVER = "org.h2.Driver";
  //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
  private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
  private final static String DB_USER ="sa";
  private final static String DB_PASSWORD = "";
  
  private static final Logger LOGGER= Logger.getLogger(OdontologoDaoH2.class);
  
  @Override
  public Odontologo guardar(Odontologo odontologo) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    LOGGER.info("Iniciando una operación gardar un odontologo");
    try {
      //1 Levantar el driver
      Class.forName(DB_JDBC_DRIVER);
      
      //2 Conectarnos
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      
      //3 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
      preparedStatement = connection.prepareStatement("INSERT INTO odontologos(numeroMatricula,nombre,apellido) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
      //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
      //preparedStatement.setInt(1,odontologo.getId());
      
      preparedStatement.setString(1, odontologo.getNumeroMatricula());
      preparedStatement.setString(2, odontologo.getNombre());
      preparedStatement.setString(3, odontologo.getApellido());
  
      //4 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
      preparedStatement.executeUpdate();
  
      ResultSet keys = preparedStatement.getGeneratedKeys();
      if(keys.next())
        odontologo.setId(keys.getInt(1));
  
      preparedStatement.close();
      
    } catch (Exception e) {
      LOGGER.error("Error al guardar un odontologo", e);
      e.printStackTrace();
    }
    return odontologo;
  }
  
  @Override
  public Odontologo buscar(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Odontologo odontologo = null;
    try {
      //1 Levantar el driver
      Class.forName(DB_JDBC_DRIVER);
      //2 Conectarnos
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
  
      //3 Crear una sentencia
      preparedStatement = connection.prepareStatement("SELECT id,numeroMatricula,nombre,apellido FROM odontologos where id = ?");
      preparedStatement.setInt(1,id);
      
      //4 Ejecutar una sentencia SQL
      ResultSet resultSet = preparedStatement.executeQuery();
      
      //5 Recorrer el resultado
      if(resultSet.next()){
        odontologo = new Odontologo(resultSet.getInt("id"),resultSet.getString("numeroMatricula"),
            resultSet.getString("nombre"),resultSet.getString("apellido"));
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return odontologo;
  }
  
  @Override
  public void eliminar(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    try {
      //1 Levantar el driver
      Class.forName(DB_JDBC_DRIVER);
      //2 Conectarnos
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
  
      //3 Crear una sentencia
      preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
      preparedStatement.setInt(1,id);
  
      //4 Ejecutar una sentencia SQL
      preparedStatement.executeUpdate();
      preparedStatement.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }
  
  @Override
  public void actualizar(Odontologo odontologo) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    try {
      //1 Levantar el driver
      Class.forName(DB_JDBC_DRIVER);
      //2 Conectarnos
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
  
      //3 Crear una sentencia
      preparedStatement = connection.prepareStatement("UPDATE odontologos SET numeroMatricula = ?, nombre = ?, apellido = ? WHERE id = ?");
      preparedStatement.setString(1, odontologo.getNumeroMatricula());
      preparedStatement.setString(2, odontologo.getNombre());
      preparedStatement.setString(3, odontologo.getApellido());
      preparedStatement.setInt(4, odontologo.getId());
  
      //4 Ejecutar una sentencia SQL
      preparedStatement.executeUpdate();
      preparedStatement.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public List<Odontologo> buscarTodos() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    List<Odontologo> odontologos = new ArrayList<>();
try {
      //1 Levantar el driver
      Class.forName(DB_JDBC_DRIVER);
      //2 Conectarnos
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
  
      //3 Crear una sentencia
      preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
      
      //4 Ejecutar una sentencia SQL
      ResultSet resultSet = preparedStatement.executeQuery();
      
      //5 Recorrer el resultado
      while(resultSet.next()){
        odontologos.add(new Odontologo(resultSet.getInt("id"),resultSet.getString("numeroMatricula"),
            resultSet.getString("nombre"),resultSet.getString("apellido")));
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return odontologos;
  }
}
