package org.webhop.ywdc;

public class Rounder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Double doubled = 48.650000000000006;
		double doubled100 = doubled * 100;
		int intdoubled = (int) doubled100;
		Double roundeddown = (double) (intdoubled);
		roundeddown = roundeddown/100;
		System.out.println(roundeddown.toString());
		

	}

}
