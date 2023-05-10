package components

import com.katalyst.dom.BodyContext

fun BodyContext.includeSiteFooter() {
    footer {
        text("&copy;2023 Anand Bose. All rights reserved.")
        br()
        a(href = "https://github.com/droidista", text = "GitHub")
        text(" ")
        a(href = "https://gitlab.com/anandbose", text = "Gitlab")
        text(" ")
        a(
            href = "https://mastodon.social/@anandbose",
            text = "Mastodon",
            customAttributes = mapOf("rel" to "me"),
        )
        text(" ")
        a(
            href = "https://pixelfed.social/@anandbose",
            text = "Pixelfed",
            customAttributes = mapOf("rel" to "me"),
        )
    }
}