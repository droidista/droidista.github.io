package pages

import com.katalyst.dom.document
import com.katalyst.environment.Environment
import components.includeError404Avatar
import components.includeSiteFooter
import components.includeSiteHead
import components.includeSiteHeader

suspend fun buildError404Page(environment: Environment) {
    val indexPage = document(environment) {
        html {
            includeSiteHead(
                title = "Anand's Blog",
                description = "Learner and content creator focussed on Android, Kotlin, Jetpack compose, Linux and security.",
                imagePath = "/about.jpg"
            )
            body {
                includeSiteHeader()
                main {
                    includeError404Avatar(
                        className = "display-dark-only",
                        color = "#FFFFFF"
                    )
                    includeError404Avatar(
                        className = "display-light-only",
                        color = "#000000"
                    )
                    h2("Page Not Found")
                    p("Sorry, the page you are looking for doesn't exist or has been moved.")
                }
                includeSiteFooter()
            }
        }
    }
    indexPage.recursivelyResolveDeferredNodes()
    indexPage.writeToFile("/404.html")
}