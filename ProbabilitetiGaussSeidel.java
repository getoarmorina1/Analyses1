
/* Autor : Getoar Morina , Rrezarta Musmurati */


import javax.swing.*;
import java.util.Arrays;

public class ProbabilitetiGaussSeidel {
    public static final int NR_MAX_ITERACIONEC = 100;
    private double[][] A;

    public ProbabilitetiGaussSeidel(double[][] matrica) {
        A = matrica;
    }

    String[] rezultati;

    public boolean transformoMatricDominante(int r, boolean[] V, int[] R) {
        int n = A.length;
        if (r == A.length) {
            double[][] T = new double[n][n + 1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = A[R[i]][j];
            }

            A = T;

            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) continue;

            double sum = 0;

            for (int j = 0; j < n; j++)
                sum += Math.abs(A[i][j]);

            if (2 * Math.abs(A[i][r]) > sum) { // diagonalja dominante
                V[i] = true;
                R[r] = i;

                if (transformoMatricDominante(r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }

    public boolean Dominante() {
        boolean[] shtylla = new boolean[A.length];
        int[] rreshtat = new int[A.length];

        Arrays.fill(shtylla, false);

        return transformoMatricDominante(0, shtylla, rreshtat);
    }

    public void zgjidhja() {
        int iterations = 0;
        int n = A.length;
        double TOL = 1e-4;
        double[] X = new double[n];
        double[] P = new double[n];
        Arrays.fill(X, 0);

        rezultati = new String[n];
        while (true) {
            for (int i = 0; i < n; i++) {
                double shuma = A[i][n]; 

                for (int j = 0; j < n; j++)
                    if (j != i)
                        shuma -= A[i][j] * X[j];

                X[i] = 1 / A[i][i] * shuma;
            }

            for (int i = 0; i < n; i++) {
                System.out.println("Popabiliteti i rruges së " + (i + 1) + " është " + X[i] + "  ");
                rezultati[i] = "     " + "Popabiliteti i rruges së " + (i + 1) + " është " + X[i] + "     ";
            }


            iterations++;
            if (iterations == 1)
                continue;

            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > TOL)
                    stop = false;

            if (stop || iterations ==  NR_MAX_ITERACIONEC ) {

                String printRez = "";
                for(int i = 0; i < n; i++){

                    printRez = printRez + rezultati[i] + "\n";
                }
                JOptionPane.showMessageDialog(null, printRez + "\n", "Rezultati", JOptionPane.PLAIN_MESSAGE);
                break;
            }


            P = (double[]) X.clone();
        }
    }

    public static void main(String[] args) {
        double[][] M = {{4, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0},
                {-1, 5, -1, -1, -1, 0, 0, 0, 0, 0, 0},
                {-1, -1, 5, 0, -1, -1, 0, 0, 0, 0, 0},
                {0, -1, 0, 5, -1, 0, -1, -1, 0, 0, 0},
                {0, -1, -1, -1, 6, -1, 0, -1, -1, 0, 0},
                {0, 0, -1, 0, -1, 5, 0, 0, -1, -1, 0},
                {0, 0, 0, -1, 0, 0, 4, -1, 0, 0, 1},
                {0, 0, 0, -1, -1, 0, -1, 5, -1, 0, 1},
                {0, 0, 0, 0, -1, -1, 0, -1, 5, -1, 1},
                {0, 0, 0, 0, 0, -1, 0, 0, -1, 4, 1}};


        ProbabilitetiGaussSeidel gausSeidel = new ProbabilitetiGaussSeidel(M);

        gausSeidel.zgjidhja();
    }
}