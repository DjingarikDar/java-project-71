package hexlet.code;

import hexlet.code.formatter.FactoryFormatter;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.formatter.PlainFormatter;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FactoryFormatterTest {
    @Test
    void createFormatter() {
        String format;

        Formatter actual;
        format = "stylish";
        actual = FactoryFormatter.getFormatter(format);
        assertInstanceOf(StylishFormatter.class, actual);
        format = "plain";
        actual = FactoryFormatter.getFormatter(format);
        assertInstanceOf(PlainFormatter.class, actual);
    }
}
