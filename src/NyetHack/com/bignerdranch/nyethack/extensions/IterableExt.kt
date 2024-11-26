package NyetHack.com.bignerdranch.nyethack.extensions

// 파일의 이름은 확장이 되는 수신자 타입 이름에 Ext를 붙이자.

fun <T> Iterable<T>.random() : T = this.shuffled().first()
