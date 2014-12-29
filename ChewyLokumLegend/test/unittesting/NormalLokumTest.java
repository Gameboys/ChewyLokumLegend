package unittesting;

import gameboys.chewylokumlegend.NormalLokum;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class NormalLokumTest {
	
	//Following test is for Lokum (abstract) classes repOK condition
	@Test(expected = NullPointerException.class)
	public void testNullLokum(){
		NormalLokum normalLokum = null;
		normalLokum.repOK();
	}
	
	//Following two tests are for NormalLokum classes repOK conditions
	@Test
	public void testOverTypeNumber(){
		NormalLokum normalLokum = new NormalLokum(9);
		Assertions.assertFalse("normalLokum repOK is false", normalLokum.repOK());
	}
	
	@Test
	public void testUnderTypeNumber(){
		NormalLokum normalLokum = new NormalLokum(-1);
		Assertions.assertFalse("normalLokum repOK is false", normalLokum.repOK());
	}
}
