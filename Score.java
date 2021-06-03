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
public class Score {
     public void insertUpdateDeleteStudent(char operation,Integer id, Integer cid,Integer score )
    {
      Connection con = MyConnection.getConnection();
     PreparedStatement ps;
      
      if(operation=='i')
      {
          try {
              ps = con.prepareStatement("INSERT INTO `score`( `student_id`, `course_id`, `student_score`) VALUES (?,?,?)");
              ps.setInt(1, id);
              ps.setInt(2, cid);
              ps.setInt(3, score);
             
             
              if(ps.executeUpdate()>0)
              {
                  JOptionPane.showMessageDialog(null, "Done Score added");
              }
          } catch (SQLException ex) {
              Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
//     UPDATE `course` SET `label`=?,`hours_number`=? WHERE `id`=?
     if(operation=='u')
      {
          try {
              ps = con.prepareStatement("UPDATE `score` SET `course_id`=?,`student_score`=? WHERE `student_id`=?");
             
              ps.setInt(1, cid);
              ps.setInt(2, score);
              ps.setInt(3, id);
              if(ps.executeUpdate()>0)
              {
                  JOptionPane.showMessageDialog(null, "Done Score Updated");
              }
          } catch (SQLException ex) {
              Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
       if(operation=='d')
      {
          try {
              ps = con.prepareStatement("DELETE FROM `score` WHERE `student_id`=?");
              
              ps.setInt(1,id);
              if(ps.executeUpdate()>0)
              {
                  JOptionPane.showMessageDialog(null, "Done Score Deleted");
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
            ps=con.prepareStatement("SELECT * FROM `score`");
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
