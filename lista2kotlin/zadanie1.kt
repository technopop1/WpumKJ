fun convertToRoman(n: Int): String
{
    if (n <= 0) return "Cannot convert number " + n.toString()
    var toStr = mapOf<Int, String>(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L", 40 to "XL", 10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I" )
    var intToConvert : Int = n
    var result : String = ""
    for (i in toStr) { // for ( (key, value) in romStr )
        while( intToConvert - i.key >= 0 ) { // if able to substract, Roman number exist in result  
            intToConvert -= i.key
            result += i.value       
        }
    }
    result.reversed()
    return result
}

fun main()
{
    println(convertToRoman(5))
    println(convertToRoman(45))
    println(convertToRoman(53))
    println(convertToRoman(10))
    println(convertToRoman(19))
    println(convertToRoman(1578))
    println(convertToRoman(782))
    println(convertToRoman(799))
    println(convertToRoman(422))
    println(convertToRoman(0))
    println(convertToRoman(-555))
}