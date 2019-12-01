package hmm_zodiac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

 class hmm_read {
	static HashMap<String,Integer> charint  = new HashMap<String,Integer>(27);
	static char [] alphabet= new char[26];
	 static
	 {
		 alphabet[0]='a';
		 alphabet[1]='b';
		 alphabet[2]='c';
		 alphabet[3]='d';
		 alphabet[4]='e';
		 alphabet[5]='f';
		 alphabet[6]='g';
		 alphabet[7]='h';
		 alphabet[8]='i';
		 alphabet[9]='j';
		 alphabet[10]='k';
		 alphabet[11]='l';
		 alphabet[12]='m';
		 alphabet[13]='n';
		 alphabet[14]='o';
		 alphabet[15]='p';
		 alphabet[16]='q';
		 alphabet[17]='r';
		 alphabet[18]='s';
		 alphabet[19]='t';
		 alphabet[20]='u';
		 alphabet[21]='v';
		 alphabet[22]='w';
		 alphabet[23]='x';
		 alphabet[24]='y';
		 alphabet[25]='z';

		 
	 }
	
	static
	{
		charint.put("a",0);
		charint.put("b",1);
		charint.put("c",2);
		charint.put("d",3);
		charint.put("e",4);
		charint.put("f",5);
		charint.put("g",6);
		charint.put("h",7);
		charint.put("i",8);
		charint.put("j",9);
		charint.put("k",10);
		charint.put("l",11);
		charint.put("m",12);
		charint.put("n",13);
		charint.put("o",14);
		charint.put("p",15);
		charint.put("q",16);
		charint.put("r",17);
		charint.put("s",18);
		charint.put("t",19);
		charint.put("u",20);
		charint.put("v",21);
		charint.put("w",22);
		charint.put("x",23);
		charint.put("y",24);
		charint.put("z",25);
	
		
	}
	
	static int[][] generate_diagrah(char [] data,int n)
	{
		
	int [][] diagraph=new int[26][26];
	for(int i =0;i<n-1;i++)
		{
		String val1=Character.toString(data[i]);
		String val2=Character.toString(data[i+1]);
		int l=charint.get(val1);
		int m=charint.get(val2);
		diagraph[l][m]+=1;
		}
	return 	diagraph;
	}
	
	static float[][] normalise_diagraph(int[][] diagraph)
	{
		
		int [] sum_di_row=new int[26];
		float [][] n_di=new float[26][26];
		for(int i =0;i<26;i++)
			sum_di_row[i]=0;
			
		for(int i=0;i<26;i++)
		{
			for(int j =0;j<26;j++)
			{
				diagraph[i][j]+=5;
				sum_di_row[i]+=diagraph[i][j];
			}
				
		}
		
		for(int i=0;i<26;i++)
		{
			for(int j =0;j<26;j++)

				n_di[i][j]=(float)diagraph[i][j]/(float)sum_di_row[i];
			
		}

		
		return n_di;
	}
	
	
	static char [] read_data() throws IOException
	{
		File folder=new File("/Users/shreya90/Documents/hmm_shreya/src/hmm_shreya/corpus");
	    
		File[] files = folder.listFiles();
		
		List<String> records = new ArrayList<String>();
		String result="";
		 for (int g=0;g<105;g++)
	        {
			 File file=files[g];
			 try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
			    while ((line = reader.readLine()) != null)
			    {
			    	
			    String temp=line.substring(15).replaceAll("([^a-zA-Z ]+?)", "").toLowerCase();
			    String temp1=temp.replaceAll("\\s+","");
			    records.add(temp1);
			      
			    }
			    reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            
	        }
		 for (int i=0;i<records.size();i++)
			{
			 result+=records.get(i);
	
					
			}
		 char resarray[]=result.toCharArray();
		 return resarray;
	}

}
