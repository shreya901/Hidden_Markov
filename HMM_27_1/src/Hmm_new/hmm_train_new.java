package Hmm_new;

import Hmm_new.hmm_train.model;
import Hmm_new.hmm_train.model_log;

public class hmm_train_new {
	
	public static  void train_new(int [] obs_subset,int itr ,int hidden_state) throws InterruptedException
	{
		model_log model_log = hmm_train.train(obs_subset,200,hidden_state);
		double prev_log_prob=Integer.MIN_VALUE;
		model res_model = null;
		int i =0;
		while(i<itr)
		{
			model_log = hmm_train.train(obs_subset,200,hidden_state);
			//Thread.sleep(2000);
			System.out.println("Restart number :"+i);
			if(model_log.log_prob>prev_log_prob)
			{
				res_model=model_log.model;
			   prev_log_prob=model_log.log_prob;
			   
			   System.out.println("Improvement");
			   
			
				
			}
				
			i+=1;
			
		}
		hmm_train_new.print(res_model, hidden_state);
		
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
		for (int j = 0; j < 27; j++) 
		{
			System.out.printf("%f     |        %f ",m.b[0][j],m.b[1][j]);
			System.out.println();
			
		}
		
		
	}

}
