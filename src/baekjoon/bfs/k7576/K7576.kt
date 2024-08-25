package baekjoon.bfs.k7576

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


/*
1. 그래프를 입력 받기
2. 익은 토마토 위치를 모두 큐에 넣기
2-1. 그래프의 모든 값이 1이면 1 출력
3. BFS로 모든 1에서부터 방문 가능한 모든 0을 방문하면서, 1을 더한다
4. 모든 탐색 후 익지 않은 토마토가 남아있으면 -1을, 그렇지 않으면 그래프의 최댓값 -1을 출력
 */

private var map = Array(0) { Array(0) { 0 } }
private var visit = Array(0) { Array(0) { false } }
private var unripeCnt = 0
val dx = arrayOf(-1,1,0,0)
val dy = arrayOf(0,0,-1,1)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()

    val que : Queue<Pair<Int,Int>> = LinkedList()
    map = Array(N) {Array(M) { 0 } }

    repeat(N) { x ->
        st = StringTokenizer(br.readLine())
        repeat(M) { y ->
            val tomato = st.nextToken().toInt()
            map[x][y] = tomato
            when (tomato) {
                1 -> que.offer(Pair(x,y))
                0 -> unripeCnt++
            }

        }
    }

    // 모든 토마토가 익은 경우
    if(unripeCnt == 0) {
        println(0)
        return
    }

    println(bfs(que))
}

fun bfs(que: Queue<Pair<Int,Int>>): Int {

    var day = 0

    while (que.isNotEmpty()) {
        val target = que.poll()

        day = map[target.first][target.second]

        for(i in 0..3 step 1) {
            val nx = target.first + dx[i]
            val ny = target.second + dy[i]

            if(nx !in map.indices || ny !in map[0].indices || map[nx][ny] != 0) continue

            unripeCnt--
            map[nx][ny] = map[target.first][target.second] + 1
            que.offer(Pair(nx,ny))
        }
    }

    if(unripeCnt != 0) {
        return -1
    }

    return day-1
}