package jaunty.servlet;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefTest {

	@Test
	public void testRef() {
		String[] message = new String[]{"unchanged"};
		
		change(message);
		
		assertEquals("changed", message[0]);
	}
	
	@Test
	public void testRefInt() {
		int i = 9;
		inc(i);
		assertEquals(10, i);
	}
	
	private void change(String[] message) {
		message[0] = "changed";
	}
	
	private void inc(int i) {
		i++;
	}
}
