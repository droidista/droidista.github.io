package pages

import com.katalyst.dom.document
import com.katalyst.environment.Environment
import components.includeSiteFooter
import components.includeSiteHead
import components.includeSiteHeader
import posts.getAllPosts
import posts.getAuthorTimeZoneOffset
import java.time.Instant
import java.time.format.DateTimeFormatter
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
                        .sortedByDescending { it.publishedDateEpochSeconds }
                        .take(4)
                        .forEach { post ->
                            article {
                                h2(customAttributes = mapOf("style" to "margin-bottom: 0;")) {
                                    a(href = post.getRelativePath(environment), text = post.title)
                                }
                                val zonedTime = Instant.ofEpochSecond(post.publishedDateEpochSeconds)
                                    .atZone(getAuthorTimeZoneOffset())
                                val isoFormattedTime = zonedTime.format(DateTimeFormatter.ISO_DATE_TIME)
                                val humanFormattedTime =
                                    zonedTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
                                textContainer(
                                    tag = "time",
                                    className = "article-date",
                                    text = humanFormattedTime,
                                    customAttributes = mapOf("datetime" to isoFormattedTime),
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