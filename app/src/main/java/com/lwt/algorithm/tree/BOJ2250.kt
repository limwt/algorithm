package com.lwt.algorithm.tree

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val tree = Tree2250(N)

    for (i in 0..N) {
        tree.root.add(i, Node2250(-1, i, -1, -1))
    }

    repeat(N) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        tree.add(a, b, c)
    }

    // tree 에 정보들이 잘 들어 갔는 지 확인 한다.
    /*tree.root.forEach {
        println("${it.parent}, ${it.num}, ${it.left}, ${it.right}")
    }*/


    // root index 를 찾는다.
    var rootIndex = -1

    for (i in 1..N) {
        if (tree.root[i].parent == -1) {
            rootIndex = i
            break
        }
    }

    //println("rootIndex : $rootIndex")


    // 중위 순회(왼쪽 -> 루트 -> 오른쪽) 수행한다.
    tree.inOrder(rootIndex, 0)

    // 결과를 출력한다.
    tree.getResult()
}

private data class Node2250(
    var parent: Int = -1,
    var num: Int = -1,
    var left: Int = -1,
    var right: Int = -1
)

private class Tree2250(private val n: Int) {
    val root = mutableListOf<Node2250>()
    var min = IntArray(n + 1) { Int.MAX_VALUE }
    var max = IntArray( n + 1) { 0 }
    var index = 0

    fun add(num: Int, left: Int, right: Int) {
        root[num].left = left
        root[num].right = right

        if (left != -1) root[left].parent = left
        if (right != -1) root[right].parent = right
    }

    fun inOrder(rootIndex: Int, depth: Int) {
        val idx = depth + 1
        if (root[rootIndex].left != -1) inOrder(root[root[rootIndex].left].parent, idx)
        index++
        min[idx] = Math.min(min[idx], index)
        max[idx] = index
        //print("${root[rootIndex].num}/($idx, ${min[idx]}, ${max[idx]}) ")
        if (root[rootIndex].right != -1) inOrder(root[root[rootIndex].right].parent, idx)
    }

    fun getResult() {
        var result = Int.MIN_VALUE
        var resultIndex = 0
        for (i in 1..n) {
            //println("$i = ${min[i]}, ${max[i]}")
            if (result < (max[i] - min[i] + 1)) {
                result = (max[i] - min[i] + 1)
                resultIndex = i
            }
        }

        println("$resultIndex $result")
    }
}