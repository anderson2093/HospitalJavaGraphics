/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Coneccion {
    static Connection con=null;
    public static Connection ConexionLocal(String nomBD,String user,String pasw,String port){
        if(!nomBD.equals(""))
        {
            try {
                System.out.println("Cargando el controlador...");
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Controlador OK!");
                System.out.println("Conectandose a la base de datos...");
                String urlDatabase;
                
                if(port.equals("")){
                    urlDatabase="jdbc:mysql://localhost/"+nomBD;
                }else{
                    urlDatabase="jdbc:mysql://localhost:"+port+"/"+nomBD;
                }
                if((user.equals(""))&& (pasw.equals("")))
                {
                    user="root";
                    pasw="";
                }
                System.out.println(urlDatabase);
                Connection con=DriverManager.getConnection(urlDatabase+"?user="+user+"&password="+pasw);
                if(con!=null)
                {
                    System.out.println("Conexión exitosa!");
                }
                return con;
            } catch (Exception e) {
                System.out.println("Falló la conexion");
                JOptionPane.showMessageDialog(null, e);
                return null;
            }
        }
        JOptionPane.showMessageDialog(null, "Especifique un nombre para la base de datos");
        return con;
    }
    
    public static Connection ConexionDistante(String nomBD,String user,String pasw  )
    {
        String coneccionString="jdbc:sqlserver://projetece.database.windows.net:1433;database=" +
                nomBD+";"+
                "Usuario="+user+"proyecto;"+
                "password="+pasw+";"+
                "encrypt=true;"+
                "trustServerCertificate=false;"+
                "hostNameInCertificate=*.database.window.net;"+
                "loginTimeOut=10";
        try {
            JOptionPane.showMessageDialog(null, "Esperando una respuesta del servidor ...");
            con=DriverManager.getConnection(coneccionString);
            JOptionPane.showMessageDialog(null, "Conectar...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        return con;
    }
}

