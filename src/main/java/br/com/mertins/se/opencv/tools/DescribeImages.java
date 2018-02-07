package br.com.mertins.se.opencv.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
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
