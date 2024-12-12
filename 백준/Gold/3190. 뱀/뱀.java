import java.util.*;
import java.io.*;

class Main
{
	static class Point{
		int y;
		int x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}

	static class Order{
		int time;
		int delta;
		
		Order(int t, int d){
			time = t;
			delta = d;
		}
	}

	static class Snake{
		Point tail;
		Point head;
		int length;

		Snake(Point a, Point b, int c){
			tail = a;
			head = b;
			length = c;
		}
	} 


	static int N;
	static int[][] map;
	static int K;
	static int L;
	static Queue<Order> command = new LinkedList<>();

	static Queue<Point> tails = new LinkedList<>();

	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N+1][N+1];

		K = sc.nextInt();
		for (int i = 0; i < K; ++i){
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
		}

		L = sc.nextInt();

		for (int i = 0; i < L; ++i){ 
			int t = sc.nextInt();
			int delta = sc.next().charAt(0) == 'D' ? +1 : -1;
			command.add(new Order(t, delta));
		}

		//입력 끝
		Snake curr = new Snake(new Point(1, 1), new Point(1, 1), 1); 
		int time = 0;
		int dIndex = 0;
		map[1][1] = 2;
		tails.add(new Point(1,1));

		// printMap();

		while(true){
			//머리 이동
			// System.out.println("time : " + time + " , dIndex : " + dIndex );

			// System.out.println("before -  head  : " + curr.head.y + " , " + curr.head.x + "  dIndex : " +  dIndex );

			curr.head.y += dy[dIndex];
			curr.head.x += dx[dIndex];
			
			// System.out.println("after - head  : " + curr.head.y + " , " + curr.head.x + "  dIndex : " +  dIndex );

			// printMap();

			++time;

			if (curr.head.y < 1 || curr.head.x < 1 || curr.head.y > N || curr.head.x > N ||
			   map[curr.head.y][curr.head.x] == 2){
				// printMap();
				break;
			}//게임 종료 조건
		

			tails.add(new Point(curr.head.y, curr.head.x));

			if(map[curr.head.y][curr.head.x] == 0){
				// System.out.println(curr.tail.y + "   " + curr.tail.x);
				Point end = tails.poll();
				map[end.y][end.x] = 0;
				// map[curr.tail.y][curr.tail.x] = 0;
				// curr.tail.y += dy[dIndex];
				// curr.tail.x += dx[dIndex];
			}//해당 공간에 아무것도 없다면(사과를 못먹었다면) 꼬리 이동
			// else {
			// 	curr.length += 1;
			// }//사과를 먹은 경우 몸 길이 늘리기. //몸길이는 중요하지 않다.
		
			map[curr.head.y][curr.head.x] = 2; //내 몸의 자리 기억


			//방향전환
			if(!command.isEmpty()){
				Order next = command.peek();
				
				// System.out.println("curr : " + time + "  next : " +  next.time);
				
				if (time == next.time){
					command.poll();
					dIndex += next.delta;
					if(dIndex < 0) dIndex = 3;
					else if(dIndex > 3) dIndex = 0;
				}
			}


			// System.out.println(time);
			// printMap();
		}

		// printMap();

		System.out.println(time);
	}


	static void printMap(){
		System.out.println("---------------------------------");
		for(int i = 1; i <= N; ++i){
			for(int k = 1; k <= N; ++k){
				System.out.print(map[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------");
	}
}

/*

빈공간 0
사과 1
뱀 2

 * 몸길이를 늘려 머리를 다음 칸
 * 벽이나 자기자신 부딪히면 게임 끝
 * 
 * 이동한 칸에 사과가 있다면 사과는 없어지고 꼬리는 움직이지 않는다
 * 
 * 사과가 없다면 몸길이를 줄여 꼬리 칸 비워준다.(몸길이 변하지 않는다)
 * 
 * 사과의 위치, 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
 * 
 * 
 * 
 * 
 * N
 * 0 <= K(사과아ㅢ 개수) <= 100
 * 사과의 y, 사과의 x
 * 뱀의 방향 변환 횟수 1 <=  L <= 100
 * X초 끝난 뒤 왼쪽(L) 오른쪽(D) 90도 회전
 * X <= 10,000  X 오름차순으로 주어진다.
 * 
 * 출력 : 게임이 몇초에 끝나는지 출력
 * 
 */