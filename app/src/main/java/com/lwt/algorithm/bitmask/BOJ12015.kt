package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val A = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val seq = mutableListOf(Int.MIN_VALUE)
    // N 이 1,000,000 까지 이므로
    // 완전 탐색을 할 경우 시간 초과가 발생하기 때문에
    // 이분 탐색을 이용하여 LIS 길이를 구한다.
    for (i in 0 until N) {
        // A[i] 가 seq[index] 보다 크면 index + 1 을 index 로 가지는 seq 에 넣는다.
        if (A[i] > seq.last()) {
            seq.add(A[i])
        } else {
            // A[i] 가 seq[index] 보다 크지 않으면 현재 A[i] 값을 seq 의 어느 index 에 넣는 지 찾는다.
            val targetIndex = binarySearch(seq, A[i])
            //println("i = $i, ${A[i]}, $targetIndex")
            seq[targetIndex] = A[i]
        }
    }

    println(seq.size - 1)
}

private fun binarySearch(arr: MutableList<Int>, target: Int): Int {
    var start = 0
    var end = arr.size - 1

    while (start <= end) {
        val mid = (start + end) / 2
        if (arr[mid] > target) end = mid - 1
        else if (arr[mid] < target) start = mid + 1
        else return mid
    }

    return start
}