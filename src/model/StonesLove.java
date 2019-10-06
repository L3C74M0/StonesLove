package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StonesLove {
	static int suma;
	static int[] output;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();
		String[] temp = line.split(" ");

		int amountDays = Integer.parseInt(temp[0]);
		int amountQueries = Integer.parseInt(temp[1]);

		line = br.readLine();
		String[] stones = line.split(" ");

		line = br.readLine();
		String[] consultas = line.split(" ");

		int[] array = new int[amountDays];

		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(stones[i]);
		}

		suma = 0;
		output = new int[amountDays];

		cuantosDiasTardo(array, amountQueries);

		for (int i = 0; i < amountQueries; i++) {
			System.out.println(searchDays(output, Integer.parseInt(consultas[i])));
		}
	}

	public static void cuantosDiasTardo(int[] array, int posLimit) {
		cuantosDiasTardo(array, 0, array.length - 1, posLimit);
	}

	private static void cuantosDiasTardo(int[] array, int i, int j, int posLimit) {
		int m = (i + j) / 2;

		if (i == j) {
			if (i <= posLimit) {
				suma += array[i];
				output[i] = suma;
			}
		} else {
			cuantosDiasTardo(array, i, m, posLimit);

			cuantosDiasTardo(array, m + 1, j, posLimit);
		}
	}

	public static int searchDays(int[] vector, int toSearch) {
		return searchDays(vector, 0, vector.length - 1, toSearch);
	}

	private static int searchDays(int[] vector, int i, int j, int toSearch) {
		if (i == j) {
			return i+1;
		} else {
			int izq;
			int der;
			int m = (i + j) / 2;
			
			if (vector[m] == toSearch) {
				return m + 1;
			} else {
				if (vector[m] < toSearch) {
					if ((m + 1) <= vector.length - 1) {
						if (toSearch < vector[m + 1]) {
							return m + 2;
						} else {
							return der = searchDays(vector, m + 1, j, toSearch);
						}
					} else {
						return m + 1;
					}
				} else {
					if ((m - 1) >= 0) {
						if (toSearch > vector[m - 1]) {
							return m + 1;
						} else {
							return izq = searchDays(vector, i, m, toSearch);
						}
					} else {
						return m + 1;
					}
				}
			}
		}
	}
}