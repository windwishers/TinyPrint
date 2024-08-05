# TinyPrint


## how to use

1. copy TinyPrint.kt to your Project
2. add TinyPrint interface
    ```kotlin
    class ParameterClass2(val vl : Int, p : String, var vr : Double) : TinyPrint
    ```
3. use `<<YourClass>>`.print
    ```kotlin
    println(ParameterClass2(vl = 1,p = "", vr = 1.0).print) // ParameterClass2{vl=1, vr=1.0}
    ```
   

## more example
show Main.kt [link]()