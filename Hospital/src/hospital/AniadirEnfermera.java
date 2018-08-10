/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Admin
 */
public class AniadirEnfermera extends JFrame{

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField nomfield;
  private JTextField prenomfield;
  private JTextField adressefield;
  private JTextField telfield;
  private JTextField salairefield;
  
  public AniadirEnfermera(final Connection cnx, String _nom, String _prenom, String _tel, String _adresse, final String _num, String salaire, final boolean modify)
  {
    setResizable(false);
    if (!modify) {
      setTitle("Añadir una enfermera");
    } else {
      setTitle("Modificar una enfermera");
    }
    setDefaultCloseOperation(2);
    setBounds(100, 100, 467, 685);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.nomfield = new JTextField();
    this.nomfield.setText(_nom);
    this.nomfield.setColumns(10);
    this.nomfield.setBounds(142, 36, 259, 58);
    this.contentPane.add(this.nomfield);
    
    this.prenomfield = new JTextField();
    this.prenomfield.setText(_prenom);
    this.prenomfield.setColumns(10);
    this.prenomfield.setBounds(142, 107, 259, 58);
    this.contentPane.add(this.prenomfield);
    
    this.adressefield = new JTextField();
    this.adressefield.setText(_adresse);
    this.adressefield.setColumns(10);
    this.adressefield.setBounds(142, 178, 259, 58);
    this.contentPane.add(this.adressefield);
    
    this.telfield = new JTextField();
    this.telfield.setText(_tel);
    this.telfield.setColumns(10);
    this.telfield.setBounds(142, 249, 259, 58);
    this.contentPane.add(this.telfield);
    
    JLabel label = new JLabel("Nombre :");
    label.setBounds(54, 57, 56, 16);
    this.contentPane.add(label);
    
    JLabel label_1 = new JLabel("Apellido :");
    label_1.setBounds(39, 128, 56, 16);
    this.contentPane.add(label_1);
    
    JLabel label_2 = new JLabel("Direccion :");
    label_2.setBounds(39, 199, 85, 16);
    this.contentPane.add(label_2);
    
    JLabel label_3 = new JLabel("Telefono :");
    label_3.setBounds(25, 270, 85, 16);
    this.contentPane.add(label_3);
    
    final JComboBox<String> comboBox = new JComboBox();
    comboBox.setModel(new DefaultComboBoxModel(new String[] { "DIA", "NOCHE" }));
    comboBox.setToolTipText("Profession del empleado");
    comboBox.setBounds(142, 320, 259, 58);
    this.contentPane.add(comboBox);
    
    JLabel lblSpcialit = new JLabel("Rotacion :");
    lblSpcialit.setBounds(39, 341, 56, 16);
    this.contentPane.add(lblSpcialit);
    
    JLabel lblService = new JLabel("Servicio : ");
    lblService.setBounds(54, 412, 56, 16);
    this.contentPane.add(lblService);
    
    final JComboBox<String> comboBox_1 = new JComboBox();
    comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "CAR", "CHG", "REA" }));
    comboBox_1.setBounds(142, 391, 259, 58);
    this.contentPane.add(comboBox_1);
    
    this.salairefield = new JTextField();
    if (modify) {
      this.salairefield.setText(salaire);
    }
    this.salairefield.setColumns(10);
    this.salairefield.setBounds(142, 462, 259, 58);
    this.contentPane.add(this.salairefield);
    
    JLabel lblSalaire = new JLabel("Salario : ");
    lblSalaire.setBounds(54, 483, 56, 16);
    this.contentPane.add(lblSalaire);
    JButton btnAniadirButton;
    btnAniadirButton=new JButton();
    //btnAniadiButton1=new JButton();
    if (!modify) {
      btnAniadirButton = new JButton("Añadir");
    } else {
      //btnAniadirButton1 = new JButton("Modificar");
    }
    btnAniadirButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String prenom = AniadirEnfermera.this.prenomfield.getText().toString();
        String nom = AniadirEnfermera.this.nomfield.getText().toString();
        String tel = AniadirEnfermera.this.telfield.getText().toString();
        String adresse = AniadirEnfermera.this.adressefield.getText().toString();
        String num = _num;
        String rotation = comboBox.getSelectedItem().toString();
        String codeservice = comboBox_1.getSelectedItem().toString();
        String salaire = AniadirEnfermera.this.salairefield.getText().toString();
        if ((!prenom.equals("")) && (!nom.equals("")) && (!tel.equals("")) && (!adresse.equals("")))
        {
          String sql;
          if (!modify) {
            sql = "INSERT into employe (prenom , nom , tel  , adresse , numero) values (? , ? , ? , ? , ?)";
          } else {
            sql = "Update employe set prenom = ? , nom = ? , tel = ? , adresse = ? where numero = " + num;
          }
          try
          {
            PreparedStatement prepared = cnx.prepareStatement(sql);
            prepared.setString(1, prenom);
            prepared.setString(2, nom);
            prepared.setString(3, tel);
            prepared.setString(4, adresse);
            if (!modify) {
              prepared.setString(5, num);
            }
            prepared.execute();
            if (!modify) {
              sql = "INSERT into infirmier (code_service , rotation , salaire , numero) values (? , ? , ? , ?)";
            } else {
              sql = "update infirmier set code_service = ? , rotation = ? , salaire = ? where numero = " + num;
            }
            prepared = cnx.prepareStatement(sql);
            
            prepared.setString(1, codeservice);
            prepared.setString(2, rotation);
            prepared.setString(3, salaire);
            if (!modify) {
              prepared.setString(4, num);
            }
            prepared.execute();
            if (!modify)
            {
              JOptionPane.showMessageDialog(null, "Nueva enfermera agregada");
              AniadirEnfermera.this.dispose();
            }
            else
            {
              JOptionPane.showMessageDialog(null, "Informacion modificada");
              AniadirEnfermera.this.dispose();
            }
          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Completa los campos");
        }
      }
    });
    btnAniadirButton.setBounds(69, 546, 311, 79);
    this.contentPane.add(btnAniadirButton);
  }
}
