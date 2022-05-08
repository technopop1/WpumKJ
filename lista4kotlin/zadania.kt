fun formatResult(name: String, n: Int, f: (Int) -> Int): String {
    val msg = "The %s of %d is %d."
    return msg.format(name, n, f(n))
}

fun <A> isSorted(aa : List<A>, order : (A, A) -> Boolean) : Boolean {

    for ( i in 0..(aa.size-2))
        if (!order(aa[i], aa[i+1])) return false

    return true
}

fun suma(a : Array<Int> ) : Int {
    var sum : Int
    // a.filter { if(it>=0) sum+=it; it >= 0} //// samym filtrem
    sum = a.filter{ it > 0}.reduce { i, j ->  i + j }
    return sum
}

fun <T> countElements(a : Array<Array<T>> ) : Map<T, Int> {
    var result : MutableMap<T, Int> = mutableMapOf()
    a.forEach { 
        it.forEach { 
            if(result.containsKey(it))
                result += Pair(it, result.get(it)!!+1)
            else
                result += mapOf(it to 1)
        }
    }
    return result
}

fun main() {
    // zadanie 1
    println( " \nZADANIE 1 " )
    val foo : (String, Int) -> String = { s:String, i:Int -> s.repeat(i)}
    println(foo("a", 3)+"\n")
    println(foo("ebe ", 3)+"\n")

    // zadanie 2
    println( " ZADANIE 2 " )
    val f : (String) -> String = { it + "!" }
    println(f("co?")+"\n")

    // zadanie 3
    println( " ZADANIE 3 " )
    tailrec fun fib(i : Int, a : Int = 0, b : Int = 1) : Int {
        if ( i == 0 ) return a;
        else if ( i == 1 ) return b;
        else return fib(i-1, b, a + b)
    }
    println( fib(5) )
    println( fib(7) )
    println( fib(11) )
    println( fib(19) )
    println()
    
    // zadanie 4
    println( " ZADANIE 4 " )
    fun log2(i: Int) : Int {
        return (Math.log(i.toDouble())/Math.log(2.0)).toInt()
    }
    println()
    println( log2(4) )
    println( log2(8) )
    println( log2(32) )
    println( log2(1024) )
    println()

    // zadanie 5
    println( " ZADANIE 5 " )
    println(formatResult("Fibonacci", 7) { fib(7) })
    println(formatResult("log_2", 32, ::log2 ))
    println()

    /// zadanie 6
    println( " ZADANIE 6 " )
    fun <T> List<T>.tail() : List<T> = drop(1)
    fun <T> List<T>.head() : T = first()

    val zad5a : List<Int> = listOf(5, 4, 3, 2, 1)
    val zad5b : List<String> = listOf("pierwszy", "b", "c", "d", "e")
    println(zad5a.tail())
    println(zad5b.head())
    println()
    
    /// zadanie 7
    println( " ZADANIE 7 " )
    println( isSorted(listOf(1, 2, 3, 4), {i: Int, j: Int -> i < j}) )
    println( isSorted(listOf(1, 1, 2, 1), {i: Int, j: Int -> i == j}) )
    println( isSorted(listOf(1, 2, 1, 4), {i: Int, j: Int -> i < j}) )
    println( isSorted(listOf(4, 1, 3, 2), {i: Int, j: Int -> i < j}) )
    println( isSorted(listOf("ahyyhh", "bkjn", "cnn", "duu"), {i: String, j: String -> i.first() < j.first()}) )
    println()

    /// zadanie 8
    println( " ZADANIE 8 " )
    println(suma(arrayOf(1, -4, 12, 0, -3, 29, -150)))
    println()

    /// zadanie 9
    println( " ZADANIE 9 " )
    println(countElements(arrayOf(
        arrayOf("a", "b", "c"),
        arrayOf("c", "d", "f"),
        arrayOf("d", "f", "g")
      )))
    println(countElements(arrayOf(
        arrayOf(7, 7, 2),
        arrayOf(5, 2, 5),
        arrayOf(9, 2, 1),
        arrayOf(3, 3, 5)
    )))
    println(countElements(arrayOf(
        arrayOf(true, false, false),
        arrayOf(true, "g", false),
        arrayOf(false, null, "g")
    )))
    println()

}