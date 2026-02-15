package com.example;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainScreen {

    public static void main(String[] args) throws IOException {
        Inputscreen();
    }

    public static void Inputscreen() throws IOException {
        JFrame input = new JFrame();
        input.setVisible(true);
        input.setSize(1850, 1080);
        input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label1 = new JLabel("hihihihihi herroe");
        input.add(label1);

        BufferedImage myImage = ImageIO.read(new File("BL.png"));
    }

}

class ImagePanel extends JComponent {
    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
