package br.com.mertins.se.opencv.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
Referência
https://www.youtube.com/watch?v=WEzm7L5zoZE
http://cogcomp.org/Data/Car/

* Executar codigo java que produz bg.txt e cars.info
https://github.com/lucmertins/OpenCVJava

Gerar vector
opencv_createsamples -info cars.info -num 550 -w 48 -h 24 -vec cars.vec

Visualizar
opencv_createsamples -vec cars.vec -w 48 -h 24


opencv_traincascade -data data -vec cars.vec -bg bg.txt -numPos 500 -numNeg 500 -numStages 50 -w 48 -h 24 -featureType LBP
ou
opencv_traincascade -data data -vec cars.vec -bg bg.txt -numPos 500 -numNeg 500 -numStages 50 -w 48 -h 24 -featureType HAAR

 * @author mertins
 */
public class DescribeImages {

    public void positives() throws IOException {
        File folder = new File("/home/mertins/Documentos/UFPel/Dr/SistemasEvolutivos/OpenCV/TrainingCar/positive");
        PrintWriter writer = new PrintWriter(new FileWriter("/home/mertins/Documentos/UFPel/Dr/SistemasEvolutivos/OpenCV/TrainingCar/cars.info"));
        for (File file : folder.listFiles()) {
            writer.format("%s 1 0 0 100 40\n", file.getAbsoluteFile());
        }
        writer.close();
    }

    public void negatives() throws IOException {
        File folder = new File("/home/mertins/Documentos/UFPel/Dr/SistemasEvolutivos/OpenCV/TrainingCar/negative");
        PrintWriter writer = new PrintWriter(new FileWriter("/home/mertins/Documentos/UFPel/Dr/SistemasEvolutivos/OpenCV/TrainingCar/bg.txt"));
        for (File file : folder.listFiles()) {
            writer.format("%s\n", file.getAbsoluteFile());
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        DescribeImages desc = new DescribeImages();
        desc.positives();
        desc.negatives();

    }
}
