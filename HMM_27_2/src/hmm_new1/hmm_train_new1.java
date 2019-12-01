package hmm_new1;

import hmm_new1.hmm_train1.model;
import hmm_new1.hmm_train1.model_log;

public class hmm_train_new1 {
	
	public static  void train_new(int [] obs_subset,int itr ,int hidden_state ,float A[][]) throws InterruptedException
	{
		model_log model_log = hmm_train1.train(obs_subset,200,hidden_state,A);
		double prev_log_prob=Integer.MIN_VALUE;
		model res_model = null;
		int i =0;
		while(i<itr)
		{
			model_log = hmm_train1.train(obs_subset,200,hidden_state,A);
			//Thread.sleep(2000);
			System.out.println("Restart Number :"+i);
			if(model_log.log_prob>prev_log_prob)
			{
				res_model=model_log.model;
			   prev_log_prob=model_log.log_prob;
			   
			   System.out.println("Improvement");
			   
			
				
			}
				
			i+=1;
			
		}
		hmm_train_new1.print(res_model, hidden_state);
		
	}
	
	
	 static void print(model m,int N)

	{
		System.out.println();
		System.out.println();
		System.out.println("Final value of a matrix");
		for(int i=0;i<N;i++)
		{
			for (int j = 0; j < N; j++) 
		        System.out.print(m.a[i][j] + "    ");
		        
		    
		    System.out.println();
			
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Final value of pi matrix");
		for(int i=0;i<N;i++)
			System.out.print(m.pi[i] + "    ");
			
		
		System.out.println();
		System.out.println();
		
		
		
		System.out.println("Final value of b matrix");
		for (int row = 0; row < N; row++) 
		{
			
			double max=0;
			int i =-1;;
			int j=-1;;
			for (int col = 0; col < 27; col++) 
			{
				if(m.b[row][col]>max)
				{
					max=m.b[row][col];
					i=row;
					j=col;
				}
					
				
			}
			System.out.print("P="+encrypt1.alphabet[i]+"------>  C="+encrypt1.alphabet[j]+"-------->"+m.b[i][j] + "    ");

			System.out.println(); 
		}
		
		
	}

}
