package sle.matrix;

import sle.vector.Vector;
import sle.vector.VectorDefaultImp;

import java.io.FileWriter;

public class TriDiagonalMatrixWithAdditionalRowAndColumnOnK extends AbstractTriDiagonalMatrixWithTwoAdditionalRowsOrColumns {
    private int _kPos;

    public TriDiagonalMatrixWithAdditionalRowAndColumnOnK(
            Vector a, Vector b, Vector c, Vector p, Vector q, int k) throws Exception {
        super(a, b, c, p, q);
        if (k < 0 || k > b.length() - 1) {
            throw new Exception("Некорректная позиция векторов");
        }
        _kPos = k;
    }

    static private float[][] formMatrix(Vector a, Vector b, Vector c, Vector p, Vector q, int k) {
        return formMatrix(a, b, c, p, q, k, null);
    }

    static private float[][] formMatrix(Vector a, Vector b, Vector c, Vector p, Vector q, int k, Vector f) {
        try {
            int size = b.length();
            float[][] matr = new float[size][f == null ? size : size + 1];
            for (int i = 0; i < size; ++i) {
                if (i == k) {
                    for (int j = 0; j < size; ++j) {
                        matr[i][j] = q.get(j);
                    }
                }
                else {
                    matr[i][i] = b.get(i);
                    matr[i][k] = p.get(i);
                    if (i > 0) {
                        matr[i][i - 1] = a.get(i - 1);
                    }
                    if (i < size - 1) {
                        matr[i][i + 1] = c.get(i);
                    }
                }
                if (f != null) {
                    matr[i][size] = f.get(i);
                }
            }
            return matr;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SquareMatrix add(SquareMatrix matr) throws Exception {
        return null;
    }

    @Override
    public SquareMatrix subtract(SquareMatrix matr) throws Exception {
        return null;
    }

    @Override
    public Vector multiply(Vector vec) throws Exception {
        int size = getSize();
        if (size != vec.length()) {
            throw new Exception("Невозможно умножить на вектор");
        }
        float[] newVec = new float[size];
        for (int i = 0; i < size; ++i) {
            if (i == _kPos) {
                newVec[i] = 0;
                for (int j = 0; j < size; ++j) {
                    newVec[i] += getQ().get(j) * vec.get(j);
                }
            }
            else {
                newVec[i] = getB().get(i) * vec.get(i);
                if (i > 0) {
                    newVec[i] += getA().get(i - 1) * vec.get(i - 1);
                }
                if (i < size - 1) {
                    newVec[i] += getC().get(i) * vec.get(i + 1);
                }
                if (i != _kPos - 1 && i != _kPos + 1) {
                    newVec[i] += getP().get(i) * vec.get(_kPos);
                }
            }
        }
        return new VectorDefaultImp(newVec);
    }

    @Override
    public Vector resolveLinearEquationSystem(Vector right) throws Exception {
        float[][] matr = formMatrix(getA(), getB(), getC(), getP(), getQ(),_kPos, right);
        int size = getB().length();
        //printM(matr);
        //System.out.println();

        // шаг 1
        for (int i = 0; i < _kPos; ++i) {
            float r = 1 / matr[i][i];
            matr[i][i] = 1; // bi
            matr[i][i + 1] *= r; // ci
            if (i + 1 != _kPos) {
                matr[i][_kPos] *= r; // pi
            }
            matr[i][size] *= r; // fi
            r = matr[i + 1][i]; // ai+1
            matr[i + 1][i] = 0;
            matr[i + 1][i + 1] = matr[i + 1][i + 1] - r * matr[i][i + 1];
            if (i + 1 != _kPos) {
                matr[i + 1][_kPos] = matr[i + 1][_kPos] - r * matr[i][_kPos];
            }
            matr[i + 1][size] = matr[i + 1][size] - r * matr[i][size];
            if (i + 1 != _kPos) {
                r = matr[_kPos][i]; // qi
                matr[_kPos][i] = 0;
                matr[_kPos][i + 1] = matr[_kPos][i + 1] - r * matr[i][i + 1];
                matr[_kPos][_kPos] = matr[_kPos][_kPos] - r * matr[i][_kPos];
                matr[_kPos][size] = matr[_kPos][size] - r * matr[i][size];
            }
        }
        //printM(matr);
        //System.out.println();

        //шаг 2
        for (int i = size - 1; i >= _kPos + 1; --i) {
            float r = 1 / matr[i][i]; // bi
            matr[i][i] = 1;
            matr[i][i - 1] *= r; //ai
            if (i - 1 != _kPos) {
                matr[i][_kPos] *= r; // pi
            }
            matr[i][size] *= r; // fi
            r = matr[i - 1][i]; // ci-1
            matr[i - 1][i] = 0;
            matr[i - 1][i - 1] = matr[i - 1][i - 1] - r * matr[i][i - 1]; // bi-1
            if (i - 1 != _kPos) {
                matr[i - 1][_kPos] = matr[i - 1][_kPos] - r * matr[i][_kPos]; //pi-1
            }
            matr[i - 1][size] = matr[i - 1][size] - r * matr[i][size]; // fi-1
            if (i - 1 != _kPos) {
                r = matr[_kPos][i]; // qi
                matr[_kPos][i] = 0;
                matr[_kPos][i - 1] = matr[_kPos][i - 1] - r * matr[i][i - 1];
                matr[_kPos][_kPos] = matr[_kPos][_kPos] - r * matr[i][_kPos];
                matr[_kPos][size] = matr[_kPos][size] - r * matr[i][size];
            }
        }
        //printM(matr);
       // System.out.println();

        // шаг 3
        matr[_kPos][size] /= matr[_kPos][_kPos];
        matr[_kPos][_kPos] = 1;
        for (int i = 0; i < size; ++i) {
            if (i != _kPos) {
                float r = matr[i][_kPos]; //pi
                matr[i][_kPos] = 0;
                matr[i][size] = matr[i][size] - r * matr[_kPos][size];
            }
        }
        //printM(matr);
        //System.out.println();

        // шаг 4
        for (int i = _kPos + 1; i < size - 1; ++i) {
            float r = matr[i + 1][i];
            matr[i + 1][i] = 0;
            matr[i + 1][size] = matr[i + 1][size] - r * matr[i][size];
        }
        //printM(matr);
        //System.out.println();

        //шаг 5
        for (int i = _kPos - 1; i >= 1; --i) {
            float r = matr[i - 1][i]; // ci-1
            matr[i - 1][i] = 0;
            matr[i - 1][size] = matr[i - 1][size] - r * matr[i][size];
        }
        //printM(matr);
        //System.out.println();

        float[] vec = new float[size];
        for (int i = 0; i < size; ++i) {
            vec[i] = matr[i][size];
        }
        return new VectorDefaultImp(vec);
    }

    private void printM(float[][] m) {
        for (float[] str : m) {
            for (float e : str) {
                System.out.printf("%.2f ", e);
            }
            System.out.println();
        }
    }

    @Override
    public void printToConsole() {
        float[][] matr = formMatrix(getA(), getB(), getC(), getP(), getQ(), _kPos);
        for (float[] str : matr) {
            for (float el : str) {
                System.out.printf("%8.2f ", el);
            }
            System.out.println();
        }
    }

    @Override
    public void writeToFile(String fileName) throws Exception {
        if (fileName == null || fileName.equals("")) {
            throw new Exception("Имя файла не может быть пустым");
        }
        float[][] matr = formMatrix(getA(), getB(), getC(), getP(), getQ(), _kPos);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(getSize() + System.lineSeparator());
            for (float[] row : matr) {
                for (float el : row) {
                    writer.write(el + " ");
                }
            }
        }
    }
}
