package hmm_zodiac;

import java.util.Random;



public class hmm_z_train {

	public static model_log  train(int [] obs_seq,int itr,int hidden_state, float[][] A,int M)
	{
		int max_itr=itr;
		int N=hidden_state;
		double old_log_prob=Integer.MIN_VALUE;
		hmm_z_train my_hmm=new hmm_z_train();
		
		model m =my_hmm.get_initial_model(N,obs_seq.length,A,M);
		for (int i =0;i<max_itr;i++)
		{
			
			alpha_c ac=my_hmm.alpha_pass(m,obs_seq);
			double [][] beta=my_hmm.beta_pass(m, obs_seq, ac);
			gamma_gammasum gg=my_hmm.gammapass(m,obs_seq,ac,beta);
			model m_new=my_hmm.re_estimation(m, obs_seq, beta, ac, gg,M);
			double log_prob=my_hmm.getlog(ac.c);
			//System.out.printf("Score of Model in %d th iteration is : %f",i ,log_prob);
			//System.out.println();
			
			
			if(log_prob>old_log_prob)
			{
				
				old_log_prob=log_prob;
				m=m_new;
				
			}
				
			else
				break;	
			
		}
		
		return my_hmm.new model_log(m,old_log_prob);
		
	}
	
	private double getlog(double c[])
	{
		double log_prob=0;
		for(int i=0;i<c.length;i++)
			log_prob+=Math.log(c[i]);
		return -log_prob;
			
	}
	private model re_estimation(model m,int[] O,double beta[][],alpha_c ac,gamma_gammasum gg,int M)
	{
		int N=m.a.length;
		int T=m.T;
		int K=M;
		//double a[][]=new double[N][N];
		double b[][]=new double[N][M];
		double pi[]=new double[N];
		for (int i =0;i<N;i++)
		 {
			pi[i]=gg.gamma_sum[0][i];
		 }
	        
		/*for (int i =0;i<N;i++)
		{
			 for (int j =0;j<N;j++)
			 {
				 double numer=0;
				 double denom=0;
				 for(int t=0;t<T-1;t++)
				 {
					 numer+=gg.gamma[t][i][j];
					 denom+=gg.gamma_sum[t][i];
					
				 }
				 
				 a[i][j]=numer/denom;
				 
			 }
		}*/
		for(int i =0;i<N;i++)
		{
			for (int j =0;j<K;j++)
			{
				double numer=0;
				double denom=0;
				for(int t=0;t<T-1;t++)
				 {
					if(O[t]==j)
						numer+=gg.gamma_sum[t][i];
					denom+=gg.gamma_sum[t][i];
						
				 }
				b[i][j]=numer/denom;
				
			}
		}
	        
	           
	    return new model(m.a,b,pi,T);
	       
	           
	}
	private gamma_gammasum gammapass(model m,int[] O,alpha_c ac,double [][] beta)	
	{
		int N=m.a.length;
		int T=m.T;
		double gamma [][][]=new double[T-1][N][N];
		double gamma_sum [][] =new double[T][N];
		double denom;

		for(int t=0;t<T-1;t++)
		{
			 denom=0.0f;
			 for (int i =0;i<N;i++)
			 {
				 for (int j =0;j<N;j++)
				 {
					 denom+=ac.alpha[t][i]*m.a[i][j]*m.b[j][O[t+1]]*beta[t+1][j];
				 }
			 }

			 for (int i =0;i<N;i++)
			 {
				 for (int j =0;j<N;j++)
				 {
					 gamma[t][i][j]=(ac.alpha[t][i]*m.a[i][j]*m.b[j][O[t+1]]*beta[t+1][j])/denom;
				     gamma_sum[t][i]+=gamma[t][i][j];
				     
				     
				     
				     
				 }
				 
			 }
		}
	    denom=0.0f;
	    for (int i =0;i<N;i++)
	    {
	    	denom+=ac.alpha[T-1][i];
	    		    
	    }
	    for (int i =0;i<N;i++)
	    {
	    	gamma_sum[T-1][i]=ac.alpha[T-1][i]/denom;
	    }
	      
	    return  new gamma_gammasum(gamma,gamma_sum);
		
	}
	private double [][] beta_pass(model m,int[] obs_seq,alpha_c ac)
	{
		int N=m.a.length;
		int T=m.T;
		double beta[][]=new double[T][N];
		for (int i =0;i<N;i++)
		{
			beta[T-1][i]=ac.c[T-1];
			
		}
	        
	    int t=T-2;
	   
	    while (t>=0)
	    {
	    	for(int i =0;i<N;i++)
	    	{
	    		
	    		for (int j =0;j<N;j++)
	    		{
	    			beta[t][i]+=m.a[i][j]*m.b[j][obs_seq[t+1]]*beta[t+1][j];
	    			
	    		}
	    		beta[t][i]=ac.c[t]*beta[t][i];
	    		
	    		
	    		
	    	}
	    	t-=1;
	    	
	    }
	    
	    return beta;
		
	}
	private alpha_c alpha_pass(model m,int[] O)
		{
			int N=m.a.length;
			int T=m.T;
			float[][] alpha_bar=new float [T][N];
			double c[]=new double[T];
			for (int i =0;i<N;i++)
			{
				alpha_bar[0][i]=(float) (m.pi[i]*m.b[i][O[0]]);
				c[0]+=alpha_bar[0][i];
				
			}
			c[0]=1/c[0];
			for (int i =0;i<N;i++)
			{
				alpha_bar[0][i]=(float) (c[0]*alpha_bar[0][i]);
			}
			for (int t=1;t<T;t++)
			{
				for (int i =0;i<N;i++)
				{
					for (int j =0;j<N;j++)
					
						alpha_bar[t][i]+=alpha_bar[t-1][j]*m.a[j][i];
					
					alpha_bar[t][i]=(float) (alpha_bar[t][i]*m.b[i][O[t]]);
					c[t]+=alpha_bar[t][i];
					
				}
				c[t]=1/c[t];
				for (int i =0;i<N;i++)
	
					alpha_bar[t][i]=(float) (c[t]*alpha_bar[t][i]);
					

			}
			return  new alpha_c(alpha_bar,c);
			
			
			
		}
	private class gamma_gammasum
	{
		double  gamma [][][];
		double gamma_sum [][];
		gamma_gammasum(double  gamma [][][], double gamma_sum[][])
		{
			this.gamma=gamma;
			this.gamma_sum=gamma_sum;
		}
		
	}
	private class alpha_c
	{
		float alpha[][];
		double c[];
		alpha_c(float alpha[][],double c[])
		{
			this.alpha=alpha;
			this.c=c;
		}
	}
	class model
	{
		float[][] a;
		double b[][];
		double pi[];
		int T;
		model(float[][] a,double b[][],double pi[],int T)
		{
			this.a=a;
			this.b=b;
			this.pi=pi;
			this.T=T;
		}
	}
	private  model get_initial_model(int N,int T, float[][] A,int M)
	{
		double pi[]=new double[N];
		float a[][]= A;
		double b[][]=new double[N][M];
		
		double sum_pi=0;
		//double sum_a[]=new double[N];
		double sum_b[]=new double[N];
		
		
		Random ran = new Random();
		
		double var = 0.001 + ran.nextDouble() * (0.006 - 0.000);
		
		  
		for (int i =0;i<N;i++)
		{
			pi[i]=(double)Math.abs((ran.nextGaussian()*0.037+var));
			sum_pi+=pi[i];
			
		}

		/*for (int i =0;i<N;i++)
		{
			for (int j =0;j<N;j++)
			{
				a[i][j]=(double)Math.abs((ran.nextGaussian()*0.037+var));
				sum_a[i]+=a[i][j];
			}
		}
		
		for (int i=0;i<N;i++)
		{
			
			for(int j=0;j<N;j++)
				a[i][j]=a[i][j]/sum_a[i];
					
		}
		*/
		
		for (int i =0;i<N;i++)
		{
			for (int j =0;j<M;j++)
			{
				b[i][j]=(double)Math.abs((ran.nextGaussian()*0.017+var));
				sum_b[i]+=b[i][j];
			}
		}
		
		for (int i=0;i<N;i++)
		{
			pi[i]=pi[i]/sum_pi;
					
		}
		
		for (int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				b[i][j]=b[i][j]/sum_b[i];
				
			}
				
					
		}
		return  new model(a,b,pi,T);
		
		
		
	}
	
	class model_log
	{
		
		double log_prob;
		hmm_z_train.model model;
		model_log(model m,double log_prob)
		{
			
			this.model=m;
			this.log_prob=log_prob;
		}
		
	}
}


