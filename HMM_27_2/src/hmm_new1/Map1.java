package hmm_new1;


import java.util.HashMap;

class Map1 {
	static HashMap<String,Integer> charint  = new HashMap<String,Integer>(27);
	static HashMap<Integer,String> intchar  = new HashMap<Integer,String>(27);
	

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
		charint.put("*",26);
		
	}
	static 
	{
		intchar.put(0,"a");
		intchar.put(1,"b");
		intchar.put(2,"c");
		intchar.put(3,"d");
		intchar.put(4,"e");
		intchar.put(5,"f");
		intchar.put(6,"g");
		intchar.put(7,"h");
		intchar.put(8,"i");
		intchar.put(9,"j");
		intchar.put(10,"k");
		intchar.put(11,"l");
		intchar.put(12,"m");
		intchar.put(13,"n");
		intchar.put(14,"o");
		intchar.put(15,"p");
		intchar.put(16,"q");
		intchar.put(17,"r");
		intchar.put(18,"s");
		intchar.put(19,"t");
		intchar.put(20,"u");
		intchar.put(21,"v");
		intchar.put(22,"w");
		intchar.put(23,"x");
		intchar.put(24,"y");
		intchar.put(25,"z");
		intchar.put(26,"*");
		
	}

	static int[][] generate_diagrah(char [] data,int n)
	{
	int [][] diagraph=new int[27][27];
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
		
		int [] sum_di_row=new int[27];
		float [][] n_di=new float[27][27];
		for(int i =0;i<27;i++)
			sum_di_row[i]=0;
			
		for(int i=0;i<27;i++)
		{
			for(int j =0;j<27;j++)
			{
				diagraph[i][j]+=5;
				sum_di_row[i]+=diagraph[i][j];
			}
				
		}
		
		for(int i=0;i<27;i++)
		{
			for(int j =0;j<27;j++)

				n_di[i][j]=(float)diagraph[i][j]/(float)sum_di_row[i];
			
		}

		
		return n_di;
	}
	
}
