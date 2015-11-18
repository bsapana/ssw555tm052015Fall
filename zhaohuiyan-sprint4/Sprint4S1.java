import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Sprint4S1 {
	//US31
	
	public HashMap<String, HashMap<String,String>> in = new HashMap<String, HashMap<String,String>>();
	public HashMap<String,String> fm = new HashMap<String, String>();
	public ArrayList<String> person=new ArrayList<String>();
	public String currentDate;
	public HashMap<String,String> month=new HashMap<String,String>();
	
	public Sprint4S1(){
		month.put("JAN","1");
		month.put("FEB","2");
		month.put("MAR","3");
		month.put("APR","4");
		month.put("MAY","5");
		month.put("JUN", "6");
		month.put("JUL","7");
		month.put("AUG","8");
		month.put("SEP","9"); 
		month.put("OCT","10");
		month.put("NOV","11");
		month.put("DEC","12");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint4S1 s=new Sprint4S1();
		s.outputError("TestFamily2");
		//System.out.println(s.computeAge("8 AUG 1992", "1 SEP 2015"));
	}
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("-----------List all living people over 30 who have never been married in a GEDCOM file--------------");
		for(String s:person){
			if(fm.containsKey(s)) continue;
			if(this.computeAge(in.get(s).get("BIRT"), currentDate)>30){
				if(in.get(s).get("DEAT")==null)
				System.out.println("Individual: "+s+" NAME:"+in.get(s).get("NAME")+" is living and his/her age is over 30 and has never been married!");
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
				if(array[0].equals("1")&&array[1].equals("DATE")){
					currentDate=array[2];
				}
				if (array[1].startsWith("@")&&array[2].equals("INDI")) { // find the individual and
						HashMap<String,String> content=new HashMap<String,String>();
						String name = array[1];
						person.add(name);
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
					//fm.add(array[1]);        // add every fam id in the arraylist al
					s=buffer.readLine();
					while(s!=null&&!s.startsWith("0")){
						String[] array2=this.parseLine(s);
						if(array2[1].equals("HUSB")||array2[1].equals("WIFE")){
							fm.put(array2[2],"Y");
						}
						s=buffer.readLine();
					}
					
					
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
	
	
	public int computeAge(String s,String s2) {
		Calendar dob = Calendar.getInstance();  
		String[] dateA1=this.parseLine(s);
		int day=Integer.parseInt(dateA1[0]);
		int m=Integer.parseInt(month.get(dateA1[1]));
		int y=Integer.parseInt(dateA1[2]);
		dob.set(y,m,day);  
		Calendar today = Calendar.getInstance();
		String[] dateA2=this.parseLine(s2);
		int day2=Integer.parseInt(dateA2[0]);
		int m2=Integer.parseInt(month.get(dateA2[1]));
		int y2=Integer.parseInt(dateA2[2]);
		today.set(y2, m2, day2);
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}
	
	

}
