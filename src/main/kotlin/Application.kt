
import br.com.ktortest.config.intiDB
import br.com.ktortest.config.userAppModule
import br.com.ktortest.route.user
import br.com.ktortest.service.UserService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext.startKoin
import java.text.DateFormat

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule(){
    // Start Koin
    startKoin(listOf(userAppModule))
    intiDB()
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
    // in v1.0 we are going to inject inside Route, for now only in application
    val service: UserService by inject()

    routing {
        root()
        user(service)
    }
}

// Extracted route
fun Routing.root() {
    get("/") {
        call.respondText("Hello World!")
    }
}

