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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.net.URL;

public class MainScreen extends JFrame implements ActionListener, KeyListener{
    private Image backgroundImage;
    private JPanel backgroundPanel;
    static JFrame frame;
    private JTextField TOemail;
    private JTextArea message;
    private JButton proceed;
    


    public MainScreen() {

        setBounds(0,0,1650,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0,0,1650,1080);
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        
        TOemail = new JTextField();
        TOemail.setBounds(885,290,660,130);
        TOemail.setVisible(true);
        TOemail.setOpaque(false);
        TOemail.setBorder(BorderFactory.createEmptyBorder());
        TOemail.setFont(new Font("Prompt",Font.BOLD,30));
        TOemail.setCaretPosition(0);
        TOemail.addActionListener(this);

        message = new JTextArea();
        message.setBounds(885,555,640,310);
        message.setVisible(true);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setBorder(BorderFactory.createEmptyBorder());
        message.setOpaque(false);
        message.setFont(new Font("Prompt",Font.BOLD,30));
        message.addKeyListener(this);

        proceed = new JButton("proceed");
        proceed.setBounds(1350,30,250,150);
        proceed.setVisible(true);
        proceed.addActionListener(this);
        proceed.setBorderPainted(false);
        proceed.setOpaque(false);
                                
        backgroundPanel.add(TOemail);
        backgroundPanel.add(message);
        backgroundPanel.add(proceed);


    }





    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if (!e.isShiftDown()){
            String text = message.getText();
                System.out.println("gonenenenenene");
                System.out.println(text);
                e.consume();
                message.setEditable(false);
                message.setCaretPosition(0);
                message.getCaret().setVisible(false);
            }
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == TOemail){
            String email = TOemail.getText();
            bigdata.setmessage(email);
            TOemail.setEditable(false);
            TOemail.setCaretPosition(0);
            TOemail.getCaret().setVisible(false);
            System.out.println(email);
        } 

        if (e.getSource() == proceed){
            if (bigdata.getrecipient() == ""){
                System.out.println("Needs a recipient to send the email!!!");
            } else {
                System.out.println("yay going to new page");
                new Main();
            }
        }
    }

    class BackgroundPanel extends JPanel{
        private Image background;

        public BackgroundPanel(){

            try{
                URL imageURL = getClass().getResource("BL2.png");
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

