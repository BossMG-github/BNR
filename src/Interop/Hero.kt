package Interop

// NyetHack 왕국의 용사를 나타내는 파일

fun main() {
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.toLowerCase() ?: "무슨 의미인지 난해하군요.")
}