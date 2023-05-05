package posts

import com.katalyst.dom.BodyContext
import com.katalyst.dom.document
import com.katalyst.environment.Environment
import components.includeSiteFooter
import components.includeSiteHead
import components.includeSiteHeader
import java.io.File
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

data class Post(
    val title: String,
    val shortDescription: String,
    val imagePath: String,
    val authorName: String,
    val publishedDateEpochSeconds: Long,
    val slug: String,
    val content: BodyContext.() -> Unit,
) {
    fun getTargetFile(environment: Environment): File {
        val postsDir = environment.ensurePostsDirectory()
        val date = Instant.ofEpochSecond(publishedDateEpochSeconds)
            .atOffset(getAuthorTimeZoneOffset())
        val yearDir = File(postsDir, String.format(Locale.US, "%04d", date.year))
        if (!yearDir.exists()) yearDir.mkdir()
        val monthDir = File(yearDir, String.format(Locale.US, "%02d", date.monthValue))
        if (!monthDir.exists()) monthDir.mkdir()
        val dayOfMonthDir = File(monthDir, String.format(Locale.US, "%02d", date.dayOfMonth))
        if (!dayOfMonthDir.exists()) dayOfMonthDir.mkdir()
        return File(dayOfMonthDir, String.format(Locale.US, "%s.html", slug))
    }

    fun getRelativePath(environment: Environment): String {
        return getTargetFile(environment).relativeTo(environment.outputDir).path
    }

    suspend fun render(environment: Environment) {
        val outputFile = getTargetFile(environment)
        if (outputFile.exists()) outputFile.delete()
        val document = document(environment) {
            html {
                includeSiteHead(title, shortDescription, imagePath)
                body {
                    includeSiteHeader()
                    main {
                        h1(text = title, customAttributes = mapOf("style" to "margin-bottom: 0;"))
                        val zonedTime = Instant.ofEpochSecond(publishedDateEpochSeconds)
                            .atZone(getAuthorTimeZoneOffset())
                        val isoFormattedTime = zonedTime.format(DateTimeFormatter.ISO_DATE_TIME)
                        val humanFormattedTime =
                            zonedTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
                        span(className = "article-date") {
                            text("Published ")
                            textContainer(
                                tag = "time",
                                text = humanFormattedTime,
                                customAttributes = mapOf("datetime" to isoFormattedTime),
                            )
                            text(" by ")
                            text(authorName)
                        }
                        content()
                    }
                    includeSiteFooter()
                }
            }
        }
        document.recursivelyResolveDeferredNodes()
        val relativePath = outputFile.relativeTo(environment.outputDir).path
        document.writeToFile(relativePath, isOverwriteAllowed = true)
    }
}

fun Environment.ensurePostsDirectory(): File {
    val postsDir = File(outputDir, "posts")
    if (!postsDir.exists()) {
        postsDir.mkdir()
    }
    return postsDir
}

fun getAllPosts() = listOf(
    testPost20230505,
    testPost20230504,
    testPost20230503,
    testPost20230502,
    testPost20230501,
    testPost20230429,
    testPost20230428,
)