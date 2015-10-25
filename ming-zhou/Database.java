public class Database {
private String Name="";
private String Sex="";

private String bdate="";
private String FAMC="";
private String FAMS="";
private String FAMC1="";
private String FAMS1="";
private int noOfspouse;
public String getFAMC1() {
	return FAMC1;
}
public void setFAMC1(String fAMC1) {
	FAMC1 = fAMC1;
}
public String getFAMS1() {
	return FAMS1;
}
public void setFAMS1(String fAMS1) {
	FAMS1 = fAMS1;
}
private String ddate="";
private String email="";
public String getName() {
	return Name;
}
public void setName(String name) {
	//System.out.println("setting name"+name);
	Name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSex() {
	return Sex;
}
public void setSex(String sex) {
	Sex = sex;
}
public String getBdate() {
	return bdate;
}
public void setBdate(String bdate) {
	this.bdate = bdate;
}
public String getFAMC() {
	return FAMC;
}
public void setFAMC(String fAMC) {
	FAMC = fAMC;
}
public String getFAMS() {
	return FAMS;
}
public Database(String name, String sex, String bdate, String fAMC,
		String fAMS, String ddate,String email,String fAMC1,String fAMS1) {
	super();
	Name = name;
	Sex = sex;
	this.bdate = bdate;
	FAMC = fAMC;
	FAMS = fAMS;
	FAMC1 = fAMC1;
	FAMS1 = fAMS1;
	this.ddate = ddate;
	this.email=email;
	noOfspouse=0;
}
public int getNoOfspouse() {
	return noOfspouse;
}
public void setNoOfspouse(int noOfspouse) {
	this.noOfspouse = noOfspouse;
}
public void setFAMS(String fAMS) {
	FAMS = fAMS;
}
public String getDdate() {
	return ddate;
}
public void setDdate(String ddate) {
	this.ddate = ddate;
}

}
