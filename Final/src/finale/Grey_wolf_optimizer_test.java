package finale;



class calculateFitness 
{
	 
	public double calcFit(double x[][])
	{
		int CT = 0;
		int MT = 0;
		int DT = 0;
		double o = 0;
		//double c = 0;
		
		//Penalty for Direction Change 
		for (int i=0; i< 9; i++)
		{
			if ((x[4][i] == x[4][i+1] && x[4][i] == 2) && (x[5][i] == x[5][i+1]))
			{
				CT += 0;
				MT += 0;
				DT += 0; //Disassembled in combination
			}
			else 
			{
				if (x[1][i] % 2 != 0 &&  x[1][i+1] == (x[1][i] +1))
				{
					CT += 2; //180 Degrees rotation
				}
				if (x[1][i] % 2 == 0 && x[1][i+1] == (x[1][i] -1))
				{
					CT += 2; //180 Degrees Rotation
				}
				if (x[1][i] == x[1][i+1])
				{
					CT += 0; //no direction change
				}
				//Penalty for Method Change
				if(x[2][i] != x[2][i + 1])
					MT += 1;
				//Penalty for disassemble time 
				DT += x[3][i]; 
				if(i == 8)
				{
					DT += x[3][i+1]; //count last number
				}
				else
				{
					CT += 1; // 90 degrees direction change
					
				}
		    }
		}
		
		 o = CT + MT + DT;
		
		 return o;
		 
		 
}
}	


public class   Grey_wolf_optimizer_test
{
    public static void main(String args[])
    {
      double[] Lower = {0.0};
      double[] Upper = {100000.0};   
       int maxiter= 500; 
	       int N= 10; 
	       calculateFitness ff=new calculateFitness();    
            Grey_wolf_optimizerTestVersion qbpso=new  Grey_wolf_optimizerTestVersion(ff,Lower,Upper,maxiter,N);
           long startTime = System.currentTimeMillis();
           qbpso.toStringnew();
            long endTime   = System.currentTimeMillis();
       long totalTime = endTime - startTime;
       System.out.println((totalTime)+" ms");
 
    }
}