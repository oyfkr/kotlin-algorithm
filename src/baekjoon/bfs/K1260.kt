package baekjoon.bfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

private var arr = Array(0) { Array(0) { 0 } }
private var visit = Array(0) { false }
private var sb = StringBuilder()
private var N = 0
private var M = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    val V = st.nextToken().toInt()

    arr = Array(N+1) { Array(N + 1) { 0 } }
    visit = Array(N+1) { false }

    for(i in 1..M step 1) {
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        arr[x][y] = 1
        arr[y][x] = 1
    }

    DFS(V)
    sb.append('\n')

    visit = Array(N+1) { false }
    BFS(V)

    println(sb)
}

fun DFS(nodeNum: Int) {
    visit[nodeNum] = true
    sb.append("$nodeNum ")

    for(i in 1..N step 1) {
        if(arr[nodeNum][i] == 1 && !visit[i]) {
            DFS(i)
        }
    }
}

fun BFS(nodeNum: Int) {
    visit[nodeNum] = true
    sb.append("$nodeNum ")

    val que : Queue<Int> = LinkedList()
    que.offer(nodeNum)

    while (que.isNotEmpty()) {
        val nodeNum = que.poll()

        for(i in 1..N step 1) {
            if(arr[nodeNum][i] == 1 && !visit[i]) {
                que.offer(i)
                visit[i] = true
                sb.append("$i ")
            }
        }
    }
}