package posts

import java.time.ZoneId
import java.time.ZonedDateTime

val testPost20230505 = Post(
    title = "Test Post 01",
    shortDescription = "This is a test post 01",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 5, 5, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_01",
) {
    h2("Hello! 01")
    p("Welcome to my first post! 01")
}

val testPost20230504 = Post(
    title = "Test Post 02",
    shortDescription = "This is a test post 02",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 5, 4, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_02",
) {
    h2("Hello! 02")
    p("Welcome to my first post! 02")
}

val testPost20230503 = Post(
    title = "Test Post 03",
    shortDescription = "This is a test post 03",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 5, 3, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_03",
) {
    h2("Hello! 03")
    p("Welcome to my first post! 03")
}

val testPost20230502 = Post(
    title = "Test Post 04",
    shortDescription = "This is a test post 04",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 5, 2, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_04",
) {
    h2("Hello! 04")
    p("Welcome to my first post! 04")
}

val testPost20230501 = Post(
    title = "Test Post 05",
    shortDescription = "This is a test post 05",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 5, 1, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_05",
) {
    h2("Hello! 05")
    p("Welcome to my first post! 05")
}

val testPost20230430 = Post(
    title = "Test Post 06",
    shortDescription = "This is a test post 06",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 4, 30, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_06",
) {
    h2("Hello! 06")
    p("Welcome to my first post! 06")
}

val testPost20230429 = Post(
    title = "Test Post 07",
    shortDescription = "This is a test post 07",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 4, 29, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_07",
) {
    h2("Hello! 07")
    p("Welcome to my first post! 07")
}

val testPost20230428 = Post(
    title = "Test Post 08",
    shortDescription = "This is a test post 08",
    imagePath = "/about.png",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(2023, 4, 28, 22, 48, 0, 0, ZoneId.of("GMT+05:30")).toInstant()
        .toEpochMilli(),
    slug = "test_post_08",
) {
    h2("Hello! 08")
    p("Welcome to my first post! 08")
}