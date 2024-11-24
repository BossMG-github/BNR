package NyetHack.com.bignerdranch.nyethack

import java.util.*

interface Fightable { // 몬스터, 플레이어는 개념적으로 상속관계가 없음. 하지만 공통적인 속성이나 행동을 갖는 경우이기 때문에 인터페이스를 사용.
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int
    val damageRoll: Int
        get() = (0 until diceCount).map {
            Random().nextInt(diceSides) + 1
        }.sum()

    fun attack(opponent: Fightable): Int
}

abstract class Monster( // 상속 관계를 가지면서 인스턴ㅅ 생성이 필요 없는 경우면서 슈퍼 클래스가 필요한 상황이기 때문에 추상 클래스를 사용.
    val name: String,
    val description: String,
    override var healthPoints: Int) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}

class Goblin(
    name: String = "Goblin",
    description: String = "추하게 생긴 고블린",
    healthPoints: Int = 30, ) : Monster(name, description, healthPoints) {

    override val diceCount = 2
    override val diceSides = 8

}