package com.lwt.algorithm.tree

import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val n = sc.nextLine().toInt()
    val tree = Tree()

    repeat(n) {
        val (a, b, c) = sc.nextLine().split(" ")
        tree.add(a, b, c)
    }

    tree.preOrder(tree.root!!)
}

private data class TreeNode<T>(
    var data: T,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
)

private class Tree {
    var root: TreeNode<String>?= null

    // 트리 추가
    fun add(data: String, left: String, right: String) {
        if (root == null) {
            // 새로 생성
            if (data != ".") root = TreeNode(data)
            if (left != ".") root!!.left = TreeNode(left)
            if (right != ".") root!!.right = TreeNode(right)
        }
        // 트리가 존재할 때
        else {
            search(root!!, data, left, right)
        }
    }

    fun search(root: TreeNode<String>, data: String, left: String, right: String) {
        // 루트를 찾았을 때
        if (root.data == data) {
            if (left != ".") root.left = TreeNode(left)
            if (right != ".") root.right = TreeNode(right)
        }
        // 루트를 못찾았을 때 좌우 탐색
        else {
            if (root.left != null) search(root.left!!, data, left, right)
            if (root.right != null) search(root.right!!, data, left, right)
        }
    }

    // 전위 순회 (루트, 왼쪽 자식, 오른쪽 자식)
    fun preOrder(root: TreeNode<String>) {
        println(root.data)
        root.left?.let {
            preOrder(it)
        }
        root.right?.let {
            preOrder(it)
        }
    }

    // 중위 순회 (왼쪽 자식, 루트, 오른쪽 자식)
    fun inOrder(root: TreeNode<String>) {
        root.left?.let { inOrder(it) }
        println(root.data)
        root.right?.let { inOrder(it) }
    }

    // 후위 순회(왼쪽 자식, 오른쪽 자식, 루트)
    fun postOrder(root: TreeNode<String>) {
        root.left?.let { postOrder(it) }
        root.right?.let { postOrder(it) }
        println(root.data)
    }
}