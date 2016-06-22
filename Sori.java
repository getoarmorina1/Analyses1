public class Sori {
	public static String sor(int n ,double[][] A, double[] b, double[] x0, double TOL,int N, double w){
		
      // zbatim i algoritmit te Sor 
	     int k =1; 
	     double shuma =0;
	     double shuma1 =0;
	     double[] x=new double[n];
	   while( k <= N)
	     {
	      for(int i =0; i<n; i++) 
	      {
	       for(int j = 0; j < i-1; j++)
	       {
	        shuma =shuma+ A[i][j]*x[j];
	       }
	       for(int j = i+1; j<n; j++) 
	       {
	        shuma1 =shuma1+ A[i][j]*x0[j];
	       }
	       x[i] = (1 -w)*x0[i] +(w *(-shuma - shuma1 + b[i]))/A[i][i];
	      shuma = 0;
	      shuma1 =0;
	      }
	      for (int i=0;i<n;i++){
	      if (Math.abs(x[i]-x0[i])<TOL);
	      System.out.print("Vlerat jane :");
	      for(int t =0; t<n; t++)
	        {
	         System.out.print(x[t] + ", "); 
	        }
	      System.exit(0);
	      }
	      k++;
	      for (int i =1; i <n; i++ ){
	    	x0[i]=x[i];  
	      }
	
		
	}
	return  "Numr i iteracioneve ka tejkaluar " + k;

	   
	   
	}
	public static void main (String[]args){
		 int n =3; // Matrica  katrore me madhesi 3 me 3 
	     double[][] A = {{4,3,0}, {3,4,-1},{0,-1,4}}; // elementet e matrices
	     double[] b = {24,30,-24}; // vektor me gjymtyr te lira te sis. ekuacioneve lineare
	     double[] x0 = {1,1,1}; // vektori fillestar
	     double TOL = 0.01; // Toleranca 
	     int NO = 2; // numri i iteracioneve 
	     double w = 1.25; // vlera fillestare
	     System.out.println("rezultati: " + sor(n,A,b,x0,TOL,NO,w));
	}
}