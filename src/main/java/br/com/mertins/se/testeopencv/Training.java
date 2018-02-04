package br.com.mertins.se.testeopencv;

import java.io.File;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.ml.SVM;

/**
 * training c
 * http://coding-robin.de/2013/07/22/train-your-own-opencv-haar-classifier.html
 * training java
 * https://stackoverflow.com/questions/31454644/java-opencv-svm-training-error
 * 
 *
 * 
 * tools for training - não é necessário codificar....
 * https://docs.opencv.org/2.4/doc/user_guide/ug_traincascade.html
 * https://www.cs.auckland.ac.nz/~m.rezaei/Tutorials/Creating_a_Cascade_of_Haar-Like_Classifiers_Step_by_Step.pdf
 * 
 * @author mertins
 */
public class Training {

    protected static final String PATH_POSITIVE = "/home/mertins/temp/opencvSVM/data/positivo/";
    protected static final String PATH_NEGATIVE = "/home/mertins/temp/opencvSVM/data/negativo/";
    protected static final String XML = "/home/mertins/temp/opencvSVM/data/test.xml";
    protected static final String FILE_TEST = "/home/mertins/temp/opencvSVM/data/negativo/1.jpg";

    private static Mat trainingImages;
    private static Mat trainingLabels;
    private static Mat trainingData;
    private static Mat classes;
    private static SVM clasificador;

    static {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        trainingImages = new Mat();
        trainingLabels = new Mat();
        trainingData = new Mat();
        classes = new Mat();
    }

    public static void main(String[] args) {
        trainPositive();
        trainNegative();
        train();
        test();
    }

    protected static void test() {
        Mat in = Imgcodecs.imread(new File(FILE_TEST).getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        clasificador.load(new File(XML).getAbsolutePath());
        System.out.println(clasificador);
        Mat out = new Mat();
        in.convertTo(out, CvType.CV_32FC1);
        out = out.reshape(1, 1);
        System.out.println(out);
        System.out.println(clasificador.predict(out));
    }

    protected static void train() {
        trainingImages.copyTo(trainingData);
        trainingData.convertTo(trainingData, CvType.CV_32FC1);
        trainingLabels.copyTo(classes);
        SVM clasificador = SVM.create();
        clasificador.train(trainingData, SVM.LINEAR, classes);
        clasificador.save(XML);
    }

    protected static void trainPositive() {
        for (File file : new File(PATH_POSITIVE).listFiles()) {
            Mat img = getMat(file.getAbsolutePath());
            trainingImages.push_back(img.reshape(1, 1));
            trainingLabels.push_back(Mat.ones(new Size(1, 1), CvType.CV_32FC1));
        }
    }

    protected static void trainNegative() {
        for (File file : new File(PATH_NEGATIVE).listFiles()) {
            Mat img = getMat(file.getAbsolutePath());
            trainingImages.push_back(img.reshape(1, 1));
            trainingLabels.push_back(Mat.zeros(new Size(1, 1), CvType.CV_32FC1));
        }
    }

    protected static Mat getMat(String path) {
        Mat img = new Mat();
        Mat con = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        con.convertTo(img, CvType.CV_32FC1, 1.0 / 255.0);
        return img;
    }

}
