package sle.matrix;

import sle.vector.Vector;

/**
 * Абстрактная трехдиагональная матрица с дополнительными векторами p и q
 */
public abstract class AbstractTriDiagonalMatrixWithTwoAdditionalRowsOrColumns implements SquareMatrix{
    /**
     * Вектор под главной диагональю
     */
    private Vector _aVec;

    /**
     * Вектор главной диагонали
     */
    private Vector _bVec;

    /**
     * Вектор над главной диагональю
     */
    private Vector _cVec;

    /**
     * 1-й дополнительный вектор
     */
    private Vector _pVec;

    /**
     * 2-й дополнительный вектор
     */
    private Vector _qVec;

    protected Vector getA() {
        return _aVec;
    }

    protected Vector getB() {
        return _bVec;
    }

    protected Vector getC() {
        return _cVec;
    }

    protected Vector getP() {
        return _pVec;
    }

    protected Vector getQ() {
        return _qVec;
    }

    /**
     * Конструктор
     * @param a вектор под главной диагональю
     * @param b вектор главной диагонали
     * @param c вектор над главной диагональю
     * @param p 1-й дополнительный вектор
     * @param q 2-й дополнительный вектор
     * @throws Exception если размеру векторов не соответвуют друг другу
     */
    public AbstractTriDiagonalMatrixWithTwoAdditionalRowsOrColumns(
            Vector a, Vector b, Vector c, Vector p, Vector q) throws Exception {
        if (a.length() != b.length() - 1 || c.length() != b.length() - 1 ||
                b.length() != p.length() || b.length() != q.length()) {
            throw new Exception("Невозможно составить матрицу из векторов разных размеров");
        }
        for (int i = 0; i < b.length(); ++i) {
            if (b.get(i) == 0) {
                throw new Exception("Вектор главное диагонали не должен содержть 0");
            }
        }
        _aVec = a;
        _bVec = b;
        _cVec = c;
        _pVec = p;
        _qVec = q;
    }

    @Override
    public int getSize() {
        return _bVec.length();
    }
}
