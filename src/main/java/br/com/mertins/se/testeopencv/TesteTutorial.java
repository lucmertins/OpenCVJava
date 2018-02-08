package br.com.mertins.se.testeopencv;

// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
//https://docs.opencv.org/3.2.0/d9/d52/tutorial_java_dev_intro.html

// outros exemplos
// http://onegocioeprogramar.blogspot.com.br/2014/05/java-e-opencv-se-divertindo-com.html

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

//
class DetectFaceDemo {

    public void run() {
        System.out.println("\nRunning DetectFaceDemo");
        // Create a face detector from the cascade file in the resources
        // directory.
        CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("/car_cascade.xml").getPath());
        Mat image = Imgcodecs.imread(getClass().getResource("/test-12.pgm").getPath());
        // Detect faces in the image.
        // MatOfRect is a special container class for Rect.
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
        // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 255, 0));
        }
        // Save the visualized detection.
        String filename = "carDetection.png";
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
}

public class TesteTutorial {

    public static void main(String[] args) {
        System.out.println("Hello, OpenCV");
        // Load the native library.
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new DetectFaceDemo().run();
    }
}
