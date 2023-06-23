package com.lwt.algorithm.programmers

fun main(args: Array<String>) {
    val players: Array<String> = arrayOf("mumu", "soe", "poe", "kai", "mine")
    val callings: Array<String> = arrayOf("kai", "kai", "mine", "mine")

    val map1 = mutableMapOf<Int, String>() // 순위, 이름으로 맵에 저장
    val map2 = mutableMapOf<String, Int>() // 이름, 순위로 맵에 저장

    players.forEachIndexed { index, player ->
        map1[index] = player
        map2[player] = index
    }

    callings.forEach {calling ->
        if (map2.containsKey(calling)) {
            // calling 의 등수 확인
            val grade: Int = map2[calling]!!
            //println("grade : $grade")

            // 이미 1등이면 순위를 바꾸지 않는다.
            if (grade != 0) {
                val temp: String = map1[grade - 1]!!
                map1[grade - 1] = calling
                map1[grade] = temp

                map2[calling] = grade - 1
                map2[temp] = grade

                println("swap : ${map1[grade - 1]}, ${map1[grade]}")
            }
        }
    }

    println(map1.values.toTypedArray())
}