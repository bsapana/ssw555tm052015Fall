import java.util.*;

public class family {

	private String id;
	private String husb;
	private String marriage;
	private String divorce;
	
	private String wife;
	private ArrayList<String> child;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHusb() {
		return husb;
	}
	public void setHusb(String husb) {
		this.husb = husb;
	}
	public String getWife() {
		return wife;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getDivorce() {
		return divorce;
	}
	public void setDivorce(String divorce) {
		this.divorce = divorce;
	}
	public void setWife(String wife) {
		this.wife = wife;
	}
	public ArrayList<String> getChild() {
		return child;
	}
	public void setChild(ArrayList<String> child) {
		this.child = child;
	}
	//constructor
	public family(String id) {
		this.id = id;
		this.husb = null;
		this.wife = null;
		this.marriage = null;
		this.divorce = null;
		this.child = null;
	}
	
	public family(String id, String husb, String marriage, String divorce, String wife, ArrayList<String> child) {
		super();
		this.id = id;
		this.husb = husb;
		this.marriage = marriage;
		this.divorce = divorce;
		this.wife = wife;
		this.child = child;
	}
	
	@Override
	public String toString() {
		return "family [id=" + id + ", husb=" + husb + ", wife=" + wife + ", child=" + child + 
				",marriage =" + marriage+ ",divorce =" +divorce +"]";
	}
	

	
}
