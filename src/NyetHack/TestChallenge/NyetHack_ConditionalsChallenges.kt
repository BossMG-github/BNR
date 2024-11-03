package NyetHack.TestChallenge

fun main(args: Array<String>) {
    val name = "마드리갈"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    //아우라
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()
    val auraColor = when (karma) {
        in 16..20 -> "GREEN"
        in 11..15 -> "PURPLE"
        in 6..10 -> "ORANGE"
        in 0..5 -> "RED"
        else -> "NONE"
    }


    val healthStatus = when (healthPoints) {
        100 -> " 최상의 상태임!"
        in 90..99 -> " 약간의 찰과상만 있음."
        in 75..89 -> if (isBlessed) {
            " 경미한 상처가 있지만 빨리 치유되고 있음"
        } else {
            " 경미한 상처만 있음."
        }

        in 15..74 -> " 많이 다친 것 같음."
        else -> " 최악의 상태임!"
    }

    // 플레이어의 상태 출력
    println(
        "(Aura: ${
            if (auraVisible) {
                auraColor
            } else {
                
            }
        }) " +
                "(Blessed: ${
                    if (isBlessed) {
                        "YES"
                    } else {
                        "NO"
                    }
                })"
    )
    println("$name $healthStatus")

    // 형식 문자열을 사용하여 플레이어의 상태 출력
    /*val statusFormatString = "(HP: $healthPoints)(Aura: $auraColor)(Blessed: ${if (isBlessed) "YES" else "NO"}) -> $name $healthStatus"
    println(statusFormatString)*/
    val statusFormatString = String.format("(HP: %d)(Aura: %s)(Blessed: %s) -> %s %s", healthPoints, auraColor, if(isBlessed) "YES" else "NO", name, healthStatus)
    println(statusFormatString)


}