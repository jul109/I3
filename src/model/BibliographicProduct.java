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
	/**
 	* This method returns the id of the bibliographic product
 	*
 	* @return The id of the product.
 	*/
	public String getId() {
	    return id;
	}
	/**
 	* Returns the name of the product.

 	* @return the name of this product
 	*/

	public String getName() {
	    return name;
	}
	/**
 	* Returns the number of pages of the product.

 	* @return the number of pages
 	*/

	public int getNumPages() {
	    return numPages;
	}
	
	/**
 	* Returns the number of read pages of the product.
 	* @return the number of read pages
 	*/

	public int getReadPages() {
	    return readPages;
	}
	/**
 	* Returns the publication date of the product
 	*
 	* @return the publication date of the product
 	*/

	public GregorianCalendar getPublicationDate() {
	    return publicationDate;
	}
	/**
 	* Returns the URL of this object.
 	*
 	* @return the URL of this object
 	*/

	public String getUrl() {
	    return url;
	}
	/**
 	* Returns the value of the book or the value of the magazine's suscription
 	*
 	* @return the value of the object
 	*/

	public double getValue() {
	    return value;
	}
	/**
 	* Returns the number of solds of the product
 	*
 	* @return the number of solds of the product
 	*/

	public int getSolds() {
	    return solds;
	}
	/**
 	* Returns the total value paid for this product.
 	*
 	* @return the total value paid for the product
 	*/
	public double getTotalValuePaid(){
		return totalValuePaid;
	}
	/**
 	* Sets the name of this Product object.
 	*
 	* @param name the new name for this Product object
 	*/
	public void setName(String name) {
	    this.name = name;
	}
	/**
 	* Sets the number of pages for the product.
 	*
 	* @param numPages the new number of pages for the product
 	*/
	public void setNumPages(int numPages) {
	    this.numPages = numPages;
	}
	/**
 	* Sets the number of pages read for this object.
 	*
 	* @param readPages the new number of pages read for this object
 	*/

	public void setReadPages(int readPages) {
	    this.readPages = readPages;
	}
	/**
 	* Sets the publication date for the product
 	*
 	* @param publicationDate the new publication date for the product
 	*/

	public void setPublicationDate(GregorianCalendar publicationDate) {
	    this.publicationDate = publicationDate;
	}
	/**
 	* Sets the URL for this object.
 	*
 	* @param url the new URL for this object
 	*/
	public void setUrl(String url) {
	    this.url = url;
	}
	/**
 	* Sets the value of the product
 	*
 	* @param value the new value for the product
 	*/

	public void setValue(double value) {
	    this.value = value;
	}
	/**
 	* Sets the number of solds for this object.
 	*
 	* @param solds the new number of solds for this object
 	*/

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
	/**
 	* Compares this BibliographicProduct object with the specified BibliographicProduct object for order.
 	* Returns a negative integer, zero, or a positive integer as this object has fewer, the same,
 	* or more read pages than the specified object.
 	*
 	* @param product the BibliographicProduct object to be compared
 	* @return a negative integer, zero, or a positive integer as this object has fewer,
 	*         the same, or more read pages than the specified object
 	*/
	public int compareTo(BibliographicProduct product){
		int ans;
		ans = product.getReadPages()-this.readPages;
		return ans;
	}
	/**
 	* Increments the number of solds for this object and updates the total value paid.
 	*/

	public void newSold(){
		totalValuePaid+=this.value;
		solds++;
	}





}	