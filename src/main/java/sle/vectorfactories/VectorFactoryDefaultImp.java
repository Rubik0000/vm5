package sle.vectorfactories;

import sle.vector.Vector;
import sle.vector.VectorDefaultImp;

import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

/**
 * Реализация фабрики векторов по-умолчанию
 */
public class VectorFactoryDefaultImp implements VectorFactory {
    private Random random = new Random();

    @Override
    public Vector createVector(float... values) throws Exception {
        return new VectorDefaultImp(values);
    }

    @Override
    public Vector readFromConsole(int len) throws Exception {
        if (len < 1) {
            throw new Exception("Некорректная длина вектора");
        }
        Scanner scanner = new Scanner(System.in);
        float[] vec = new float[len];
        for (int i = 0; i < len; ++i) {
            vec[i] = scanner.nextFloat();
        }
        return new VectorDefaultImp(vec);
    }

    @Override
    public Vector readFromFile(String fileName) throws Exception{
        if (fileName == null || fileName.equals("")) {
            throw new Exception("Имя файла не может быть пустым");
        }
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            if (!scanner.hasNextInt()) {
                throw new Exception("Неверный формат файла");
            }
            int len = scanner.nextInt();
            float[] vec = new float[len];
            for (int i = 0; i < len; ++i) {
                if (!scanner.hasNextFloat()) {
                    throw new Exception("Невозможно считать " + i + "-й компонент вектора");
                }
                vec[i] = scanner.nextFloat();
            }
            return new VectorDefaultImp(vec);
        }
    }

    @Override
    public Vector getRandomVector(int len, float min, float max) throws Exception {
        return getRandomVector(len, min, max, false);
    }

    @Override
    public Vector getRandomVector(int len, float min, float max, boolean noZeros) throws Exception{
        float[] vec = new float[len];
        for (int i = 0; i < len; ++i) {
            vec[i] = min + random.nextFloat() * (max - min);
            if (noZeros) {
                while (vec[i] == 0) {
                    vec[i] = min + random.nextFloat() * (max - min);
                }
            }
        }
        return new VectorDefaultImp(vec);
    }
}
