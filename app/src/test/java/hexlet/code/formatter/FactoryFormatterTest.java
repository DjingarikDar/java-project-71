package hexlet.code.formatter;

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
