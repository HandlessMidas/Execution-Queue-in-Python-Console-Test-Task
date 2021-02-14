import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader

fun executeFile(filepath: String) {
    var counter = 0
    val counting = GlobalScope.launch {
        while (true) {
            counter++
            delay(1000)
            println("Running for $counter seconds")
        }
    }

    val process = try {
        Runtime.getRuntime().exec("$filepath -m timeit -r 10")
    } catch (e: Exception) {
        println("Error: wrong input!")
        return
    }

    val outputReader = BufferedReader(process.inputStream.reader())
    println(outputReader.readText())

    counting.cancel()
}

fun main() {
    println("Input path to python file:")

    val filepath = readLine()
    executeFile(filepath!!)
}