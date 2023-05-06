package pages

import com.katalyst.dom.document
import com.katalyst.environment.Environment
import com.katalyst.extension.formattedTime
import components.includeSiteFooter
import components.includeSiteHead
import components.includeSiteHeader
import posts.getAllPosts
import java.time.ZoneId
import java.time.format.FormatStyle

suspend fun buildIndexPage(environment: Environment) {
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
                    getAllPosts()
                        .sortedByDescending { it.publishedDateEpochMillis }
                        .take(4)
                        .forEach { post ->
                            article {
                                h2(customAttributes = mapOf("style" to "margin-bottom: 0;")) {
                                    a(href = post.getRelativePath(environment), text = post.title)
                                }
                                formattedTime(
                                    className = "article-date",
                                    epochMillis = post.publishedDateEpochMillis,
                                    dateFormat = FormatStyle.FULL,
                                    timeFormat = null,
                                    timeZoneId = ZoneId.of("GMT+05:30"),
                                )
                                p(post.shortDescription)
                            }
                        }
                }
                includeSiteFooter()
            }
        }
    }
    indexPage.recursivelyResolveDeferredNodes()
    indexPage.writeToFile("/index.html")
}