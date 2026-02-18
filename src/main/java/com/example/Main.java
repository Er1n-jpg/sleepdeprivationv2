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

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.Buffer;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;



public class Main {

    public Mat image;
    public ImageIcon imageicon;


    public Main(){
        System.out.println("yes");
        OpenCV.loadLocally();
        CascadeClassifier faceDetector = new CascadeClassifier();
        CascadeClassifier eyeDetector = new CascadeClassifier();
        String classifierPath = "haarcascade_frontalface_default.xml";
        String eyeClassifierPath = "haarcascade_eye.xml";
        faceDetector.load(classifierPath);
        eyeDetector.load(eyeClassifierPath);
        

        JFrame newFeed = new JFrame("Camerafeed");
        newFeed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel Label = new JLabel("loading...");
        Label.setFont(new Font("Prompt", Font.BOLD, 67));
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        newFeed.getContentPane().add(Label);
        newFeed.setSize(1650,1080);
        newFeed.setVisible(true);
        newFeed.setExtendedState(JFrame.MAXIMIZED_BOTH);

        VideoCapture camera = new VideoCapture(0);
        camera.set(org.opencv.videoio.Videoio.CAP_PROP_FRAME_WIDTH, 1650);
        camera.set(org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT, 1080);
        if (!camera.isOpened()){
        }
        newFeed.setExtendedState(JFrame.MAXIMIZED_BOTH);

        if (!camera.isOpened()){
            return;
        }

            Mat image = new Mat();
            MatOfRect faceDetections = new MatOfRect();
            MatOfRect eyeDetections = new MatOfRect();

            boolean eyesopen = true;
            int frameswoeyes = 0;
            final int blinkthreshold = 3; //1 minute = ~28 frames holy jon
            int blinkcounter = 0;
            
            long lastime = System.nanoTime();
            int frames = 0;
            double fps = 0.0;

  
        while(newFeed.isVisible()){ 
            camera.read(image);
                if (image.empty() || image.cols() == 0 || image.rows() == 0) {
                System.out.println("Empty frame, skipping...");
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    break;
                }
                continue;
        }   

        boolean eyesdetectedtsframe = false;
        faceDetector.detectMultiScale(image, faceDetections);
        
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0),3);

        int eyearea = (int)(rect.height * 0.5);

        Rect eyeregion = new Rect(rect.x, rect.y,rect.width,eyearea);


        Mat faceArea = new Mat(image, eyeregion);
        eyeDetector.detectMultiScale(faceArea, eyeDetections);

        for (Rect eyeRects: eyeDetections.toArray()){
            Imgproc.rectangle(image,new Point( eyeregion.x + eyeRects.x, eyeregion.y + eyeRects.y), new Point(eyeregion.x + eyeRects.x + eyeRects.width, eyeregion.y + eyeRects.y + eyeRects.height), new Scalar(225,0,0),2);
            }

            Rect [] ERArray = eyeDetections.toArray();
            if (ERArray.length >= 1){
                eyesdetectedtsframe = true;
            }
        }

        if (eyesdetectedtsframe){
            if (!eyesopen && frameswoeyes >= blinkthreshold){
                blinkcounter++;
                sendemail(bigdata.getrecipient(), "honk mimimimi", bigdata.getmessage());
                System.out.println("Blinks:" + blinkcounter);
        }

            eyesopen = true;
            frameswoeyes = 0;

        } else{
            frameswoeyes++;

            if (frameswoeyes>= blinkthreshold){
                eyesopen = false;
            }
        }
     
        ImageIcon frame = new ImageIcon(matToBufferedImage(image));
        SwingUtilities.invokeLater(() -> Label.setIcon(frame));

        }

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
        
    public static void sendemail(String toemail,String subject, String Body){
        String Fromemail = "blinkedtoolong@gmail.com";
        String Password = "gpcrnuhhaijwygam";

        Properties props= new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Fromemail, Password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Fromemail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toemail));
            message.setSubject(subject);
            message.setText(Body);


            Transport.send(message);
        } catch (MessagingException e){
            System.out.println("error");
        }
    }
}



    


        
    

