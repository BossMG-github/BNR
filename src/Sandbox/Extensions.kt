package Sandbox

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun main() {
    println("마드리갈이 그 건물에서 나왔습니다".addEnthusiasm())
}