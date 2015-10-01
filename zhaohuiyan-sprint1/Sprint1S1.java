import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
//author by zhaohui yan for stevens ssw555 project: sprint1 us07
public class Sprint1S1 {
    public HashMap<String,String> month=new HashMap<String,String>();
    public HashMap<String,Integer> element=new HashMap<String,Integer>();
	public HashMap<String,HashMap<String,String>> in=new HashMap<String,HashMap<String,String>>();
	public ArrayList<String> al=new ArrayList<String>();
	public String currentDate;
	public Sprint1S1(){
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
		element.put("DEAT",0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sprint1S1 sp=new Sprint1S1();
		String file="zhaohui.yanP2.ged";
		sp.outputError(file);
	}
	
	
	public void outputError(String file){
		this.parseData(file);
		System.out.println("current date in this file is "+this.currentDate);
		System.out.println("---------------current date should be less than 150 years after birth for all living people-----------------------------");
		for(String s:al){
			if(this.in.get(s).get("BIRT")!=null&&this.in.get(s).get("DEAT")==null){
				String dBirth=this.in.get(s).get("BIRT");
				//System.out.println(dBirth);
				String[] array=this.parseLine(dBirth);
				dBirth=array[0]+" "+array[1]+" "+(Integer.parseInt(array[2])+150);
				//System.out.println(dBirth);
				if(this.compareDate(dBirth,currentDate)<=0){
					System.out.println("ERROR US07: current date of "+this.in.get(s).get("NAME")+"("+s+") who is still living should be less than 150 years after birth ");
				}
			}
		}	
		System.out.println("---------------Death should be less than 150 years after birth for dead people-----------------------------");
		for(String s:al){
			if(this.in.get(s).get("BIRT")!=null&&this.in.get(s).get("DEAT")!=null){
				String dBirth=this.in.get(s).get("BIRT");
				String dDeath=this.in.get(s).get("DEAT");
				String[] array=this.parseLine(dBirth);
				dBirth=array[0]+" "+array[1]+" "+(Integer.parseInt(array[2])+150);
				if(this.compareDate(dBirth,dDeath)<=0){
					System.out.println("ERROR US07: Death of "+this.in.get(s).get("NAME")+"("+s+") be less than 150 years after birth ");
				}
				
			}

		}
	}
	
	public void parseData(String file){
		try {
		FileReader f=new FileReader(file);
		BufferedReader buffer=new BufferedReader(f);
		String s=buffer.readLine();
		while(s!=null){
			String[] array=this.parseLine(s);
			if(array[0].equals("1")&&array[1].equals("DATE")){
				currentDate=array[2];
			}
			if(array[1].startsWith("@")&&array[2].equals("INDI")){
					al.add(array[1]);
					String name=array[1];
					HashMap<String,String> content=new HashMap<String,String>();
					s=buffer.readLine();
					while(s!=null&&!s.startsWith("0")){
						String[] array2=this.parseLine(s);
						if(array2[1].equals("NAME")){
							content.put(array2[1],array2[2]);
						} else {
							if(element.containsKey(array2[1])){
								if(!array2[1].equals("DEAT")){
									s=buffer.readLine();
									String[] array3=this.parseLine(s);
									content.put(array2[1],array3[2]);
								} else {
									if(array2[2].equals("Y")){
										s=buffer.readLine();
										String[] array3=this.parseLine(s);
										content.put(array2[1],array3[2]);
									}
								}
							}
						}
					
						s=buffer.readLine();
					}
					
					in.put(name,content);
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
