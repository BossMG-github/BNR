package NyetHack.com.bignerdranch.nyethack

import java.nio.file.Files.move
import java.util.*
import kotlin.system.exitProcess

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

    // 격자 형태의 공간
    private var worldMap = listOf(
            listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room")))


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
            println(GameInput(readLine()).processCommand())
        }
    }
    private fun gameQuit(): Nothing {
        println("수고하셨습니다! 게임을 종료합니다.")
        exitProcess(0)
    }

    private fun move(directionInput: String) =
        try{
            val direction = Direction.valueOf(directionInput.uppercase(Locale.getDefault()))
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if(!newPosition.isInBounds) {
                throw IllegalStateException("$direction 쪽 방향이 범위를 벗어남.")
            }

            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "Ok, $direction 방향의 ${newRoom.name}로 이동했습니다."
        } catch (e: Exception) {
            "잘못된 방향임: $directionInput"
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

    private class GameInput(arg : String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0] // split으로 반환된 리스트에서 인덱스 0 값을 대입. 즉, 사용자 입력 명령을 대입
        val argument = input.split(" ").getOrElse(1) {""}

        private fun commandNotFound() = "적합하지 않은 명령입니다!"

        fun processCommand() = when (command.lowercase(Locale.getDefault())) {
            "move" -> move(argument)
            "quit" -> gameQuit()
            "exit" -> gameQuit()
            else -> commandNotFound()
        }
    }

}