private const val FOO = "foo" // 1. constant at the top level

class Constant {

    fun testFunction() {
        println(DEFAULT_NAME)
    }

    companion object {
        const val SIZE = 100 // 2. constants in a companion object
        private const val DEFAULT_NAME = "John"
    }

}

object ConstantObject {
    const val SIZE = 50 // 3. constants in a named object
    private const val DEFAULT_NAME = "Lucy"
}

fun main() {
    println(FOO)

    println(Constant.SIZE)
//    println(Constant.DEFAULT_NAME) // is private
    println(Constant().testFunction())

    println(ConstantObject.SIZE)
//    println(ConstantObject.DEFAULT_NAME) // is private
}
