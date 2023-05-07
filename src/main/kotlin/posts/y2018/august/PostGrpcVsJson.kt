package posts.y2018.august

import com.katalyst.highlighter.kotlin.dom.kotlinCode
import posts.Post
import java.time.ZoneId
import java.time.ZonedDateTime

fun buildGrpcVsJson06Aug2018() = Post(
    title = "Bye bye JSON! Welcome Protocol Buffers!",
    shortDescription = "This is an introduction of Google's Protocol Buffers, a data serialization technology that is performant and efficient compared to traditional data serialization techniques like JSON.",
    imagePath = "",
    authorName = "Anand Bose",
    publishedDateEpochMillis = ZonedDateTime.of(
        2018, 8, 6,
        0, 0, 0, 0,
        ZoneId.of("GMT+05:30")
    ).toInstant().toEpochMilli(),
    slug = "bye-bye-json-welcome-protocol-buffers"
) {
    p("Developers are familiar working with REST services and its implementation in your client application. REST services are most simple method of data exchange between the client and server. Well, it’s real simple text based communication technique, and easier to learn and debug, and many tools like Postman, Insomnia also exists to play with REST endpoints. I also did all those funnier things in my early days of career.")

    h2("Story of JSON")

    code {
        pre(className = "code-block") {
            text("""
                {"status":"OK","message":"Hello JSON!"}
            """.trimIndent())
        }
    }

    p {
        text("In the JSON object the characters like ")
        code("{ } [ ] , : \"", className = "highlight")
        text(" doesn’t possess any kind of data. Instead it helps the serializer to format the data, so that it can be decoded and structured at the end point. The keywords helps to format data to a much more meaningful data for a newbie who is reading the JSON document for the first time. For example, ")
        code("OK Hello JSON!", className = "highlight")
        text(" are the exact data wrapped in the above JSON object, but it looks like a sentence, and doesn’t convey the exact meaning of the JSON object.")
    }
    p {
        text("For getting things more clear, let’s do an exercise. Count the number of characters in the above JSON object. It contains a total of ")
        b("39")
        text("  characters including spaces. We have 1 left curly bracket, 8 quotation marks, 2 colons, 1 comma and 1 right curly bracket, so ")
        b("13")
        text("  characters in total. The keywords occupies a total of 6 + 7 = ")
        b("13")
        text("  characters. The data holds 2 + 11 = ")
        b("13")
        text(" characters. Hence the data of two strings are wrapped up in a JSON object of ")
        b("26")
        text("  characters.")
    }
    p("Let’s summarize:")
    code {
        pre(className = "code-block") {
            text("""
                JSON object length:           39 bytes
                Information length:           13 bytes
                Non-information length:       26 bytes /* Wastage */
            """.trimIndent())
        }
    }
    h2("Protocol Buffers")
    p("In my earlier times of career, REST and SOAP are the most popular data exchange mechanisms existed, and most devs and companies encourages to learn and use them. And the time flies, the game has moved to the next level of faster and efficient binary data serialization techniques, and I met Protocol Buffers.")
    p {
        text("Protocol buffers are Google’s language-neutral, platform-neutral, extensible mechanism for serializing structured data. For Protocol buffers, everything is a message. Message is equivalent to ")
        b("class")
        text(" or ")
        b("structure")
        text(" in programming languages. We declare message types with unique names and gives a list of fields like this. Let’s define a message in ")
        code("greeting.proto", className = "highlight")
    }
    code {
        pre(className = "code-block") {
            text("""
                syntax = "proto3";

                message Greeting {
                    string status = 1; 
                    string message = 2;
                }
            """.trimIndent())
        }
    }
    p {
        text("The ")
        code("syntax = \"proto3\";", className = "highlight")
        text(" sentence tells the compiler that you’re using the version 3 of protocol buffers. In the message body, you can define the fields associated with the message. It supports unsigned and signed integers, floats, doubles, byte-arrays, strings, booleans, enums and user defined messages. In the above example, ")
        i("don’t confuse it like assigning a number to a string")
        text(". It’s just field numbers, that represent the order of those fields in serialized data.")
    }
    p {
        text("What’s next? Get protocol buffer compiler ")
        code("protoc", className = "highlight")
        text(" from ")
        a(href = "https://github.com/google/protobuf", text = "google/protobuf")
        text(" and use it to generate some protobuf classes and methods equivalent to the message. You can generate code for ")
        b("any programming language you want")
        text(". All you have to do is simply use that classes for data serialization and deserialization. For demonstration, I will use Javascript here.")
    }
    p("This is how to generate protobuf classes:")
    code {
        pre(className = "code-block") {
            text("protoc --js_out=import_style=commonjs,binary:. greeting.proto")
        }
    }
    p {
        text("That’s it! ")
        code("protoc", className = "highlight")
        text(" has generated ")
        code("greeting_pb.js", className = "highlight")
        text(" from ")
        code("greeting.proto", className = "highlight")
        text(" for you. Now you can use them anywhere you want, like this:")
    }
    kotlinCode("""
        var pb = require("./greeting_pb")

        // Serialization
        var data = { status: "OK", message: "Hello JSON!" }
        var msg = new pb.Greeting();
        msg.setStatus(data.status)
        msg.setMessage(data.message)
        var bytes = msg.serializeBinary();

        // Deserialization
        var msg2 = pb.Greeting.deserializeBinary(bytes)
        console.log(msg2.getStatus(), msg2.getMessage())
    """.trimIndent())
    p {
        text("The serialized data you got is ")
        code("UInt8Array", className = "highlight")
        text(". Let’s see how it looks like:")
    }
    code {
        pre(className = "code-block") {
            text("""
                10 2 79 75 18 11 72 101 108 108 111 32 74 83 79 78 33
                     O  K        H   e   l   l   o     J  S  O  N  !
            """.trimIndent())
        }
    }
    p {
        text("The serialized data is only just 17 bytes long! Also we can see that there is no more ")
        code("\"status\"", className = "highlight")
        text(" and ")
        code("\"message\"", className = "highlight")
        text(" literals. They are nicely obfuscated by protobuf object, and only they can understand the serialized message.")
    }
    p("In summary,")
    code {
        pre(className = "code-block") {
            text("""
                Serialized data length:       17 bytes
                Information length:           13 bytes
                Non-information length:        4 bytes  /* In JSON it is 26 */
            """.trimIndent())
        }
    }
    p("Also, for deserialization, the Protocol Buffers feels so easier, because it only has to remove those unnecessary bytes from the serialized data. Hence, the data exchange will become much faster and cost effective. If you’re planning to build an IoT based project or mobile apps, Protocol Buffers is just for you.")
}