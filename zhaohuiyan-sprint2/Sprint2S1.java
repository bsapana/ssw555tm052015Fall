import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
//author by zhaohui yan for stevens ssw555 project: sprint1 us08
public class Sprint2S1 {
    public HashMap<String,Integer> element=new HashMap<String,Integer>();
	public HashMap<String,ArrayList<String>> fn=new HashMap<String,ArrayList<String>>();
	public ArrayList<String> al=new ArrayList<String>();
	public Sprint2S1(){
		element.put("NAME",0);
		element.put("CHIL",0);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint2S1 p=new Sprint2S1();
		String file="TestFamily1.ged";
		p.outputError(file);

	}
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("---------------------------ERROR:more than 15 siblings--------------------");
		for(String s:al){
			if(fn.get(s).size()>=15){
				System.out.println("ERROR: FAMILY "+s+" has more than 15 siblings!");
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
			if(array[1].startsWith("@")&&array[2].equals("FAM")){//find the individual and family
					al.add(array[1]);        // add every fam id in the arraylist al
					String name=array[1];
					ArrayList<String> content=new ArrayList<String>();
					s=buffer.readLine();
					while(s!=null&&!s.startsWith("0")){
						String[] array2=this.parseLine(s);
						if(element.containsKey(array2[1])&&array2[1].equals("CHIL")){
							content.add(array2[2]);
						} 
						s=buffer.readLine();
					}
					fn.put(name,content);
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
