@file:JvmName("Hero")
package Interop

// NyetHack 왕국의 용사를 나타내는 파일

fun main() {
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.toLowerCase() ?: "무슨 의미인지 난해하군요.")

    val adversaryHitPoints: Int = adversary.hitPoints
    println(adversaryHitPoints.dec())
    println(adversaryHitPoints.javaClass)

    adversary.greeting = "안녕하세요, 용사시여."
    println(adversary.utterGreeting())

    adversary.offerFood()
}

fun makeProclamation() = "안녕, 괴물아!"

@JvmOverloads
fun handOverFood(leftHand: String = "딸기", rightHand: String = "고기") {
    println("맛있는 $leftHand 와 $rightHand 를 넘겨주었습니다.")
}

class Spellbook {
    @JvmField
    val spells = listOf("Magic Ms. L", "Lay on Hans")

    companion object {
        @JvmField
        val MAX_SPELL_COUNT = 10

        @JvmStatic
        fun getSpellbookGreeting() = println("나는 위대한 그리모어다!")
    }
}