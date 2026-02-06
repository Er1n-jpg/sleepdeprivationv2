package com.example;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

import nu.pattern.OpenCV;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import nu.pattern.OpenCV;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.Buffer;

public class Main {

    public Mat image;
    public ImageIcon imageicon;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        OpenCV.loadLocally();
        System.out.println("OpenCV loaded successfully: " + Core.VERSION);



        
        cameraopen();

    }

    public static void cameraopen(){
        System.out.println("yes");
        CascadeClassifier faceDetector = new CascadeClassifier();
        String classifierPath = "haarcascade_frontalface_default.xml";
        faceDetector.load(classifierPath);

        JFrame newFeed = new JFrame("Camerafeed");
        newFeed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel Label = new JLabel();
        newFeed.getContentPane().add(Label);
        newFeed.setSize(1650,1080);
        newFeed.setVisible(true);
        newFeed.setExtendedState(JFrame.MAXIMIZED_BOTH);

        VideoCapture camera = new VideoCapture(0);

             if (!camera.isOpened()){
                System.out.println("Camera not open");
                return;
            }
            System.out.println("open");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }   

            Mat image = new Mat();
            MatOfRect faceDetections = new MatOfRect();
            System.out.println("deeznuts");
            ImageIcon frame = new ImageIcon(matToBufferedImage(image));
            System.out.println("frame loaded");
            Label.setIcon(frame);
            newFeed.pack();
            boolean dlajd = true;
            while(dlajd){
                camera.read(image);
                System.out.println("Camera is visible");

                if (!image.empty()) {
                // Detect faces
                faceDetector.detectMultiScale(image, faceDetections);
                System.out.println("detected");
                // Draw rectangles around detected faces
                for (Rect rect : faceDetections.toArray()) {
                    Imgproc.rectangle(
                        image,
                        new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0), // Green color
                        3 // Thickness
                    );
                }
            }   
                System.out.println("Faces detected: " + faceDetections.toArray().length);
                
                // Display

            

                try{
                    Thread.sleep(30);

                } catch (InterruptedException e){
                    break;
                }
            }
            camera.release();
        

        }



        public static BufferedImage matToBufferedImage(Mat mat){
            int type = BufferedImage.TYPE_BYTE_GRAY;
            if (mat.channels()> 1){
                type = BufferedImage.TYPE_3BYTE_BGR;
            }
            int buffersize = mat.channels() * mat.cols() * mat.rows();
            byte[] buffer = new byte [buffersize];
            mat.get(0,0,buffer);
            BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
            final byte[] targetPixel = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            System.arraycopy(buffer, 0, targetPixel, 0, buffer.length);
            return image;
        }
}        
    


