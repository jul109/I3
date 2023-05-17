package model;
import java.util.GregorianCalendar;
public final class Book extends BibliographicProduct{
	private String review;
	private BookGenre genre;
	public static int NUM_PAGES_BEETWEEN_ADS=2;
	Book(String id,String name, int numPages,double value, GregorianCalendar publicationDate, String url, BookGenre genre,String review ){
		super(id,name,numPages, value, publicationDate, url);
		this.review=review;
		this.genre=genre;
	}
	public String getReview() {
	    return review;
	}

	public BookGenre getGenre() {
	    return genre;
	}

	public void setReview(String review) {
	    this.review = review;
	}

	public void setGenre(BookGenre genre) {
	    this.genre = genre;
	}
	@Override
	public String toString() {
    String info = "";
    info += "ID: " + super.id + "\n";
    info += "Name: " + super.name + "\n";
    info += "Number of Pages: " + super.numPages + "\n";
    info += "Value: $" + super.value + "\n";
    info += "Publication Date: " + super.calendarToString(super.publicationDate) + "\n";
    info += "URL: " + super.url + "\n";
    info += "Genre: " + genre.name() + "\n";
    info += "Review: " + review+"\n";
    info += "read pages: "+ super.readPages; 
    return info;
}



}