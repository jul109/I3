package model;
import java.util.GregorianCalendar;
public final class Magazine extends BibliographicProduct{
	private String publicationFrequency;
	private MagazineCategory category;
	public static int NUM_PAGES_BEETWEEN_ADS=5;
	/**
	* Constructs a new Magazine object with the specified details.
 	*
 	* @param id the unique identifier of the magazine
 	* @param name the name of the magazine
 	* @param numPages the number of pages in the magazine
 	* @param value the value of the magazine
 	* @param publicationDate the date of publication of the magazine
 	* @param url the URL of the magazine's website
 	* @param category the category of the magazine
 	* @param publicationFrequency the frequency of publication of the magazine
 	*/

	Magazine(String id,String name, int numPages,double value, GregorianCalendar publicationDate, String url,MagazineCategory category, String publicationFrequency ){
		super(id,name,numPages, value, publicationDate, url);
		this.publicationFrequency=publicationFrequency;
		this.category=category;
	}
	public String getPublicationFrequency() {
    	return publicationFrequency;
	}
	public MagazineCategory getCategory() {
	    return category;
	}

	public void setPublicationFrequency(String publicationFrequency) {
	    this.publicationFrequency = publicationFrequency;
	}

	public void setCategory(MagazineCategory category) {
	    this.category = category;
	}
	public void cancelSuscription(){
		this.solds--;
	}
    @Override
	public String toString() {
	    String info = "";
	    info += "ID: " + super.id + "\n";
	    info += "Name: " + super.name + "\n";
	    info += "Number of Pages: " + super.numPages + "\n";
	    info += "Value: " + super.value + "\n";
	    info += "Publication Date: " + super.calendarToString(super.publicationDate) + "\n";
	    info += "URL: " + super.url + "\n";
	    info += "Category: " + category.name() + "\n";
	    info += "Publication Frequency: " + publicationFrequency+"\n";
	    info += "read pages: "+ super.readPages; 
	    return info;
	}

	



}