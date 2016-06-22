import java.util.*;

public class PageRank {
  
    public int rruge[][] = new int[10][10];
    public double pagerank[] = new double[10];
  
public void calc(double n)
{    
 double n0;
 double c=0; 
 double temp[] = new double[10];
 int i,j,u=1,k=1;
 n0 = 1/n;
 System.out.print(" vlera e n-it:"+n+"\t  vlera  :"+n0+"\n");
 for(i=1;i<=n;i++)
   this.pagerank[i]=n0;
   System.out.print("\n Vlera fillestare e  PageRank , hapi fillestar \n");
    for(i=1;i<=n;i++)
   System.out.print(" Page Rank i "+i+" eshte :\t"+this.pagerank[i]+"\n");
   
   
   while(u<=2)
   {
    for(i=1;i<=n;i++)
    {  temp[i]=this.pagerank[i];
          this.pagerank[i]=0;
       }
     
 for(j=1;j<=n;j++)
        for(i=1;i<=n;i++)
     if(this.rruge[i][j] == 1)
     {  k=1;c=0; 
           while(k<=n)
     {
       if(this.rruge[i][k] == 1 )
                   c=c+1;
     k=k+1;
     } 
             this.pagerank[j]=this.pagerank[j]+temp[i]*(1/c);    
      } 
   
  System.out.printf("\n pas hapit te"+u+" \n"); 
     for(i=1;i<=n;i++) 
      System.out.printf(" Page Rank i "+i+" eshte :\t"+this.pagerank[i]+"\n"); 
   
     u=u+1;
    } 
}
 public static void main(String args[])
    {
        int nyje,i,j;
        Scanner in = new Scanner(System.in);
        System.out.println("Shkruaj numrin e Grafeve: \n");
        nyje= in.nextInt();
        PageRank p = new PageRank();
        System.out.println("Shkruaj me 1 nese ka nyje dhe me 0 nese nuk ka nyje ne mes te dy grafeve: \n");
        for(i=1;i<=nyje;i++)
          for(j=1;j<=nyje;j++)
          {
            p.rruge[i][j]=in.nextInt();
            if(j==i)
              p.rruge[i][j]=0;
          }
        p.calc(nyje);
    
           
    }   
 
}