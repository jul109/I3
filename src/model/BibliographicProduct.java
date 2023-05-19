package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
public abstract class BibliographicProduct implements Comparable<BibliographicProduct>{
	protected String id;
	protected String name;
	protected int numPages;
	protected int readPages;
	protected GregorianCalendar publicationDate;
	protected String url;
	protected double value;
	protected int solds;
	//private ArrayList<User> users;
	private double totalValuePaid;
	/**
 	* It is used to create Books and Magazines
 	*
 	* @param id              the ID of the bibliographic product
 	* @param name            the name of the bibliographic product
 	* @param numPages        the number of pages in the bibliographic product
 	* @param value           the value of the bibliographic product
 	* @param publicationDate the publication date of the bibliographic product
 	* @param url             the URL of the bibliographic product
 	*/
	BibliographicProduct(String id,String name, int numPages,double value, GregorianCalendar publicationDate, String url){
		this.name=name;
		this.numPages=numPages;
		this.value=value;
		this.publicationDate=publicationDate;
		this.url=url;
		numPages=0;
		readPages=0;
		solds=0;
		totalValuePaid=0;
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
	public double getTotalValuePaid(){
		return totalValuePaid;
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
	/**
 	* Increments in one the number of pages read by users
 	*/

	public void readNewPage(){
		readPages++;
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

	//public abstract String getInfo();
	@Override
	public int compareTo(BibliographicProduct product){
		int ans;
		ans = product.getReadPages()-this.readPages;
		return ans;
	}

	public void newSold(){
		totalValuePaid+=this.value;
		solds++;
	}





}	