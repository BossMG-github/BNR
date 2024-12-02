class Weapon(val name: String)
class Player{
    var weapon: Weapon? = Weapon("Ebony Kris")

    fun printWeaponName(){
        /*if(weapon != null){
            println(weapon.name) // 경합 상태가 일어날 수 있기 때문에 컴파일러가 에러 발생시킴 즉, 스마트 캐스팅 불가
        }*/
        weapon?.also {
            println(it.name)
        }
    }
}

fun main() {
    Player().printWeaponName()
}