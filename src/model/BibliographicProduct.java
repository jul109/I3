package model;
import java.util.GregorianCalendar;
public abstract class BibliographicProduct{
	private String id;
	private String name;
	private int numPages;
	private int readPages;
	private GregorianCalendar publicationDate;
	private String url;
	private double value;
	private int solds;
	BibliographicProduct(String name, int numPages,double value, GregorianCalendar publicationDate, String url){
		this.name=name;
		this.numPages=numPages;
		this.value=value;
		this.publicationDate=publicationDate;
		this.url=url;
		numPages=0;
		readPages=0;
		solds=0;
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


}	