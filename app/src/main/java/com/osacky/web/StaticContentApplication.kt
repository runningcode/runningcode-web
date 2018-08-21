package com.osacky.web

import io.ktor.application.*
import io.ktor.content.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.ContentType
import io.ktor.http.content.*
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import kotlinx.html.*
import java.io.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    routing {
        routeFilesystem()
        routeResources()
    }
}

fun Route.routeFilesystem() {
    get("/") {
        call.respondHtml {
            head {
                title { +"Under construction" }
            }
            body {
                header {
                    h1 { +"This site is under construction" }
                }
            }
        }
    }
    get("foo") {
        call.respondHtml {
            head {
                title { +"Nelson Osacky" }
                styleLink("/static/styles.css")
            }
            body {
                header {
                    h1 { +"Nelson Osacky" }
                    ul("list-inline") {
                        li { a(href = "/blog") {+"Blog"} }
                        li { a(href = "/resume") {+"Resume"} }
                        li { a(href = "/talks") {+"Talks"} }
                        li { a(href = "/github") {+"Github"} }
                    }
                }
            }
        }
    }
    static("static") {
        // When running under IDEA make sure that working directory is set to this sample's project folder
        staticRootFolder = File("files")
        files("css")
        files("js")
        file("image.png")
        file("random.txt", "image.png")
        default("index.html")
    }
    static("custom") {
        staticRootFolder = File("/tmp") // Establishes a root folder
        files("public") // For this to work, make sure you have /tmp/public on your system
        static("themes") {
            // services /custom/themes
            files("data")
        }
    }
}

fun Route.routeResources() {
    get("/resources") {
        call.respondHtml {
            head {
                title { +"Ktor: static-content" }
                styleLink("/static/styles.css")
            }
            body {
                p {
                    +"Hello from Ktor static content served from resources, if the background is cornflowerblue"
                }
            }
        }
    }

    static("static-resources") {
        resources("css")
    }
}