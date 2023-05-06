package pages

import com.katalyst.dom.document
import com.katalyst.environment.Environment
import com.katalyst.extension.formattedTime
import components.includeSiteFooter
import components.includeSiteHead
import components.includeSiteHeader
import posts.getAllPosts
import java.time.Instant
import java.time.ZoneId
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
                        Instant.ofEpochMilli(it.publishedDateEpochMillis)
                            .atZone(ZoneId.of("GMT+05:30"))
                            .year
                    }
                    years.sortedDescending().forEach { year ->
                        val allPostsOfYear = posts.filter {
                            Instant.ofEpochMilli(it.publishedDateEpochMillis)
                                .atZone(ZoneId.of("GMT+05:30"))
                                .year == year
                        }.sortedByDescending { it.publishedDateEpochMillis }
                        h2(text = year.toString())
                        ul {
                            allPostsOfYear.forEach { post ->
                                li {
                                    a(href = post.getRelativePath(environment), text = post.title)
                                    text(" ")
                                    formattedTime(
                                        epochMillis = post.publishedDateEpochMillis,
                                        timeZoneId = ZoneId.of("GMT+05:30"),
                                        pattern = "EEEE, dd MMMM",
                                        customAttributes = mapOf(
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