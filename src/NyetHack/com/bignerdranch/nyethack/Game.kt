package NyetHack.com.bignerdranch.nyethack

import NyetHack.com.bignerdranch.nyethack.extensions.frame
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
        println("Welcome, ${player.name}".frame(5))
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

    private fun map(): String{
        /*var result = ""
        for(y in worldMap.indices) {
            for(x in worldMap[y].indices) {
                if(player.currentPosition.x == x && player.currentPosition.y == y) {
                    result += "X "
                } else {
                    result += "O "
                }
            }
            result += "\n"
        }
        return result*/
        return worldMap.mapIndexed { y, row ->
            row.mapIndexed { x, _ ->
                if (player.currentPosition.x == x && player.currentPosition.y == y) "X" else "O"
            }.joinToString(" ")
        }.joinToString("\n")
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

    private fun fight() = currentRoom.monster?.let {
        while(player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1000)
        }
        "전투가 끝났음."
    } ?: "여기에는 싸울 괴물이 없습니다..."

    private fun slay(monster: Monster) {
        println("${monster.name} -- ${monster.attack(player)} 손상을 입힘!")
        println("${player.name} -- ${player.attack(monster)} 손상을 입힘!")

        if(player.healthPoints <= 0) {
            println(">>>> 당신은 졌습니다! 게임을 종료합니다.. <<<<")
            gameQuit()
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} -- 격퇴됨! <<<<")
            currentRoom.monster = null
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

    private class GameInput(arg : String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0] // split으로 반환된 리스트에서 인덱스 0 값을 대입. 즉, 사용자 입력 명령을 대입
        val argument = input.split(" ").getOrElse(1) {""}

        private fun commandNotFound() = "적합하지 않은 명령입니다!"

        fun processCommand() = when (command.lowercase(Locale.getDefault())) {
            "fight" -> fight()
            "move" -> move(argument)
            "quit", "exit" -> gameQuit()
            "map" -> map()
            "ring" -> if(currentRoom is TownSquare) (currentRoom as TownSquare).ringBell() else "현재 방에서는 종을 칠 수 없습니다."
            else -> commandNotFound()
        }
    }

}