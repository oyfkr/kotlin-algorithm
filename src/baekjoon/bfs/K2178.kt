package baekjoon.bfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

val dx = arrayOf(-1,1,0,0)
val dy = arrayOf(0,0,-1,1)

data class Node (var x: Int, var y: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val map = Array(N) { Array(M) {0} }

    repeat(N) { x ->
        val line = br.readLine()
        repeat(M) { y ->
            map[x][y] = line[y] - '0'
        }
    }

    bfs(N, M, map)
}

fun bfs(n : Int, m : Int, map: Array<Array<Int>>) {

    val que: Queue<Node> = LinkedList()

    val visit = Array(n) {Array(m) {1} }
    que.offer(Node(0,0))

    while (que.isNotEmpty()) {

        val target = que.poll()

        for(i in 0..<4) {
            val nx = target.x + dx[i]
            val ny = target.y + dy[i]

            if(nx !in 0..<n || ny !in 0..<m || map[nx][ny] == 0 || visit[nx][ny] != 1)
                continue

            que.offer(Node(nx, ny))
            visit[nx][ny] = visit[target.x][target.y] + 1

            if(nx == n-1 && ny == m-1) {
                println(visit[nx][ny])
                break
            }
        }
    }
}