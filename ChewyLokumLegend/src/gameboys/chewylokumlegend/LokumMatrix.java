package gameboys.chewylokumlegend;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author Gameboys
 *
 */
public class LokumMatrix {

	private static final String COLORBOMB = Constants.COLORBOMB;
	private static final String WRAPPED = Constants.WRAPPED;
	private static final String STRIPED = Constants.STRIPED;
	private static final String NORMAL = Constants.NORMAL;
	private static final String ILLEGAL = Constants.ILLEGAL;

	private static final int TYPE_ROSE = Constants.TYPE_ROSE;
	private static final int TYPE_HAZELNUT = Constants.TYPE_HAZELNUT;
	private static final int TYPE_PISTACHIO = Constants.TYPE_PISTACHIO;
	private static final int TYPE_COCONUT = Constants.TYPE_COCONUT;

	private static int POINTS_NORMAL = Constants.POINTS_NORMAL;

	private int width;
	private int height;
	private BoardObject[][] lokumMatrix;

	/**
	 * Constructor which creates a (width x height) lokumMatrix
	 * 
	 * @param width the number of columns of the matrix
	 * @param height the number of rows of the matrix
	 */
	public LokumMatrix(int width, int height){
		setWidth(width);
		setHeight(height);
		lokumMatrix = new Lokum[height][width];
		createLokumMatrix(width,height);
	}

	/**
	 * Creates a randomly generated (width x height) game board
	 * which contains no lokum patterns (3+ lokums of same color
	 * in a row, horizontally or vertically).
	 *  
	 * @param width the number of columns of the matrix
	 * @param height the number of rows of the matrix
	 * @requires n/a
	 * @modifies the lokumMatrix
	 * @ensures there exist no patterns in the board
	 */
	public void createLokumMatrix(int width, int height){
		lokumMatrix = new Lokum[height][width];
		ArrayList<Integer> list = new ArrayList<Integer>();
		int countX = 0;
		int countY = 0;
		int type = 0;
		int xMin = 0;
		int yMin = 0;
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				list = new ArrayList<Integer>();
				list.add(1);
				list.add(2);
				list.add(3);
				list.add(4);
				Collections.shuffle(list);
				countX = 0;
				countY = 0;
				type = list.remove(0);
				xMin = Math.max(0,j-2);
				yMin = Math.max(0, i-2);
				for(int x=xMin; x<j; x++){
					if(type == getLokum(x,i).getType()){
						countX++;
					}
				}

				for(int y=yMin; y<i; y++){
					if(type == getLokum(j,y).getType())countY++;
				}

				if(countX==2){
					type = list.remove(0);
					if(i>1 && j>1){
						if(type == lokumMatrix[i-1][j].getType() && type == lokumMatrix[i-2][j].getType()){
							type = list.remove(0);
						}
					}
				}
				if(countY==2){
					type = list.remove(0);	
					if(i>1 && j>1){
						if(type == lokumMatrix[i][j-1].getType() && type == lokumMatrix[i][j-2].getType()){
							type = list.remove(0);
						}
					}
				}
				lokumMatrix[i][j] = new NormalLokum(type);
			}
		}
	}

	/**
	 * Swaps the lokums in (x1,y1) and (x2,y2). Checks for patterns afterwards
	 * for both swapped lokums and calls the destroyLokum method for the lokums
	 * that form a pattern. No swapping is performed for illegal moves.
	 * 
	 * @param x1 the x index of the first lokum in the lokumMatrix
	 * @param y1 the y index of the first lokum in the lokumMatrix
	 * @param x2 the x index of the second lokum in the lokumMatrix
	 * @param y2 the y index of the second lokum in the lokumMatrix
	 * @requires 2 lokums present at index coordinates (x1,y1) and (x2,y2)
	 * in the lokumMatrix. One of the two lokums is not a special lokum
	 * @modifies the lokumMatrix.
	 */
	public void swapLokums(int x1, int y1, int x2, int y2){
		BoardObject temp = getLokum(x1,y1);
		setLokum(x1,y1,getLokum(x2,y2));
		setLokum(x2,y2,temp);
		int[][] patterns1 = analyzePatterns(x1,y1);
		int[][] patterns2 = analyzePatterns(x2,y2);
		String patternAnalysis1 = typeOfLokumFormed(patterns1);
		String patternAnalysis2 = typeOfLokumFormed(patterns2);
		if(patternAnalysis1.equals(ILLEGAL)&&patternAnalysis2.equals(ILLEGAL)){
			temp = getLokum(x1,y1);
			setLokum(x1,y1,getLokum(x2,y2));
			setLokum(x2,y2,temp);
		}else{
			GameWindow.scoreBoard.makeMove();
			Main.cukcukSound.setFramePosition(0);
			Main.cukcukSound.loop(1);
			if(!patternAnalysis1.equals(ILLEGAL))destroyPattern(patterns1,x1,y1,1);
			if(!patternAnalysis2.equals(ILLEGAL))destroyPattern(patterns2,x2,y2,1);
			runActionTimer(1,1);
		}
	}

	/**
	 * @param x1 
	 * @param y1 
	 * @param x2 
	 * @param y2 
	 */
	public void swapSpecialLokums(int x1, int y1, int x2, int y2){
		BoardObject lokum1 = getLokum(x1,y1);
		BoardObject lokum2 = getLokum(x2,y2);
		if(lokum1 instanceof StripedLokum 
				&& lokum2 instanceof StripedLokum){
			destroyRow(y2,1);
			destroyColumn(x2,1);
		}else if((lokum1 instanceof StripedLokum && lokum2 instanceof WrappedLokum)
				||(lokum2 instanceof StripedLokum && lokum1 instanceof WrappedLokum)){
			for(int i=Math.max(0, x2-1); i<Math.min(width,x2+2); i++)destroyColumn(i,1);
			for(int j=Math.max(0, y2-1); j<Math.min(height,y2+2); j++)destroyRow(j,1);
		}else if(lokum1 instanceof WrappedLokum && lokum2 instanceof WrappedLokum){
			destroy5x5(x1,y1,1);
			destroy5x5(x2,y2,1);
			awardPoints(Constants.POINTS_WRAPPED_WRAPPED,1);
		}else if(lokum1 instanceof StripedLokum && lokum2 instanceof ColorBombLokum){
			convertAndDestroyAllOfType(lokum1.getType(),1);
		}else if(lokum2 instanceof StripedLokum && lokum1 instanceof ColorBombLokum){
			convertAndDestroyAllOfType(lokum2.getType(),1);
		}else if(lokum1 instanceof WrappedLokum && lokum2 instanceof ColorBombLokum){
			destroyAllOfType(lokum1.getType(),1);
			destroyAllOfType(randomTypeExcept(-1),lokum1.getType());
		}else if(lokum2 instanceof WrappedLokum && lokum1 instanceof ColorBombLokum){
			destroyAllOfType(lokum2.getType(),1);
			destroyAllOfType(randomTypeExcept(-1),lokum2.getType());
		}else if(lokum1 instanceof NormalLokum && lokum2 instanceof ColorBombLokum){
			setLokum(x2,y2,null);
			destroyAllOfType(lokum1.getType(),1);
		}else if(lokum2 instanceof NormalLokum && lokum1 instanceof ColorBombLokum){
			setLokum(x1,y1,null);
			destroyAllOfType(lokum2.getType(),1);
		}else{
			destroyBoard(1);
		}
		GameWindow.scoreBoard.makeMove();
		Main.cukcukSound.setFramePosition(0);
		Main.cukcukSound.loop(1);
		runActionTimer(1,1);

	}

	/**
	 * @param patterns 
	 * @param xFixed x index of the lokum in the middle
	 * @param yFixed y index of the lokum in the middle
	 * @param multiplier 
	 */
	private void destroyPattern(int[][] patterns, int xFixed, int yFixed,int multiplier){
		String patternAnalysis = typeOfLokumFormed(patterns);
		int type = getLokum(xFixed,yFixed).getType();
		for(int i=0; i<5; i++){
			int x = patterns[0][i];
			int y = patterns[1][i];
			if(x!=-1)destroyLokum(x,yFixed,multiplier);
			if(y!=-1)destroyLokum(xFixed,y,multiplier);
		}
		awardPoints(POINTS_NORMAL,multiplier);
		switch(patternAnalysis){
		case STRIPED:
			boolean direction = patterns[1][3]==-1;
			setLokum(xFixed,yFixed,new StripedLokum(type,direction));
			awardPoints(Constants.POINTS_STRIPED_FORMED - POINTS_NORMAL,multiplier);
			break;
		case WRAPPED:
			setLokum(xFixed,yFixed,new WrappedLokum(type));
			awardPoints(Constants.POINTS_WRAPPED_FORMED - POINTS_NORMAL,multiplier);
			break;
		case COLORBOMB:
			setLokum(xFixed,yFixed,new ColorBombLokum());
			awardPoints(Constants.POINTS_COLORBOMB_FORMED - POINTS_NORMAL,multiplier);
			break;
		}
	}

	/**
	 * Produces a pattern analysis according to the lokums surrounding (x,y).
	 * A pattern analysis is a 2x5 int matrix which contains index numbers.
	 * The first row of the pattern analysis contains the x coordinates of
	 * all lokums in column y that have the same color with the lokum at (x,y) in a row.
	 * Similarly, the second row of the pattern analysis contains the y coordinates
	 * of all lokums in row x that have the same color with the lokum at (x,y) in a row.
	 * 
	 * Remaining slots in the matrix are filled with a -1.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 * @return a 2x5 matrix which contains the pattern analysis
	 * @requires the indexes ([x-2 to x+2],y) and (x,[y-2 to y+2])
	 * contain lokums that are not null; unless the indexes are out
	 * of the board's bounds.
	 * @modifies n/a
	 */
	public int[][] analyzePatterns(int x, int y){
		int type = getLokum(x,y).getType();
		int[][] patterns = new int[][]{{-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1}};
		int count = 0;
		int xMin = Math.max(0, x-2);
		int xMax = Math.min(width-1, x+2);
		for(int i=xMin; i<=xMax; i++){
			if(getLokum(i,y)!=null && getLokum(i,y).getType()==type){
				if(i!=xMax || patterns[0][Math.max(0, count-1)]!=-1)patterns[0][count] = i;
				count++;
			}else if (count > 2)break;
			else{
				count = 0;
				patterns[0] = new int[]{-1,-1,-1,-1,-1};
			}
		}
		count = 0;
		int yMin = Math.max(0, y-2);
		int yMax = Math.min(height-1, y+2);
		for(int j=yMin; j<=yMax; j++){
			if(getLokum(x,j)!= null && getLokum(x,j).getType()==type){
				if(j!=yMax || patterns[1][Math.max(0, count-1)]!=-1)patterns[1][count] = j;
				count++;
			}else if (count > 2)break;
			else{
				count = 0;
				patterns[1] = new int[]{-1,-1,-1,-1,-1};
			}
		}
		return patterns;
	}

	/**
	 * This method takes in the pattern analysis object and returns the
	 * type of lokum that should be formed after the swap move.
	 * 
	 * @param patterns the 2x5 pattern analysis matrix
	 * @return a global String variable which represents the type of lokum
	 * formed by the created pattern
	 * @requires the pattern analysis object was successfully created
	 * @modifies n/a
	 */
	public String typeOfLokumFormed(int[][] patterns){
		int numSameColorInX = 0;
		int numSameColorInY = 0;
		for(int i=0; i<5; i++){
			if(patterns[0][i]!=-1)numSameColorInX++;
			if(patterns[1][i]!=-1)numSameColorInY++;
		}
		if(numSameColorInX == 5 || numSameColorInY == 5) return COLORBOMB;
		else if(numSameColorInX > 2 && numSameColorInY > 2) return WRAPPED;
		else if(numSameColorInX == 4 || numSameColorInY == 4) return STRIPED;
		else if(numSameColorInX == 3 || numSameColorInY == 3) return NORMAL;
		else return ILLEGAL;
	}

	/**
	 * This method takes in indexes for the lokum matrix and
	 * sets the lokum at the specified location to null.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 * @param multiplier 
	 * @requires a lokumMatrix is created, the point (x,y) is in bounds
	 * @modifies the lokumMatrix, the Lokum object at coordinates (x,y)
	 */
	public void destroyLokum(int x, int y,int multiplier){
		BoardObject bo = getLokum(x,y);
		if(bo instanceof StripedLokum){
			if(((StripedLokum)bo).getStripeDirection()){
				setLokum(x,y,null);
				explode(x,y);
				destroyColumn(x,multiplier);
			}else{
				setLokum(x,y,null);
				explode(x,y);
				destroyRow(y,multiplier);
			}
		}else if(bo instanceof WrappedLokum){
			setLokum(x,y,null);
			destroy3x3(x,y,multiplier);
			runActionTimerWrapped(x,y+1,1,multiplier);
		}else if(bo instanceof ColorBombLokum){
			setLokum(x,y,null);
			destroyAllOfType(randomTypeExcept(-1),multiplier);
		}else{
			setLokum(x,y,null);
			explode(x,y);
		}
	}

	/**
	 * @param x 
	 * @param y 
	 * 
	 */
	private void explode(int x, int y){
		final JLabel gif = new JLabel(Main.explodeImage,JLabel.CENTER);
		gif.setVerticalAlignment(JLabel.CENTER);
		gif.setBounds(x*Constants.LOKUM_SIZE,y*Constants.LOKUM_SIZE,Constants.LOKUM_SIZE,Constants.LOKUM_SIZE);
		System.out.println(gif.getLocation());
		GameWindow.gameBoard.add(gif);
		final Timer end = new Timer(10000,new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.gameBoard.remove(gif);
				((Timer)arg0.getSource()).stop();
			}
		});
		end.setInitialDelay(500);
		end.start();
	}

	/**
	 * @param points
	 * @param multiplier 
	 */
	private void awardPoints(int points, int multiplier){
		GameWindow.scoreBoard.increaseScore(points*multiplier);
	}

	/**
	 * @param row
	 * @param multiplier 
	 */
	public void destroyRow(int row,int multiplier){
		for(int i=0; i<width; i++){
			if(getLokum(i,row)!=null){
				awardPoints(POINTS_NORMAL,multiplier);
				destroyLokum(i,row,multiplier);
			}
		}
	}

	/**
	 * @param column
	 * @param multiplier 
	 */
	public void destroyColumn(int column,int multiplier){
		for(int j=0; j<height; j++){
			if(getLokum(column,j)!=null){
				awardPoints(POINTS_NORMAL,multiplier);
				destroyLokum(column,j,multiplier);
			}
		}
	}

	/**
	 * @param x
	 * @param y
	 * @param multiplier 
	 */
	public void destroy3x3(int x, int y, int multiplier){
		for(int i=Math.max(0, x-1); i<Math.min(width, x+2); i++){
			for(int j=Math.max(0, y-1); j<Math.min(height, y+2); j++){
				destroyLokum(i,j,multiplier);
			}
		}
		awardPoints(Constants.POINTS_WRAPPED_DESTROYED, multiplier);
	}

	/**
	 * @param x
	 * @param y
	 * @param multiplier 
	 */
	public void destroy5x5(int x, int y, int multiplier){
		for(int i=Math.max(0, x-2); i<Math.min(width, x+3); i++){
			for(int j=Math.max(0, y-2); j<Math.min(height, y+3); j++){
				destroyLokum(i,j,multiplier);
			}
		}
	}

	/**
	 * @param exceptType
	 * @return
	 */
	public int randomTypeExcept(int exceptType){
		ArrayList<Integer> types = new ArrayList<Integer>();
		types.add(Constants.TYPE_ROSE);
		types.add(Constants.TYPE_HAZELNUT);
		types.add(Constants.TYPE_PISTACHIO);
		types.add(Constants.TYPE_COCONUT);
		types.remove((Object)exceptType);
		Collections.shuffle(types);
		return types.get(0);
	}
	
	/**
	 * @param type
	 * @param multiplier 
	 */
	public void destroyAllOfType(int type,int multiplier){
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				if(getLokum(i,j)!=null && getLokum(i,j).getType()==type){
					// Candy Crush Saga awards 60 points per lokum destroyed,
					// so we implemented that one.
					awardPoints(Constants.POINTS_NORMAL,multiplier);
					destroyLokum(i,j,multiplier);
				}
			}
		}
	}

	/**
	 * @param type
	 * @param multiplier 
	 */
	public void convertAndDestroyAllOfType(int type, int multiplier){
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				if(getLokum(i,j)!= null && getLokum(i,j).getType()==type){
					boolean dir = new Random().nextBoolean();
					setLokum(i,j,new StripedLokum(type,dir));
					delayedDestroy(i,j,multiplier);
				}
			}
		}
	}

	/**
	 * @param multiplier 
	 * 
	 */
	public void destroyBoard(int multiplier){
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				awardPoints(Constants.POINTS_BOARDCLEAR_PER_LOKUM,multiplier);
				destroyLokum(i,j,multiplier);
			}
		}
	}

	/**
	 * This method checks for holes in the lokumMatrix (locations
	 * where the object is null) and drops the lokums above until
	 * all holes are filled. At the end, all holes must be at the top.
	 * 
	 * @requires a lokumMatrix is created
	 * @modifies the lokumMatrix
	 * @ensures any hole in the lokumMatrix is at the top
	 */
	public void dropLokums(){
		int yNext = 0;
		for(int i=height-1; i>=0; i--){
			for(int j=0; j<width; j++){
				yNext = Math.min(i+1,height-1);
				while(lokumMatrix[yNext][j]==null){
					lokumMatrix[yNext][j] = lokumMatrix[Math.max(yNext-1,0)][j];
					lokumMatrix[Math.max(yNext-1,0)][j] = null;
					if(yNext==height-1)break;
					yNext = Math.min(yNext+1,height-1);
				}
			}
		}
	}

	/**
	 * This method fills the holes at the top of the lokumMatrix
	 * by randomly assigned lokums. The lokums are random, meaning
	 * that they can also form patterns.
	 * 
	 * @requires a lokumMatrix is created.
	 * @modifies the lokumMatrix
	 * @ensures the lokumMatrix contains no holes
	 */
	public void fillInTheBlanks(){
		int type = 0;
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				if(lokumMatrix[i][j] == null){
					type = (new Random().nextInt(4)+1);
					lokumMatrix[i][j] = new NormalLokum(type);
				}
			}
		}
	}

	/**
	 * Scans the board for existing patterns AFTER a manual
	 * swap. Always call the method with argument 1; it is used
	 * to supply recursive calls.
	 * 
	 * @param multiplier 
	 */
	private void scanForPatterns(int multiplier){
		boolean patternFound = false;
		for(int j=0; j<height; j++){
			for(int i=0; i<width; i++){
				BoardObject bo = getLokum(i,j);
				if(bo!=null){
					int x = i, y = j;
					if(i+2<width && getLokum(i+1,j)!=null && bo.getType()==(getLokum(i+1,j).getType())){
						x++;
						if(getLokum(i+2,j)!=null && bo.getType()==(getLokum(i+2,j)).getType()){
							x++;
						}
					}
					int[][] patterns = analyzePatterns(x,y);
					String patternAnalysis = typeOfLokumFormed(patterns);
					if(!patternAnalysis.equals(ILLEGAL)){
						patternFound = true;
						destroyPattern(patterns,x,y,multiplier);
						setLokum(i,j,bo);
						x=i;
						if(j+2<height && getLokum(i,j+1)!=null && bo.getType()==(getLokum(i,j+1)).getType()){
							y++;
							if(getLokum(i,j+2)!=null && bo.getType()==(getLokum(i,j+2)).getType()){
								y++;
							}
						}
						patterns = analyzePatterns(x,y);
						patternAnalysis = typeOfLokumFormed(patterns);
						if(!patternAnalysis.equals(ILLEGAL)){
							patternFound = true;
							destroyPattern(patterns,x,y,multiplier);
						}else setLokum(i,j,null);
					}

				}
			}
		}
		if(patternFound){
			Main.cukcukSound.setFramePosition(0);
			Main.cukcukSound.loop(1);
			runActionTimer(multiplier+1,1);
		}else{
			if(multiplier>8){
				Main.headshotSound.setFramePosition(0);
				Main.headshotSound.loop(1);
			}else if(multiplier>6){
				Main.divineSound.setFramePosition(0);
				Main.divineSound.loop(1);
			}else if(multiplier>4){
				Main.deliciousSound.setFramePosition(0);
				Main.deliciousSound.loop(1);
			}else if(multiplier>2){
				if(new Random().nextBoolean()){
					Main.tastySound.setFramePosition(0);
					Main.tastySound.loop(1);
				}else{
					Main.sweetSound.setFramePosition(0);
					Main.sweetSound.loop(1);
				}
			}
			ScoreBoard sb = GameWindow.scoreBoard;
			if(sb.getCurrentScore()>=sb.getTargetScore()){
				GameWindow.gameBoard.youWin();
			}else if(sb.getMovesLeft()==0)GameWindow.gameBoard.gameOver();
		}
	}

	/**
	 * @param multiplier
	 * @param step 
	 */
	public void runActionTimer(final int multiplier,final int step){
		Timer timer = new Timer(100000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(step==1){
					dropLokums();
					((Timer)e.getSource()).stop();
					runActionTimer(multiplier,step+1);
				}else if(step==2){
					fillInTheBlanks();
					((Timer)e.getSource()).stop();
					runActionTimer(multiplier,step+1);
				}else{
					scanForPatterns(multiplier);
					((Timer)e.getSource()).stop();
				}
			}
		});
		timer.setInitialDelay(400);
		timer.start();
	}

	/**
	 * @param x 
	 * @param y 
	 * @param multiplier
	 * @param step 
	 */
	public void runActionTimerWrapped(final int x, final int y, final int multiplier,final int step){
		Timer timer = new Timer(100000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(step==1){
					dropLokums();
					((Timer)e.getSource()).stop();
					runActionTimerWrapped(x,y,multiplier,step+1);
				}else if(step==2){
					fillInTheBlanks();
					((Timer)e.getSource()).stop();
					runActionTimerWrapped(x,y,multiplier,step+1);
				}else if(step==3){
					destroy3x3(x,Math.min(height-1, y),multiplier);
					((Timer)e.getSource()).stop();
					runActionTimerWrapped(x,y,multiplier,step+1);
				}else if(step==4){
					dropLokums();
					((Timer)e.getSource()).stop();
					runActionTimerWrapped(x,y,multiplier,step+1);
				}else{
					fillInTheBlanks();
					((Timer)e.getSource()).stop();
				}
			}
		});
		timer.setInitialDelay(400);
		timer.start();
	}

	/**
	 * @param x
	 * @param y
	 * @param multiplier 
	 */
	public void delayedDestroy(final int x, final int y, final int multiplier){
		Timer timer = new Timer(100000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				destroyLokum(x,y,multiplier);
				((Timer)e.getSource()).stop();
			}
		});
		timer.setInitialDelay(400);
		timer.start();
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean isNormalColorBombPair(int x1, int y1, int x2, int y2){
		return (getLokum(x1,y1) instanceof NormalLokum && getLokum(x2,y2) instanceof ColorBombLokum)
				|| (getLokum(x2,y2) instanceof NormalLokum && getLokum(x1,y1) instanceof ColorBombLokum);
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the north.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapNorth(int x, int y){
		if(y>0){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x,y-1) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x,y-1))swapSpecialLokums(x,y,x,y-1);
			else swapLokums(x,y,x,y-1);
		}
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the northeast.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapNorthEast(int x, int y){
		if(x<width-1 && y>0){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x+1,y-1) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x+1,y-1))swapSpecialLokums(x,y,x+1,y-1);
			else swapLokums(x,y,x+1,y-1);
		}
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the east
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapEast(int x, int y){
		if(x<width-1){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x+1,y) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x+1,y))swapSpecialLokums(x,y,x+1,y);
			else swapLokums(x,y,x+1,y);
		}
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the southeast.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapSouthEast(int x, int y){
		if(x<width-1 && y<height-1){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x+1,y+1) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x+1,y+1))swapSpecialLokums(x,y,x+1,y+1);
			else swapLokums(x,y,x+1,y+1);
		}
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the south.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapSouth(int x, int y){
		if(y<height-1){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x,y+1) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x,y+1))swapSpecialLokums(x,y,x,y+1);
			else swapLokums(x,y,x,y+1);
		}
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the southwest.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapSouthWest(int x, int y){
		if(x>0 && y<height-1){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x-1,y+1) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x-1,y+1))swapSpecialLokums(x,y,x-1,y+1);
			else swapLokums(x,y,x-1,y+1);
		}
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the west.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapWest(int x, int y){
		if(x>0){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x-1,y) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x-1,y))swapSpecialLokums(x,y,x-1,y);
			else swapLokums(x,y,x-1,y);
		}
	}

	/**
	 * Swaps the lokum at the given index coordinates with
	 * the one to the northwest.
	 * 
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 */
	public void swapNorthWest(int x, int y){
		if(x>0 && y>0){
			if((getLokum(x,y) instanceof SpecialLokum
					&& getLokum(x-1,y-1) instanceof SpecialLokum)
					|| isNormalColorBombPair(x,y,x-1,y-1))swapSpecialLokums(x,y,x-1,y-1);
			else swapLokums(x,y,x-1,y-1);
		}
	}

	/**
	 * @return true if current state of GameBoard is OK
	 *		   false otherwise
	 */
	public boolean repOK(){
		// Inspects state, returns true if it is OK
		if(lokumMatrix == null)
			return false;
		else if(lokumMatrix.length != height)
			return false;
		else if(lokumMatrix[0].length != width)
			return false;
		else return true;
	}

	/**
	 * Paints the lokums in the lokumMatrix on the
	 * Graphics object g;
	 * @param g the Graphics object to be painted on
	 * @requires a lokumMatrix is created
	 * @modifies the Graphics object g.
	 */
	public void paint(Graphics g){
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				BoardObject bo = getLokum(i,j);
				if(bo!=null) bo.paint(g,(int)(5*Constants.SCALE)+i*Constants.LOKUM_SIZE,(int)(5*Constants.SCALE)+j*Constants.LOKUM_SIZE);
			}
		}
	}

	public String toString(){
		String output = "";
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				BoardObject bo = getLokum(j,i);
				if(bo!=null) output += " " + bo;
				else output += " " + 0;
			}
			output += "\n";
		}
		return output;
	}

	/**
	 * @param x the x index of the lokum in the lokumMatrix
	 * @param y the y index of the lokum in the lokumMatrix
	 * @return the Lokum object at (x,y)
	 * @requires a lokumMatrix is created, (x,y) is in bounds
	 * @modifies n/a
	 */
	public BoardObject getLokum(int x, int y){
		return lokumMatrix[y][x];
	}

	/**
	 * @param x the x index of the board object in the lokumMatrix
	 * @param y the y index of the board object in the lokumMatrix
	 * @param lokum the BoardObject object to place into (x,y)
	 * @requires a lokumMatrix is created, (x,y) is in bounds
	 * @modifies the lokumMatrix
	 * @ensures the object at (x,y) is equal to the argument "lokum"
	 */
	public void setLokum(int x, int y, BoardObject lokum){
		lokumMatrix[y][x] = lokum;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}


}
