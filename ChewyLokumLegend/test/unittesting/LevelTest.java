package unittesting;

import gameboys.chewylokumlegend.Level;
import gameboys.chewylokumlegend.LokumMatrix;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class LevelTest {
	
	@Test(expected = NullPointerException.class)
	public void testNullLevel(){
		Level level = null;
		level.repOK();
	}
	
}
