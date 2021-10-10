package com.epam.rd.java.basic.practice5;

import java.util.Scanner;
import java.util.logging.Logger;

public class Spam {

	public static final Logger LOGGER = Logger.getLogger(Spam.class.getName());
	public static final String IN = "Interruped";
	private Thread[] threads;

	public Spam(final String[] messages, final int[] delays) {
		threads = new Thread[messages.length];
		for (int i = 0; i < messages.length; i++) {
			threads[i] = new Worker(messages[i], delays[i]);

		}
	}

	public static void main(final String[] args) {
		String[] messages = new String[] { "@@@", "bbbbbbb" };
		int[] delays = new int[] { 333, 222 };
		Spam spam = new Spam(messages, delays);
		spam.start();
		Scanner sc = new Scanner(System.in);
		String value = sc.nextLine();
		while (true) {
			if (value.isEmpty()) {
				spam.stop();
				break;
			}
		}
		sc.close();

	}

	public void start() {
		for (int q = 0; q < threads.length; q++) {
			threads[q].start();
		}
	}

	public void stop() {
		for (int w = 0; w < threads.length; w++) {
			threads[w].interrupt();
		}
	}

	private static class Worker extends Thread {
		private String message;
		private int delay;

		public Worker(String message, int delay) {
			this.message = message;
			this.delay = delay;
		}

		public void run() {
			while (true) {
				System.out.print(message);
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

}
