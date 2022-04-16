import java.math.BigInteger
fun isCyclic(i: String): Boolean{
    //if ( i[0] == '0' ) return false
    val numbersArray = i.split("")
    val number : BigInteger = i.toBigInteger()
    // println(i)
    if ( number.compareTo(0.toBigInteger()) <= 0) return false
    for ( multiplier in 2..(i.length) ){
        var multiple : String = (number * multiplier.toBigInteger()).toString()
        
        if (multiple.length < i.length) 
            multiple = "0" + multiple
        // println(multiple)
        if (multiple.length != i.length) return false
        for ( character in numbersArray) {
            val x : Int = multiple.indexOf(character)
            if ( !multiple.toString().contains(character.toString()) ) return false 
            multiple = multiple.substring(0, x) + multiple.substring(x + character.length)
        }
    }
    return true
}

fun main() {
    println(isCyclic("142857"))
    println(isCyclic("-142857"))
    println(isCyclic("076923"))
    println(isCyclic("0588235294117647"))
    println(isCyclic("0212765957446808510638297872340425531914893617"))
    println(isCyclic("02127659574468085106382978723404253191893617"))
}