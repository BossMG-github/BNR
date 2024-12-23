package NyetHack.com.bignerdranch.nyethack

import java.io.File
import java.util.*
import NyetHack.com.bignerdranch.nyethack.extensions.random as randomizer

class Player(
    _name: String,
    override var healthPoints: Int = 100,
    val isBlessed: Boolean,
    private val isImmortal: Boolean ): Fightable {

    var name = _name
        get() = "${field.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} of $hometown"
        private set(value) {
            field = value.trim()
        }

    val hometown by lazy { selectHometown() }
    var currentPosition = Coordinate(0, 0)

    init {
        require(healthPoints > 0) { "healthPoints는 0보다 커야 합니다."}
        require(name.isNotBlank()) {"플레이어는 이름이 있어야 합니다."}
    }

    constructor(name: String) : this(name,
        isBlessed = true,
        isImmortal = false) {
         if (name.lowercase(Locale.getDefault()) == "kar") healthPoints = 40
    }

    private fun selectHometown() =
        File("src/NyetHack/data/towns.txt").readText().split("\r\n").randomizer()

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "최상의 상태임!"
            in 90..99 -> "약간의 찰과상만 있음."
            in 75..89 -> if (isBlessed) {
                "경미한 상처가 있지만 빨리 치유되고 있음!"
            } else {
                "경미한 상처만 있음."
            }

            in 15..74 -> "많이 다친 것 같음."
            else -> "최악의 상태임!"
        }

    fun castFireball(numFireballs: Int = 2) =
        println("한 덩어리의 파이어볼이 나타난다. (x$numFireballs)")

    override val diceCount = 3
    override val diceSides = 6
    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}