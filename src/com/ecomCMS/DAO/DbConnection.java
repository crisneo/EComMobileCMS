package com.ecomCMS.DAO;

import java.sql.*;

/**
 * Clase que permite conectar con la base de datos
 * @author chenao
 *
 */
public class DbConnection {
   /**Parametros de conexion*/
   static String bd = "ecommobilecms";
   static String login = "root";
   static String password = "Control123";
   static String url = "jdbc:mysql://localhost/"+bd;

   Connection connection = null;

   /** Constructor de DbConnection */
   public DbConnection() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexi�n
         connection = DriverManager.getConnection(url,login,password);

         if (connection!=null){
            System.out.println("Conexi�n a base de datos "+bd+" OK\n");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexi�n*/
   public Connection getConnection(){
      return connection;
   }

   public void disconnect(){
      connection = null;
   }
}
