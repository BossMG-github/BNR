package Sandbox

fun main() {

    val tenDollarWords = listOf("aaaaaaa","bbb","ccccccccccc")
    val tenDollarWordLengths = tenDollarWords.map { it.length }
    println(tenDollarWordLengths)

    println(listOf(listOf(1,2,3),listOf(4,5,6)).flatMap { it })
}