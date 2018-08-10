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
public class AnadierDoctor extends JFrame
{
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField  nomfield;
  private JTextField prenomfield;
  private JTextField adressefield;
  private JTextField telfield;
  
  public AnadierDoctor(final Connection cnx, String _nom, String _prenom, String _tel, String _adresse, final String _num, final boolean modify)
  {
    this.setResizable(false);
    if (!modify) {
      setTitle("Añadir un doctor");
    } else {
      setTitle("Modificar un doctor");
    }
    setDefaultCloseOperation(2);
    setBounds(100, 100, 463, 612);
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
    this.prenomfield.setBounds(142, 118, 259, 58);
    this.contentPane.add(this.prenomfield);
    
    this.adressefield = new JTextField();
    this.adressefield.setText(_adresse);
    this.adressefield.setColumns(10);
    this.adressefield.setBounds(142, 201, 259, 58);
    this.contentPane.add(this.adressefield);
    
    this.telfield = new JTextField();
    this.telfield.setText(_tel);
    this.telfield.setColumns(10);
    this.telfield.setBounds(142, 282, 259, 58);
    this.contentPane.add(this.telfield);
    
    JLabel label = new JLabel("Nombre :");
    label.setBounds(54, 57, 56, 16);
    this.contentPane.add(label);
    
    JLabel label_1 = new JLabel("Apellido :");
    label_1.setBounds(39, 139, 56, 16);
    this.contentPane.add(label_1);
    
    JLabel label_2 = new JLabel("Direccion :");
    label_2.setBounds(39, 222, 85, 16);
    this.contentPane.add(label_2);
    
    JLabel label_3 = new JLabel("Telefono :");
    label_3.setBounds(25, 303, 85, 16);
    this.contentPane.add(label_3);
    
    final JComboBox<String> comboBox = new JComboBox();
    comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "General", "Anestesista", "Cardiologo", "Ortopedista", "Neumologo", "Radiologo", "Traumatologo" }));
    comboBox.setToolTipText("Profession del empleado");
    comboBox.setBounds(142, 364, 259, 58);
    this.contentPane.add(comboBox);
    
    JLabel lblSpcialit = new JLabel("Especialista : ");
    lblSpcialit.setBounds(25, 385, 85, 16);
    this.contentPane.add(lblSpcialit);
    JButton btnNewButton;
    JButton btnNewButton1;
    if (!modify) {
      btnNewButton = new JButton("Agregar");
    } else {
      btnNewButton = new JButton("Modificar");
    }
    btnNewButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e)
      {
        String prenom = AnadierDoctor.this.prenomfield.getText().toString();
        String nom = AnadierDoctor.this.nomfield.getText().toString();
        String tel = AnadierDoctor.this.telfield.getText().toString();
        String adresse = AnadierDoctor.this.adressefield.getText().toString();
        String num = _num;
        String specialite = comboBox.getSelectedItem().toString();
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
              sql = "INSERT into docteur (specialite , numero) values (? , ?)";
            } else {
              sql = "update docteur set specialite = ? where numero = " + num;
            }
            prepared = cnx.prepareStatement(sql);
            if (!modify) {
              prepared.setString(2, num);
            }
            prepared.setString(1, specialite);
            prepared.execute();
            if (!modify)
            {
                JOptionPane.showMessageDialog(null, "Nuevo medico agregado");
              AnadierDoctor.this.dispose();
            }
            else
            {
              JOptionPane.showMessageDialog(null, "Doctor numero" + num + " fue modificado con exito");
              AnadierDoctor.this.dispose();
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
          JOptionPane.showMessageDialog(null, "Completar los campos!");
        }
      }
    });
    btnNewButton.setBounds(122, 473, 196, 58);
    this.contentPane.add(btnNewButton);
  }

}