package com.lwt.algorithm.lis

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val list = IntArray(N) { Int.MAX_VALUE }
    list[0] = arr[0]
    var index = 0

    for (i in 1 until N) {
        if (list[index] < arr[i]) {
            list[++index] = arr[i]
        } else {
            // 이분탐색을 이용해서 넣을 위치를 찾는다.
            val target = binarySearch(list, arr[i])
            list[target] = arr[i]
        }
    }

    for (l in list) {
        print("$l ")
    }
    println()
}

private fun binarySearch(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.size - 1

    while (start < end) {
        val mid = (start + end) / 2

        if (arr[mid] < target) start = mid + 1
        else if (arr[mid] > target) end = mid - 1
        else return mid
    }

    return start
}