
import javax.swing.JOptionPane;
import java.util.Scanner;
public class Matrica {

    public static void main(String arg[])
    {
        ShkronjNumer gm = new ShkronjNumer();
        Scanner input = new Scanner(System.in);

        String dimension = JOptionPane.showInputDialog("Sheno dimensionin e matrices:");

        int n = Integer.parseInt(dimension); // integjer dimensinon 
        String matrica[][]= new String[n][n];

        for(int i=0; i<n; i++)
        {
            for(int j = 0; j < n; j++){

                int pozitaI = i + 1; // e kshyr poziten e elementit 
                int pozitaJ = j + 1; // e kshyr poziten e elemnetit tjeter te matricess
                String elementi = JOptionPane.showInputDialog("Sheno elementin ne poziten " + pozitaI + ", " +
                         + pozitaJ + " te matries:");

                boolean isValid = true;
                while(isValid){
                    if(elementi == null){

                        System.exit(0);
                    }


                    if(elementi.length() != 1 || elementi.equals("0")|| elementi.equals("1")|| elementi.equals("2")
                       || elementi.equals("3")|| elementi.equals("4")|| elementi.equals("5")|| elementi.equals("6")
                        || elementi.equals("7")|| elementi.equals("8")|| elementi.equals("9")){

                 elementi = JOptionPane.showInputDialog("Vlere jo valide per elementin " + pozitaI + ", " +
                          + pozitaJ + " te matries, " + "jep vlere tjeter:");

                }
                else{
                        isValid = false;
                    }
                }
                matrica[i][j] = elementi;

            }


        }
        // paraqitja e e matrices 
        System.out.println("Matrica me shkronja");
        for(int i=0; i<n; i++)
        {
            System.out.print("");
            for(int j=0; j<n; j++)
            {
                System.out.print(" " + matrica[i][j]);
            }
            System.out.println();
        }
         // kthimi i matrices nga shkronje ne numer 
        double [][] numeric = new double[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                numeric[i][j] = gm.ShkronjNumer(matrica[i][j]);
            }
        }
         // e kthen matricen nga shkronjat ne numra
        System.out.println("Matrica me numra");
        for(int i=0; i<n; i++)
        {
            System.out.print("");
            for(int j=0; j<n; j++)
            {
                System.out.print(" " + numeric[i][j]);
            }
            System.out.println();
        }
        // gjetja e inversit te matrices 
        double d[][] = invert(numeric);
        System.out.println("Inversi i matrices eshte: ");
        for (int i=0; i<n; ++i)
        {
            for (int j=0; j<n; ++j)
            {
                System.out.print(d[i][j]+"  ");
            }
            System.out.println();
        }

        // inversin e matreices se kthyre nga shkronjat ne numra e gjen prap inversin 
        double d2[][] = invert(d);
        
        System.out.println("Inversi i matrices inverse eshte: ");
        for (int i=0; i<n; ++i)
        {
            for (int j=0; j<n; ++j)
            {
                System.out.print(d2[i][j]+"  ");
            }
            System.out.println();
        }
        // kthimi i elementeve nga numrat ne shkronja 
        String[][] character = new String[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                character[i][j] = gm.NumerShkronj(d2[i][j]);
            }
        }
        // paraqitja e matrices te pare ose fillestare
        System.out.println("Matrica me shkronja");
        for(int i=0; i<n; i++)
        {
            System.out.print("");
            for(int j=0; j<n; j++)
            {
                System.out.print(" " + character[i][j]);
            }
            System.out.println();
        }

        input.close();
    }

    public static double[][] invert(double numeric[][])
    {
        int n = numeric.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        gaussian(numeric, index);

        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= numeric[index[j]][i]*b[index[i]][k];

        for (int i=0; i<n; ++i)
        {
            x[n-1][i] = b[index[n-1]][i]/numeric[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= numeric[index[j]][k]*x[k][i];
                }
                x[j][i] /= numeric[index[j]][j];
            }
        }
        return x;
    }

    public static void gaussian(double numeric[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];

        for (int i=0; i<n; ++i)
            index[i] = i;

        for (int i=0; i<n; ++i)
        {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(numeric[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(numeric[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = numeric[index[i]][j]/numeric[index[j]][j];

                numeric[index[i]][j] = pj;

                for (int l=j+1; l<n; ++l)
                    numeric[index[i]][l] -= pj*numeric[index[j]][l];
            }
        }
    }
}
