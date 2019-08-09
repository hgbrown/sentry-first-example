package dev.hbrown

import io.sentry.Sentry
import io.sentry.event.BreadcrumbBuilder
import io.sentry.event.UserBuilder

fun main() {
    fireSentryEvents()
}

fun fireSentryEvents() {
    val sentry = Sentry.init()

    val context = sentry.context
    context.recordBreadcrumb(BreadcrumbBuilder().setMessage("user action in instance").build())
    context.user = UserBuilder().setEmail("henry.g.brown@gmail.com").build()

    sentry.sendMessage("This is a test message from: UsingInstance")

    try {
        unsafeMethod()
    } catch (e: UnsupportedOperationException) {
        sentry.sendException(e)
    }

    Thread.sleep(1_000) // sleep a little to keep tread alive to complete the call
}