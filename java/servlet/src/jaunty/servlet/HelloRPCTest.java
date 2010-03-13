package jaunty.servlet;

import org.junit.Test;

public class HelloRPCTest implements HelloRPC {

	public String sayHello(int[] message) {
		
		String returnMessage = "return value 0";
		
		message = new int[]{1, 2};

		// message[0] = 4;
		// message[1] = 5;		
		
		return returnMessage;
	}

	
	public void change(Temp t) {
		t.i = 6;
		t = new Temp(12); 
	}
	
	@Test
	public void testSayHello() {
		int[] i = {1, 2};

		sayHello(i);
		
		System.out.println(i[0] + " " + i[1]);
	
		Temp t = new Temp(1);
		change(t);
		System.out.println(t.i);
	}
}


class Temp {
	
	public int i = 4;
	
	Temp(int i) {
		this.i = i;
	}
	
}