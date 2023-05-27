package model;
import java.lang.Math;
public class IdGenerator{
	private int numOfHexadecimalGeneratedIds;
	private int numOfAlphanumericGeneratedIds;
	private final char[] HEXADECIMAL_CHARACTERS={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F'};
	private char[] ALPHANUMERIC_CHARACTERS={'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private String[] hexadecimalIds;
	private String[] alphanumericIds;
	public static final int LENGTH_OF_ID=3;
	/**
 	* Constructs a new IdGenerator object and initializes the number of generated IDs to zero.
 	*/
	IdGenerator(){
		numOfHexadecimalGeneratedIds=0;
		numOfAlphanumericGeneratedIds=0;
		hexadecimalIds=initPossibleIds(HEXADECIMAL_CHARACTERS);
		alphanumericIds=initPossibleIds(ALPHANUMERIC_CHARACTERS);


	}
	/**
 	* Initializes an array of possible IDs with the given character options.
 	*
 	* @param options the character options to use for generating IDs
 	* @return an array of possible IDs
 	*/
	private String[] initPossibleIds(char[] options){
		int numOfPossibleIds=(int)Math.pow(options.length,LENGTH_OF_ID);
		int currentPositionOfArrayOfPossibleIds=0;
		String[] possibleIds=new String[numOfPossibleIds];
		for (int i=0;i<options.length;i++ ) {
			for (int j=0;j<options.length ;j++ ) {
				for (int k=0;k<options.length ;k++ ) {
					String id=""+options[i]+options[j]+options[k];
					possibleIds[currentPositionOfArrayOfPossibleIds]=id;
					currentPositionOfArrayOfPossibleIds++;
				}
			}
		}
		return possibleIds;
	}
	/**
 	* Generates an alphanumeric ID.
 	*
 	* @return an alphanumeric ID
 	*/
	public String generateAlphanumericId(){
		String id="";
		if(numOfAlphanumericGeneratedIds<alphanumericIds.length){
			id=alphanumericIds[numOfAlphanumericGeneratedIds];
			numOfAlphanumericGeneratedIds++;
		}
		return id;
	}
	/**
 	* Generates a hexadecimal ID.
 	*
 	* @return a hexadecimal ID
 	*/
	public String generateHexadecimalId(){
		String id="";
		if(numOfHexadecimalGeneratedIds<hexadecimalIds.length){
			id=hexadecimalIds[numOfHexadecimalGeneratedIds];
			numOfHexadecimalGeneratedIds++;
		}
		return id;
	}
}