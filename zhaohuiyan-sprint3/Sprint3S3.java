import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
//us23
public class Sprint3S3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint3S3 p=new Sprint3S3();
		String file="TestFamily1.ged";
		p.outputError(file);
	}
	
	public HashMap<String, HashMap<String,String>> in = new HashMap<String, HashMap<String,String>>();
	public HashMap<String, ArrayList<String>> inn = new HashMap<String, ArrayList<String>>();
	public ArrayList<String> repeteI = new ArrayList<String>();
	
	public Sprint3S3() {
		
	}
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("--------------Unique name and birth date -----------------------");
		for(String s: repeteI){
			HashMap<String,ArrayList<String>> h=new HashMap<String,ArrayList<String>>();
			ArrayList<String> o=new ArrayList<String>();
			for(String i:inn.get(s)){
				ArrayList<String> a=new ArrayList<String>();
				if(!h.containsKey(in.get(i).get("BIRT"))){
					a.add(i);
					h.put(in.get(i).get("BIRT"), a);
				} else {
					if(!o.contains(in.get(i).get("BIRT")))o.add(in.get(i).get("BIRT"));
					h.get(in.get(i).get("BIRT")).add(i);
				}
			}
			
			for(String n:o){
				String f="";
				for(String m: h.get(n)){
					f+=m+",";
				}
				
				System.out.println("Individual "+f+" have the same name and date");
			}
		}
	}
	
	
	public void parseData(String file) {
		try {
			FileReader f = new FileReader(file);
			BufferedReader buffer = new BufferedReader(f);
			String s = buffer.readLine();
			while (s != null) { // start to read file
				String[] array = this.parseLine(s); // parse the current line
				if (array[1].startsWith("@")&&array[2].equals("INDI")) { // find the individual and
					HashMap<String,String> content=new HashMap<String,String>();
					ArrayList<String> temp=new ArrayList<String>();
						String name = array[1];
						s=buffer.readLine(); 
						while(s!=null&&!s.startsWith("0")){
							String[] array2=this.parseLine(s);
							if(array2[1].equals("NAME")){
								content.put(array2[1],array2[2]);
								if (!inn.containsKey(array2[2])) {
									temp.add(name);
									inn.put(array2[2], temp);
								} else {
									if(!repeteI.contains(array2[2]))repeteI.add(array2[2]);
									inn.get(array2[2]).add(name); 
								}
							} else {
								
									if(array2[1].equals("BIRT")){
										s=buffer.readLine();
										String[] array3=this.parseLine(s);
										content.put(array2[1],array3[2]);
									} 
							}
						
							s=buffer.readLine();
						}
					
					in.put(name,content);
				} else {
					s = buffer.readLine();
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
