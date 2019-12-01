package Hmm_new;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class hmm_new {
	
	public static void main(String args[]) throws IOException, InterruptedException 
	{
		char [] data1=hmm_new.fetch_data();
		int [] obs_seq=new int[50000];
		int [] obs_subset=new int[1000];
		/*double sum_di_row[]=new double[27];
		char data2[]=hmm_new.fetch_english_text();
		
		int [][] diagraph=new int[27][27];
		float [][] diagraph1=new float[27][27];
		for(int i =0;i<27;i++)
			sum_di_row[i]=0;*/
		
		for (int i =0;i<50000;i++)
		{
			String key=Character.toString(data1[i]);
			obs_seq[i]=Map.charint.get(key);
		}
		
		
		obs_subset=Arrays.copyOfRange(obs_seq,0,1000);
		
		hmm_train_new.train_new(obs_seq,1,2);
		
		/*diagraph=Map.generate_diagrah(data2,data2.length);
		diagraph1=Map.normalise_diagraph(diagraph);
		
		System.out.println(" Normalised Diagraph matrix");
		for (int i = 0; i < 27; i++)
		{
		    for (int j = 0; j < 27; j++)
		    {
		    	System.out.print(diagraph1[i][j] + "    ");
		    	sum_di_row[i]+=diagraph1[i][j];
		    	
		    }
		    System.out.println("Row Stochastic sum--->"+sum_di_row[i]);
		        
		        
		    
		    System.out.println();
		}*/
		
			
		
		
	}
	
	private static char[] fetch_data() throws IOException
	{
		char[] res;
		HashMap<String,String> key;
		char encoded_res[]=new char[50000];
		try {
		 res=encrypt.read_data();
		 System.out.println(res.length);
		 key=encrypt.get_key(encrypt.alphabet,3);
		 encoded_res=encrypt.encode(key,res);
		 System.out.println(encoded_res.length);

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
		english_text=encrypt.read_data();
		System.out.println(english_text.length);
		return english_text;
		
	}
	

}
