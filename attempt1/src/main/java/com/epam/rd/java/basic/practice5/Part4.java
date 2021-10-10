package com.epam.rd.java.basic.practice5;

import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part4 {
	public static final Logger LOGGER = Logger.getLogger(Part4.class.getName());
	public static final String IN = "Interruped";
	private static int max;
	private static int max2;
	private static final String FILE = "part4.txt";
	private static int M = 5;
	private static int N = 20;
	private static int[][] arr = new int[M][N];

	public static void main(final String[] args) {
		read();
		MultiThread mt = new MultiThread();
		mt.run();
		SingleThread st = new SingleThread();
		st.run();

	}

	public static void read() {
		Scanner sc = null;
		try (FileReader fr = new FileReader(FILE)) {
			sc = new Scanner(fr);

			for (int i = 0; i < M; i++) {
				for (int q = 0; q < N; q++) {
					if (sc.hasNext()) {
						arr[i][q] = sc.nextInt();
					}
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, IN, e);
			Thread.currentThread().interrupt();
			sc.close();
		}

	}

	public static int maxVal(int[] arr) {
		max = arr[0];
		for (int w = 1; w < arr.length; w++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ex) {
				LOGGER.log(Level.WARNING, IN, ex);
				Thread.currentThread().interrupt();

			}
			if (arr[w] > max) {
				max = arr[w];
			}

		}
		return max;
	}

	static class MultiThread extends Thread {
		long time;

		@Override
		public void run() {
			final int[] item = new int[M];

			for (int t = 0; t < M; t++) {
				final int val = t;
				Thread thread = new Thread() {
					@Override
					public void run() {
						time = System.currentTimeMillis();
						item[val] = maxVal(arr[val]);
						time = System.currentTimeMillis() - time;
					}
				};
				thread.start();
			}

			try {
				Thread.sleep(300);
			} catch (InterruptedException ei) {
				LOGGER.log(Level.WARNING, IN, ei);
				Thread.currentThread().interrupt();
			}
			Arrays.sort(item);
			System.out.println(item[M - 1]);
			System.out.println(time);
		}

	}

	static class SingleThread extends Thread {
		long time;

		@Override
		public void run() {
			time = System.currentTimeMillis();
			max2 = 0;
			for (int e = 0; e < M; e++) {
				for (int r = 0; r < arr[e].length; r++) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException eu) {
						LOGGER.log(Level.WARNING, IN, eu);
						Thread.currentThread().interrupt();
					}
					if (arr[e][r] > max2) {
						max2 = arr[e][r];
					}
				}
			}
			time = System.currentTimeMillis() - time;
			System.out.println(max2);
			System.out.println(time);
		}
	}
}
