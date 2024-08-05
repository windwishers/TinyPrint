package fail.toepic.tinyprint

import kotlin.reflect.KFunction
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

interface TinyPrint {
    val print : String
        get() = TinyPrintable.tinyPrint(this)
}

object TinyPrintable{

    //TODO public 이외에도 접근이 가능한 케이스가 있는지 검토 필요.
    private fun <T> ifAccessible(f : KFunction<T>) : Boolean = f.visibility == KVisibility.PUBLIC


    fun tinyPrint(any : Any?) : String {

        if(any == null) return "null"
        val clazz = any::class
        val constructor = kotlin.runCatching {
            clazz.primaryConstructor?.takeIf(::ifAccessible) ?: clazz.constructors.filter(::ifAccessible)
                .maxBy { it.parameters.count() }
        }.getOrNull()
        return if(constructor != null){
            val cp = constructor.parameters
            kotlin.runCatching {
                if(cp.isEmpty()){
                    clazz.simpleName
                }else{
                    val map = clazz.memberProperties.associateBy{ it.name}
                    cp.mapNotNull { pra ->
                        map[pra.name]?.let { pro -> pra to pro }
                    }.joinToString(prefix = clazz.simpleName + "{", separator = ", ", postfix = "}"){(pra,pro)->
                        pra.name + "=" + monoPrint(pro.call(any))
                    }
                }
            }.getOrNull() ?: ""
        }else{
            any.toString()
        }
    }

    fun monoPrint(any: Any?): String {
        return when (any) {
            null -> "null"
            is TinyPrint -> any.print
            is String -> any
            is Collection<*> -> {
                any.joinToString(prefix = "[", separator = ", ", postfix = "]", transform = ::monoPrint)
            }

            is Array<*> -> {
                any.joinToString(prefix = "[", separator = ", ", postfix = "]", transform = ::monoPrint)
            }

            is Map<*, *> -> {
                any.entries.joinToString(prefix = "{", separator = ", ", postfix = "}", transform = ::monoPrint)
            }

            is Map.Entry<*, *> -> monoPrint(any.key) + "=" + monoPrint(any.value)
            is Pair<*, *> -> "" + monoPrint(any.first) + " to " + monoPrint(any.second)
            is Triple<*, *, *> -> "{" + monoPrint(any.first) + "/" + monoPrint(any.second) + "/" + monoPrint(any.third) + "}"
            else -> {
                val clazz = any::class
                if (clazz.isCompanion) {
                    clazz.qualifiedName?.split(".")?.takeLast(2)?.joinToString("._")
                        ?.replace("._Companion", ".__") ?: ""
                } else if (clazz.objectInstance != null) {
                    clazz.simpleName ?: ""
                } else {
                    tinyPrint(any)
                }
            }
        }
    }
}

@Suppress("unused")
fun TinyPrint.tinyPrint() : String = TinyPrintable.tinyPrint(this)

@Suppress("unused", "UnusedReceiverParameter")
fun TinyPrint.mono(any : Any?) : String = TinyPrintable.monoPrint(any)