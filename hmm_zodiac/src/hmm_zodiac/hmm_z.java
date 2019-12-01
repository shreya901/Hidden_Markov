package hmm_zodiac;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;




public class hmm_z {
	private static hmm_z hmm_zodiac;

	public static void main(String args[]) throws IOException, InterruptedException
	{
		
	
		double sum_di_row[]=new double[27];
		char data2[]=hmm_z.fetch_english_text();
		
		int [][] diagraph=new int[27][27];
		float [][] diagraph1=new float[27][27];
		for(int i =0;i<27;i++)
			sum_di_row[i]=0;
	
		
	
		
	
		
		diagraph=hmm_read.generate_diagrah(data2,data2.length);
		diagraph1=hmm_read.normalise_diagraph(diagraph);
		String pt="ilikekillingpeoplebecauseitissomuchfunitismorefunthankillingwildgameintheforrestbecausemanisthemostdangeroueanamalofalltokillsomethinggivesmethemostthrillingexperenceitisevenbetterthangettingyourrocksofwithagirlthebestpartofitisthaewhenidieiwillbereborninparadiceandalltheihavekilledwillbecomemyslavesiwillnotgiveyoumynamebecauseyouwilltrytosloidownoratopmycollectiogofslavesformyafterlifeebeorietemethhpiti";
		
		System.out.println("Normalised Diagraph matrix");
		/*for (int i = 0; i < 26; i++)
		{
		    for (int j = 0; j < 26; j++)
		    {
		    	System.out.print(diagraph1[i][j] + "    ");
		    	sum_di_row[i]+=diagraph1[i][j];
		    	
		    }
		    System.out.println("Row Stochastic sum--->"+sum_di_row[i]);
		        
		        
		    
		    System.out.println();
		}*/
		hmm_train_zodiac.train_new(observation.obs_seq,1000,26,diagraph1,pt);
			
		
		
	}
	
	private static char[] fetch_english_text() throws IOException 
	{
		char english_text[]=new char[100000];
		english_text=hmm_read.read_data();
	
		return english_text;
		
	}
	
	

}
