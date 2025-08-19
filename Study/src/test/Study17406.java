package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Study17406 {

	private static int N, M, K, min;
	private static int[][] array, rotate;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		array = new int[N][M];
		rotate = new int[K][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rotate[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE; // 최솟값 저장할 변수
		permutation(new int[K], new boolean[K], 0);
		System.out.println(min);
	}

	private static int[][] rotateArray(int[][] array, int r, int c, int s) {
		int[][] afterRotation = new int[N][M];
		for (int i = 0; i < N; i++) {
			afterRotation[i] = array[i].clone();
		}

		// 회전하기 : 1행의 1 2 3 4 5 6 이 회전을 해야 한다면, 2~6까지 바로 앞의 수를 가져옴.
		// 그래서 각각 4 방향으로 읽어주고 껍질 하나씩 벗김.
		while (s > 0) {
			for (int j = 1; j <= s * 2; j++) {
				afterRotation[r - s][j + c - s] = array[r - s][j + c - s - 1];
			}
			for (int j = 1; j <= s * 2; j++) {
				afterRotation[r - s + j][c + s] = array[r - s + j - 1][c + s];
			}
			for (int j = 0; j < s * 2; j++) {
				afterRotation[r + s][c - s + j] = array[r + s][j + c - s + 1];
			}
			for (int j = 0; j < s * 2; j++) {
				afterRotation[r - s + j][c - s] = array[j + r - s + 1][c - s];
			}
			
			s--;
		}

		return afterRotation;
	}

	// 순열 조합 뽑는 함수
	private static void permutation(int[] out, boolean[] visited, int cnt) {
		if (cnt == K) {
			int[][] tempArray = array;
			
			// tempArray에 순서대로 회전한 배열 넣어줌.
			for(int i=0; i<K; i++) {
				tempArray = rotateArray(tempArray, rotate[out[i]][0]-1, rotate[out[i]][1]-1, rotate[out[i]][2]);
			}
			
			int sum; // 행 합계를 저장할 변수
			for (int i = 0; i < N; i++) {
				sum = 0;
				for (int j = 0; j < M; j++) {
					sum += tempArray[i][j];
				}
				if(sum<min) min = sum; // 최솟값 계산
			}
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				out[cnt] = i;
				permutation(out, visited, cnt + 1);
				visited[i] = false;
			}
		}
	}
}