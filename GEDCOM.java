import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
class MainActivity
{   
    public static boolean isNumeric(String str)
    {
    return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    public static void main(String args[])
    {
        if(args.length==1)
        {
        String filename=args[0];
        File f=new File(filename);
        
           if(f.exists())
           {   int ln=0;
               String Tags[]={"INDI","NAME","SEX","BIRT","DEAT","FAMC","FAMS","FAM","MARR","HUSB","WIFE","CHIL","DIV","DATE","HEAD","TRLR","NOTE"};
               System.out.println(filename+" is being accessed");
               boolean Err=false;
               boolean Cerr=false;
               boolean Infoerr=false;
               boolean error=false;
               
               String lastevent="null";
               boolean evt=false;
               int evtl=0;
               
               ArrayList<String> indi = new ArrayList<String>();
               int ic=-1;
               Map name = new HashMap(); 
               Map sex = new HashMap(); 
               Map birt = new HashMap(); 
               Map deat = new HashMap(); 
               Map famc = new HashMap(); 
               Map fams = new HashMap();

               
               ArrayList<String> fam = new ArrayList<String>();
               int fc=-1;
               Map marr = new HashMap(); 
               Map husb = new HashMap(); 
               Map wife = new HashMap(); 
               Map chil = new HashMap(); 
               Map div = new HashMap();                
               
               ArrayList<String> month = new ArrayList<String>();
               month.add("JAN");
               month.add("FEB");
               month.add("MAR");
               month.add("APR");
               month.add("MAY");
               month.add("JUN");
               month.add("JUL");
               month.add("AUG");
               month.add("SEP");
               month.add("OCT");
               month.add("NOV");
               month.add("DEC");
               
               
               String ip="null";
               String fp="null";
               
               ArrayList<String> wife_list = new ArrayList<String>();
               ArrayList<String> husband_list = new ArrayList<String>();

               //for printing
             /*  int linenumber=0;
                 try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                 for(String line; (line = br.readLine()) != null; ) {
                   linenumber++;
                   System.out.println("line"+linenumber+":\t+line);
                 }
                }
                catch(Exception e)
                {
                      System.out.println("Error in reading the file "+f+" : "+e.getMessage() );
                }
               */
               
               
               //logics are added separately
               try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                 for(String line; (line = br.readLine()) != null; ) {
                     if(Err)
                     {
                        // break;
                        }
                         if(Cerr)
                     {
                       // break;
                        }
                        if(error)
                        {
                            //break;
                        }
                     ln++;
                     String[] data=line.split(" ");
                     int level=9;
                     String tag="null";
                        if(data.length>1)
                        { 
                            if(isNumeric(data[0]))
                              {
                                  level=Integer.parseInt(data[0]);
                                  if(level==0)
                                  {        
                                      
                                    if(data[1].compareTo(Tags[14])==0)//checking head
                                    {
                                        tag=data[1];
                                    }
                                    else if(data[1].compareTo(Tags[15])==0)//checking trailer
                                    {
                                        tag=data[1];
                                    }
                                    else if(data[1].compareTo(Tags[16])==0) //checking note
                                    {
                                        tag=data[1];
                                        
                                        
                                    }
                                     else
                                    {     if(data.length==3)
                                        {
                                            if(data[2].compareTo(Tags[0])==0)//checking indi
                                                {
                                                    tag=data[2];
                                                    if(!(indi.contains(data[1])))
                                                    {
                                                        ic++;
                                                        ip=data[1];
                                                        indi.add(ic,data[1]);
                                                        name.put(ip,"null");
                                                        sex.put(ip,"null");
                                                        birt.put(ip,"null");
                                                        deat.put(ip,"null");
                                                        famc.put(ip,"null");
                                                        fams.put(ip,"null");
                                                    }
                                                    else
                                                    {
                                                        Cerr=true;
                                                        System.out.println("Critical Error: Duplicate id for \"INDI\" at line "+ ln );
                                                    }
                                                    
                                                }
                                             else   if(data[2].compareTo(Tags[7])==0) //checking fam
                                                {
                                                    tag=data[2];
                                                    if(!(fam.contains(data[1])))
                                                    {
                                                        fc++;
                                                        fp=data[1];
                                                        fam.add(fc,data[1]);
                                                        marr.put(fp,"null");
                                                        husb.put(fp,"null");
                                                        wife.put(fp,"null");
                                                        chil.put(fp,"null");
                                                        div.put(fp,"null");
                                                    }
                                                    else
                                                    {
                                                         Cerr=true;
                                                        System.out.println("Critical Error: Duplicate id for \"FAM\" at line "+ ln );
                                                    }
                                                                                                       
                                                }
                                                else
                                                {
                                                    Err=true;
                                                    System.out.println("Syntax Error: Invalid Tag \""+data[2]+"\"at line "+ ln );
                                                }
                                        }
                                        else
                                        {
                                        Err=true;
                                        System.out.println("Syntax Error: Invalid Tag \""+data[2]+"\"at line "+ ln );
                                        }
                                    }
                                }
                                else
                                {
                                    if(data[1].compareTo(Tags[1])==0)//checking name
                                    {
                                        tag=data[1];
                                    if(ip.compareTo("null")!=0)
                                     {
                                         if(name.get(ip)=="null")
                                         {
                                        if(data.length==3)
                                        {
                                            name.put(ip,data[2]);
                                        }
                                        else if(data.length==4)
                                        {
                                            name.put(ip,data[2]+" "+ data[3].replace("/",""));
                                        }
                                        else if(data.length==5)
                                        {
                                            name.put(ip,data[2]+" "+ data[3].replace("/","")+" "+data[4].replace("/",""));
                                        }
                                        else
                                        {
                                            Cerr=true;
                                            System.out.println("Critical Error: Invalid parameters (length of data ="+data.length+") for the Tag : \""+tag+"\" at line "+ln);
                                        }
                                         }
                                         else
                                         {
                                             Infoerr=true;
                                             System.out.println("Informatics Error: Value already assigned for the Tag : \""+tag+"\" at line "+ln);
                                         }
                                    }
                                    else
                                    {
                                        Cerr=true;
                                        System.out.println("Critical Error: Unique Id not set for the tag : \""+tag+"\" at line "+ln);
                                    }
                                   }
                                    else if(data[1].compareTo(Tags[2])==0)//checking gender
                                    {
                                        tag=data[1];
                                        if(ip.compareTo("null")!=0)
                                        {
                                        if(sex.get(ip)=="null")
                                        {
                                        if(data[2].compareTo("M")==0||data[2].compareTo("F")==0)
                                        {
                                            sex.put(ip,data[2]);
                                        }
                                        else
                                        {  
                                            Cerr=true;
                                            System.out.println("Critical Error: Invalid parameters for the Tag : \""+tag+"\" at line "+ln);
                                        }
                                        }
                                         else
                                         {
                                             Infoerr=true;
                                             System.out.println("Informatics Error: Value already assigned for the Tag : \""+tag+"\" at line "+ln);
                                         }
                                        }
                                        else
                                    {
                                        Cerr=true;
                                        System.out.println("Critical Error: Unique Id not set for the tag : \""+tag+"\" at line "+ln);
                                    }
                                    }
                                    else if(data[1].compareTo(Tags[3])==0)//checking birt
                                    {
                                        tag=data[1];

                                               if(!evt)
                                        {
                                        lastevent="birt";
                                        evtl=ln;
                                        evt=true;
                                        }
                                        else
                                        {
                                            Cerr=true;
                                            System.out.println("Critical Error: Date is missing for an event : \""+lastevent+"\" at line "+evtl);
                                        }
                                       
                                     

                                    }
                                    else if(data[1].compareTo(Tags[4])==0)//checking deat
                                    {
                                        tag=data[1];
                                        if(!evt)
                                        {
                                        lastevent="deat";
                                        evtl=ln;
                                        evt=true;
                                        }
                                        else
                                        {
                                            Cerr=true;
                                            System.out.println("Critical Error: Date is missing for an event : \""+lastevent+"\" at line "+evtl);
                                        }
                                    }
                                    else if(data[1].compareTo(Tags[5])==0)//checking famc
                                    {
                                        tag=data[1];
                                        if(ip.compareTo("null")!=0)
                                        {
                                        famc.put(ip,data[2]);
                                        }
                                        else
                                        {
                                        Cerr=true;
                                        System.out.println("Critical Error: Unique Id not set for the tag : \""+tag+"\" at line "+ln);
                                        }
                                    }
                                    else if(data[1].compareTo(Tags[6])==0)//checking fams
                                    {
                                        tag=data[1];
                                        if(ip.compareTo("null")!=0)
                                        {
                                        fams.put(ip,data[2]);
                                        }
                                        else
                                       {
                                        Cerr=true;
                                        System.out.println("Critical Error: Unique Id not set for the tag : \""+tag+"\" at line "+ln);
                                        }
                                    }
                                    else if(data[1].compareTo(Tags[8])==0)//checking marr
                                    {
                                        tag=data[1];
                                        if(!evt)
                                        {
                                        lastevent="marr";
                                        evtl=ln;
                                        evt=true;
                                        }
                                        else
                                        {
                                            Cerr=true;
                                            System.out.println("Critical Error: Date is missing for an event : \""+lastevent+"\" at line "+evtl);
                                        }
                                    }
                                     else if(data[1].compareTo(Tags[9])==0)//checking husb 
                                    {
                                        tag=data[1];
                                        if(fp.compareTo("null")!=0)
                                        {
                                            if(husb.get(fp)=="null")
                                            {
                                               husb.put(fp,data[2]);
                                            }
                                             else
                                            {
                                             Infoerr=true;
                                             System.out.println("Informatics Error: Value already assigned for the Tag : \""+tag+"\" at line "+ln);
                                            }
                                        }
                                        else
                                        {
                                        Cerr=true;
                                        System.out.println("Critical Error: Unique Id not set for the tag : \""+tag+"\" at line "+ln);
                                        }

                                    }
                                    else if(data[1].compareTo(Tags[10])==0)//checking wife
                                    {
                                        tag=data[1];
                                        if(fp.compareTo("null")!=0)
                                        {
                                            if(wife.get(fp)=="null")
                                            {
                                               wife.put(fp,data[2]);
                                            }
                                            else
                                            {
                                             Infoerr=true;
                                             System.out.println("Informatics Error: Value already assigned for the Tag : \""+tag+"\" at line "+ln);
                                            }
                                        }
                                        else
                                       {
                                        Cerr=true;
                                        System.out.println("Critical Error: Unique Id not set for the tag : \""+tag+"\" at line "+ln);
                                        }
                                    }
                                    else if(data[1].compareTo(Tags[11])==0)//checking chil
                                    {
                                        tag=data[1];
                                        if(fp.compareTo("null")!=0)
                                        {
                                            
                                            if(chil.get(fp)=="null")
                                            {
                                                chil.put(fp,data[2]);
                                            }
                                            else
                                            {
                                               String cids=""+chil.get(fp);
                                               cids=cids+"~"+data[2];
                                               chil.put(fp,cids);
                                            }
                                        
                                         }
                                        else
                                       {
                                        Cerr=true;
                                        System.out.println("Critical Error: Unique Id not set for the tag : \""+tag+"\" at line "+ln);
                                        }
                                    }
                                    else if(data[1].compareTo(Tags[12])==0)//checking div
                                    {
                                        tag=data[1];
                                        if(!evt)
                                        {
                                        lastevent="div";
                                        evtl=ln;
                                        evt=true;
                                        }
                                        else
                                        {
                                            Cerr=true;
                                            System.out.println("Critical Error: Date is missing for an event : \""+lastevent+"\" at line "+evtl);
                                        }
                                    }
                                    else if(data[1].compareTo(Tags[13])==0)//checking date
                                    {
                                        tag=data[1];
                                        if(data.length==5)
                                        {
                                            String day=data[2];
                                            String mon=data[3];
                                            String year=data[4];
                                             if((day.charAt(0)+"".compareTo("0")!=0)&&(day.length()<3)&&(year.length()==4)&&month.contains(mon))
                                            {
                                                if(evt)
                                                {
                                                    if(lastevent.compareTo("birt")==0)
                                                    {
                                                       birt.put(ip,day+"-"+mon+"-"+year);
                                                       evt=false;
                                                    }
                                                    else if(lastevent.compareTo("deat")==0)
                                                    {
                                                        deat.put(ip,day+"-"+mon+"-"+year);
                                                        evt=false;
                                                    }
                                                    else if(lastevent.compareTo("marr")==0)
                                                    {
                                                        marr.put(fp,day+"-"+mon+"-"+year);
                                                        evt=false;
                                                    }
                                                    else if(lastevent.compareTo("div")==0)
                                                    {
                                                        div.put(fp,day+"-"+mon+"-"+year);
                                                        evt=false;
                                                    }
                                                    else
                                                    {
                                                        Cerr=true;
                                                        System.out.println("System Malfunction: Event feed is having isssues");
                                                    }
                                                }
                                                else
                                                {
                                                    Cerr=true;
                                                    System.out.println("Critical Error: Date is not pointed to any event at line "+ln);
                                                }
   
                                                
                                            }
                                            else
                                            {
                                                Cerr=true;
                                                System.out.println("Critical Error: Invalid parameters for the Tag : \""+tag+"\" at line "+ln);
                                            }
                                            
                                        }
                                    }
                                    else
                                    {    
                                        Err=true;
                                        System.out.println("Syntax Error: Invalid Tag \""+data[1]+"\"at line "+ ln );
                                    }
                                }
                            }
                            else
                            {
                                 Err=true;
                                 System.out.println("Syntax Error: Invalid Level \""+data[0]+"\"at line "+ ln );
                            }
                           // if(tag.compareTo("null")!=0&&!Cerr)
                           System.out.println(" Level :"+ level+ "  TAG :"+ tag ); 
                        }
                        else
                        {   Err=true;
                            System.out.println("Syntax Error: No Tag at line "+ ln );
                           
                        }
                    // System.out.println("line"+ln+":"+line);
                 }
                 //printing saved data
                     System.out.println("\n");
                     System.out.println("\n");
                 System.out.println("Individual Info");
                  for(int c=0;c<indi.size();c++)
                  {
                  System.out.println("\n");
                  System.out.println("\n");
                  String id=indi.get(c);
                  System.out.println("Individual Id:\t\t" +id);
                  System.out.println("Name:\t\t\t" + name.get(id));
                  System.out.println("Gender:\t\t\t" + sex.get(id));
                  
                  String dob,dod;
                  dob=birt.get(id).toString();
                  dod=deat.get(id).toString();
                  
                  if((dod.compareTo("null")!=0)&&(dob.compareTo("null")!=0))
                  {
                      if( !birthvalidation(dod, dob))
                      {
                          System.out.println("Error US03:Birth date of "+name.get(id)+" ("+id.replace("@","")+") occurs after  death date.");
                        }
                    }
                    
                  System.out.println("D.O.B:\t\t\t" + dob);
                  
                  
                  
                  
                  
                           		//start: Added for US01
                  if(dob.compareTo("null")!=0)
                 {   
                     if( !currentdatevalidation(dob))
                     {
                    	 System.out.println("Error US01 :Birth date of "+name.get(id)+" ("+id.replace("@","")+") should not be after current date.");
                     }
                   }
                  //end: Added for US01
                  
                  
                  
                  
                  
                  
                  System.out.println("D.O.D:\t\t\t" + dod );
                  
                  
                  
                  
          		 //start: Added for US01
                  if(dod.compareTo("null")!=0)
                 {   
                     if( !currentdatevalidation(dod))
                     {
                    	 System.out.println("Error US01 :Death date of "+name.get(id)+" ("+id.replace("@","")+") should not be after current date.");
                     }
                   }
                  //end: Added for US01
                  
                  
                  
                  System.out.println("Child of Family:\t" + famc.get(id));
                  System.out.println("Spouse of Family:\t" + fams.get(id));
                 }
                 
                 
                 System.out.println("\n");
                 System.out.println("\n");
                 System.out.println("Family Info");
                 for(int c=0;c<fam.size();c++)
                 {
                  System.out.println("\n");
                  System.out.println("\n");
                  String id=fam.get(c);  
                  System.out.println("Family Id:\t\t" +id);
                  
                  String dom,dod;
                  dom=marr.get(id).toString();
                  dod=div.get(id).toString();
                  
                  System.out.println("Date of Marriage:\t" + marr.get(id));
                  
                  
                  
                  
                  
                   //start: Added for US01 and US02
                  String hdob,wdob;
                  
                  hdob=birt.get(husb.get(id)).toString(); //Added for US02
                  wdob=birt.get(wife.get(id)).toString(); //Added for US02
                  if(dom.compareTo("null")!=0)
                 {   
                     if( !currentdatevalidation(dom))
                     {
                         System.out.println("Error US01 : Marriage date of "+name.get(husb.get(id))+" ("+(husb.get(id)).toString().replace("@","")+") and "+name.get(wife.get(id))+" ("+(wife.get(id)).toString().replace("@","")+") should not be after current date");
                     }
                   }
                  if((dom.compareTo("null")!=0) && (hdob.compareTo("null")!=0))
                 {   
                     if( !birthvalidation(dom, hdob))
                     {
                         System.out.println("Error US02 : Birth date of Husband "+name.get(husb.get(id))+" ("+(husb.get(id)).toString().replace("@","")+") should not be after marriage date");
                     }
                   }
                  if((dom.compareTo("null")!=0) && (wdob.compareTo("null")!=0))
                 {   
                     if( !birthvalidation(dom, wdob))
                     {
                         System.out.println("Error US02 : Birth date of Wife "+name.get(wife.get(id))+" ("+(wife.get(id)).toString().replace("@","")+") should not be after marriage date");
                     }
                   }
                  //end: Added for US02
                  
                  
                  
                  
                  
                  
                  
                  
                  System.out.println("Husband's Id:\t\t" + husb.get(id)+" "+name.get(husb.get(id)));
                  if(husband_list.contains( husb.get(id)))
                  {
                      System.out.println("Anomaly US11:"+name.get(husb.get(id))+"("+(husb.get(id)).toString().replace("@","")+") is already married to another spouse");
                    }
                  husband_list.add(husb.get(id)+"");
                  System.out.println("Wife's Id:\t\t" + wife.get(id)+" "+name.get(wife.get(id)));
                  if(wife_list.contains( wife.get(id)))
                  {
                      System.out.println("Anomaly US11:"+name.get(wife.get(id))+"("+(wife.get(id)).toString().replace("@","")+") is already married to another spouse");
                    }
                  wife_list.add(wife.get(id)+"");
                  System.out.println("Id/s of Child/Children:\t" + chil.get(id));
                  System.out.println("Date of Divorce:\t" + div.get(id));
                  
                  
                  
                  
                  //start: Added for US01
                  
                  if(dod.compareTo("null")!=0)
                 {   
                     if( !currentdatevalidation(dod))
                     {
                         System.out.println("Error US01 : Divorce date of "+name.get(husb.get(id))+" ("+(husb.get(id)).toString().replace("@","")+") and "+name.get(wife.get(id))+" ("+(wife.get(id)).toString().replace("@","")+") should not be after current date");
                     }
                   }
                  
                //end: Added for US01
                  
                  
                  
                   if((dod.compareTo("null")!=0)&&(dom.compareTo("null")!=0))
                  {
                      if( !birthvalidation(dod, dom))
                      {
                          System.out.println("Error US04: Marriage date of "+name.get(husb.get(id))+" ("+(husb.get(id)).toString().replace("@","")+") and "+name.get(wife.get(id))+" ("+(wife.get(id)).toString().replace("@","")+")occurs after their divorce.");
                        }
                    }
                  
                    
                    //checking us12 : parents too old
                    try{
                 boolean moth=false;
                 boolean fath=false;
                  String children[]=(chil.get(id)).toString().split("~");
                  for(int z=0;z<children.length;z++)
                  {
                 if( mAgeValidation((birt.get(wife.get(id))).toString(),(birt.get(children[z])).toString()))
                 {
                     
                     moth=true;
                    }
                 if( fAgeValidation((birt.get(husb.get(id))).toString(),(birt.get(children[z])).toString()))
                 {
                      
                      fath=true;
                    }
                  }
                  
                  if(moth&&fath)
                  {
                      System.out.println("Anomaly US12 : Parents are too old ");
                    }
                  if(moth&&!fath)
                  {
                      System.out.println("Anomaly US12 : Mother is too old ");
                    }
                    if(fath&&!moth)
                    {
                        System.out.println("Anomaly US12 : Father is too old ");
                    }
                  
                }
                catch(Exception e)
                {
                }
                  
                  
                  
                }
                  
                 
                  }
                  catch(Exception e)
                  {
                      System.out.println("System Error in reading the file "+f+" : "+e.getMessage() );
                    }
           }
           else
           {
               System.out.println("System Error: "+filename+" cannot be accessed");
           }
           
        }
        else
        {
        System.out.println("System Error: Invalid Number of Paramaters");
        }

     }
     
     
     
     public static boolean birthvalidation(String deat, String birt )
     {
         try
         {
         ArrayList<String> month = new ArrayList<String>();
               month.add("JAN");
               month.add("FEB");
               month.add("MAR");
               month.add("APR");
               month.add("MAY");
               month.add("JUN");
               month.add("JUL");
               month.add("AUG");
               month.add("SEP");
               month.add("OCT");
               month.add("NOV");
               month.add("DEC");

               int death_day,death_month,death_year,birth_day,birth_month,birth_year;
               String[] array = deat.split("-");
               
               if(array[0].length()<2)
               {
               death_day=Integer.parseInt(0+array[0]);
               }
               else
               {
                death_day=Integer.parseInt(array[0]);
                }
               
               
               death_month=(month.indexOf(array[1]));
               death_year=Integer.parseInt(array[2]);
               
               String[] array2 = birt.split("-");
               if(array2[0].length()<2)
               {
               birth_day=Integer.parseInt(0+array2[0]);
               }
               else
               {
               birth_day=Integer.parseInt(array2[0]);
                }
               birth_month=(month.indexOf(array2[1]));
               birth_year=Integer.parseInt(array2[2]);
               
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date death_date = sdf.parse( death_year+"-"+death_month+"-"+death_day);
        	Date birth_date = sdf.parse( birth_year+"-"+birth_month+"-"+birth_day);
        	
        	if(birth_date.compareTo(death_date )>0){
        		return false;
                }
        	
        }
        catch(Exception e)
        {
            System.out.println("Error in birthvalidation() :"+e.getMessage() );
        }
               
         return true;
        }
        
        public static boolean mAgeValidation(String mother, String child )
        {
   
               int m_year,c_year;
               String[] array =mother.split("-");
               m_year=Integer.parseInt(array[2]);
               
               String[] array2 = child.split("-");
               c_year=Integer.parseInt(array2[2]);
               
               if(c_year-m_year<60)
               {
                     return false;
                }

        	
        
            return true;
        }
        public static boolean fAgeValidation(String father, String child )
        {
   
               int f_year,c_year;
               String[] array =father.split("-");
               f_year=Integer.parseInt(array[2]);
               
               String[] array2 = child.split("-");
               c_year=Integer.parseInt(array2[2]);
               
               if(c_year-f_year<80)
               {
                   return false;
                }
        	
        
            return true;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         //start: Added for US01
     
     public static boolean currentdatevalidation(String date)
     {
         try
         {
         ArrayList<String> month = new ArrayList<String>();
               month.add("JAN");
               month.add("FEB");
               month.add("MAR");
               month.add("APR");
               month.add("MAY");
               month.add("JUN");
               month.add("JUL");
               month.add("AUG");
               month.add("SEP");
               month.add("OCT");
               month.add("NOV");
               month.add("DEC");

               int date_day,date_month,date_year;
               String[] array = date.split("-");
               
               if(array[0].length()<2)
               {
            	   date_day=Integer.parseInt(0+array[0]);
               }
               else
               {
            	   date_day=Integer.parseInt(array[0]);
                }
               
               
               date_month=(month.indexOf(array[1]));
               date_year=Integer.parseInt(array[2]);
               
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               Date checkDate = sdf.parse(date_year+"-"+date_month+"-"+date_day);
        	
        	    Calendar cal = Calendar.getInstance();
        	    SimpleDateFormat curr = new SimpleDateFormat("yyyy-MM-dd");
        	    String dat = curr.format(cal.getTime());
        	    Date currDate = curr.parse(dat);
        	
        	if(checkDate.compareTo(currDate) > 0){
        		return false;
                }
        	
        }
        catch(Exception e)
        {
            System.out.println("Error in currentdatevalidation() :"+e.getMessage() );
        }
               
         return true;
        }
     
     //end: Added for US01
        
        
}
