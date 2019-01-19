package sle.vector;

import java.io.FileWriter;

public class VectorDefaultImp implements Vector {
    private float[] _vector;

    public VectorDefaultImp(float ...values) throws Exception {
        if (values == null) {
            throw new Exception("Передано null значение");
        }
        if (values.length == 0) {
            throw new Exception("Вектор должен иметь хотя бы одну компоненту");
        }
        _vector = values;
    }

    /**
     * Складывает 2 вектора, умножая каждую компоненту 2-го вектора на коэффициент
     *
     * @param vec вектор
     * @param k коэффициент при компоненте
     * @return новый вектор, полученные в результате сложения
     * @throws Exception если размеры векторов разные
     */
    private Vector addWithCoef(Vector vec, float k) throws Exception {
        if (length() != vec.length()) {
            throw new Exception("Невозможно сложить вектора разных размеров");
        }
        int len = length();
        float[] newVec = new float[len];
        for (int i = 0; i < len; ++i) {
            newVec[i] = vec.get(i) + k * get(i);
        }
        return new VectorDefaultImp(newVec);
    }

    @Override
    public int length() {
        return _vector.length;
    }

    @Override
    public float get(int i) throws Exception {
        if (i < 0 || i > length() - 1) {
            throw new Exception("Индекс за границами вектора");
        }
        return _vector[i];
    }

    @Override
    public Vector add(Vector vec) throws Exception {
        return addWithCoef(vec, 1f);
    }

    @Override
    public Vector subtract(Vector vec) throws Exception {
        return addWithCoef(vec, -1f);
    }

    @Override
    public float scalarMultiply(Vector vec) throws Exception {
        if (length() != vec.length()) {
            throw new Exception("Невозможно вычислить скалярное произведение векторов разных размеров");
        }
        int len = length();
        float result = 0;
        for (int i = 0; i < len; ++i) {
            result += vec.get(i) * get(i);
        }
        return result;
    }

    @Override
    public float getNorm() {
        try {
            float max = Math.abs(get(0));
            for (int i = 1; i < length(); ++i) {
                if (Math.abs(get(i)) > max) {
                    max = Math.abs(get(i));
                }
            }
            return max;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public void printToConsole() {
        for (float x : _vector) {
            System.out.printf("%.2f ", x);
        }
        System.out.println();
    }

    @Override
    public void writeToFile(String fileName) throws Exception {
        if (fileName == null || fileName.equals("")) {
            throw new Exception("Имя файла не может быть пустым");
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(length() + System.lineSeparator());
            for (float component : _vector) {
                writer.write(component + " ");
            }
            writer.write(System.lineSeparator());
        }
    }
}
