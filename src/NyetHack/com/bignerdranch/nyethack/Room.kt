package NyetHack.com.bignerdranch.nyethack

open class Room(val name: String) {
    protected open val dangerLevel = 5

    fun description() = "Room: $name\r\n" +
            "위험 수준: $dangerLevel"

    open fun load() = "아무도 여기에 오지 않았습니다..."
}

class TownSquare : Room("Town Square") {
    override fun load() = "당신의 참여를 주민들이 다 함께 환영합니다!"
}