package NyetHack

fun main(args: Array<String>) {
//    var test1: String? = null
    var beverage = readLine()
//    beverage = null
    if (beverage != null){
        beverage = beverage.capitalize()
    } else {
        println("beverage가 null입니다!")
    }
    val beverageServed: String = beverage ?: "맥주"
    println(beverageServed)
}