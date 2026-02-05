package com.example;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

import nu.pattern.OpenCV;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import nu.pattern.OpenCV;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Main {

    public VideoCapture camera;
    public Mat image;
    public ImageIcon imageicon;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        OpenCV.loadLocally();
        System.out.println("OpenCV loaded successfully: " + Core.VERSION);








        
        System.out.println("Camera closed.");

    }

    public void startcamera(){

        JFrame newFeed = new JFrame("Camerafeed");
        newFeed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel Label = new JLabel();
        newFeed.getContentPane().add(Label);
        newFeed.setSize(1650,1080);
        newFeed.setVisible(true);
        newFeed.setExtendedState(JFrame.MAXIMIZED_BOTH);


            camera = new VideoCapture(0);
            image = new Mat();

            while(newFeed.isVisible()){
                camera.read(image);

                if (!image.empty()){
                    imageicon = new ImageIcon();
                    

                }
            }
        }
}        
    


