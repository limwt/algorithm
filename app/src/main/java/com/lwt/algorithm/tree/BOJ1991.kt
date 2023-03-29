package com.lwt.algorithm.tree

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val tree = Tree1991()

    repeat(N) {
        val (a, b, c) = br.readLine().split(" ")
        tree.add(a, b, c)
    }

    tree.preOrder(tree.root!!)
    println()
    tree.inOrder(tree.root!!)
    println()
    tree.postOrder(tree.root!!)
}

private data class TreeNode1991<T>(
    var data: String?,
    var left: TreeNode1991<T>? = null,
    var right: TreeNode1991<T>? = null
)

private class Tree1991 {
    var root: TreeNode1991<String>? = null

    // 트리 추가
    fun add(data: String, left: String, right: String) {
        // 트리내 루트가 비어있는 경우
        if (root == null) {
            if (data != ".") root = TreeNode1991(data)
            if (left != ".") root!!.left = TreeNode1991(left)
            if (right != ".") root!!.right = TreeNode1991(right)
        }
        // 루트가 비어있지 않으면 하위 루트를 찾는다.
        else {
            search(root!!, data, left, right)
        }
    }

    fun search(root: TreeNode1991<String>, data: String, left: String, right: String) {
        // 같은 루트 데이터를 찾는다.
        if (root.data == data) {
            if (left != ".") root.left = TreeNode1991(left)
            if (right != ".") root.right = TreeNode1991(right)
        }
        // 같은 루트가 아닐 경우 왼쪽 오른쪽을 탐색한다.
        else {
            if (root.left != null) search(root.left!!, data, left, right)
            if (root.right != null) search(root.right!!, data, left, right)
        }
    }

    // 전위 순회(루트 -> 왼쪽 -> 오른쪽)
    fun preOrder(root: TreeNode1991<String>) {
        print(root.data)
        root.left?.let { preOrder(it) }
        root.right?.let { preOrder(it) }
    }

    // 중위 순회(왼쪽 -> 루트 -> 오른쪽 탐색)
    fun inOrder(root: TreeNode1991<String>) {
        root.left?.let { inOrder(it) }
        print(root.data)
        root.right?.let { inOrder(it) }
    }

    // 후위 순회(왼쪽 -> 오른쪽 -> 루트)
    fun postOrder(root: TreeNode1991<String>) {
        root.left?.let { postOrder(it) }
        root.right?.let { postOrder(it) }
        print(root.data)
    }
}