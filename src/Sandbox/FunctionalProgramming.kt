package Sandbox

fun main() {

//    ----------------------- 변환 --------------------------
    val tenDollarWords = listOf("aaaaaaa","bbb","ccccccccccc")
    val tenDollarWordLengths = tenDollarWords.map { it.length }
    println(tenDollarWordLengths)

    println(listOf(listOf(1,2,3),listOf(4,5,6)).flatMap { it })

//    ----------------------- 필터 --------------------------
    val itemsOfManyColors = listOf(listOf("red apple", "green apple", "blue apple"), listOf("red fish","blue fish"), listOf("yellow banana","teal banana"))
    val redItems = itemsOfManyColors.flatMap { it.filter { it.contains("red") } }
    println(redItems)

    val numbers = listOf(7,4,8,4,3,22,18,11)
    val primes = numbers.filter {number ->
        (2 until number).map { number % it }.none { it == 0}
    }
    println(primes)
}