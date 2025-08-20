package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Study1904 { 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 피보나치 수열 문제
		int N = Integer.parseInt(br.readLine());
		long[] array = new long[1000001];

		array[1] = 1;
		array[2] = 2;

		for (int i = 3; i <= N; i++) {
			array[i] = (array[i - 2] + array[i - 1])% 15746;
			// 여기서 안 나눠주면 범위가 너무 커져서 값이 이상하게 나옴...
		}
		System.out.println(array[N]);
	}
}
