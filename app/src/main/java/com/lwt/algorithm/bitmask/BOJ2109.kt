package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val list = mutableListOf<Lecture>()

    repeat(n) {
        val (p, d) = br.readLine().split(" ").map { it.toInt() }
        list.add(Lecture(p, d))
    }

    // 강연료 기준으로 내림차순 정렬 한다.
    list.sortWith { o1, o2 ->
        o2.p - o1.p
    }

    val lecture = IntArray(10001) { 0 }

    // list 를 탐색한다.
    for (l in list) {
        // lecture 배열의 list 의 d를 index 로 했을 때 값이 0이면 강연 스케쥴이 비어 있으므로 강연을 할 수 있다.
        if (lecture[l.d] == 0) {
            lecture[l.d] = l.p
        } else {
            // lecture 배열의 list 의 d를 index 로 했을 때 값이 0이 아니면
            // 이미 다른 강연 스케쥴이 있으므로 잡혀있는 강연 스케쥴보다 강연료가 비싼지 확인하여
            // 비싸면 현재 값으로 업데이트 한다.
            // 비싸지 않으면 이전 날짜를 탐색하여 비어있는 날짜 혹은 강연료가 현재 강연료 보다 싼 강연을 탐색한다.
            if (lecture[l.d] < l.p) {
                lecture[l.d] = l.p
            } else {
                var day = l.d - 1
                while (day > 0) {
                    if (lecture[day] == 0) {
                        lecture[day] = l.p
                        break
                    } else {
                        if (lecture[day] < l.p) {
                            lecture[day] = l.p
                            break
                        } else {
                            day--
                        }
                    }
                }
            }
        }
    }

    var result = 0

    for (lec in lecture) {
        if (lec != 0) result += lec
    }

    println(result)
}

private data class Lecture(val p: Int, val d: Int)