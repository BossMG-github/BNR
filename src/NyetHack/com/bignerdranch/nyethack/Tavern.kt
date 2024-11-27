package NyetHack.com.bignerdranch.nyethack

import java.io.File
import NyetHack.com.bignerdranch.nyethack.extensions.random

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Farnsworth", "Baggins")
//val uniquePatrons = mutableSetOf<String>()
val uniquePatrons: Set<String> = generateSequence{
    val first = patronList.random()
    val last = lastName.random()
    "$first $last"
}.distinct().take(9).toSet()
val menuList = File("C:\\study\\kotlin\\BNR\\src\\NyetHack\\data\\tavern-menu-items.txt").readText().split("\r\n")
//val patronGold = mutableMapOf<String, Double>()
val patronGold = uniquePatrons.map { it to 6.0 }.toMap().toMutableMap()
fun main(args: Array<String>) {

    if (patronList.contains("Eli")) {
        println("술집 주인이 말한다: Eli는 안쪽 방에서 카드하고 있어요.")
    } else {
        println("술집 주인이 말한다: Eli는 여기 없어요.")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("술집 주인이 말한다: 네, 모두 있어요.")
    } else {
        println("술집 주인이 말한다: 아니오, 나간 사람도 있습니다.")
    }

/*    (0..9).forEach { // 범위는 Iterable타입이라서 forEach 사용 가능
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }*/

    /*
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }
    */

    var orderCount = 1
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.random(), menuList.random())
        orderCount++
    }

    displayPatronBalances()
}

// 술집 문지기
private fun gatekeeper(){
    val patronsToRemove = mutableListOf<String>()
    patronGold.forEach { (patron, balance) ->
        if(balance < 1.22) {
            println("$patron 은 금화가 부족하여 술집에서 쫓겨납니다!")
            patronsToRemove += patron
        }
    }
    /*patronsToRemove.forEach {
        uniquePatrons.remove(it)
        patronGold.remove(it)
    }*/
}

private fun displayPatronBalances(){
    patronGold.forEach { (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName 은 $tavernMaster 에게 주문한다")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName 은 금화 $price 로 $name ($type)를 구입한다."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
//        "$patronName 이 감탄한다: ${toDragonSpeak("와, $name 진짜 좋구나!")}"
        "$patronName 이 감탄한다: ${"와, $name 진짜 좋구나!".toDragonSpeak()}"
    } else {
        "$patronName 이 말한다: 감사합니다 $name"
    }
    println(phrase)
}

private fun String.toDragonSpeak() =
    this.replace(Regex("[aeiouAEIOU]")) {
        when (it.value.lowercase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

/*
private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")) {
        when (it.value.lowercase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }*/
