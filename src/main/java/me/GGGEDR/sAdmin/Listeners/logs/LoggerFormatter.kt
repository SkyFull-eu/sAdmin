package me.GGGEDR.sAdmin.Listeners.logs

import java.util.logging.LogRecord
import java.util.logging.SimpleFormatter

class LoggerFormatter : SimpleFormatter() {

    @Synchronized
    override fun format(record: LogRecord): String? {
        record.sourceClassName = LoggerFormatter::class.java.getName()
        return String.format(
                "| %2\$s\n",
                record.level.name, formatMessage(record))
    }
}
