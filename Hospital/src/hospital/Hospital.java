/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Hospital extends JFrame implements ActionListener{
    
    private static final long serialVersionUID = 1L;

    Connection con = null;
    //Panel Principal
    JPanel panel1=new JPanel();
    //Panel para los Iconos
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JPanel panel4=new JPanel();
    JPanel panel5=new JPanel();
    
    //Panel para los labels
    JPanel panel6=new JPanel();
    JPanel panel7=new JPanel();
    JPanel panel8=new JPanel();
    JPanel panel9=new JPanel();
    
    
    ImageIcon ImgPersonal=new ImageIcon(getClass().getResource("empleado.png"));
    JLabel LblPersonal=new JLabel("Gestión Personal");
    
    ImageIcon ImgPacientes=new ImageIcon(getClass().getResource("paciente.png"));
    JLabel LblPacientes=new JLabel("Gestión Pacientes");
    
    ImageIcon ImgDoctor=new ImageIcon(getClass().getResource("docto.png"));
    JLabel LblDoctor=new JLabel("Gestión Doctor");
    
    ImageIcon ImgSalir=new ImageIcon(getClass().getResource("salir.png"));
    JLabel LblSalir=new JLabel("Salir");
    
    JLabel txtCreador=new JLabel("Creado por Mario Cusma y Nilton Mercado");
    
    JButton btnPersonal=new JButton();
    JButton btnPacientes=new JButton();
    JButton btnDoctor=new JButton();
    JButton btnSalir=new JButton();
    

    
    public Hospital(Connection _con){
         this.con = _con;
        setResizable(false);
        setTitle("Menu Principal");
        panel1.setLayout(new GridLayout(2,2));
        this.getContentPane().add(panel1);
        panel1.add(panel2);
        panel1.add(panel3);
        panel1.add(panel4);
        panel1.add(panel5);
        

        
        btnPersonal.setIcon(ImgPersonal);
        btnPacientes.setIcon(ImgPacientes);
        btnDoctor.setIcon(ImgDoctor);
        btnSalir.setIcon(ImgSalir);
        
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.setBorder(BorderFactory.createEmptyBorder(50,400,50,50));
        panel2.add(btnPersonal);
        panel2.add("Center",LblPersonal);
        LblPersonal.setFont(new Font("Serif",Font.PLAIN,45));
        btnPersonal.addActionListener(this);
        btnSalir.addActionListener(this);/*{
        
                Connection con = null;
            @Override
            public void actionPerformed(ActionEvent e) {
            GestionPersonal obj = new GestionPersonal(this.con);
            obj.setLocationRelativeTo(null);
            obj.setVisible(true);
            Hospital.this.dispose();
        
        Hospital.this.dispose();
            }
        });*/
       
        
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.PAGE_AXIS));
        panel3.setBorder(BorderFactory.createEmptyBorder(50,200,50,50));
        panel3.add(btnPacientes);
        panel3.add("Center",LblPacientes);
        LblPacientes.setFont(new Font("Serif",Font.PLAIN,45));
        
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.PAGE_AXIS));
        panel4.setBorder(BorderFactory.createEmptyBorder(50,400,50,50));
        panel4.add(btnDoctor);
        panel4.add("Center",LblDoctor);
        LblDoctor.setFont(new Font("Serif",Font.PLAIN,45));
        
        panel5.setLayout(new BoxLayout(panel5,BoxLayout.PAGE_AXIS));
        panel5.setBorder(BorderFactory.createEmptyBorder(50,200,50,50));
        panel5.add(btnSalir);
        panel5.add("Center",LblSalir);
        LblSalir.setFont(new Font("Serif",Font.PLAIN,45));
        LblSalir.setLocation(150, 250);
        pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
/*
    
    
    public Hospital(){
       
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Hospital hosp=new Hospital();
        hosp.setTitle("Sistema de Gestios del Hospital");
        hosp.setSize(1980, 1080);
        hosp.setVisible(true);
        hosp.setResizable(false);
    }
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnPersonal)
        {
            GestionPersonal obj = new GestionPersonal(this.con);
            obj.setLocationRelativeTo(null);
            obj.setVisible(true);
            Hospital.this.dispose();
            Hospital.this.dispose();
        }
        if(e.getSource()==btnSalir)
        {
            System.exit(0);
        }

    }
}
