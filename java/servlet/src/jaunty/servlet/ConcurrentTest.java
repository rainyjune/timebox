package jaunty.servlet;

import java.util.concurrent.Semaphore;

import org.junit.Test;

public class ConcurrentTest {

	// @Test
	public void runTest() {

		new Thread(new Worker(0), "A").start();
		new Thread(new Worker(1), "B").start();
		new Thread(new Worker(2), "C").start();
	}

	@Test
	public void runMyTest() {

		new Thread(new MyWorker(0), "A").start();
		new Thread(new MyWorker(1), "B").start();
		new Thread(new MyWorker(2), "C").start();
	}
}

class Worker implements Runnable {

	static int count = 0;
	static Object lock = new Object();

	int index;
	int pCount;

	public Worker(int index) {
		this.index = index;
	}

	public void run() {
		synchronized (lock) {
			for (;;) {
				if (count % 3 == index) {
					System.out.println(Thread.currentThread().getName());
					count++;
					pCount++;
					lock.notifyAll();
					if (pCount == 10)
						break;
				} else {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

class MyWorker implements Runnable {

	private static Semaphore[] semaphores = { new Semaphore(1),
			new Semaphore(0), new Semaphore(0) };

	private int index = 0;
	private int count = 0;

	public MyWorker(int index) {
		this.index = index;
	}

	public void run() {
		for (;;) {
			try {
				semaphores[index].acquire();
				System.out.println(Thread.currentThread().getName());
				count++;
				semaphores[(index + 1) % 3].release();
				if (count >= 10)
					break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new MyWorker(0), "A").start();
		new Thread(new MyWorker(1), "B").start();
		new Thread(new MyWorker(2), "C").start();
	}
}
