/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stdmangement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmad
 */
public class Course {
     public void insertUpdateDeleteStudent(char operation,Integer id, String label,Integer hours )
    {
      Connection con = MyConnection.getConnection();
     PreparedStatement ps;
      
      if(operation=='i')
      {
          try {
              ps = con.prepareStatement("INSERT INTO `course`( `label`, `hours_number`) VALUES (?,?)");
              ps.setString(1, label);
              ps.setInt(2, hours);
             
             
              if(ps.executeUpdate()>0)
              {
                  JOptionPane.showMessageDialog(null, "Done Course added");
              }
          } catch (SQLException ex) {
              Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
//     UPDATE `course` SET `label`=?,`hours_number`=? WHERE `id`=?
     if(operation=='u')
      {
          try {
              ps = con.prepareStatement("UPDATE `course` SET `label`=?,`hours_number`=? WHERE `id`=?");
              ps.setString(1, label);
              ps.setInt(2, hours);
              ps.setInt(3,id);
              if(ps.executeUpdate()>0)
              {
                  JOptionPane.showMessageDialog(null, "Done Course Updated");
              }
          } catch (SQLException ex) {
              Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
       if(operation=='d')
      {
          try {
              ps = con.prepareStatement("DELETE FROM `course` WHERE `id`=?");
              
              ps.setInt(1,id);
              if(ps.executeUpdate()>0)
              {
                  JOptionPane.showMessageDialog(null, "Done Course Deleted");
              }
          } catch (SQLException ex) {
              Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
    public void fillCourseJtable(JTable table)
    {
        Connection con = MyConnection.getConnection();
     PreparedStatement ps;
     
        try {
            ps=con.prepareStatement("SELECT * FROM `course`");
           // ps.setString(1,"%"+ValueToSearch+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row ;
            while(rs.next())
            {
                row = new Object[3];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getInt(3);
                model.addRow(row);
            }
            
            }
        catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
}

