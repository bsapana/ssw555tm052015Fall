import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
//US32
public class Sprint4S2 {

	public HashMap<String, HashMap<String,String>> in = new HashMap<String, HashMap<String,String>>();
	public HashMap<String,ArrayList<String>> fm = new HashMap<String, ArrayList<String>>();
	public ArrayList<String> family=new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint4S2 s=new Sprint4S2();
		s.outputError("TestFamily2");
	}
	
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("--------------List all multiple births in a GEDCOM file -----------------------");
		for(String s: family){
			System.out.println("Multiple births in the family "+s+" are: ");
			for(String children:fm.get(s)){
				System.out.print(children);
				System.out.println(in.get(children).get("NAME"));
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
						String name = array[1];
						s=buffer.readLine(); 
						while(s!=null&&!s.startsWith("0")){
							String[] array2=this.parseLine(s);
							if(array2[1].equals("NAME")){
								content.put(array2[1],array2[2]);
							} else {
								
									if(array2[1].equals("BIRT")){
										s=buffer.readLine();
										String[] array3=this.parseLine(s);
										content.put(array2[1],array3[2]);
									} 
									
									if(array2[1].equals("DEAT")){
										content.put(array2[1],array2[2]);
									}
							}
						
							s=buffer.readLine();
						}
					
					in.put(name,content);
				}else if(array[1].startsWith("@")&&array[2].equals("FAM")){    // find the family
					family.add(array[1]);        // add every fam id in the arraylist al
					String name=array[1];
					ArrayList<String> content=new ArrayList<String>();
					s=buffer.readLine();
					while(s!=null&&!s.startsWith("0")){
						String[] array2=this.parseLine(s);
						if(array2[1].equals("CHIL")){
							content.add(array2[2]);
						}
						s=buffer.readLine();
					}
					fm.put(name,content);
					
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
