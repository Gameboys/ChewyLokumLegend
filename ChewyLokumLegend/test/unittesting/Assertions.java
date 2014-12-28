package unittesting;

import gameboys.chewylokumlegend.BoardObject;
import gameboys.chewylokumlegend.Lokum;
import gameboys.chewylokumlegend.LokumMatrix;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("javadoc")
public class Assertions {

	public static void assertTrue(String message, boolean condition){
		org.junit.Assert.assertTrue(message,condition);
	}
	
	public static void assertFalse(String message, boolean condition){
		assertTrue(message,!condition);
	}
	
	public static void assertEqualLokums(String message, Lokum lokum1, Lokum lokum2){
		assertTrue(message, lokum1.getType()==lokum2.getType());
	}
	
	public static void assertLokumNotNull(String message, BoardObject lokum){
		assertTrue(message, lokum!=null);
	}
	
	public static void assertLokumMatrixFull(String message, LokumMatrix matrix){
		boolean full = true;
		for(int i=0; i<matrix.getWidth();i++){
			for(int j=0; j<matrix.getHeight(); j++){
				if(matrix.getLokum(i, j)==null)full=false;
			}
		}
		assertTrue(message,full);
	}
	
	
}
