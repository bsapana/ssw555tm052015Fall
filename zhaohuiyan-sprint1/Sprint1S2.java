import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
//author by zhaohui yan for stevens ssw555 project: sprint1 us08
public class Sprint1S2 {
    public HashMap<String,String> month=new HashMap<String,String>();
    public HashMap<String,Integer> element=new HashMap<String,Integer>();
	public HashMap<String,HashMap<String,String>> in=new HashMap<String,HashMap<String,String>>();
	public HashMap<String,HashMap<String,String>> fn=new HashMap<String,HashMap<String,String>>();
	public ArrayList<String> al=new ArrayList<String>();
	public Sprint1S2(){
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
		element.put("NAME",0);
		element.put("BIRT",0);
		element.put("FAMC",0);
		element.put("MARR",0);
		element.put("DIV",0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint1S2 p=new Sprint1S2();
		String file="zhaohui.yanP2.ged";
		p.outputError(file);

	}
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("--------------------------Child should be born after marriage of parents------------------------");
		for(String s: this.al){
			if(this.in.get(s).get("FAMC")!=null){
				String dbirth=this.in.get(s).get("BIRT");
				String dmarr=this.fn.get(this.in.get(s).get("FAMC")).get("MARR");
				System.out.println(dbirth+"-----"+dmarr);
				if(this.compareDate(dbirth,dmarr)<0){
					System.out.println("Anomaly US08: Birth date of "+this.in.get(s).get("NAME") +"("+s+") occurs before the marriage date of his parents in Family "+this.in.get(s).get("FAMC"));
				} 
			}
		}
		
		System.out.println("---------------------Child should be born before parents' divorce-----------------------------");
		for(String s:this.al){
			if(this.in.get(s).get("FAMC")!=null){
				String dbirth=this.in.get(s).get("BIRT");
				if (this.fn.get(this.in.get(s).get("FAMC")).get("DIV")!=null){
					String ddiv=this.fn.get(this.in.get(s).get("FAMC")).get("DIV");
					System.out.println(dbirth+"-----"+ddiv);
					if(this.compareDate(dbirth,ddiv)>0){
						System.out.println("Anomaly US08: Birth date of "+this.in.get(s).get("NAME") +"("+s+") occurs after the divorce date of his parents in Family "+this.in.get(s).get("FAMC"));
					} 
				}
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
					al.add(array[1]);             // add every individual id in the arraylist al
					String name=array[1];            //get the individual id
					HashMap<String,String> content=new HashMap<String,String>();  //reserve all the information of the individual inthe hashmap
					s=buffer.readLine();    //read next line
					while(s!=null&&!s.startsWith("0")){       //make sure that the nextline is in the individual id
						String[] array2=this.parseLine(s);            
						if(!array2[1].equals("BIRT")&&element.containsKey(array2[1])){
							content.put(array2[1],array2[2]);
						} else {
							if(element.containsKey(array2[1])){
								s=buffer.readLine();
								String[] array3=this.parseLine(s);
								content.put(array2[1],array3[2]);
							}
						}
					
						s=buffer.readLine();
					}
					
					in.put(name,content);
					
				} else if(array[2].equals("FAM")){    // find the family
					String name=array[1];
					HashMap<String,String> content=new HashMap<String,String>();
					s=buffer.readLine();
					while(s!=null&&!s.startsWith("0")){
						String[] array2=this.parseLine(s);
						if(element.containsKey(array2[1])){
							s=buffer.readLine();
							String[] array3=this.parseLine(s);
							content.put(array2[1],array3[2]);
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
	
	public int compareDate(String date1,String date2){
		try {
			String[] dateA1=this.parseLine(date1);
			String[] dateA2=this.parseLine(date2);
			String d1=dateA1[0]+" "+month.get(dateA1[1])+" "+dateA1[2];
			String d2=dateA2[0]+" "+month.get(dateA2[1])+" "+dateA2[2];
			SimpleDateFormat sdf=new SimpleDateFormat("dd MM yyyy");
			return sdf.parse(d1).compareTo(sdf.parse(d2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	
	}

}
