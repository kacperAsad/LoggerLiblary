/**
 * Class that can be used to make a colors in output (not working with saving logs to file).
 * Can use a custom colors defined by user. Use {@link Colors} to change default values. If created with {@link Options#Options()}, have a predefined colors
 */
public class Options {
    /**
     * Log color representation in the string
     */
    String logColorCode;
    /**
     * Warning color representation in the string
     */
    String warningColorCode;
    /**
     * Debug color representation in the string
     */
    String debugColorCode;
    /**
     * Error color representation in the string
     */
    String errorColorCode;

    /**
     * Used to create a custom Logger output colors. Use {@link Colors} or asci code colors
     * @param logColorCode log message color
     * @param warningColorCode warning message color
     * @param debugColorCode debug message color
     * @param errorColorCode error message color
     */
    public Options(String logColorCode, String warningColorCode, String debugColorCode, String errorColorCode){
        this.logColorCode = logColorCode;
        this.warningColorCode = warningColorCode;
        this.debugColorCode = debugColorCode;
        this.errorColorCode = errorColorCode;
    }

    /**
     * Used to create color logger output. Have a predefined colors
     */
    public Options(){
        this.logColorCode = Colors.BLUE_BOLD_BRIGHT;
        this.warningColorCode = Colors.RED_BOLD;
        this.debugColorCode = Colors.GREEN_BOLD_BRIGHT;
        this.errorColorCode = Colors.RED_BOLD_BRIGHT + Colors.RED_UNDERLINED;
    }
}
