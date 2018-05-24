package com.akalji.matrix;

import com.akalji.matrix.exceptions.IncompatibleDimensions;

import java.util.Scanner;

/**
 * @author Nikolai Tikhonov <akalji@ya.ru> akalji
 *  Yet another class of matrix
 */
public class Matrix {
    private int vsize, hsize;
    private double M[][];

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Constructor of any dimension Matrix
     * @param vsize - number of rows
     * @param hsize - number of columns
     * @author akalji (Nikolai Tikhonov)
     */
    public Matrix(int vsize, int hsize) {
        this.vsize = vsize;
        this.hsize = hsize;
        this.M = new double[vsize][hsize];

        for (int i = 0; i < this.vsize; i++) {
            for (int j = 0; j < this.hsize; j++) {
                this.M[i][j] = 0.0;
            }
        }
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * A copy constructor of the Matrix
     * @param A - The copied matrix
     */
    public Matrix(Matrix A) {
        vsize = A.getVsize();
        hsize = A.getHsize();
        this.M = new double[vsize][hsize];

        for (int i = 0; i < this.vsize; i++) {
            for (int j = 0; j < this.hsize; j++) {
                this.M[i][j] = A.getElement(i, j);
            }
        }
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Enter the matrix through the scanner
     * first line is size of square matrix
     *
     * @param in - Scanner object     *
     */
    public Matrix(Scanner in) {
        this.hsize = this.vsize = Integer.parseInt(in.next());
        this.M = new double[this.vsize][this.hsize];
        for (int row = 0; row < this.vsize; row++) {
            for (int col = 0; col < this.hsize; col++) {
                this.M[row][col] = Double.parseDouble(in.next());
            }
        }
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Constructor of square matrix
     * @param N
     */
    public Matrix(int N) {
        this.vsize = N;
        this.hsize = N;
        this.M = new double[vsize][hsize];

        for (int i = 0; i < this.vsize; i++) {
            for (int j = 0; j < this.hsize; j++) {
                this.M[i][j] = 0.0;
            }
        }
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Setter of value into cell of matrix
     * @param i - row
     * @param j - column
     * @param value
     * @author akalji (Nikolai Tikhonov)
     */
    public void set(int i, int j, double value) {
        if (i <= vsize && j <= hsize) {
            this.M[i][j] = value;
        } else {
            throw new IncompatibleDimensions();
        }

    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * @return - number of rows
     */
    public int getVsize() {
        return this.vsize;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * @return - number of columns
     */
    public int getHsize() {
        return this.hsize;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * @param i - row
     * @param j - column
     * @return - value of cell
     */
    public double getElement(int i, int j) {
        return this.M[i][j];
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Swap elements in rows a & b
     * @param a
     * @param b
     */
    public void swapRows(int a, int b) {
        if (a == b) return;
        double[] tmp = M[a];
        M[a] = M[b];
        M[b] = tmp;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Swap elements in columns a & b
     * @param a
     * @param b
     */
    public void swapColumns(int a, int b) {
        if (a == b) {
            return;
        }
        int N = this.vsize;
        if (a > N || b > N) {
            throw new IncompatibleDimensions();
        }
        double tmp;
        for (int i = 0; i < N; ++i) {
            tmp = M[i][a];
            M[i][a] = M[i][b];
            M[i][b] = tmp;
        }
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Set 0.0 in all elements of the matrix
     */
    public void clear() {
        for (int i = 0; i < vsize; i++)
            for (int j = 0; j < hsize; j++)
                M[i][j] = 0.0;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Insert value in diagonal
     * @param value
     */
    public void insertDiag(double value) {
        for (int i = 0; i < vsize; ++i)
            for (int j = 0; j < hsize; ++j) {
                if (i == j) M[i][j] = value;
            }
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     *
     * @return
     */
    public double norm() {
        int n = vsize;
        int m = hsize;
        double max = 0;
        for (int i = 0; i < n; ++i) {
            double tmp = 0;
            for (int j = 0; j < m; ++j)
                tmp += Math.abs(M[i][j]);
            if (tmp > max) max = tmp;
        }
        return max;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public void diagonalize() {
        for (int i = 0; i < vsize; i++) {
            double tmp = 0.0;
            for (int j = 0; j < hsize; j++)
                tmp += Math.abs(this.getElement(i, j));
            if (this.getElement(i, i) < 0){
                M[i][i]=-tmp;
            }
            else {
                M[i][i] = tmp;
            }
        }
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * @param M
     * @return Transposed Matrix M
     */
    public static Matrix transpose(Matrix M) {
        int n = M.getVsize();
        int m = M.getHsize();
        Matrix A = new Matrix(m, n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                A.set(j, i, M.getElement(i, j));
        return A;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * @param A
     * @param B
     * @return A+B
     */
    public static Matrix sum(Matrix A, Matrix B) {
        if (A.getHsize() != B.getHsize() || A.getVsize() != B.getVsize()) {
            throw new IncompatibleDimensions();
        }
        int hsize = A.getHsize();
        int vsize = A.getVsize();
        Matrix C = new Matrix(vsize, hsize);
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                C.set(i, j, A.getElement(i, j) + B.getElement(i, j));
            }
        }
        return C;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * @param A
     * @param B
     * @return A-B
     */
    public static Matrix subtract(Matrix A, Matrix B) {
        if (A.getHsize() != B.getHsize() || A.getVsize() != B.getVsize()) {
            throw new IncompatibleDimensions();
        }
        int hsize = A.getHsize();
        int vsize = A.getVsize();
        Matrix C = new Matrix(vsize, hsize);
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                C.set(i, j, A.getElement(i, j) - B.getElement(i, j));
            }
        }
        return C;
    }

    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * @param A
     * @param B
     * @return A*B
     */
    public static Matrix multiply(Matrix A, Matrix B) {
        if (A.getHsize() != B.getVsize()) {
            throw new IncompatibleDimensions();
        }
        int vsize = A.getHsize();
        int hsize = B.getVsize();
        Matrix C = new Matrix(vsize, hsize);
        for (int i = 0; i < A.getVsize(); ++i) {
            for (int j = 0; j < B.getHsize(); ++j) {
                for (int k = 0; k < A.getHsize(); ++k) {
                    C.set(i, j, C.getElement(i, j) + A.getElement(i, k) * B.getElement(k, j));
                }
            }
        }
        return C;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Matrix {");
        stringBuilder.append(System.lineSeparator());
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                stringBuilder.append(M[i][j]);
                if (j < hsize - 1) {
                    stringBuilder.append("\t");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}


