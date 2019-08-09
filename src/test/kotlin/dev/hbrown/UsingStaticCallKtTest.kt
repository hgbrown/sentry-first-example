package dev.hbrown

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UsingStaticCallKtTest {
    @Test
    @DisplayName("should have DSN configured so no exceptions should result")
    internal fun `should have DSN configured so no exceptions should result`() {
        fireSentryEventsStatic()
    }
}