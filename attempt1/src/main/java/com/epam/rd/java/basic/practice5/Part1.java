package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {

	public static final Logger LOGGER = Logger.getLogger(Part1.class.getName());
	public static final String IN = "Interruped";

	public static void main(String[] args) {
		MyThread first = new MyThread();
		first.setName("First");
		Thread second = new Thread(new MyThreadRunnable());
		second.setName("Second");

		try {
			first.start();
			first.join(1000);
			Thread.sleep(1000);

			second.start();
			second.join(1000);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARNING, IN, e);
			Thread.currentThread().interrupt();
		}

	}

	static class MyThread extends Thread {

		@Override
		public void run() {

			try {
				for (int i = 0; i < 4; i++) {
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName());
				}
			} catch (InterruptedException e) {
				LOGGER.log(Level.WARNING, IN, e);
				Thread.currentThread().interrupt();
			}

		}
	}

	static class MyThreadRunnable implements Runnable {

		@Override
		public void run() {
			try {
				for (int i = 0; i < 4; i++) {
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName());
				}
			} catch (InterruptedException e) {
				LOGGER.log(Level.WARNING, IN, e);
				Thread.currentThread().interrupt();
			}

		}

	}

}
