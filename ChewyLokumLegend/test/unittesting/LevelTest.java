package unittesting;

import gameboys.chewylokumlegend.Level;
import gameboys.chewylokumlegend.LevelFactory;
import gameboys.chewylokumlegend.LokumMatrix;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class LevelTest {
	
	@Test(expected = NullPointerException.class)
	public void testNullLevel(){
		Level level = null;
		level.repOK();
	}
	
	@Test
	public void testZeroLevel(){
		Level level = LevelFactory.getLevel(0);
		level.setTargetScore(0);
		level.setNumSpecialSwaps(0);
		Assertions.assertFalse("level repOK is false", level.repOK());
	}
	
	@Test
	public void testNegativeLevel(){
		Level level = LevelFactory.getLevel(-1);
		level.setTargetScore(-1);
		level.setNumSpecialSwaps(-1);
		Assertions.assertFalse("level repOK is false", level.repOK());
	}
}
