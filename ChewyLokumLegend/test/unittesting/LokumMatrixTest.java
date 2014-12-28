package unittesting;

import gameboys.chewylokumlegend.Constants;
import gameboys.chewylokumlegend.Lokum;
import gameboys.chewylokumlegend.LokumMatrix;
import gameboys.chewylokumlegend.NormalLokum;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("javadoc")
public class LokumMatrixTest {

	private static LokumMatrix testBoard1;
	private static LokumMatrix testBoard2;
	private static LokumMatrix testBoard3;
	private static LokumMatrix testBoard4;
	private static LokumMatrix testBoard5;
	private static LokumMatrix testBoard6;
	private static LokumMatrix testBoard7;

	@BeforeClass
	public static void setup(){
		testBoard1 = createSimpleNoPatternBoard();
		testBoard1.setLokum(5, 0, null);
		testBoard1.setLokum(6, 0, null);

		testBoard2 = createSimpleNoPatternBoard();

		testBoard3 = createSimpleNoPatternBoard();

		testBoard4 = createSimpleNoPatternBoard();
		testBoard4.setLokum(5, 5, new NormalLokum(Constants.TYPE_HAZELNUT));
		testBoard4.setLokum(5, 6, new NormalLokum(Constants.TYPE_HAZELNUT));

		testBoard5 = createSimpleNoPatternBoard();
		testBoard5.setLokum(5, 6, new NormalLokum(Constants.TYPE_HAZELNUT));

		testBoard6 = createSimpleNoPatternBoard();
		testBoard7 = createSimpleNoPatternBoard();

	}

	public static LokumMatrix createSimpleNoPatternBoard(){
		LokumMatrix testBoard = new LokumMatrix(10,10);
		for(int i=0; i<testBoard.getWidth(); i++){
			for(int j=0; j<testBoard.getHeight(); j++){
				testBoard.setLokum(i, j, new NormalLokum((i+j)%4+1));
			}
		}
		return testBoard;
	}

	@Test(expected = NullPointerException.class)
	public void testNullMatrix(){
		LokumMatrix matrix = null;
		matrix.repOK();
	}
	
	@Test(expected = NegativeArraySizeException.class)
	public void testNegativeMatrix(){
		LokumMatrix matrix = new LokumMatrix(-1,-1);
		matrix.repOK();
	}
	
	@Test
	public void testEmptyMatrix() {
		LokumMatrix matrix = new LokumMatrix(0,0);
		Assertions.assertFalse("matrix repOK is false", matrix.repOK());
	}
	
	@Test
	public void testFill(){
		testBoard1.fillInTheBlanks();
		Assertions.assertLokumMatrixFull("matrix is full", testBoard1);
	}

	@Test
	public void testDrop(){
		testBoard2.setLokum(3, 4, null);
		testBoard2.dropLokums();
		Assertions.assertLokumNotNull("Old location is not empty", testBoard2.getLokum(3, 4));
	}

	//CURRENTLY GIVES ERROR BECAUSE "DESTROY"S TALK TO THE UI TO AWARD POINTS
	@Test
	public void testDestroy(){
		int x1 = 3, x2 = 4, x3 = 5, x4 = 6, y1 = 5, y2 = 6, y3 = 7, y4 = 8;
		testBoard3.destroyLokum(x1, y1,1);
		testBoard3.destroyLokum(x2, y2,1);
		testBoard3.destroyLokum(x3, y3,1);
		testBoard3.destroyLokum(x4, y4,1);
		Assertions.assertTrue("matrix repOK is true", testBoard3.repOK());
	}

	@Test
	public void testAnalyze(){
		testBoard4.typeOfLokumFormed(testBoard4.analyzePatterns(5, 5));
		Assertions.assertTrue("matrix repOK is true", testBoard4.repOK());
	}

	//CURRENTLY GIVES ERROR BECAUSE "SWAP"S TALK TO THE UI TO PLACE IMAGES
	@Test
	public void testLegalSwap(){
		testBoard5.swapLokums(4, 5, 5, 5);
		Assertions.assertTrue("matrix repOK is true", testBoard5.repOK());
	}

	@Test
	public void testIllegalSwap(){
		Lokum lokum1 = (Lokum) testBoard6.getLokum(5, 5);
		testBoard6.swapLokums(4, 5, 5, 5);
		Lokum lokum2 = (Lokum) testBoard6.getLokum(5, 5);
		Assertions.assertEqualLokums("Lokums are not swapped",lokum1,lokum2);
	}

	@Test
	public void testCreateMatrix(){
		testBoard7.createLokumMatrix(testBoard7.getWidth(), testBoard7.getHeight());
		Assertions.assertTrue("matrix repOK is true", testBoard7.repOK());
	}

}
