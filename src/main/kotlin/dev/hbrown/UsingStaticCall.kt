package dev.hbrown

import io.sentry.Sentry
import io.sentry.event.BreadcrumbBuilder
import io.sentry.event.UserBuilder
import java.lang.UnsupportedOperationException

fun main() {
    fireSentryEventsStatic()
}

fun unsafeMethod() {
    throw UnsupportedOperationException("Testing the sentry fault reporting service")
}

fun fireSentryEventsStatic() {
    Sentry.getContext().recordBreadcrumb(BreadcrumbBuilder().setMessage("Message from Static Call").build())
    Sentry.getContext().user = UserBuilder().setUsername("hbrown").setData(mapOf("clever" to  "no")).build()
    Sentry.getContext().addExtra("spice", "yes")
    Sentry.getContext().addTag("stupid", "true")

    Sentry.capture("This test message from UsingStaticCall")

    try {
        unsafeMethod()
    } catch (e: UnsupportedOperationException) {
        Sentry.capture(e)
    }

    Thread.sleep(1_000) // sleep a little to keep tread alive to complete the call
}