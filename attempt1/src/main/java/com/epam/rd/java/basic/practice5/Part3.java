package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {
	private static final Logger LOGGER = Logger.getLogger(Part3.class.getName());
	public static final String IN = "Interruped";
	private static int counter = 0;
	private static int counter2 = 0;
	private static int numberOfIterations = 2;
	private static int numberOfThreads = 2;
	private static String separator = System.lineSeparator();
	private static Thread[] threads = new Thread[numberOfThreads];

	public Part3(int numberOfThreads, int numberOfIterations) {
		Part3.numberOfThreads = numberOfThreads;
		Part3.numberOfIterations = numberOfIterations;
	}

	public static void main(final String[] args) {
		Part3.compare();
		Part3.compareSync();
	}

	static class ThreadOne extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < numberOfIterations; i++) {
				System.out.print(counter + " " + counter2 + separator);
				try {
					counter++;
					Thread.sleep(100);
					counter2++;
				} catch (InterruptedException e) {
					LOGGER.log(Level.WARNING, IN, e);
					Thread.currentThread().interrupt();
				}
			}

		}
	}

	public static void compare() {

		for (int q = 0; q < threads.length; q++) {
			threads[q] = new ThreadOne();
			threads[q].start();
		}
		for (int q = 0; q < threads.length; q++) {
			try {
				threads[q].join();
			} catch (InterruptedException e) {
				LOGGER.log(Level.WARNING, IN, e);
				Thread.currentThread().interrupt();
			}
		}

	}

	public static synchronized void compareSync() {
		for (int q = 0; q < threads.length; q++) {
			threads[q] = new ThreadOne();
			threads[q].start();
		}

	}

}
