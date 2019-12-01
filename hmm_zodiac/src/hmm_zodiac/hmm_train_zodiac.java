package hmm_zodiac;


import java.util.*;
import java.util.Map;
import java.util.HashMap;

import hmm_zodiac.hmm_z_train.model;
import hmm_zodiac.hmm_z_train.model_log;

public class hmm_train_zodiac {

	public static  void train_new(int [] obs,int itr ,int hidden_state ,float A[][],String pt) throws InterruptedException
	{
		model_log model_log = hmm_z_train.train(obs,200,hidden_state,A,57);
		double prev_log_prob=Integer.MIN_VALUE;
		model res_model = null;
		int i =0;
		while(i<itr)
		{
			model_log = hmm_z_train.train(obs,200,hidden_state,A,57);
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
		
		Map<String, ArrayList<Integer>> res=hmm_train_zodiac.print(res_model,hidden_state,57);
		hmm_train_zodiac.check_accuracy(res, pt, obs);
		
	}
	static Map<String, ArrayList<Integer>> print (model m,int N,int M)
	{
		Map<String, ArrayList<Integer>> result = new HashMap<String, ArrayList<Integer>>();
		System.out.println("Final B matrix");
		for(int i=0;i<N;i++)
		{
			ArrayList<Integer> mappings=new ArrayList<Integer>();
			for (int j = 0; j < M; j++) 
			{
				if(m.b[i][j]>0.05)
					mappings.add(j);
				
					
				
			}
			
			System.out.println("Possible mappings of "+hmm_read.alphabet[i]);
			System.out.println(mappings);
			result.put(Character.toString(hmm_read.alphabet[i]), mappings);
			System.out.println();
			
		}
		return result;
		
	}
	static void check_accuracy(Map<String, ArrayList<Integer>> result,String plaintext,int []obs_seq)
	{
		int pos=0;
		int neg=0;
		
		for(int i =0;i<plaintext.length();i++)
		{
			String c =Character.toString(plaintext.charAt(i));
			if(result.get(c).contains(Integer.valueOf(obs_seq[i])))
				pos+=1;
			else
				neg+=1;
			
				
				
		}
		System.out.println("No of Incorrect Prediction-->"+neg);
		System.out.println("No of correct Prediction-->"+pos);
	
	}
}
