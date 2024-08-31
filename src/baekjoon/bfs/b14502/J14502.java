package baekjoon.bfs.b14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class J14502 {

    static int n,m;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max;

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(max);
    }

    private static void dfs(int cnt) {

        if (cnt == 3) {
            bfs();
            return;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void bfs() {

        Queue<Node> queue = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 2) {
                    queue.add(new Node(i,j));
                }
            }
        }

        int[][] copy = new int[n][m];
        for(int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = target.x + dx[i];
                int ny = target.y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && copy[nx][ny] == 0) {
                    queue.add(new Node(nx,ny));
                    copy[nx][ny] = 2;
                }
            }
        }

        int tmp = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(copy[i][j] == 0) {
                    tmp++;
                }
            }
        }

        max = Math.max(tmp, max);
    }
}
