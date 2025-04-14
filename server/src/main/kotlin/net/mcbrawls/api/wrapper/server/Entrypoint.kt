package net.mcbrawls.api.wrapper.server

import io.ktor.server.application.Application
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

suspend fun main() {
  embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
    .startSuspend(wait = true)
}

fun Application.module() {
  configureRouting()
}
