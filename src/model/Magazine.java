package model;
import java.util.GregorianCalendar;
public final class Magazine extends BibliographicProduct{
	private String publicationFrequency;
	private MagazineCategory category;
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
	



}