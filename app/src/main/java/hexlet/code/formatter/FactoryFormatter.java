package hexlet.code.formatter;

public class FactoryFormatter {
    public static Formatter createFormatter(FormatterType formatterType) {
        return switch (formatterType) {
            case STYLISH -> new StylishFormatter();
            case PLAIN -> new PlainFormatter();
            case JSON -> new JsonFormatter();
        };
    }
    public static Formatter getFormatter(String format) {
        return switch (format) {
            case "stylish" -> createFormatter(FormatterType.STYLISH);
            case "plain" -> createFormatter(FormatterType.PLAIN);
            case "json" -> createFormatter(FormatterType.JSON);
            default -> throw new IllegalStateException("wrong format: " + format);
        };
    }
}
