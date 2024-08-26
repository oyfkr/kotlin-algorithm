package baekjoon.bfs.b2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class J2589 {

    static class Node {
        int x, y, len;

        Node(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    static char[][] map;
    static boolean[][] visited;
    static int max = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'L') {
                    visited = new boolean[N][M];
                    bfs(i,j);
                }
            }
        }

        System.out.println(max);
    }

    private static void bfs(int x, int y) {

        int maxMove = 0;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y,0));
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Node target = que.poll();

            if(maxMove < target.len) {
                maxMove = target.len;
            }

            for(int i = 0; i<4; i++) {
                int nx = target.x + dx[i];
                int ny = target.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(map[nx][ny] == 'L' && !visited[nx][ny]) {
                    que.offer(new Node(nx, ny, target.len+1));
                    visited[nx][ny] = true;
                }
            }
        }

        max = Math.max(maxMove, max);
    }
}
