package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Study14510 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input14510.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int N, max, sum, day;
		int[] trees, needs;
		StringTokenizer st;
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());

			trees = new int[N];
			needs = new int[N];

			max = 0;
			sum = 0;
			day = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, trees[i]); // 최대 높이 나무 길이 저장
			}

			for (int i = 0; i < N; i++) {
				needs[i] = max - trees[i]; // 각 나무마다 필요한 높이 만큼 저장
				sum += needs[i]; // 필요한 높이의 총 합계
			}

			while (sum > 0) {
				day++;
				if (day % 2 == 0) { // 나무 키가 2씩 자라는 날 
					for (int i = 0; i < N; i++) {
						if (needs[i] >= 2) { //2보다 큰 거 있으면 2씩 빼줌
							needs[i] -= 2;
							sum -= 2;
							break;
						}
					}
				} else { // 나무 키가 1씩 자라는 날
					int temp = 0; // 홀수인 숫자가 아무것도 없는지 확인하는 변수
					for (int i = 0; i < N; i++) {
						if (needs[i] > 0) { 
							if (needs[i] % 2 != 0) { // 일단 홀수인 것들부터 빼줌
								needs[i]--;
								sum--;
								temp = -1; // 이 if문에서 걸리면 temp는 -1이 됨
								break;
							}
							temp = i; // temp에 needs[i] 중 0보다 큰 값을 가진 요소의 i 저장
						}
					}
					if (temp != -1 && sum >= 4) { // 홀수인 나무가 하나도 없다면 sum이 4보다 클 때 0보다 큰 값 아무거나 하나 물 줌.
						needs[temp]--;				// 4보다 크면 만약 2 2 4 일 때, 3 2 4 -> 3 4 4 -> 4 4 4 로 3일 만에 다 키울 수 있음
						sum--;
					}

				}
			}
			System.out.println("#" + test_case + " " + day);
		}
	}
}
