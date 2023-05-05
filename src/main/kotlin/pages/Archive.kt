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

suspend fun buildArchivePage(environment: Environment) {
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
                    h2(text = "Archive")
                    p("Browse all posts by year of publishing.")
                    val posts = getAllPosts()
                    val years = mutableSetOf<Int>()
                    posts.mapTo(years) {
                        Instant.ofEpochSecond(it.publishedDateEpochSeconds)
                            .atOffset(getAuthorTimeZoneOffset())
                            .year
                    }
                    years.sortedDescending().forEach { year ->
                        val allPostsOfYear = posts.filter {
                            Instant.ofEpochSecond(it.publishedDateEpochSeconds)
                                .atOffset(getAuthorTimeZoneOffset())
                                .year == year
                        }.sortedByDescending { it.publishedDateEpochSeconds }
                        h2(text = year.toString())
                        ul {
                            allPostsOfYear.forEach { post ->
                                li {
                                    val zonedTime = Instant.ofEpochSecond(post.publishedDateEpochSeconds)
                                        .atZone(getAuthorTimeZoneOffset())
                                    val isoFormattedTime = zonedTime.format(DateTimeFormatter.ISO_DATE_TIME)
                                    val humanFormattedTime =
                                        zonedTime.format(DateTimeFormatter.ofPattern("MMM dd"))
                                    a(href = post.getRelativePath(environment), text = post.title)
                                    text(" ")
                                    textContainer(
                                        tag = "time",
                                        text = humanFormattedTime,
                                        customAttributes = mapOf(
                                            "datetime" to isoFormattedTime,
                                            "style" to "color: var(--secondary-text-color); font-size: 0.8em;"
                                        ),
                                    )
//                                    text(" ")
//                                    a(href = post.getRelativePath(environment), text = post.title)
                                }
                            }
                        }
                    }
                }
                includeSiteFooter()
            }
        }
    }
    indexPage.recursivelyResolveDeferredNodes()
    indexPage.writeToFile("/archive.html")
}