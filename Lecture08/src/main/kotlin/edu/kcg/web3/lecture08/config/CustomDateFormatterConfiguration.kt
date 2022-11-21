package edu.kcg.web3.lecture08.config

import com.samskivert.mustache.Mustache.*
import com.samskivert.mustache.Mustache.Formatter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Configuration
class CustomDateFormatterConfiguration {

    @Bean
    fun mustacheCompiler(
        mustacheTemplateLoader: TemplateLoader?,
        environment: Environment
    ): Compiler {
        return compiler()
            .withLoader(mustacheTemplateLoader)
            .withFormatter(customDateFormatter())
    }

    /**
     * Custom way to format dates in the templates - using the advantages of saving the date-time using Instant
     * as "timestamp with a tine zone"
     */
    private fun customDateFormatter(): Formatter {
        return object : Formatter {
            override fun format(value: Any): String {
                return if (value is Instant) {
                    formatter.format(value)
                } else {
                    value.toString()
                }
            }

            val formatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(Locale.ENGLISH)
                .withZone(ZoneId.of("Europe/Prague"))
        }
    }

}