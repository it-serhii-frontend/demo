package com.epam.rd.java.basic.practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {
	public static final Logger LOGGER = Logger.getLogger(Part2.class.getName());
	public static final String IN = "Interruped";

	public static void main(final String[] args) {
		InputStream is = new ByteArrayInputStream(System.lineSeparator().getBytes());

		System.setIn(is);

		Thread t = new Thread() {
			@Override
			public void run() {
				Spam.main(null);
			}
		};

		t.start();

		try {
			Thread.sleep(2000);
			t.join();
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARNING, IN, e);
			Thread.currentThread().interrupt();
		}
		System.setIn(System.in);
	}

}
