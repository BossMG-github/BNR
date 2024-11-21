package NyetHack.com.bignerdranch.nyethack

fun main(args: Array<String>) {
    /*performCombat()
    performCombat("ulrich")
    performCombat("Hildr", true)
    */

    Game.play()
}


/*private fun auraColor(
    isBlessed: Boolean,
    healthPoints: Int,
    isImmortal: Boolean
) = if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"


private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) = when (healthPoints) {
    100 -> " 최상의 상태임!"
    in 90..99 -> " 약간의 찰과상만 있음."
    in 75..89 -> if (isBlessed) {
        " 경미한 상처가 있지만 빨리 치유되고 있음"
    } else {
        " 경미한 상처만 있음."
    }

    in 15..74 -> " 많이 다친 것 같음."
    else -> " 최악의 상태임!"
}*/


fun performCombat() {
    println("적군이 없다!")
}

fun performCombat(enemyName: String) {
    println("$enemyName 과 전투를 시작함.")
}

fun performCombat(enemyName: String, isBlessed: Boolean) {
    if (isBlessed) {
        println("$enemyName 과 전투를 시작함. 축복을 받음!")
    } else {
        println("$enemyName 과 전투를 시작함.")
    }
}

object Game{
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    init {
        println("방문을 환영합니다.")
        player.castFireball()
    }

    fun play() {
        while (true) {
            // NyetHack 게임을 시작한다
            println(currentRoom.description())
            println(currentRoom.load())

            // 플레이어의 상태 출력
            printPlayerStatus(player)

            print("> 명령을 입력하세요: ")
            println("최근 명령: ${readLine()}")
        }
    }

    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()}) " +
                    "(Blessed: ${
                        if (player.isBlessed) {
                            "YES"
                        } else {
                            "NO"
                        }
                    })"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }
}