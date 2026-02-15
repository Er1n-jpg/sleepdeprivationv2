package com.example;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.net.URL;

public class MainScreen extends JFrame implements ActionListener, KeyListener, MouseListener{
    private Image backgroundImage;
    private JPanel backgroundPanel;
    static JFrame frame;
    private JTextField TOemail,FROMemail,mailpassword;
    


    public MainScreen() {

        setBounds(0,0,1650,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0,0,1650,1080);
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        
        TOemail = new JTextField();
        TOemail.setBounds(850,175,670,130);
        TOemail.setVisible(true);
        TOemail.setOpaque(false);
        TOemail.setBorder(BorderFactory.createEmptyBorder());
        TOemail.setFont(new Font("Prompt",Font.BOLD,30));

        FROMemail = new JTextField();
        FROMemail.setBounds(850,455,670,130);
        FROMemail.setVisible(true);
        FROMemail.setOpaque(false);
        FROMemail.setBorder(BorderFactory.createEmptyBorder());
        FROMemail.setFont(new Font("Prompt",Font.BOLD,30));
        

        mailpassword = new JTextField();
        mailpassword.setBounds(850,765,670,130);
        mailpassword.setOpaque(false);
        mailpassword.setVisible(true);
        mailpassword.setBorder(BorderFactory.createEmptyBorder());
        mailpassword.setFont(new Font("Prompt",Font.BOLD,30));

        backgroundPanel.add(TOemail);
        backgroundPanel.add(FROMemail);
        backgroundPanel.add(mailpassword);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    class BackgroundPanel extends JPanel{
        private Image background;

        public BackgroundPanel(){
            System.out.println("Constructorcalled");

            try{
                URL imageURL = getClass().getResource("/BLink.png");
                System.out.println("IMGURL:" + imageURL);

                if (imageURL != null){
                    background = ImageIO.read(imageURL);
                } 
            } catch (IOException e){
                e.printStackTrace();

            }

            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (background != null){
                    g.drawImage(background,0,0,getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.RED);
                    g.fillRect(0,0,getWidth(),getHeight());
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial",Font.BOLD,40));
                    g.drawString("Imagenotloaded",200,200);
                }
                
        }
    }

}

