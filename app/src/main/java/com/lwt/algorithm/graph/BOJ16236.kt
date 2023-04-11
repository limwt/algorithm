package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val map = Array(N) { IntArray(N) { 0 } }

    repeat(N) { index ->
        map[index] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            // 상어 위치를 찾아서 0 을 바꾸고 BFS 를 수행한다.
            // 싱어가 이동을 하기 때문에 쉽게 풀기 위해서 0으로 변경한다.
            if (map[i][j] == 9) {
                map[i][j] = 0
                println("${bfs(N, i, j, map)}")
                break
            }
        }
    }
}

private fun bfs(N: Int, x: Int, y: Int, map: Array<IntArray>): Int {
    var result = 0
    // first  : 현재 상어의 X 좌표
    // second : 현재 상어의 Y 좌표
    // third  : 상어가 물고기를 먹으로 이동한 거리
    val queue = LinkedList<Triple<Int, Int, Int>>()
    queue.add(Triple(x, y, 0))
    var sharkSize = 2 // 현재 아기 상어의 크기
    var eatCount = 0  // 아기 상어가 먹은 물고기의 수

    while (true) {
        // 상어가 먹을 수 있는 물고기를 찾기위해서 탐색한다.
        val visit = Array(N) { BooleanArray(N) { false } }

        if (queue.isEmpty()) break

        queue.peek()?.let { shark ->
            visit[shark.first][shark.second] = true
        }

        // 아기 상어가 잡아먹은 물고기 정보를 저장하는 queue
        val eatenFish = LinkedList<Triple<Int, Int, Int>>()

        // 아기 상어가 잡어먹을 수 있는 물고기를 찾기 위해서 탐색한다.
        while (queue.isNotEmpty()) {
            val curShark = queue.pop()
            val dX = intArrayOf(-1, 1,  0, 0)
            val dY = intArrayOf( 0, 0, -1, 1)

            for (i in 0..3) {
                val nX = curShark.first + dX[i]
                val nY = curShark.second + dY[i]

                //범위 밖 탐색
                if ((nX < 0 || nX >= N) || (nY < 0 || nY >= N)) continue
                //아기 상어보다 큰 물고기 이므로 이동할 수 없다.
                if (sharkSize < map[nX][nY]) continue
                //이미 방문한 영역
                if (visit[nX][nY]) continue

                queue.add(Triple(nX, nY, curShark.third + 1))
                visit[nX][nY] = true

                //println("queue $queue")

                //아기 상어가 물고기보다 크므로 잡어먹을 수 있다.
                if (map[nX][nY] in 1..6 &&  sharkSize > map[nX][nY]) eatenFish.add(Triple(nX, nY, curShark.third + 1))
            }
        }

        //잡아 먹은 물고기가 없는 경우이므로 결과를 출력한다.
        if (eatenFish.isEmpty()) return result

        var resultFish = eatenFish[0]
        //잡아 먹은 물고기가 1마리 이상일 경우 아기 상어가 최종적으로 먹은 물고기를 찾는다.
        for (i in 1 until eatenFish.size) {
            //거리가 제일 짧은 물고기를 찾고(eatenFish.third)
            if (resultFish.third > eatenFish[i].third) {
                resultFish = eatenFish[i]
            } else if (resultFish.third == eatenFish[i].third) {
                //거리가 제일 짧은 물고기가 여러 마리면 제일 위쪽에 위치한 물고기를 찾고(eatenFish.first 가 제일 작은 것)
                if (resultFish.first > eatenFish[i].first) {
                    resultFish = eatenFish[i]
                } else if (resultFish.first == eatenFish[i].first) {
                    //제일 위쪽의 물고기가 여러 마리면 제일 왼쪽의 물고기를 찾는다.(eatenFish.second 가 제일 작은 것)
                    if (resultFish.second > eatenFish[i].second) {
                        resultFish = eatenFish[i]
                    }
                }
            }
        }

        // 최종적으로 잡아먹은 물고기 위치를 큐에 저장하고 거리는 0으로 초기화 한다.
        result += resultFish.third
        queue.add(Triple(resultFish.first, resultFish.second, 0))
        // 물고기 정보를 0으로 업데이트 한다.
        map[resultFish.first][resultFish.second] = 0
        eatCount++

        // 아기 상어의 크기가 커지는 지 확인한다.
        if (sharkSize == eatCount) {
            sharkSize++
            eatCount = 0
        }
    }

    return result
}