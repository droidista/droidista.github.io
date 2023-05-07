package posts

import posts.y2018.august.buildGrpcVsJson06Aug2018
import posts.y2020.may.buildAndroidKeystoreRotation08May2020

fun getAllPosts() = listOf(
    buildAndroidKeystoreRotation08May2020(),
    buildGrpcVsJson06Aug2018(),
)