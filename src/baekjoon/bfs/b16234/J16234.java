package baekjoon.bfs.b16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class J16234 {

    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Node> nodes;
    static int n,l,r;

    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(check());
    }

    private static int check() {
        int result = 0;

        while (true) {
            boolean isContinued = false;
            visit = new boolean[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(!visit[i][j]) {
                        bfs(i, j);
                        if (nodes.size() > 1) {
                            isContinued = true;
                        }
                    }
                }
            }
            if(!isContinued) return result;
            result++;
        }
    }

    private static void bfs(int x , int y) {

        Queue<Node> q = new LinkedList<>();

        nodes = new ArrayList<>();
        nodes.add(new Node(x, y));
        q.offer(new Node(x, y));
        int sum = 0;
        sum += map[x][y];
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node target = q.poll();
            for(int i = 0; i<4; i++) {
                int nx = target.x + dx[i];
                int ny = target.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {
                    int diff = Math.abs(map[target.x][target.y] - map[nx][ny]);
                    if(diff >= l && diff <= r) {
                        visit[nx][ny] = true;
                        nodes.add(new Node(nx, ny));
                        q.offer(new Node(nx, ny));
                        sum += map[nx][ny];
                    }
                }
            }
        }

        if(nodes.size() > 1) {
            int tmp = sum / nodes.size();
            for(Node node : nodes) {
                map[node.x][node.y] = tmp;
            }
        }
    }
}
