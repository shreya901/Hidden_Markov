package Hmm_new;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



 class encrypt {
	 static char [] alphabet= new char[27];
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
		 alphabet[26]='*';
		 
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
			    String temp1=temp.replaceAll("\\s+","*");
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
	static HashMap<String,String> get_key(char[] alphabet,int shift)
	{
		 HashMap<String,String> key  = new HashMap<String,String>(27);
		 for(int i =0;i<27-shift;i++)
		 
			 key.put(Character.toString(alphabet[i]), Character.toString(alphabet[i+shift]));
		 int k =0;
		 for(int i=27-shift;i<27;i++)
		 {
			 key.put(Character.toString(alphabet[i]), Character.toString(alphabet[0+k]));
			 k+=1;
			 
		 }

		 return key;
		 
	}
	static char[] encode(HashMap<String,String> key,char resarray[])
	{
		char encoded_array []=new char[50000];
		for(int i =0;i<50000;i++)
		{
			
			String val=key.get(Character.toString(resarray[i]));
			encoded_array[i]=val.charAt(0);
		}
		return encoded_array;
	}
	

}
