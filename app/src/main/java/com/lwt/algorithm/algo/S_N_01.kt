package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

data class TreeNode<T> (
    var data: T?,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
)

class Tree {
    var root: TreeNode<String>? = null

    fun add(data: String) {
        println("add $root, $data")

        if (root == null) {
            root = TreeNode(data)
        } else {
            add(data, root!!)
        }
    }

    fun add(data: String, root: TreeNode<String>) {
        println("add2 $root, ${root.data} $data")

        if (root.left == null) {
            val node = TreeNode(data)
            root.left = node
        } else if (root.right == null) {
            val node = TreeNode(data)
            root.right = node
        } else {
            if (root.left!!.left == null) {
                add(data, root.left!!)
            } else if (root.right!!.right == null) {
                add(data, root.right!!)
            }
        }
    }

    // 전위 순회 (루트, 왼쪽 자식, 오른쪽 자식)
    fun preOrder(root: TreeNode<String>){
        //println("${root.data} ${root.left} ${root.right}")
        print(root.data)
        if(root.left != null) preOrder(root.left!!)
        if(root.right != null) preOrder(root.right!!)
    }

    // 중위 순회 (왼쪽 자식, 루트, 오른쪽 자식)
    fun inOrder(root: TreeNode<String>){
        if(root.left != null) inOrder(root.left!!)
        print(root.data)
        if(root.right != null) inOrder(root.right!!)
    }

    // 후위 순회 (왼쪽 자식, 오른쪽 자식, 루트)
    fun postOrder(root: TreeNode<String>){
        if(root.left != null) postOrder(root.left!!)
        if(root.right != null) postOrder(root.right!!)
        print(root.data)
    }
}

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val inputStr = br.readLine()
    val tree = Tree()

    for (i in inputStr.indices) {
        /*var a = "" // 루트
        var b = "" // 왼쪽
        var c = "" // 오른쪽

        for (j in i..i + 2) {
            if (j == inputStr.length) break

            if (j % 3 == 0) a = inputStr[j].toString()
            else if (j % 3 == 1) b = inputStr[j].toString()
            else c = inputStr[j].toString()
        }

        //println("$a, $b, $c")
        tree.add(a, b, c)*/
        tree.add(inputStr[i].toString())
    }

    tree.postOrder(tree.root!!)
}