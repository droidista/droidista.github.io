package components

import com.katalyst.dom.BodyContext
import com.katalyst.dom.Node

fun BodyContext.includeSiteNavigation() {
    nav(className = "site-nav") {
        a(className = "nav-menu-item", href = "/", text = "Home")
        text(" ")
        a(className = "nav-menu-item", href = "/archive.html", text = "Archive")
        text(" ")
        a(className = "nav-menu-item", href = "/about.html", text = "About")
        text(" ")
        a(
            id = "btn-switch-dark",
            className = "nav-menu-item-toggle requires-js",
            href = "javascript:switchToDarkMode();",
            text = "&ThickSpace;",
            customAttributes = mapOf(
                "aria-label" to "Switch to dark mode"
            ),
        )
        //text("\n")
        text(" ")
        a(
            id = "btn-switch-light",
            className = "nav-menu-item-toggle requires-js",
            href = "javascript:switchToLightMode();",
            text = "&ThickSpace;",
            customAttributes = mapOf(
                "aria-label" to "Switch to dark mode"
            )
        )
    }
}

fun BodyContext.includeSiteHeader() {
    header {
        h1("Anand's Blog")
        includeSiteNavigation()
    }
}
