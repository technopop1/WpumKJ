
fun zadanie1( tab : List<Number>) : List<Double>{
    var result : MutableList<Double> = mutableListOf()  
    for ( i in 0..(tab.size-1)){
        if (i%2 == 1 && i > 0) result.add(tab[i].toDouble()*tab[i].toDouble())
    }
    return result
}

fun zadanie2(tab : List<String>) : List<List<String>> {
    var result : MutableList<MutableList<String>> = mutableListOf()
    // var tmpStr : String = ""
    tab.forEach {
        // tmpStr += it[0]
        val str = it
        if ( !result.contains(mutableListOf(str[0].toString()))){
            result += mutableListOf(str[0].toString())
        }
    }
    // tmpStr = tmpStr.toList().distinct().sorted().joinToString("")
    result.sortWith( Comparator<MutableList<String>> { i, j ->
        when {  i[0] > j[0] -> 1
                else -> -1  }} )
    var newTab = tab.filter { it.length % 2 == 0}
    newTab.forEach {
        for ( i in 0..(result.size-1)) {
            if ( it[0].toString() == result[i][0] )
                result[i].add(it)
        }
    }
    return result
}

fun permutations( vararg args: Int) : List<List<Int>> {
    var result : MutableList<MutableList<Int>> = mutableListOf()
    fun permut ( ArgsList : MutableList<Int>, argNum : Int = 0 ) {
        var tmpArgsList : MutableList<Int> = ArgsList
        if (argNum == ArgsList.size-1) {
            val t : MutableList<Int> = mutableListOf()
            ArgsList.forEach{  t.add(it) }      // tworze nową listę (samo przypisanie ArgsList do RESULT zmieniało każdą listę w RESULT??)
            result.add(t)
        }
        else {
            for( i in 0..(ArgsList.size-1)){
                tmpArgsList[i] = tmpArgsList[argNum].also { tmpArgsList[argNum] = tmpArgsList[i] }
                permut(tmpArgsList, argNum + 1)
                tmpArgsList[i] = tmpArgsList[argNum].also { tmpArgsList[argNum] = tmpArgsList[i] }
            }
        }
    }
    permut(args.toMutableList())
    result = result.distinct().toMutableList()
    println("amount of permutation = " + result.size)
    return result
}

fun check( N : Int, pre : List<Int>) : Int {
    
    // listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)
    for ( findex in 0..(pre.size-N-1) ) {
        var tmpPre : MutableList<Int> = mutableListOf()
        for ( i in findex..(findex+N-1) )
            tmpPre.add(pre[i])
        var preContains : Boolean = false
        for ( c1 in 0..(tmpPre.size-1) ) {

            var tmpTmpPre  : MutableList<Int> = mutableListOf()
            tmpTmpPre.addAll(tmpPre)
            tmpTmpPre.remove(tmpPre[c1])
            if ( tmpTmpPre.contains( pre[findex+N]-tmpPre[c1]) ) {
                preContains = true
            }
        }
        if ( !preContains ) { 
            print("First invalid number of list = ")
            return pre[findex+N] 
        }
    }

    return -1
}

fun main() {

    // ZADANIE 1
    println("ZADANIE 1 ")
    println(zadanie1(listOf(1, 2, 3.5, 5, -6, 1, 1)))
    println("\n")

    // ZADANIE 2
    println("ZADANIE 2 ")
    println(zadanie2(listOf("cherry", "blueberry", "citrus", "apple", "apricot", "banana", "coconut")))
    println("\n")
    
    // ZADANIE 3
    println("ZADANIE 3 ")
    println(permutations(1, 2, 3))
    println("\n")

    // ZADANIE 4
    println("ZADANIE 4 ")
    println(check(5, listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)))
    println("\n")
    
}