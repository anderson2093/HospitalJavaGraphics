/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import hospital.conexion.Coneccion;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.mysql.jdbc.Driver;
/**
 *
 * @author Admin
 */
public class Identificacion {
    //Declaracion de campos
    private JFrame frIdentificacion;
    private JTextField nomBDField;
    private JTextField userField;
    private JTextField portField;
    private JPasswordField passwordField;
    
    //Declaracion inicial de la BD
    Connection con=null;
    ResultSet result=null;
    PreparedStatement statement=null;
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try
                {
                    Identificacion window =new Identificacion();
                    window.frIdentificacion.setVisible(true);
                    window.frIdentificacion.setLocationRelativeTo(null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Identificacion(){
        inicializar();
}

    private void inicializar() {
        this.frIdentificacion=new JFrame();
        this.frIdentificacion.setResizable(false);
        this.frIdentificacion.setLocationRelativeTo(null);
        
        this.frIdentificacion.setBounds(100,100,407,537);
        this.frIdentificacion.setDefaultCloseOperation(3);
        this.frIdentificacion.getContentPane().setLayout(null);
        
        this.nomBDField=new JTextField();
        this.nomBDField.setBounds(144, 90, 177, 47);
        this.frIdentificacion.getContentPane().add(this.nomBDField);
        this.nomBDField.setColumns(20);
        
        this.userField=new JTextField();
        this.userField.setBounds(144, 150, 177, 47);
        this.frIdentificacion.getContentPane().add(userField);
        this.userField.setColumns(10);
        
        this.portField=new JTextField();
        this.portField.setBounds(144, 210, 177, 47);
        this.frIdentificacion.getContentPane().add(portField);
        this.portField.setColumns(5);
        
        this.passwordField=new JPasswordField();
        this.passwordField.setBounds(144, 270, 177, 47);
        this.frIdentificacion.getContentPane().add(this.passwordField);
        this.passwordField.setColumns(25);
        
        
        JButton btnConeccion=new JButton("Conexión Local");
        btnConeccion.addActionListener(new ActionListener() {
            Connection con=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBD=Identificacion.this.nomBDField.getText().toString();
                String user=Identificacion.this.userField.getText().toString();
                String port=Identificacion.this.portField.getText().toString();
                char[] pass=Identificacion.this.passwordField.getPassword();
                String pasw=new String(pass);
                this.con=Coneccion.ConexionLocal(nomBD, user, pasw, port);
                if(this.con!=null){
                    JOptionPane.showMessageDialog(null, "Conexion exitosa!!!");
                    Hospital obj=new Hospital(this.con);
                    obj.setLocationRelativeTo(null);
                    obj.setVisible(true);
                    Identificacion.this.frIdentificacion.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Conexión Fallida!!!");
                }
            }
        });
        btnConeccion.setBounds(144, 330, 177, 47);
        this.frIdentificacion.add(btnConeccion);
        
        JButton btnDistante=new JButton("Conexion Distante");
        btnDistante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBD=Identificacion.this.nomBDField.getText().toString();
                String user=Identificacion.this.userField.getText().toString();
                char[] pass=Identificacion.this.passwordField.getPassword();
                String pasw=new String(pass);
                Identificacion.this.con=Coneccion.ConexionDistante(nomBD, user, pasw);
                if(Identificacion.this.con!=null){
                    JOptionPane.showMessageDialog(null, "Conexion Exitosa!!!");
                    Hospital obj=new Hospital(Identificacion.this.con);
                    obj.setLocationRelativeTo(null);
                    obj.setVisible(true);
                    Identificacion.this.frIdentificacion.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Conexion Fallida!!!");
                }
            }
        });
        btnDistante.setBounds(144, 390, 177, 47);
        this.frIdentificacion.getContentPane().add(btnDistante);
        
        JLabel lblNomBD=new JLabel("Nombre de la BD: ");
        lblNomBD.setForeground(Color.black);
        lblNomBD.setFont(new Font("Tahoma",1,12));
        lblNomBD.setBounds(12, 93, 120, 40);
        this.frIdentificacion.getContentPane().add(lblNomBD);
        
        JLabel lblUser=new JLabel("Usuario: ");
        lblUser.setForeground(Color.black);
        lblUser.setFont(new Font("Tahoma",1,12));
        lblUser.setBounds(12, 157, 80, 40);
        this.frIdentificacion.getContentPane().add(lblUser);
        
        JLabel lblPort=new JLabel("Port: ");
        lblPort.setForeground(Color.black);
        lblPort.setFont(new Font("Tahoma",1,12));
        lblPort.setBounds(12, 214, 80, 40);
        this.frIdentificacion.getContentPane().add(lblPort);
        
        JLabel lblPass=new JLabel("Contraseña: ");
        lblPass.setForeground(Color.black);
        lblPass.setFont(new Font("Tahoma",1,12));
        lblPass.setBounds(12, 277, 80, 40);
        this.frIdentificacion.getContentPane().add(lblPass);
    }
}
