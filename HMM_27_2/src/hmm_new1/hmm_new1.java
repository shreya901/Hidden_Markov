package hmm_new1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class hmm_new1 {
	
	public static void main(String args[]) throws IOException, InterruptedException 
	{
		char [] data1=hmm_new1.fetch_data();
		int [] obs_seq=new int[50000];
		int [] obs_subset=new int[400];
		double sum_di_row[]=new double[27];
		char data2[]=hmm_new1.fetch_english_text();
		
		int [][] diagraph=new int[27][27];
		float [][] diagraph1=new float[27][27];
		for(int i =0;i<27;i++)
			sum_di_row[i]=0;
		
		for (int i =0;i<50000;i++)
		{
			String key=Character.toString(data1[i]);
			obs_seq[i]=Map1.charint.get(key);
		}
		
		
		obs_subset=Arrays.copyOfRange(obs_seq,1100,1400);
		
	
		
		diagraph=Map1.generate_diagrah(data2,data2.length);
		diagraph1=Map1.normalise_diagraph(diagraph);
		
		System.out.println("Normalised Diagraph matrix");
		/*for (int i = 0; i < 27; i++)
		{
		    for (int j = 0; j < 27; j++)
		    {
		    	System.out.print(diagraph1[i][j] + "    ");
		    	sum_di_row[i]+=diagraph1[i][j];
		    	
		    }
		    System.out.println("Row Stochastic sum--->"+sum_di_row[i]);
		        
		        
		    
		    System.out.println();
		}*/
		hmm_train_new1.train_new(obs_subset,1,27,diagraph1);
			
		
		
	}
	
	private static char[] fetch_data() throws IOException
	{
		char[] res;
		HashMap<String,String> key;
		char encoded_res[]=new char[50000];
		try {
		 res=encrypt1.read_data();
		 System.out.println(res.length);
		 key=encrypt1.get_key(encrypt1.alphabet,3);
		 encoded_res=encrypt1.encode(key,res);
		 System.out.println(encoded_res.length);
		 //for(int i =0;i<27;i++)
			 //System.out.println(key.get(Character.toString(encrypt1.alphabet[i])));

		}
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encoded_res;
		
		
	}
	private static char[] fetch_english_text() throws IOException 
	{
		char english_text[]=new char[100000];
		english_text=encrypt1.read_data();
		System.out.println(english_text.length);
		return english_text;
		
	}
	

}
