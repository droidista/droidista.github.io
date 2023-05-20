package pages

import com.katalyst.dom.BodyContext
import com.katalyst.dom.document
import com.katalyst.environment.Environment
import com.katalyst.responsiveimage.ResponsiveImageFormat
import com.katalyst.responsiveimage.responsiveImage
import components.includeSiteFooter
import components.includeSiteHead
import components.includeSiteHeader

private data class ContactInfo(
    val title: String,
    val url: String,
    val hyperlink: BodyContext.() -> Unit
)

suspend fun buildAboutPage(environment: Environment) {
    val contact = listOf(
        ContactInfo(
            title = "Mastodon",
            url = "https://mastodon.social/@anandbose",
            hyperlink = {
                text("@anandbose@mastodon.social")
            }
        ),
        ContactInfo(
            title = "Pixelfed",
            url = "https://pixelfed.social/anandbose",
            hyperlink = {
                text("@anandbose@pixelfed.social")
            }
        ),
        ContactInfo(
            title = "GitHub",
            url = "https://github.com/droidista",
            hyperlink = {
                text("@droidista")
            }
        ),
        ContactInfo(
            title = "Gitlab",
            url = "https://gitlab.com/anandbose",
            hyperlink = {
                text("@anandbose")
            }
        ),
        ContactInfo(
            title = "Email",
            url = "mailto:anandbose16@gmail.com",
            hyperlink = {
                text("anandbose16@gmail.com")
            }
        ),
        ContactInfo(
            title = "GPG Key",
            url = "/gpg-key.txt",
            hyperlink = {
                text("Download")
            }
        ),
    )
    val aboutHtml = document(environment) {
        html {
            includeSiteHead(
                title = "Anand's Blog",
                description = "Learner and content creator focussed on Android, Kotlin, Jetpack compose, Linux and security.",
                imagePath = "/about.jpg"
            )
            body {
                includeSiteHeader()
                main {
                    article {
                        responsiveImage(
                            src = "/about.png",
                            width = 192,
                            height = 192,
                            scaleModes = listOf(1f, 2f, 3f),
                            imageFormats = listOf(
                                ResponsiveImageFormat.AVIF,
                                ResponsiveImageFormat.PNG,
                                ResponsiveImageFormat.WEBP,
                            ),
                            customAttributes = mapOf(
                                "style" to "display: block; margin: 3em auto 1em auto;"
                            ),
                        )
                        h2("Anand Bose", customAttributes = mapOf("style" to "text-align: center;"))
                        h2("Hello! \uD83D\uDC4B")
                        p {
                            text("I'm Anand Bose. I used to do ")
                            a(
                                href = "https://twitter.com/hashtag/AndroidDev",
                                text = "#AndroidDev",
                                customAttributes = mapOf("target" to "_blank"),
                            )
                            text(" since Android 2.3 (Gingerbread). Kotlin enthusiast since the 1.0 of the language. ")
                            text("I love the Android community and so glad to be the part of it. " +
                                    "I am currently working as lead of Android team at ")
                            a(
                                href = "https://appstation.in",
                                text = "AppStation",
                                customAttributes = mapOf("target" to "_blank"),
                            )
                            text(".")
                        }
                        p {
                            text("A few things I enjoy: ")
                            ul {
                                li("Discovering more Android stuffs (Jetpack Compose)")
                                li("Writing and talking tech")
                                li("Having fun times with family")
                                li("Photography")
                                li("Cycling")
                            }
                        }
                        h3("Contact")
                        table {
                            tbody {
                                contact.forEach {
                                    tr {
                                        td(it.title)
                                        td {
                                            a(
                                                className = "value",
                                                href = it.url,
                                                customAttributes = mapOf("target" to "_blank"),
                                                block = it.hyperlink,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                includeSiteFooter()
            }
        }
    }
    aboutHtml.recursivelyResolveDeferredNodes()
    aboutHtml.writeToFile("/about.html")
}