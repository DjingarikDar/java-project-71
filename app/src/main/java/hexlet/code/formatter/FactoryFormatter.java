package hexlet.code.formatter;

public class FactoryFormatter {
    public static Formatter createFormatter(FormatterType formatterType) {
        return switch (formatterType) {
            case STYLISH -> new StylishFormatter();
            case PLAIN -> new PlainFormatter();
        };
    }
    public static Formatter getFormatter(String format) {
        return switch (format) {
            case "stylish" -> createFormatter(FormatterType.STYLISH);
            case "plain" -> createFormatter(FormatterType.PLAIN);
            default -> throw new IllegalStateException("wrong format: " + format);
        };
    }
}
