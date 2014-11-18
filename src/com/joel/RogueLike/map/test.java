package com.joel.RogueLike.map;

public class test {

	public static void main(String[] args) {
		int[] a = new int[] { 0, 1, 2, 3 };
		printArray(a);
		
		int[] b = a.clone();
		printArray(b);
		
		b[2] = 200;
		
		System.out.println("-------------------");
		
		printArray(a);
		printArray(b);
		
	}
	
	public static void printArray(int[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.printf("%d , ", a[i]);
		}
		System.out.println("\n");
	}

}
