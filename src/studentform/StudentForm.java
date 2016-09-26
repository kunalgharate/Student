/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentform;
import javax.swing.*;
import java.awt.*;       
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author KRunal
 */

class Gui extends JFrame implements ActionListener{
    private JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,lres;
    private JTextField tf1,tf2,tf3,tf4,tf5,tf6;
    private JTextArea jta1;
    private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8; 
   Connection con;
   Statement st;
   ResultSet rest;
   PreparedStatement ps;
   
    public Gui(){
        
        super("Student Info Form");
        setLayout(null);
        Border b1=BorderFactory.createTitledBorder("MyFrame");
        getRootPane().setBorder(b1);        
        
        
    	
    		
        
        jl1=new JLabel("Roll no");
        jl1.setBounds(50,50,80,30);
        add(jl1);
        tf1= new JTextField("Enter Roll No");
        tf1.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e){
                tf1.setText("");
            }
            });
         
        
           tf1.setBounds(150,50,150,40);
           add(tf1);
           
        jl2=new JLabel("Name");
        jl2.setBounds(50,100,80,30);
        add(jl2);
          tf2= new JTextField("Enter Name Here");
                    
             tf2.setBounds(150,100,150,40);
            add(tf2);
         
          
        jl3=new JLabel("Address");
         jl3.setBounds(50,150,80,30);
        add(jl3);
        jta1 =new JTextArea("Enter Address Here");
        Border addressBorder = BorderFactory.createLineBorder(Color.black);
        jta1.setBounds(150,160,200,50);
          add(jta1);
        
        jl4=new JLabel("Gender");
         jl4.setBounds(50,220,80,30);
        add(jl4);
        tf3= new JTextField("Enter GENDER Here");
                 tf3.setBounds(150,225,150,40);
                 add(tf3);
            
           
             
        jl5=new JLabel("Mobile no");
         jl5.setBounds(50,270,80,30);
        add(jl5);
        tf4= new JTextField("Enter Mobile No Here");
               tf4.setBounds(150,270,150,40);
                 add(tf4);
           
        jl6=new JLabel("Mail Id");
         jl6.setBounds(50,320,80,30);
        add(jl6);
         tf5= new JTextField("Enter Mail ID Here");
                    tf5.setBounds(150,320,150,40);         
                    add(tf5);
           
        jl7=new JLabel("Class");
         jl7.setBounds(50,370,80,30);
        add(jl7);
          tf6= new JTextField("Enter Class Here");
                tf6.setBounds(150,370,150,40);
                    add(tf6);
        
            
             
             jb1=new JButton("New");
                jb1.setBounds(50,420,80,50);
                jb2=new JButton("Insert");
                jb2.setBounds(130,420,80,50);
                 jb3=new JButton("Update");
                 jb3.setBounds(210,420,80,50);
                  jb4=new JButton("Delete");
                  jb4.setBounds(290,420,80,50);
                   jb5=new JButton("Prev");
                   jb5.setBounds(50,500,80,50);
                    jb6=new JButton("Next");
                    jb6.setBounds(130,500,80,50);
                     jb7=new JButton("Frist");
                     jb7.setBounds(210,500,80,50);
                        jb8=new JButton("Last");
                        jb8.setBounds(290,500,80,50);
                       lres = new JLabel("Record Saved success");
                       lres.setBounds(150,550,400,100);
                        Font trbi = new Font("TimesRoman", Font.BOLD, 18);
                        lres.setForeground(Color.BLACK);
                        lres.setFont(trbi);
                     

                        add(jb1);
                        add(jb2);
                        add(jb3);
                        add(jb4);
                        add(jb5);
                        add(jb6);
                        add(jb7);
                        add(jb8);
                        add(lres);
                        
                        try
    		{
    			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    			
    			 con= DriverManager.getConnection("jdbc:odbc:Student");
    			
    	 st= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

    			 rest= st.executeQuery("select * from Student");
    			con.setAutoCommit(true);
    			rest.first();
    			tf1.setText(rest.getString(1));
                        tf2.setText(rest.getString(2));
    			jta1.setText(rest.getString(3));
                         tf3.setText(rest.getString(4));
                        tf4.setText(rest.getString(5));
                        tf5.setText(rest.getString(6));
                        tf6.setText(rest.getString(7));
    		}
    		catch(ClassNotFoundException e)
    		{
    			e.printStackTrace();
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
                        
               
                
                jb1.addActionListener(this);
                jb2.addActionListener(this); 
                jb3.addActionListener(this);
                jb4.addActionListener(this);
                jb5.addActionListener(this);
                jb6.addActionListener(this);
                jb7.addActionListener(this);
                jb8.addActionListener(this);
                 
                
    }
    
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
        
        if(ae.getSource()==jb2)//insert
        {
        ps = con.prepareStatement("insert into Student values(?,?,?,?,?,?,?)");
        ps.setInt(1,Integer.parseInt(tf1.getText()));
        ps.setString(2,tf2.getText());
        ps.setString(3,jta1.getText());
        ps.setString(4,tf3.getText());
        ps.setString(5,tf4.getText());
        ps.setString(6,tf5.getText());
        ps.setString(7,tf6.getText());        
       
        clearData();
        lres.setText("Record Inserted..");
         ps.executeQuery(); 
        }
        
         if(ae.getSource()==jb3)// Update 
        {
        ps = con.prepareStatement("UPDATE Student SET Name=?, Address=?, Gender=?, Mobile=?, Mail=?, Class=? WHERE Roll_No = ?");
        ps.setString(1,tf2.getText());
        ps.setString(2,jta1.getText());
        ps.setString(3,tf3.getText());
        ps.setString(4,tf4.getText());
        ps.setString(5,tf5.getText());
        ps.setString(6,tf6.getText());     
        ps.setInt(7,Integer.parseInt(tf1.getText()));
        clearData();
        lres.setText("Record Updated..");
         ps.executeUpdate(); 
        }
        
        
         if(ae.getSource()==jb4)// Delete
        {
        ps = con.prepareStatement("DELETE * FROM Student WHERE Roll_No = ? ");
        ps.setInt(1,Integer.parseInt(tf1.getText()));
        clearData();
        lres.setText("Record Deleted..");
         ps.executeUpdate(); 
        }
        
        if(ae.getSource()==jb5)//prev
        {
            rest.previous();
            printdata();
             lres.setText("Previous Record Found.....");
        }
        
         if(ae.getSource()==jb7)//next
        {
            rest.first();
            printdata();
             lres.setText("Next Record Found.....");
        }
            if(ae.getSource()==jb6)//first
        {
            rest.next();
            printdata();
             lres.setText("First Record Found.....");
        }
          if(ae.getSource()==jb8)//Last
        {
            rest.last();
            printdata();
             lres.setText("Last Record Found.....");
        }
          
            if (ae.getSource()==jb1) // New Record 
            
            {
           
              
                clearData();
                lres.setText("Record Clear....");
               
                
            }
        
      
       
        }catch(Exception ex){}
    }
  public void clearData()
  {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");
                jta1.setText("");
  }
  
 public void printdata()   
 {
     try {
          tf1.setText(rest.getString(1));
            tf2.setText(rest.getString(2));
             jta1.setText(rest.getString(3));
             tf3.setText(rest.getString(4));
              tf4.setText(rest.getString(5));
               tf5.setText(rest.getString(6));
               tf6.setText(rest.getString(7));
                  
     } catch (Exception e) {
     }
 }
}

public class StudentForm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Gui g= new Gui();
        g.setSize(600,800);
        g.setVisible(true);         
        
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
