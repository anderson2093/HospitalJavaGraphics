/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import util.Utils;

/**
 *
 * @author Admin
 */
public class GestionPersonal extends JFrame implements ActionListener{
    private JPanel panel1;
    private JTextField numeroField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField direccionField;
    private JTextField telfField;
    private JButton btnRegresar;
    private JTable tabla;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JLabel lblNewLabel_1;
    private JButton btnImprimir;
    private JLabel lblPuesto;
    private JButton btnDecreciente;
    private JButton btnCreciente;
    private JButton button;
    
    private Connection con;
    private ResultSet resultado = null;
    private PreparedStatement preparado = null;
    
    public GestionPersonal(Connection _con){
        this.con=_con;
        setResizable(false);
        setTitle("Gestion del Personal");
        
        setDefaultCloseOperation(3);
        setBounds(100, 100, 1377, 818);
    
        this.panel1 = new JPanel();
        this.panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.panel1);
        this.panel1.setLayout(null);
    
        this.numeroField = new JTextField();
        this.numeroField.setEditable(false);
        this.numeroField.setBounds(185, 162, 259, 58);
        this.panel1.add(this.numeroField);
        this.numeroField.setColumns(10);
        
        final JComboBox<String> comboBox = new JComboBox();
    
        this.nomField = new JTextField();
        this.nomField.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e)
      {
        String entree = GestionPersonal.this.nomField.getText().toString();
        
        String sql = "Select * from employe where nom like '" + entree + "%'";
        
        GestionPersonal.this.UpdateTableQuery(sql);
      }
    });
    this.nomField.setColumns(10);
    this.nomField.setBounds(185, 245, 259, 58);
    this.panel1.add(this.nomField);
    
    this.prenomField = new JTextField();
    
    this.prenomField.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e)
      {
        String entrada = GestionPersonal.this.prenomField.getText().toString();
        
        String sql = "Select * from employe where prenom like '" + entrada + "%'";
        
        GestionPersonal.this.UpdateTableQuery(sql);
      }
    });
    this.prenomField.setColumns(10);
    this.prenomField.setBounds(185, 327, 259, 58);
    this.panel1.add(this.prenomField);
    
    this.direccionField = new JTextField();
    
    this.direccionField.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e)
      {
        String entrada = GestionPersonal.this.direccionField.getText().toString();
        
        String sql = "Select * from employe where adresse like '" + entrada + "%'";
        
        GestionPersonal.this.UpdateTableQuery(sql);
      }
    });
    this.direccionField.setColumns(10);
    this.direccionField.setBounds(185, 410, 259, 58);
    this.panel1.add(this.direccionField);
    
    this.telfField = new JTextField();
    
    this.telfField.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e)
      {
        String entrada = GestionPersonal.this.telfField.getText().toString();
        
        String sql = "Select * from employe where tel like '" + entrada + "%'";
        
        GestionPersonal.this.UpdateTableQuery(sql);
      }
    });
    this.telfField.setColumns(10);
    this.telfField.setBounds(185, 491, 259, 58);
    this.panel1.add(this.telfField);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(534, 162, 825, 609);
    this.panel1.add(scrollPane);
    
    JLabel lblNumero = new JLabel("Numero");
    lblNumero.setBounds(70, 183, 56, 16);
    this.panel1.add(lblNumero);
    
    JLabel lblNom = new JLabel("Nombre");
    lblNom.setBounds(70, 266, 56, 16);
    this.panel1.add(lblNom);
    
    JLabel lblPrenom = new JLabel("Apellido");
    lblPrenom.setBounds(70, 348, 56, 16);
    this.panel1.add(lblPrenom);
    
    JLabel lblAdresse = new JLabel("Direccion :");
    lblAdresse.setBounds(70, 431, 85, 16);
    this.panel1.add(lblAdresse);
    
    JLabel lblNewLabel = new JLabel("Telefono :");
    lblNewLabel.setBounds(70, 512, 85, 16);
    this.panel1.add(lblNewLabel);
    
    comboBox.setToolTipText("Profesion de los empleados");
    comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Todos", "Enfermera", "Doctor" }));
    comboBox.setBounds(185, 573, 259, 58);
    this.panel1.add(comboBox);
    
    this.tabla = new JTable();
    
    this.tabla.addMouseListener(new MouseAdapter() {

      public void mouseClicked(MouseEvent e)
      {
        int ligne = GestionPersonal.this.tabla.getSelectedRow();
        
        String id = GestionPersonal.this.tabla.getModel().getValueAt(ligne, 0).toString();
        
        String sql = "select * from employe where numero = '" + id + "'";
        try
        {
          GestionPersonal.this.preparado= GestionPersonal.this.con.prepareStatement(sql);
          GestionPersonal.this.resultado = GestionPersonal.this.preparado.executeQuery();
          if (GestionPersonal.this.resultado.next())
          {
              GestionPersonal.this.numeroField.setText(GestionPersonal.this.resultado.getString("Numero"));
            GestionPersonal.this.nomField.setText(GestionPersonal.this.resultado.getString("Nombre"));
            GestionPersonal.this.prenomField.setText(GestionPersonal.this.resultado.getString("Apellido"));
            GestionPersonal.this.direccionField.setText(GestionPersonal.this.resultado.getString("Direccion"));
            GestionPersonal.this.telfField.setText(GestionPersonal.this.resultado.getString("Telefono"));
          }
        }
        catch (SQLException e1)
        {
          e1.printStackTrace();
        }
        sql = "select * from employe join docteur on employe.numero = docteur.numero where employe.numero = " + id;
        try
        {
          GestionPersonal.this.preparado = GestionPersonal.this.con.prepareStatement(sql);
          GestionPersonal.this.resultado = GestionPersonal.this.preparado.executeQuery();
          if (GestionPersonal.this.resultado.next()) {
              comboBox.setSelectedItem("Doctor");
          } else {
            comboBox.setSelectedItem("Enfermera");
          }
        }
        catch (SQLException e1)
        {
          e1.printStackTrace();
        }
      }
    });
    UpdateTable();
    this.tabla.setBackground(Color.WHITE);
    this.tabla.setFillsViewportHeight(true);
    scrollPane.setViewportView(this.tabla);
    
  
    this.lblPuesto = new JLabel("Puesto : ");
    this.lblPuesto.setBounds(70, 594, 56, 16);
    this.panel1.add(this.lblPuesto);
    
    JButton btnMenu = new JButton("Menu Principal");
    btnMenu.addActionListener(this);
    /*
            new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Hospital obj = new Hospital(this.con);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
        GestionPersonal.this.dispose();
      }
    });*/
    btnMenu.setBounds(27, 27, 126, 42);
    this.panel1.add(btnMenu);
    
    this.button = new JButton(">");
    this.button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (comboBox.getSelectedItem().toString().equals("Doctor"))
        {
          String sql = "select employe.numero , employe.nom , employe.prenom , employe.adresse , employe.tel , docteur.specialite from employe join docteur ON employe.numero = docteur.numero";
          
          GestionPersonal.this.UpdateTableQuery(sql);
          
          GestionPersonal.this.prenomField.setText("");
          GestionPersonal.this.nomField.setText("");
          GestionPersonal.this.direccionField.setText("");
          GestionPersonal.this.telfField.setText("");
          GestionPersonal.this.numeroField.setText("");
        }
        else if (comboBox.getSelectedItem().toString().equals("Enfermera"))
        {
          String sql = "select employe.numero , employe.nom , employe.prenom , employe.adresse , employe.tel , infirmier.code_service , infirmier.rotation , infirmier.salaire from employe join infirmier ON employe.numero = infirmier.numero";
          
          GestionPersonal.this.UpdateTableQuery(sql);
          
          GestionPersonal.this.prenomField.setText("");
          GestionPersonal.this.nomField.setText("");
          GestionPersonal.this.direccionField.setText("");
          GestionPersonal.this.telfField.setText("");
          GestionPersonal.this.numeroField.setText("");
        }
        else if (comboBox.getSelectedItem().toString().equals("Todos"))
        {
          GestionPersonal.this.UpdateTable();
        }
      }
    });
    this.button.setBounds(456, 573, 56, 58);
    this.panel1.add(this.button);
    
    this.btnCreciente = new JButton("Creciente");
    this.btnCreciente.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int col = GestionPersonal.this.tabla.getSelectedColumn();
        String attr = GestionPersonal.this.tabla.getColumnName(col);
        if ((attr.equals("code_service")) || (attr.equals("rotation")) || (attr.equals("salaire")))
        {
          String sql = "Select employe.* , infirmier.code_service , infirmier.rotation , infirmier.salaire from employe join infirmier on infirmier.numero = employe.numero order by " + 
            attr + " asc";
          
          GestionPersonal.this.UpdateTableQuery(sql);
        }
        else if (attr.equals("specialite"))
        {
          String sql = "Select employe.* , docteur.specialite from employe join docteur on docteur.numero = employe.numero order by " + 
            attr + " asc";
          GestionPersonal.this.UpdateTableQuery(sql);
        }
        else
        {
          String sql = "select * from employe order by " + attr + " asc";
          
          GestionPersonal.this.UpdateTableQuery(sql);
        }
      }
    });
    this.btnCreciente.setBounds(1243, 124, 116, 25);
    this.panel1.add(this.btnCreciente);
    
    this.btnDecreciente = new JButton("Decreciente");
    this.btnDecreciente.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int col = GestionPersonal.this.tabla.getSelectedColumn();
        String attr = GestionPersonal.this.tabla.getColumnName(col);
        if ((attr.equals("code_service")) || (attr.equals("rotation")) || (attr.equals("salaire")))
        {
          String sql = "Select employe.* , infirmier.code_service , infirmier.rotation , infirmier.salaire from employe join infirmier on infirmier.numero = employe.numero order by " + 
            attr + " desc";
          
          GestionPersonal.this.UpdateTableQuery(sql);
        }
        else if (attr.equals("specialite"))
        {
          String sql = "Select employe.* , docteur.specialite from employe join docteur on docteur.numero = employe.numero order by " + 
            attr + " desc";
          GestionPersonal.this.UpdateTableQuery(sql);
        }
        else
        {
          String sql = "select * from employe order by " + attr + " desc";
          
          GestionPersonal.this.UpdateTableQuery(sql);
        }
      }
    });
    this.btnDecreciente.setBounds(1134, 124, 97, 25);
    this.panel1.add(this.btnDecreciente);
    
    this.btnActualizar = new JButton("Actualizar");
    this.btnActualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        GestionPersonal.this.UpdateTable();
      }
    });
    this.btnActualizar.setBounds(1025, 124, 97, 25);
    this.panel1.add(this.btnActualizar);
    
   
    
    /*this.lblNewLabel_1 = new JLabel("New label");
    //this.lblNewLabel_1.setIcon(new ImageIcon(GestionPersonal.class.getResource("/img/fond_utilisateur.png")));
    this.lblNewLabel_1.setBounds(0, 0, 1371, 783);
    this.panel1.add(this.lblNewLabel_1);*/
    
    
    this.btnAgregar = new JButton("Agregar");
    this.btnAgregar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String prenom = GestionPersonal.this.prenomField.getText().toString();
        String nom = GestionPersonal.this.nomField.getText().toString();
        String tel = GestionPersonal.this.telfField.getText().toString();
        String direccion = GestionPersonal.this.direccionField.getText().toString();
        Integer numero = Integer.valueOf(0);
        
        String sql = "select MAX(numero) , nom , prenom from employe";
        try
        {
          GestionPersonal.this.preparado = GestionPersonal.this.con.prepareStatement(sql);
          GestionPersonal.this.resultado = GestionPersonal.this.preparado.executeQuery();
          if (GestionPersonal.this.resultado.next())
          {
            numero = Integer.valueOf(GestionPersonal.this.resultado.getInt("max(numero)"));
            System.out.println("test");
            numero = Integer.valueOf(numero.intValue() + 1);
          }
        }
        catch (SQLException e1)
        {
          e1.printStackTrace();
        }
        if (comboBox.getSelectedItem().toString().equals("Doctor"))
        {
          AnadierDoctor obj = new AnadierDoctor(GestionPersonal.this.con, nom, prenom, tel, direccion, numero.toString(), false);
          obj.setLocationRelativeTo(null);
          obj.setVisible(true);
          GestionPersonal.this.prenomField.setText("");
          GestionPersonal.this.nomField.setText("");
          GestionPersonal.this.direccionField.setText("");
          GestionPersonal.this.telfField.setText("");
          GestionPersonal.this.numeroField.setText("");
        }
        else if (comboBox.getSelectedItem().toString().equals("Enfermera"))
        {
          String salaire = "";
          
          AniadirEnfermera obj = new AniadirEnfermera(GestionPersonal.this.con, nom, prenom, tel, direccion, numero.toString(), salaire, false);
          obj.setLocationRelativeTo(null);
          obj.setVisible(true);
          GestionPersonal.this.prenomField.setText("");
          GestionPersonal.this.nomField.setText("");
          GestionPersonal.this.direccionField.setText("");
          GestionPersonal.this.telfField.setText("");
          GestionPersonal.this.numeroField.setText("");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Especifique el puesto a agregar");
        }
        //GestionPersonal.this.insertTable();
        GestionPersonal.this.UpdateTable();
      }
    });
        this.btnAgregar.setBounds(68, 688, 97, 55);
        this.panel1.add(this.btnAgregar);
  }
  
  public void insertTable()
  {
      String sql="Insert into employe(nom,prenom,adresse,tel) values("+nomField.getText().toString()+","+prenomField.getText().toString()+","+direccionField.getText().toString()+","+telfField.getText().toString()+")";
      try {
        /*this.preparado = this.con.prepareStatement(sql);
        this.resultado = this.preparado.executeQuery();
        this.tabla.setModel(Utils.resultSetToTableModel(this.resultado));*/
        
        /*
        this.preparado=con.prepareStatement(sql);
        preparado.setString(1, nomField.getText().toString());
        preparado.setString(2, prenomField.getText().toString());
        preparado.setString(3, direccionField.getText().toString());
        preparado.setString(4, telfField.getText().toString());
        preparado.executeUpdate();
        
        */
        preparado.executeUpdate("Insert into employe(nom,prenom,adresse,tel) values("+nomField.getText().toString()+","+prenomField.getText().toString()+","+direccionField.getText().toString()+","+telfField.getText().toString()+")");
       // preparado.executeUpdate();
      } catch (Exception e) {
          e.printStackTrace();
          
      }
  }
  public void UpdateTable()
  {
    String sql = "Select * from employe";
    try
    {
      this.preparado = this.con.prepareStatement(sql);
      this.resultado = this.preparado.executeQuery();
      
      this.tabla.setModel(Utils.resultSetToTableModel(this.resultado));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
    
     public void UpdateTableQuery(String sql)
  {
          try
    {
      this.preparado = this.con.prepareStatement(sql);
      this.resultado = this.preparado.executeQuery();
      
      this.tabla.setModel(Utils.resultSetToTableModel(this.resultado));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        Hospital obj = new Hospital(this.con);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
        GestionPersonal.this.dispose();
    }
}

