package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val meetingRoom = mutableListOf<Pair<Int, Int>>()

    repeat(N) {
        val (start, end) = br.readLine().split(" ").map { it.toInt() }
        meetingRoom.add(Pair(start, end))
    }

    // 회의 끝나는 시간을 기준으로 오름차순 정렬한다.
    // 끝나는 시간이 같을 경우 시작 시간을 기준으로 오름 차순 정렬한다.
    meetingRoom.sortWith(compareBy({
        it.second
    }, {
        it.first
    }))

    //println(meetingRoom)

    var result = 0
    var time = 0

    for (room in meetingRoom) {
        val (start, end) = room

        // 회의 시작 시간이 저장된 time 보다 크거나 같은 지 확인 한다.
        if (time <= start) {
            time = end
            result++
        }
    }

    println(result)
}