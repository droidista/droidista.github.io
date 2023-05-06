import com.katalyst.environment.Environment
import com.katalyst.environment.clean
import com.katalyst.environment.copyStaticAssets
import pages.buildAboutPage
import pages.buildArchivePage
import pages.buildError404Page
import pages.buildIndexPage
import posts.getAllPosts
import java.io.File

suspend fun main() {
    val workingDirectory = File(System.getProperty("user.dir"))
    val environment = Environment(
        baseDir = File(workingDirectory, "static"),
        outputDir = File(workingDirectory, "dist"),
    )
    clean(environment)
    copyStaticAssets(environment)
    buildIndexPage(environment)
    buildArchivePage(environment)
    buildAboutPage(environment)
    buildError404Page(environment)
    getAllPosts().forEach { post ->
        post.render(environment)
    }
}