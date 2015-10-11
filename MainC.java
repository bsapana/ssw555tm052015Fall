import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainC {
	public static void main(String[] args) throws IOException{
		System.out.println("Enter File Path and Filename: ");   
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String fileName = bufferRead.readLine();   
		    readFile(fileName);
		}
		catch (FileNotFoundException ex) {
			System.out.println("File Not Found. Please Check Path and Filename");
			main(null);
		}		
	}
	 static void readFile(String fileName) throws IOException {
		 //lists to hold data
		
		ArrayList<String> families = new ArrayList<String>();
		HashMap<String, individual> individuals = new HashMap<String, individual>();
		 //valid tags
		String [] validTags={"INDI","NAME","SEX","BIRT","DEAT","FAMC","FAMS","FAM","MARR","HUSB","WIFE","CHIL","DIV","DATE","HEAD","TRLR","NOTE"};
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	 	String line = null;
	 	//loop through each line of the file
		while ((line = br.readLine()) != null) {
		//	System.out.println(line);
			//split the line into an array based on space
			String[] parts = line.split(" ");
			//print out the level
		//	System.out.println("Level: " + parts[0]);
			//if the 2nd element in array is a valid tag print it out
			if(Arrays.asList(validTags).contains(parts[1])){
			//	System.out.println();
				//System.out.println("TAG: " + parts[1]);
			}
			else{
				if(parts[0].equals("0") && Arrays.asList(validTags).contains(parts[2])){  
					//System.out.println();
					//System.out.println("TAG: " + parts[2]);
						//if it's an INDI record
						if (parts[2].equals("INDI")){
							//read the next line as that contains the name
						//	String nextLine=br.readLine();
							//split the next line up by space
							String key = parts[1];
			 				individual value = new individual(key);
			 				String nextline = br.readLine();
			 				while(!nextline.startsWith("0")){
			 					String[] info = nextline.split(" ");
			 					if(info[1].equals("NAME")) 
			 						value.setName(info[2] + " " + info[3].substring(1, info[3].length()-1));
			 					if(info[1].equals("SEX")) 
			 						value.setSex(info[2]);
			 					if(info[1].equals("BIRT")){
			 						nextline = br.readLine();
			 						value.setBirth(nextline.substring(7, nextline.length()));
			 					}
			 					if(info[1].equals("DEAT")){
			 						nextline = br.readLine();
			 						value.setDeath(nextline.substring(7, nextline.length()));
			 						//continue;
			 					}
			 					nextline = br.readLine();
			 				}
			 				individuals.put(key, value);	
			 				
			 				
							
					//		String[] indFamParts = nextLine.split(" ");
							/*	add the individual to the collection
								the record number is from the previous line and is the hash map key
								recordnum from previous line + first name and last name
								my lastname file had / at the start and end so I took substr of that
							*/
						//	individuals.put(parts[1], indFamParts[2] + " " + indFamParts[3].substring(1, indFamParts[3].length()-1));
						}
						/*	if it's a family record add to collection
							add the family id to the collection (this will be the header)
						*/
						else if(parts[2].equals("FAM")){
							/* 
							 i.e:
							 	Family: @F1@'
							 	@I1@ kak li
							 	@I4@ feing wan
							 * 
							 */
							family value = new family(line);
							families.add("Family:  "+ parts[1]);
							String husWife=br.readLine();
							//split the next line up by space
							String[] indFamParts = husWife.split(" ");
							families.add(indFamParts[2]+ " "+ "Husband is :"+ individuals.get(indFamParts[2].toString()));
							husWife=br.readLine();
							indFamParts = husWife.split(" ");
							families.add(indFamParts[2] + " "+ "Wife is :"+individuals.get(indFamParts[2].toString()));
							String marriage = br.readLine();
							String [] marrinfo = marriage.split(" ");
							
							if(marrinfo[1].equals("MARR")){
								String gonext = br.readLine();
								String[] domarry = gonext.split(" ");
								value.setMarriage(gonext.substring(1, gonext.length()));
								//families.add( families.get(domarry[2].toString())+gonext.substring(1, gonext.length()));
							}
							String divorce = br.readLine();
							String [] divo = divorce.split(" ");
							if(divo[1].equals("DIV")){
								String divdate = br.readLine();
								String [] divdatespli = divdate.split(" ");
								value.setDivorce(divorce.substring(1, divorce.length()));

								families.add("Divorce date is : "+ divdate.substring(1, divdate.length()));
							}
						}
				}
				/*	if the tag is not valid and is not a fam or indi record
					print invalid tag
				*/
				else{ 
					//System.out.println("TAG: INVALID" );
				}
			}
			//System.out.println("");
		}
		br.close();
		//sort the hashmap and then loop through the individual hasmap and print out
		// Get a set of the entries
	/*	 Map<String, individual> map = new TreeMap<String, individual>(individuals); 
         Set set2 = map.entrySet();
         Iterator iterator2 = set2.iterator();
         while(iterator2.hasNext()) {
              Map.Entry me2 = (Map.Entry)iterator2.next();
              System.out.print(me2.getKey() + ": ");
              System.out.println(me2.getValue());*/
		for(String key :individuals.keySet()){
			System.out.println(key + ":" + individuals.get(key));
		
         }
		System.out.println(" ");
		

		//loop through the family collection and print out
				for (int i = 0; i < families.size(); i++) {
					System.out.println(families.get(i));
				
				}
	}
}
