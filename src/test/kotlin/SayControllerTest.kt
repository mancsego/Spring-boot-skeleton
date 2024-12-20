import com.mancsego.docker_debug_hot_reload.controller.SayController
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class SayControllerTest {
    @Test
    fun testSay() {
        val controller = SayController()

        assertEquals("Hello boing!", controller.say(what = "boing"))
        assertEquals("Hello!", controller.say(""))
    }
}