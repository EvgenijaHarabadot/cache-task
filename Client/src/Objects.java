
public class Objects implements Cloneable{
private int id;
private String date;
public Objects clone(){
	try {
	return (Objects) super.clone();
} catch (CloneNotSupportedException e) {

	e.printStackTrace();
}
	return null;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
