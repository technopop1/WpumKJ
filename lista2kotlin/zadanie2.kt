fun convertFromRoman(s: String): Int {
    var arStr = mapOf<String, Int>( "CM" to 900, "M" to 1000 , "CD" to 400, "D" to 500, "XC" to 90, "C" to 100, "XL" to 40, "L" to 50, "IX" to 9, "X" to 10, "IV" to 4, "V" to 5, "I" to 1 )
    var StringToConvert : String = s
    var result : Int = 0
    var counter : Int = 0   // count amount of specific character in string
    
    for (i in arStr){
        while (StringToConvert.contains(i.key)){
            result += i.value
            val x : Int = StringToConvert.indexOf(i.key)    // closest index of took character(s)
            if ( ++counter >= 4 || ( x+i.key.length < StringToConvert.length && arStr.get(StringToConvert.get(x+i.key.length).toString())!! > i.value ) 
             || ( x-1 >= 0 && arStr.get(StringToConvert.get(x-1).toString())!! < i.value ))
            {
                println("Wrong sequence of Roman characters " + s)
                return -1
            }
            StringToConvert = StringToConvert.substring(0, x) + StringToConvert.substring(x + i.key.length) // remove checked character from string
        }
        counter = 0
    }
    return result
}

fun main() {
    println(convertFromRoman("III"))
    println(convertFromRoman("V"))
    println(convertFromRoman("XCVII"))
    println(convertFromRoman("CDXXXII"))
    println(convertFromRoman("CCCXXXIII"))
    println(convertFromRoman("DCCXCIX"))
    println(convertFromRoman("MDLXXVIII"))
    println(convertFromRoman("CDC"))

    println(convertFromRoman("XXXXXX"))
    println(convertFromRoman("VX"))
    println(convertFromRoman("XD"))
    println(convertFromRoman("CCD"))
    println(convertFromRoman("MCDM"))
}