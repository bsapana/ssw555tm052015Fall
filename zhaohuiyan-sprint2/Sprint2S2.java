import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
//author by zhaohui yan for stevens ssw555 project: sprint1 us08
public class Sprint2S2 {
    public HashMap<String,Integer> element=new HashMap<String,Integer>();
	public HashMap<String,HashMap<String,String>> in=new HashMap<String,HashMap<String,String>>();
	public HashMap<String,ArrayList<String>> fn=new HashMap<String,ArrayList<String>>();
	public HashMap<String,String> husband=new HashMap<String,String>();
	public ArrayList<String> al=new ArrayList<String>();
	public Sprint2S2(){
		element.put("NAME",0);
		element.put("SEX",0);
		element.put("CHIL",0);
		element.put("HUSB",0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint2S2 p=new Sprint2S2();
		String file="TestFamily1.ged";
		p.outputError(file);

	}
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("--------------ERROR:Male members have the same last name in one family-----------------------");
		for(String s:al){
			HashMap<String,Integer> hash=new HashMap<String,Integer>();
			int count=0;
			for(String s2:fn.get(s)){
				if(in.get(s2).get("SEX").equals("M")){
					//System.out.println(in.get(husband.get(s)).get("NAME")+"----------------"+in.get(s2).get("NAME"));
					if(!hash.containsKey(in.get(s2).get("NAME"))){
						hash.put(in.get(s2).get("NAME"),0);
						count++;
					}
				}
				
			}
			
			if(count>1){
				System.out.println("ERROR: There are "+count+" last names in "+s +"! All male members of family should have the same last name");
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
					
					String name=array[1];            //get the individual id
					HashMap<String,String> content=new HashMap<String,String>();  //reserve all the information of the individual inthe hashmap
					s=buffer.readLine();    //read next line
					while(s!=null&&!s.startsWith("0")){       //make sure that the nextline is in the individual id
						String[] array2=this.parseLine(s);            
						if(element.containsKey(array2[1])){
							if(array2[1].equals("NAME")){
								content.put(array2[1],getSurname(array2[2]));
							} else{
								content.put(array2[1],array2[2]);
							}
							
						} 
						s=buffer.readLine();
					}
					
					in.put(name,content);
					
				} else if(array[2].equals("FAM")){    // find the family
					al.add(array[1]);        // add every fam id in the arraylist al
					String name=array[1];
					ArrayList<String> content=new ArrayList<String>();
					s=buffer.readLine();
					while(s!=null&&!s.startsWith("0")){
						String[] array2=this.parseLine(s);
						if(element.containsKey(array2[1])){
							content.add(array2[2]);
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
	
	
	public String getSurname(String name){
		StringTokenizer token=new StringTokenizer(name);
		ArrayList<String> a=new ArrayList<String>();
		int j=0;
		while(token.hasMoreTokens()){
			j++;
			String n=token.nextToken();
			a.add(n);
		}
		return a.get(--j);
	}
	
	public String[] parseLine(String s){
		StringTokenizer token=new StringTokenizer(s);
		String[] array=new String[3];
		int j=0;
		while(token.hasMoreTokens()){
			String n=token.nextToken();
			if(j>=3){
				array[2]+=" "+n;
			} else {
				array[j++]=n;
			}
		}
		return array;
	}
	
	
	
} 