package Sandbox

import kotlin.system.measureNanoTime

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


    val listInNanos = measureNanoTime {
        // List를 사용하는 함수형 연쇄 호출 코드
        val toList = (1..7919).toList().filter { it.isPrime() }.take(1000)
    }

    val sequenceInNanos = measureNanoTime {
        // Sequence를 사용하는 함수형 연쇄 호출 코드
        val oneThousandPrimes = generateSequence(3) { value ->
            value + 1
        }.filter { it.isPrime() }.take(1000)
    }

    println("List 작업 완료 소요 시간: ${listInNanos/1000000000.0}")
    println("Sequence 작업 완료 소요 시간: ${sequenceInNanos/1000000000.0}")
    // 시퀀스가 약5배 빠르게 나옴.

    val gradesByStudent = mapOf("Josh" to 4.0, "Alex" to 2.0, "Jane" to 3.0)
    println(flipValues(gradesByStudent))

    val valuesToAdd = listOf(1,18,73,3,44,6,1,33,2,22,5,7)
    val firstStep = valuesToAdd.filter { it >= 5 } // 1단계 5보다 작은 수는 제외
    println(firstStep)
    val secondStep = firstStep.windowed(2,2) // 인접한 두 개의 수를 한쌍으로 묶는다. 슬라이딩 윈도 알고리즘
    println(secondStep)
    val thirdStep = secondStep.map { it.first() * it.last() } // 각 쌍의 수를 곱한다.
//    val thirdStep = secondStep.map {(a,b) -> a * b}
    println(thirdStep)
    val finalStep = thirdStep.sum() // 모든 수를 더해서 최종값을 산출한다.
    println(finalStep)
}

fun flipValues(change: Map<String,Double>) = change.map { it.value to it.key}.toMap()

fun Int.isPrime(): Boolean {
    (2 until this).map{
        if(this%it==0) {
            return false // 소수가 아니다!
        }
    }
    return true
}