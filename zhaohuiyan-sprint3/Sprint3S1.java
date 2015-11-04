import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
//us23
public class Sprint3S1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint3S1 p=new Sprint3S1();
		String file="TestFamily1.ged";
		p.outputError(file);
	}

	public HashMap<String, Integer> in = new HashMap<String, Integer>();
	public HashMap<String, Integer> fn = new HashMap<String, Integer>();
	public ArrayList<String> repeteI = new ArrayList<String>();
	public ArrayList<String> repeteF = new ArrayList<String>();

	public Sprint3S1() {
		
	}
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("--------------ERROR:All individual IDs should be unique -----------------------");
		for(String s: repeteI){
			System.out.println("individua ID "+s+" is not unique and there are  "+in.get(s)+" "+s);
		}
		System.out.println("--------------ERROR:All family IDs should be unique-----------------------");
		for(String s:repeteF){
			System.out.println("Family ID "+s+" is not unique and there are  "+fn.get(s)+" "+s);
		}
	}
	
	
	public void parseData(String file) {
		try {
			FileReader f = new FileReader(file);
			BufferedReader buffer = new BufferedReader(f);
			String s = buffer.readLine();
			while (s != null) { // start to read file
				String[] array = this.parseLine(s); // parse the current line
				if (array[1].startsWith("@")) { // find the individual and
												
					if (array[2].equals("INDI")) { // find the individual
						String name = array[1];
						if (!in.containsKey(name)) {
							in.put(name, 1);
						} else {
							if(!repeteI.contains(name))repeteI.add(name);
							in.put(name, in.get(name) + 1);
						}

					} else if (array[2].equals("FAM")) { // find the family
						String name = array[1];
						if (!fn.containsKey(name)) {
							fn.put(name, 1);
						} else {
							if(!repeteF.contains(name))repeteF.add(name);
							fn.put(name, fn.get(name) + 1);
						}

					}
					s = buffer.readLine();
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
