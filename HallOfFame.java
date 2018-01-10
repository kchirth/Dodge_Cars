/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carRace;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author manvendrasingh
 */
public class HallOfFame {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String[][] resultTable;
    protected Rectangle backButton = new Rectangle(5,5,50,50); 
    
    public void updateDataBase() throws Exception {   
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            connect = DriverManager.getConnection("jdbc:mysql://localhost/halloffame?" + 
                    "user=root&password=");
            
            statement = connect.createStatement();
            
            String sql = "INSERT INTO halloffame.hof values ('"+CarRacePanel.userName + 
                    "'," + Double.toString(CarRacePanel.elapsedSeconds)+")";
            statement.executeUpdate(sql);
          
            resultSet = statement.executeQuery("SELECT * FROM halloffame.hof ORDER BY SCORE DESC LIMIT 10");
            
        }
        catch(Exception e){
            throw e;
        }
        
        resultTable = new String[2][10];
        int i = 0;
        
        while(resultSet.next()){
               String user = resultSet.getString("USERNAME");
               String score = resultSet.getString("SCORE");
               
               resultTable[0][i] = user;
               resultTable[1][i] = score;
               i++; 
        }
        
       // jt = new JTable()
    }
    
    public void paintComponent(Graphics g) throws SQLException {
        Graphics2D g2d = (Graphics2D) g;
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 450, 600);
        //g.drawRect(0,0, 450, 600);
        
        
        Font fnt0 = new Font("arial", Font.BOLD,50);
        g.setFont(fnt0);
	g.setColor(Color.WHITE);
	g.drawString("HALL OF FAME", 30, 100);
     
        g.drawRect(100, 150, 250, 400);
        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        
        // draw back button
        g2d.draw(backButton);
        g.drawString("Back", 7, 35);
      
        for(int i = 0; i < 10; i++){
            System.out.println(i);
               g.drawString(resultTable[0][i], 110, 170+(i*40));
               g.drawString(resultTable[1][i], 235, 170+(i*40));
        }
        
    }
    
}
