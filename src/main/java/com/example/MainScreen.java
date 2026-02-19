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
import javax.swing.Timer;

import java.net.URL;

public class MainScreen extends JFrame implements ActionListener, KeyListener {
    private Image backgroundImage;
    private JPanel backgroundPanel;
    static JFrame frame;
    private JTextField TOemail;
    private JTextArea message;
    private JButton proceed;
    private JLabel warning;
    private Timer timer;

    public MainScreen() {

        setBounds(0, 0, 1650, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, 1650, 1080);
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        TOemail = new JTextField();
        TOemail.setBounds(885, 290, 660, 130);
        TOemail.setVisible(true);
        TOemail.setOpaque(false);
        TOemail.setBorder(BorderFactory.createEmptyBorder());
        TOemail.setFont(new Font("Prompt", Font.BOLD, 30));
        TOemail.setCaretPosition(0);
        TOemail.addActionListener(this);

        message = new JTextArea();
        message.setBounds(885, 555, 640, 310);
        message.setVisible(true);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setBorder(BorderFactory.createEmptyBorder());
        message.setOpaque(false);
        message.setFont(new Font("Prompt", Font.BOLD, 30));
        message.addKeyListener(this);

        proceed = new JButton("proceed");
        proceed.setBounds(1350, 30, 250, 150);
        proceed.setVisible(true);
        proceed.addActionListener(this);
        proceed.setBorderPainted(false);
        proceed.setOpaque(false);
        proceed.setContentAreaFilled(false);

        warning = new JLabel("Please enter a valid email to proceed!!!");
        warning.setBounds(825, 100, 540, 100);
        warning.setFont(new Font("Prompt", Font.ITALIC, 30));
        warning.setVisible(false);

        backgroundPanel.add(TOemail);
        backgroundPanel.add(message);
        backgroundPanel.add(proceed);
        backgroundPanel.add(warning);

        

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!e.isShiftDown()) {
                bigdata.setmessage(message.getText());
                System.out.println(bigdata.getmessage());
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
        if (e.getSource() == TOemail) {
            String email = TOemail.getText();
            bigdata.setrecipient(email);
            TOemail.setEditable(false);
            TOemail.setCaretPosition(0);
            TOemail.getCaret().setVisible(false);
            System.out.println(bigdata.getrecipient() );
        }

        if (e.getSource() == proceed) {
            String recipient = bigdata.getrecipient();
            String msg = bigdata.getmessage();

            if (recipient == null || recipient.trim().isEmpty() ||!(recipient.contains("@"))) {
                warning.setVisible(true);
                timer();
                TOemail.setEditable(true);
                TOemail.getCaret().setVisible(true);
            } else {
                new Thread(() -> {
                    new Camera();
                }).start();
            }
        }

    }

    public void timer() {
        timer = new Timer(1000, e -> {
        });

        timer.start();

        Timer stopTimer = new Timer(3000, e -> {
            timer.stop();
            ((Timer) e.getSource()).stop();
            warning.setVisible(false);
            System.out.println("fake news");
        });
        stopTimer.setRepeats(false);
        stopTimer.start();
    }

}

class BackgroundPanel extends JPanel {
    private Image background;

    public BackgroundPanel() {

        try {
            URL imageURL = getClass().getResource("/BL2.png");
            System.out.println("IMGURL:" + imageURL);

            if (imageURL != null) {
                background = ImageIO.read(imageURL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Imagenotloaded", 200, 200);
        }

    }
}
