
import br.com.ktortest.config.intiDB
import br.com.ktortest.dao.AddressTable
import br.com.ktortest.dao.UserTable
import br.com.ktortest.model.User
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.DateFormat

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule(){
    intiDB()
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
    routing {
        root()
        user()
    }
}

// Extracted route
fun Routing.root() {
    get("/") {
        call.respondText("Hello World!")
    }
}

fun Routing.user() {
    get("/user/all") {
        val resultList= mutableListOf<User>()
        transaction {
            val result = UserTable.selectAll().map {
                println("User: ${it[UserTable.id]} - ${it[UserTable.name]}")
                resultList.add(User(it[UserTable.id].value,it[UserTable.name],it[UserTable.lastName]))
            }
        }
        call.respond(resultList)
    }

    get("/user/all/address") {
        val resultList= mutableListOf<User>()
        transaction {
            (UserTable innerJoin AddressTable).selectAll().map {
                println("User: ${it[UserTable.id]} - ${it[UserTable.name]}")
                resultList.add(User(it[UserTable.id].value,it[UserTable.name],it[UserTable.lastName]))
            }
        }
        call.respond(resultList)
    }
}