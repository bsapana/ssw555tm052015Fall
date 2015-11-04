import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
//us22
public class Sprint3S2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint3S2 p=new Sprint3S2();
		String file="TestFamily1.ged";
		p.outputError(file);
	}
	
	public HashMap<String,Integer> element=new HashMap<String,Integer>();
	public HashMap<String, String> in = new HashMap<String, String>();
	public HashMap<String, HashMap<String,String>> fn = new HashMap<String, HashMap<String,String>>();
	public ArrayList<String> fm= new ArrayList<String>();

	public Sprint3S2() {
		element.put("SEX",0);
		element.put("WIFE",0);
		element.put("HUSB",0);
	}
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("--------------ERROR:Husband in family should be male and wife in family should be female -----------------------");
		for(String s:fm){
		
			if(!in.get(fn.get(s).get("HUSB")).equals("M")){
				
				System.out.println("In the family "+s+", The husband is not a male!");
			}
			
			if(!in.get(fn.get(s).get("WIFE")).equals("F")){
				System.out.println("In the family "+s+", The wife is not a female!");
			}
			
		}
	
	}
	
	
	public void parseData(String file){
		try {
		FileReader f=new FileReader(file);
		BufferedReader buffer=new BufferedReader(f);
		String s=buffer.readLine();
		while(s!=null){    // start to read file
			String[] array=this.parseLine(s);  //parse the current line 
			if(array[1].startsWith("@")){       //find the individual and family
				if(array[2].equals("INDI")){   // find the individual
					String content="";
					String name=array[1];            //get the individual id
					s=buffer.readLine();    //read next line
					while(s!=null&&!s.startsWith("0")){       //make sure that the nextline is in the individual id
						String[] array2=this.parseLine(s);            
						if(element.containsKey(array2[1])){
							content=array2[2];
						} 
						s=buffer.readLine();
					}
					
					in.put(name,content);
					
				} else if(array[2].equals("FAM")){    // find the family
					fm.add(array[1]);        // add every fam id in the arraylist al
					String name=array[1];
					HashMap<String,String> content=new HashMap<String,String>();
					s=buffer.readLine();
					while(s!=null&&!s.startsWith("0")){
						String[] array2=this.parseLine(s);
						if(element.containsKey(array2[1])){
							content.put(array2[1],array2[2]);
						}
						s=buffer.readLine();
					}
					fn.put(name,content);
				} 
			} else {
				s=buffer.readLine();
			}	
		}		
		buffer.close();
		f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public String[] parseLine(String s) {
		StringTokenizer token = new StringTokenizer(s);
		String[] array = new String[3];
		int j = 0;
		while (token.hasMoreTokens()) {
			String n = token.nextToken();
			if (j >= 3) {
				array[2] += " " + n;
			} else {
				array[j++] = n;
			}
		}
		return array;
	}

	
	
	

}
