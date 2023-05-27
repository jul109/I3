package model;
import java.util.GregorianCalendar;
public final class Book extends BibliographicProduct{
	private String review;
	private BookGenre genre;
	public static int NUM_PAGES_BEETWEEN_ADS=2;
	/**
 	* Constructs a new Book object with the specified details.
 	*
 	* @param id the unique identifier of the book
 	* @param name the name of the book
 	* @param numPages the number of pages in the book
 	* @param value the value of the book
 	* @param publicationDate the publication date of the book
 	* @param url the URL of the book
 	* @param genre the genre of the book
 	* @param review a review of the book
 	*/
	Book(String id,String name, int numPages,double value, GregorianCalendar publicationDate, String url, BookGenre genre,String review ){
		super(id,name,numPages, value, publicationDate, url);
		this.review=review;
		this.genre=genre;
	}
	/**
 	* Returns the review of the book.
 	*
 	* @return the review of the book
 	*/
	public String getReview() {
	    return review;
	}
	/**
 	* Returns the genre of the book.
 	*
 	* @return the genre of the book
 	*/

	public BookGenre getGenre() {
	    return genre;
	}
	/**
 	* Sets the review of this book to the specified value.
 	*
 	* @param review the new review for this book
 	*/

	public void setReview(String review) {
	    this.review = review;
	}
	/**
 	* Sets the genre of this book to the specified value.
 	*
 	* @param genre the new genre for this book
 	*/

	public void setGenre(BookGenre genre) {
	    this.genre = genre;
	}
	@Override
	/**
 	* Returns a string representation of this book.
 	*
 	* @return a string with the atributes of the book.
 	*/
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