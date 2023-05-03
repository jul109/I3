package model;
import java.util.GregorianCalendar;
public final class Book extends BibliographicProduct{
	private String review;
	private BookGenre genre;
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


}