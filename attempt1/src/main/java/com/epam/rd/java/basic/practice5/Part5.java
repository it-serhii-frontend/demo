package com.epam.rd.java.basic.practice5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {
	public static final Logger LOGGER = Logger.getLogger(Part5.class.getName());
	public static final String IN = "Interruped";
	private static RandomAccessFile file;

	public static void main(final String[] args) {
		Part5 part5 = new Part5();
		try {
			file = new RandomAccessFile("part5.txt", "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i <= 9; i++) {
			Writer wr = new Writer(file, i);
			wr.start();
		}
		part5.reader();
		try {
			file.close();
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, IN, e);
			Thread.currentThread().interrupt();
		}

	}

	public void reader() {
		try (RandomAccessFile data = new RandomAccessFile("part5.txt", "r")) {
			String line;
			while ((line = data.readLine()) != null) {
				System.out.println(line);
			}

			System.out.println();
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}

	public static String fullText(int integer) {
		StringBuilder result = new StringBuilder("");

		for (int i = 0; i < 20; i++) {
			result.append(integer);
		}

		result.append('\n');
		return result.toString();

	}

	private static class Writer extends Thread {
		RandomAccessFile file;
		int num;

		public Writer(RandomAccessFile file, int num) {
			this.file = file;
			this.num = num;
		}

		@Override
		public void run() {
			try {
				int retreat = this.num * 20 + num;
				file.seek(retreat);

				file.write(fullText(this.num).getBytes());
				file.close();

			} catch (IOException ex) {
				LOGGER.log(Level.WARNING, IN, ex);
				
			}
		}
	}

}
