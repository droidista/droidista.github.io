package posts.y2023.may

import posts.Post
import java.time.ZoneId
import java.time.ZonedDateTime

fun buildGoodOldDaysOfSocialMedia09May2023() = Post(
    title = "A lookback to the good old days of social media!",
    shortDescription = "My life in the fediverse is too good and brought back memories of the good old days I had in the most notorious social media platforms that now rules the internet.",
    imagePath = "",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(
        2023, 5, 9,
        0, 5, 0, 0,
        ZoneId.of("GMT+05:30")
    ).toInstant().toEpochMilli(),
    slug = "good-old-days-of-social-media"
) {
    p(
        "My life in the fediverse is too good and brought back memories of the good old days I had in the most " +
                "notorious social media platforms that now rules the internet. Back in 2010, I started my first step in " +
                "social media through Twitter. The era in my life I made friendships from different nationalities, " +
                "culture, ethnicities. The window opened up and showed the amazing diversity of the world, with a " +
                "mediocre 2G internet connection."
    )
    p(
        "The interesting fact of the fediverse is, they are not just Mastodon, they are not just pixelfed " +
                "or diaspora, they are a wide range of software platforms conforming to an open, community " +
                "maintained protocol ActivityPub that can clearly talks and understand each other which enriches " +
                "the diversity of the open web, just like our world."
    )
    p(
        "The days in Mastodon, I felt so good to be there. The freedom of instances, the freedom to run " +
                "an instance, well defined boundaries and terms that works for everyone. It brings back the " +
                "good old vibes of Twitter, as I can see different amazing personalities, cultural diversities" +
                " and much more."
    )
    p(
        "The Pixelfed is giving me the good old vibes of pre-Facebook era of Instagram. Instagram was simple, " +
                "silent, peaceful and funny photo sharing app. Initially Instagram allows only photos of 1:1 " +
                "aspect ratio, but I really liked it."
    )
    p(
        "I remember my good old friends in the platform. They always posted nice photos, likes good photos, " +
                "appreciates and support each other to keep their spirit up. Most of them left the platform after " +
                "the acquisition and the subsequent cultural changes in the platform."
    )
    p(
        "I really feel Instagram and Twitter became evil after they became fully or part of " +
                "big \$\$\$ corp. They are not anymore the peaceful platforms. They are filled with junk " +
                "of noises, ads, hypes, politics, hate and triggering trash. The culture has changed, " +
                "most people followed the culture, a few left."
    )
    p(
        "I stopped my activities in Instagram and Twitter as they never showed me the signs " +
                "of their return to their good old past. Now I am fully in the Fediverse. I found " +
                "my patterns of social media has completely changed. I found myself spending lesser " +
                "time in social media, without missing the news and fun I actually need. That is " +
                "much more efficient than wasting time scrolling all over the unwanted stuffs and " +
                "rooting for the wanted stuffs. I feel more relaxed."
    )
}