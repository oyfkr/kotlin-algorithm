package baekjoon.bfs.k2667

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var map = Array(0) { Array(0) { 0 } }
private var visit = Array(0) { Array(0) { false } }
private var cnt = 0
val dx = arrayOf(-1,1,0,0)
val dy = arrayOf(0,0,-1,1)

private data class Node(val x: Int, val y: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()

    map = Array(n) { Array(n) { 0 } }
    visit = Array(n) { Array(n) { false } }

    repeat(n) { x ->
        val line = br.readLine()
        repeat(n) { y ->
            map[x][y] = line[y] - '0'
        }
    }

    val list = ArrayList<Int>()
    // 0이 아니면서 방문 x 인 특정 점에서만 bfs를 호출
    repeat(n) { x ->
        repeat(n) { y ->
            if(map[x][y] == 1 && !visit[x][y]) {
                list.add(bfs(x,y,n))
            }
        }
    }
    println(cnt)
    list.sort()
    for(i in list.indices) {
        println(list[i])
    }
}

fun bfs(x: Int, y: Int, n: Int) : Int {

    cnt++
    var totalCnt = 1;
    val que : Queue<Node> = LinkedList()
    que.offer(Node(x,y))
    visit[x][y] = true

    while (que.isNotEmpty()) {
        val target = que.poll()

        for(i in 0..3 step 1) {
            val nx = target.x + dx[i]
            val ny = target.y + dy[i]

            if(nx !in 0..<n || ny !in 0..<n || map[nx][ny] == 0 || visit[nx][ny])
                continue

            que.offer(Node(nx, ny))
            visit[nx][ny] = true
            totalCnt++
        }
    }

    return totalCnt
}
