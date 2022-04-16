import java.util.Arrays
// zadanie 4
fun mmissingNumber(tab: IntArray): Int{
    for ( i in 0..tab.size ){
        if ( i !in tab ){
            return i
        }
    }
    return tab.last()+1
}

fun main(){
    val tab1 : IntArray = intArrayOf(0, 4, 2, 1, 3)
    val tab2 : IntArray = intArrayOf(0, 1, 4, 2, 5)
    val tab3 : IntArray = intArrayOf(1, 2, 3, 4, 5)
    println("W tablicy " + Arrays.toString(tab1) + " brakuje liczby " + mmissingNumber(tab1))
    println("W tablicy " + Arrays.toString(tab2) + " brakuje liczby " + mmissingNumber(tab2))
    println("W tablicy " + Arrays.toString(tab3) + " brakuje liczby " + mmissingNumber(tab3))
}