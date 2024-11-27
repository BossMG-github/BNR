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
//    ----------------------- 결합 --------------------------

    val employees = listOf("Denny", "Claudette", "Peter")
    val shirtSize = listOf("large", "x-large", "medium")
    val employeeShirtSizes = employees.zip(shirtSize).toMap()
    println(employeeShirtSizes["Denny"])

    val foldedValue = listOf(1,2,3,4).fold(0) { accumulator, number ->
        println("Accumulated value: $accumulator")
        accumulator + (number * 3) // 누적값은 람다의 반환 즉 결과값으로 변경됨 , 이 부분의 결과로 바뀜
        // 0 + (1 * 3)
        // 3 + (2 * 3) 이런식으로.
    }
    println("Final value: $foldedValue")
}