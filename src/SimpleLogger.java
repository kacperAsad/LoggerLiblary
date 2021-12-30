/**
 * Used for logging in small projects, static usage e.g. SimpleLogger.log().
 * Have a log, warning, debug and error and can color an output
 */

public class SimpleLogger {
    /**
     * Used for holding an information about using colors
     */
    private static boolean useColors = false;
    /**
     * Used for holding an information about debug mode
     */
    private static boolean debugMode = false;
    /**
     * Used for holding an option object
     */
    private static Options opt = new Options();

    /**
     * Changing a method of displaying a logs, in a color messages or a terminal colors
     * @param use true: use colors
     *            false: don't use a colors
     */
    public static void useColors(boolean use){
        if (use){
            log("Color Messages Turned ON");
        }
        useColors = use;
    }

    /**
     * Changing a debug mode, if true debug messages will be visible. Can be changed in running program
     * @param debug if true debug messages will be visible, if false it's not showing
     */
    public static void setDebugMode(boolean debug){
        if (debug){
            log("Debug Mode Is Turned ON");
        }
        debugMode = debug;
    }

    /**
     * Used for changing a colors of the print functions. Require {@link Options} object
     * @param o color options
     */
    public static void setOptions(Options o) {
        if (o == null) return;
        log("Loaded A Custom Colors For Messages");
        opt = o;
    }

    /**
     * Used to log a message to terminal
     * @param message a message that be logged in terminal
     */
    public static void log(String message){
        if (useColors) {
            String builder = opt.logColorCode +
                    '[' + LoggerTimer.getTimeAsString() + ']' +
                    " [LOG] => " +
                    message +
                    Colors.RESET;
            System.out.println(builder);
            return;
        }
        String builder = '[' + LoggerTimer.getTimeAsString() + ']' +
                " [LOG] => " +
                message;
        System.out.println(builder);
    }

    /**
     * Used to log a warning message to terminal
     * @param warnMessage message that be logged
     */
    public static void warning(String warnMessage){
        if (useColors){
            String builder = opt.warningColorCode +
                    '[' + LoggerTimer.getTimeAsString() + ']' +
                    " [WARNING] => " +
                    warnMessage +
                    Colors.RESET;
            System.out.println(builder);
            return;
        }
        String builder = '[' + LoggerTimer.getTimeAsString() + ']' +
                " [WARNING] => " +
                warnMessage;
        System.out.println(builder);
    }

    /**
     * Used to log a debug message to terminal
     * @param debugMessage message to be logged
     */
    public static void debug(String debugMessage){
        if (debugMode) {
            if (useColors) {
                String builder = opt.debugColorCode +
                        '[' + LoggerTimer.getTimeAsString() + ']' +
                        " [DEBUG] => " +
                        debugMessage +
                        Colors.RESET;
                System.out.println(builder);
                return;
            }
            String builder = '[' + LoggerTimer.getTimeAsString() + ']' +
                    " [DEBUG] => " +
                    debugMessage;
            System.out.println(builder);
        }
    }

    /**
     * Used to log a error message to terminal
     * @param errorMessage message that be logged
     */
    public static void error(String errorMessage){
        if (useColors){
            String builder = opt.errorColorCode +
                    '[' + LoggerTimer.getTimeAsString() + ']' +
                    " [ERROR] => " +
                    errorMessage +
                    Colors.RESET;
            System.out.println(builder);
            return;
        }
        String builder = '[' + LoggerTimer.getTimeAsString() + ']' +
                " [ERROR] => " +
                errorMessage;
        System.out.println(builder);
    }
}
