package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = br.readLine().split(" ").map { it.toInt() }
    val jewel = mutableListOf<Pair<Int, Int>>()
    val bag = LinkedList<Int>()

    repeat(N) {
        val (M, V) = br.readLine().split(" ").map { it.toInt() }
        jewel.add(Pair(M, V))
    }

    repeat(K) {
        val C = br.readLine().toInt()
        bag.add(C)
    }

    // 보석을 무게를 기준으로 오름 차순 정렬한다.
    jewel.sortWith { a, b ->
        a.first - b.first
    }

    //println("jewel $jewel")

    // 가방의 무게를 오름차순으로 정렬한다.
    bag.sort()

    //println("bag $bag")

    //가방을 다 쓸때까지 탐색한다.
    var result = 0L

    // 우선순위 큐를 선언한다.
    val queue = PriorityQueue(Comparator<Pair<Int, Int>>{ a, b ->
        b.second - a.second
    })

    var index = 0

    while (bag.isNotEmpty()) {
        val currentBag = bag.pop()

        while (index < jewel.size && currentBag >= jewel[index].first) {
            // 보석의 가격을 기준으로 우선 순위 큐에 저장한다.
            queue.add(Pair(jewel[index].first, jewel[index].second))
            index++
        }

        //println("index $index, $queue")

        if (queue.isNotEmpty()) {
            queue.poll()?.let {
                result += it.second
            }
        }
    }

    println(result)
}