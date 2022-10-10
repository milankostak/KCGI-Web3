class Functions {

    private val height = 0
    var name = ""

    fun printString(someString: String) {
        println(someString)
    }

    private fun getInfo(): String {
        return "height: $height, name: $name"
    }

    fun printInfo() {
        println(getInfo())
    }

    fun saveTextToFile(content: String, filename: String = "default_file.csv") {
        println("Saving '$content' into file '$filename'.")
        // ...
    }

}

fun main() {
    val a = Functions()
    a.name = "George"
    a.printInfo()
    a.printString("something")
    a.saveTextToFile("content")
    a.saveTextToFile("another content", "some_other_file.txt")
}
