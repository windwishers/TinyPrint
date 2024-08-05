package fail.toepic.tinyprint

fun main() {
    basicType()
    objectType()
    collectionAndMap()
    enumAndTuple()
    useCase()
}

private fun basicType() {
    println("----------- basic type --")
    with(TinyPrintable) {
        print("null    : "); println(monoPrint(null))
        print("int     : "); println(monoPrint(1))
        print("int max : "); println(monoPrint(Int.MAX_VALUE))
        print("long    : "); println(monoPrint(1L))
        print("double  : "); println(monoPrint(1.1))
        print("double2 : "); println(monoPrint(1 / 3.0))
        print("uInt    : "); println(monoPrint(1.toUInt()))
        print("uLong   : "); println(monoPrint(1.toULong()))
        print("byte    : "); println(monoPrint(1.toByte()))
        print("char    : "); println(monoPrint('c'))
        print("String  : "); println(monoPrint("String"))

    }
    println("----------- basic type --")
}

object ObjectTest

class CompanionObject {
    companion object
}

class NamedCompanionObject {
    companion object CTO
}

private fun objectType() {
    println("---------- object type --")
    with(TinyPrintable) {
        print("          object ObjectTest     : "); println(monoPrint(ObjectTest))
        print("companion object                : "); println(monoPrint(CompanionObject))
        print("companion object CTO            : "); println(monoPrint(NamedCompanionObject))
    }
    println("---------- object type --")
}



private fun collectionAndMap() {
    println("---------- collectionAndMap --")
    with(TinyPrintable) {
        print("arrayOf(1,2,3,4)                : "); println(monoPrint(arrayOf(1,2,3,4)))
        print("listOf(1,2,3,4)                 : "); println(monoPrint(listOf(1,2,3,4)))
        print("setOf(1,2,3,4)                  : "); println(monoPrint(setOf(1,2,3,4)))
        print("mutableSetOf(1,2,3,4)           : "); println(monoPrint(mutableSetOf(1,2,3,4)))
        print("listOf(1,2,3,4).toTypedArray()  : "); println(monoPrint(listOf(1,2,3,4).toTypedArray()))
        print("mapOf(1 to 2,3 to 4)            : "); println(monoPrint(mapOf(1 to 2,3 to 4)))
        print("COMPLEX     : "); println(monoPrint(mapOf(
        1 to listOf(1,2,3,4),3 to mapOf(1 to "a",2 to setOf("C","c","c"))
        )))
    }
    println("---------- collectionAndMap --")
}

@Suppress("unused")
enum class ENUM{
    TEST,ENUM2,ENUM3, ENUM4
}
@Suppress("unused")
enum class ENUMCompanion{
    TEST;

    companion object
}

private fun enumAndTuple() {
    println("---------- enumAndTuple --")
    with(TinyPrintable) {
        print("ENUM.TEST       : "); println(monoPrint(ENUM.TEST))
        print("ENUM.entries    : "); println(monoPrint(ENUM.entries))
        print("ENUMCompanion   : "); println(monoPrint(ENUMCompanion))
        println()
        print("\"String\" to 1234   : "); println(monoPrint("String" to 1234))
        print("Triple(1,2,3)      : "); println(monoPrint(Triple(1,2,3)))
    }
    println("---------- enumAndTuple --")
}


class NoParameterClass : TinyPrint

@Suppress("unused", "UNUSED_PARAMETER")
class ParameterClass(val vl : Int, p : String, var vr : Double, val f : ()->Unit) : TinyPrint

class ParameterClass2(val vl : Int, p : String, var vr : Double) : TinyPrint


class ComplexClass(val vl : Int, val pc : ParameterClass2) : TinyPrint

private fun useCase() {
    println("---------- useCase --")

    print("NoParameterClass()       : "); println(NoParameterClass())
    print("NoParameterClass().print : "); println(NoParameterClass().print)
    println()
//    print(""); println(ParameterClass(vl = 1,p = "", vr = 1.0, f = {}).print)  // TODO Lambda Support Require.
    print("ParameterClass2(vl = 1,p = \"\", vr = 1.0).print   : "); println(ParameterClass2(vl = 1,p = "", vr = 1.0).print)

    println()
    println()

    print("class ComplexClass(val vl : Int, val pc : ParameterClass2) : TinyPrint    : "); println(ComplexClass(vl = 1,pc = ParameterClass2(1,"",34.0)).print)

    println("---------- useCase --")
}