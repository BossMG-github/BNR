package NyetHack.com.bignerdranch.nyethack

class Room(val name: String) {
    fun description() = "Room: $name"

    fun load() = "아무도 여기에 오지 않았습니다..."
}