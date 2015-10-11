public class individual {

	/* Create individual class foe corresponding records.
	 * 
	 */
	private String id;
	private String name;
	private String sex;
	private String birth;
	private String death;
	//Getters and setters for fields
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getDeath() {
		return death;
	}
	public void setDeath(String death) {
		this.death = death;
	}
	//Constructor
	public individual(String id) {
		this.id = id;
		this.name = null;
		this.sex = null;
		this.birth = null;
		this.death = null;
	}
	@Override
	public String toString() {
		return "individual [id=" + id + ", name=" + name + ", sex=" + sex + ", birth date=" + birth + ", death date=" + death
				+ "]";
	}
	
	
}
