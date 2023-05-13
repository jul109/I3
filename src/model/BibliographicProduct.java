package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public abstract class BibliographicProduct{
	protected String id;
	protected String name;
	protected int numPages;
	protected int readPages;
	protected GregorianCalendar publicationDate;
	protected String url;
	protected double value;
	protected int solds;
	private ArrayList<User> users;
	BibliographicProduct(String id,String name, int numPages,double value, GregorianCalendar publicationDate, String url){
		this.name=name;
		this.numPages=numPages;
		this.value=value;
		this.publicationDate=publicationDate;
		this.url=url;
		numPages=0;
		readPages=0;
		solds=0;
		this.id=id;
	}
	public String getId() {
	    return id;
	}

	public String getName() {
	    return name;
	}

	public int getNumPages() {
	    return numPages;
	}

	public int getReadPages() {
	    return readPages;
	}

	public GregorianCalendar getPublicationDate() {
	    return publicationDate;
	}

	public String getUrl() {
	    return url;
	}

	public double getValue() {
	    return value;
	}

	public int getSolds() {
	    return solds;
	}

	public void setName(String name) {
	    this.name = name;
	}
	public void setNumPages(int numPages) {
	    this.numPages = numPages;
	}

	public void setReadPages(int readPages) {
	    this.readPages = readPages;
	}

	public void setPublicationDate(GregorianCalendar publicationDate) {
	    this.publicationDate = publicationDate;
	}

	public void setUrl(String url) {
	    this.url = url;
	}

	public void setValue(double value) {
	    this.value = value;
	}

	public void setSolds(int solds) {
	    this.solds = solds;
	}

	public void readNewPage(){
		readPages++;
	}
	public void setId(String id){
		this.id=id;
	}

	/**
	 * Take a an object of the class GregorianCalendar and return an string that represents it
 	* @param  calendar instance of the class GregorianCalendar
	* @return An string of the calendar
 	*/

	public static String calendarToString(GregorianCalendar calendar) {
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	String dateInTxtx = format.format(calendar.getTime());
    	return dateInTxtx;
	}

	public abstract String getInfo();




}	