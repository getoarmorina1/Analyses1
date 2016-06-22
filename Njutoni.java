
public class Njutoni
{
		public static double vectorLtwoNorm(double[] vector)
	{
		double result = 0;
		for(int i=0;i<vector.length;i++)
		{
			result = result + Math.pow(vector[i], 2);
		}
		return Math.sqrt(result);
	}
	
		public static double[][] matrixSubtraction(double[][] matrix1,double[][] matrix2)
	{
		double[][] result = new double[matrix1.length][matrix1.length];
		for(int i=0;i<matrix1.length;i++)
		{
			for(int j=0;j<matrix1.length;j++)
			{
				result[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		return result;
	}
	
	    public static double[][] MatrixInverseofRank3(double[][] A) {
        double[][] res = new double[3][3];
        double detA = A[0][0] * A[1][1] * A[2][2] + A[0][2] * A[1][0] * A[2][1]
        			+ A[2][0] * A[1][2] * A[0][1]- A[2][0] * A[1][1] * A[0][2] 
        			- A[2][2] * A[1][0] * A[0][1] - A[0][0] * A[2][1] * A[1][2]; 
                      
        res[0][0] = 1 / detA * (A[1][1] * A[2][2] - A[2][1] * A[1][2]);
        res[0][1] = 1 / detA * (A[0][2] * A[2][1] - A[2][2] * A[0][1]);
        res[0][2] = 1 / detA * (A[0][1] * A[1][2] - A[1][1] * A[0][2]);
        res[1][0] = 1 / detA * (A[1][2] * A[2][0] - A[2][2] * A[1][0]);
        res[1][1] = 1 / detA * (A[0][0] * A[2][2] - A[2][0] * A[0][2]);
        res[1][2] = 1 / detA * (A[0][2] * A[1][0] - A[1][2] * A[0][0]);
        res[2][0] = 1 / detA * (A[1][0] * A[2][1] - A[2][0] * A[1][1]);
        res[2][1] = 1 / detA * (A[0][1] * A[2][0] - A[2][1] * A[0][0]);
        res[2][2] = 1 / detA * (A[0][0] * A[1][1] - A[1][0] * A[0][1]);
        
        return res;
    }
	
    public static double[][] multiplyMatrixes(double[][] A, double[][] B) 
    {
        double[][] r = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) 
        {
            for (int j = 0; j < B[0].length; j++) 
            {
                for (int k = 0; k < A[0].length; k++) 
                {
                    r[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return r;
    }
	
		public static double[][] function(double x1,double x2,double x3)
	{
		double[][] r = new double[3][3];		
		r[0][0] = 3.0*x1 - Math.cos(x2*x3) - 1.0/2;
		r[1][0] = x1*x1 - 81.0*Math.pow(x2+0.1,2) + Math.sin(x3) + 1.06;
		r[2][0] = Math.exp(-x1*x2) + 20.0*x3 + (10*Math.PI - 3)/3.0;
		return r;
	}
	
	
	public static double[][] Derivatifunksionit(double x1,double x2,double x3)
	{
		double[][] r = new double[3][3];
		r[0][0] = 3;
		r[0][1] = x3*Math.sin(x2*x3);
		r[0][2] = x2*Math.sin(x2*x3);
		r[1][0] = 2*x1;
		r[1][1] = -162*(x2+0.1);
		r[1][2] = Math.cos(x3);
		r[2][0] = -1*x2*Math.exp(-1*x1*x2);
		r[2][1] = -1*x1*Math.exp(-1*x1*x2);
		r[2][2] = 20;
		return r;
	}
	
	
	public static double[][] EkuacioneteNjutonit(int E, double[][] x, double TOL)
	{
		double[][] y = new double[x.length][x.length];
		int k = 0;
		while(k<E)
		{
			double[][] inversedJ = MatrixInverseofRank3(Derivatifunksionit(x[0][0],x[1][0],x[2][0]));
			y = multiplyMatrixes(inversedJ,function(x[0][0],x[1][0],x[2][0]));
			x = matrixSubtraction(x,y);
			double[] y1 = {y[0][0],y[1][0],y[2][0]};
			if(vectorLtwoNorm(y1)<TOL)
			{return x;}
			k++;
		}
		return x;
	}
	
   	public static void main(String[] args)
	{
		double[][] A = {{0.4,0,0},	
						    {0.4,0,0},
					       {-0.4,0,0}};
		double[][] x = EkuacioneteNjutonit(6,A,0.000000000000000001);
      System.out.println("Zgjidhjet"+"\n------------------------------------------------");
		System.out.println("x1: "+x[0][0] + "\nx2: " +x[1][0] + "\nx3: " + x[2][0]);
      System.out.println("\nTestimi i perpikshmerise " + "\n------------------------------------------------");
		System.out.println("Ekuacioni i pare "+(3.0*x[0][0] - Math.cos(x[1][0]*x[2][0]) - 1.0/2)+" = 0"); // testing the solutions
		System.out.println("Ekuacioni i dyte "+(x[0][0]*x[0][0] - 81.0*Math.pow(x[1][0]+0.1,2) + Math.sin(x[2][0]) + 1.06)+" = 0"); // testing the solutions
		System.out.println("Ekuacioni i trete "+(Math.exp(-x[0][0]*x[1][0]) + 20.0*x[2][0] + (10*Math.PI - 3)/3.0)+" = 0"); // testing the solutions
	}

}
