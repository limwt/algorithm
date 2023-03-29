package com.lwt.algorithm.level2

/**
 * 주어진 배열에서 k 개 만큼 연속된 원소를 추출하여 새로운 배열을 만든다.
 * 예를 들면 k 가 3일 경우 나올 수 있는 새로운 배열은
 * [10, -4, 3], [-4, 3, 1], [3, 1, 5] ... 등이 된다.
 * 이때 추출된 배열 중 최대값을 구하여라
 *

 * Input
 *     배열 : [10, -4, 3, 1, 5, 6, -35, 12, 21, -1]
 *     k  : 3
 * Output : 32
 *
 * 단순하게 Loop 를 사용할 경우 입력 배열의 크기가 1억개 이상일 경우에 대해서 개선점을 물어 본다.
 */


fun main(args: Array<String>) {
    println(getResult(intArrayOf(10, -4, 3, 1, 5, 6, -35, 12, 21, -1), 3))
}

private fun getResult(number: IntArray, k: Int): Int {
    val s = IntArray(number.size) { 0 }
    s[0] = number[0]

    // 0부터 i 번째까지 값을 s 배열에 저장 한다.
    for (i in 1 until number.size) {
        s[i] = s[i - 1] + number[i]
    }

    // 0번째부터 2번째까지의 합    : S[2]
    // 1번째부터 3번째까지의 합    : S[3] - S[0]
    // 2번째부터 4번째까지의 합    : S[4] - S[1]
    // ...
    // n-2번째 부터 n번째까지의 합 : S[n] - S[n - 3]
    var result = s[2]

    for (i in 3 until number.size) {
        result = Math.max(result, s[i] - s[i - 3])
    }

    return result
}