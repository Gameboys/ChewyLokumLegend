package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("javadoc")
public class TestPlan {
	
	private static LokumMatrix testBoard1;
	private static LokumMatrix testBoard2;
	private static LokumMatrix testBoard3;
	private static LokumMatrix testBoard4;
	private static LokumMatrix testBoard5;
	private static LokumMatrix testBoard6;
	private static LokumMatrix testBoard7;
	
	public static void testAll(){
		testFill();
		testDrop();
		testDestroy();
		testAnalyze();
		testLegalSwap();
		testIllegalSwap();
		testCreateMatrix();
	}
	
	/**
	 * 
	 */
	public static void setup(){
		testBoard1 = createSimpleNoPatternBoard();
		testBoard1.setLokum(5, 0, null);
		testBoard1.setLokum(6, 0, null);
		
		testBoard2 = createSimpleNoPatternBoard();
		testBoard2.setLokum(3, 4, null);
		testBoard2.setLokum(4, 6, null);
		testBoard2.setLokum(4, 7, null);
		
		testBoard3 = createSimpleNoPatternBoard();
		
		testBoard4 = createSimpleNoPatternBoard();
		testBoard4.setLokum(5, 5, new NormalLokum(Constants.TYPE_HAZELNUT));
		testBoard4.setLokum(5, 6, new NormalLokum(Constants.TYPE_HAZELNUT));

		testBoard5 = createSimpleNoPatternBoard();
		testBoard5.setLokum(5, 6, new NormalLokum(Constants.TYPE_HAZELNUT));
		
		testBoard6 = createSimpleNoPatternBoard();
		testBoard7 = createSimpleNoPatternBoard();
		
	}
	
	/**
	 * @return
	 */
	public static LokumMatrix createSimpleNoPatternBoard(){
		LokumMatrix testBoard = new LokumMatrix(10,10);
		for(int i=0; i<testBoard.getWidth(); i++){
			for(int j=0; j<testBoard.getHeight(); j++){
				testBoard.setLokum(i, j, new NormalLokum((i+j)%4+1));
			}
		}
		return testBoard;
	}
	
	
	public static void testFill(){
		setup();
		System.out.println("Filling Blanks test: Before");
		System.out.println(testBoard1);
		System.out.println("repOK: "+testBoard1.repOK());
		System.out.println();
		System.out.println();
		System.out.println();
		testBoard1.fillInTheBlanks();
		System.out.println("After");
		System.out.println(testBoard1);
		System.out.println("repOK: "+testBoard1.repOK());
		System.out.println();
		System.out.println();
	}
	
	public static void testDrop(){
		setup();
		System.out.println("Dropping Lokums test: Before");
		System.out.println(testBoard2);
		System.out.println("repOK: "+testBoard2.repOK());
		System.out.println();
		System.out.println();
		testBoard2.dropLokums();
		System.out.println("After");
		System.out.println(testBoard2);
		System.out.println("repOK: "+testBoard2.repOK());
		System.out.println();
		System.out.println();
	}
	
	public static void testDestroy(){
		setup();
		int x1 = 3, x2 = 4, x3 = 5, x4 = 6, y1 = 5, y2 = 6, y3 = 7, y4 = 8;
		System.out.println("Destroy Lokums test: Before");
		System.out.println(testBoard3);
		System.out.println("repOK: "+testBoard3.repOK());
		System.out.println();
		System.out.println();
		System.out.println("Destroying ("+x1+","+y1+"), ("+x2+","+y2+"), ("+x3+","+y3+") and ("+x4+","+y4+")...");
		System.out.println();
		System.out.println();
		testBoard3.destroyLokum(x1, y1,1);
		testBoard3.destroyLokum(x2, y2,1);
		testBoard3.destroyLokum(x3, y3,1);
		testBoard3.destroyLokum(x4, y4,1);
		System.out.println("After");
		System.out.println(testBoard3);
		System.out.println("repOK: "+testBoard3.repOK());
		System.out.println();
		System.out.println();
	}
	
	public static void testAnalyze(){
		setup();
		System.out.println("Analyze Pattern test: Board:");
		System.out.println(testBoard4);
		System.out.println("repOK: "+testBoard4.repOK());
		System.out.println();
		System.out.println();
		System.out.println("Type of Pattern Formed at (5,5): " + testBoard4.typeOfLokumFormed(testBoard4.analyzePatterns(5, 5)));
		System.out.println("repOK: "+testBoard4.repOK());
		System.out.println();
		System.out.println();
	}
	
	/**
	 * 
	 */
	public static void testLegalSwap(){
		setup();
		System.out.println("Legal Swap test: Before");
		System.out.println(testBoard5);
		System.out.println("repOK: "+testBoard5.repOK());
		System.out.println();
		System.out.println();
		testBoard5.swapLokums(4, 5, 5, 5);
		System.out.println("After");
		System.out.println(testBoard5);
		System.out.println("repOK: "+testBoard5.repOK());
		System.out.println();
		System.out.println();
	}
	
	public static void testIllegalSwap(){
		setup();
		System.out.println("Illegal Swap test: Before");
		System.out.println(testBoard6);
		System.out.println("repOK: "+testBoard6.repOK());
		System.out.println();
		System.out.println();
		testBoard6.swapLokums(4, 5, 5, 5);
		System.out.println("After");
		System.out.println(testBoard6);
		System.out.println("repOK: "+testBoard6.repOK());
		System.out.println();
		System.out.println();
	}
	
	public static void testCreateMatrix(){
		setup();
		System.out.println("Create Random No-Pattern Matrix test: Before");
		System.out.println(testBoard7);
		System.out.println("repOK: "+testBoard7.repOK());
		System.out.println();
		System.out.println();
		testBoard7.createLokumMatrix(testBoard7.getWidth(), testBoard7.getHeight());
		System.out.println("After");
		System.out.println(testBoard7);
		System.out.println("repOK: "+testBoard7.repOK());
		System.out.println();
		System.out.println();
		System.out.println("NOTE: This method will be implemented into the constructor.");
	}

}
