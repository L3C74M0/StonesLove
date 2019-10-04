package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StonesLove {
	static Node iq;

	public static void main(String[] args) throws IOException {

		// Pos 0 = (dia = 1)
		// pos 1 = sumatoria
		iq = new Node(0, 0);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();
		String[] temp = line.split(" ");

		int amountDays = Integer.parseInt(temp[0]);
		int amountQueries = Integer.parseInt(temp[1]);

		line = br.readLine();
		String[] stones = line.split(" ");

		line = br.readLine();
		String[] consultas = line.split(" ");

		Node[] array = new Node[amountDays];

		for (int i = 0; i < array.length; i++) {
			array[i] = new Node(1, Integer.parseInt(stones[i]));
		}

		for (int j = 0; j < amountQueries; j++) {
			System.out.println(cuantosDiasTardo(array, Integer.parseInt(consultas[j])).day);
		}
	}

	public static Node cuantosDiasTardo(Node[] array, int toSearch) {
		return cuantosDiasTardo(array, 0, array.length - 1, toSearch);
	}

	private static Node cuantosDiasTardo(Node[] array, int i, int j, int toSearch) {
		Node izq;
		Node der;
		int m = (i + j) / 2;

		if (i == j) {
			return array[i];
		} else {
			izq = cuantosDiasTardo(array, i, m, toSearch);

			if (izq.suma >= toSearch) {
				Node temp = new Node(izq.day, izq.suma);
				return temp;
			}

			der = cuantosDiasTardo(array, m + 1, j, toSearch);

			if (izq.suma + der.suma >= toSearch) {
				Node temp = new Node(izq.day + der.day, izq.suma + der.suma);
				return temp;
			}

			Node temp = new Node(izq.day + der.day, izq.suma + der.suma);
			iq = temp;
			return temp;
		}
	}
}

class Node {
	public int day;
	public int suma;

	public Node(int day, int suma) {
		this.day = day;
		this.suma = suma;
	}
}