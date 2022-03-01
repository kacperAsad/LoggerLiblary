import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdvancedLogger {

    /**
     * Used for holding settings data, like a path to log-file
     */
    private final Map<LoggerSettings, String> settings;
    /**
     * Default Constructor for AdvancedLogger object
     */
    public AdvancedLogger(){
        settings = new HashMap<>();
        settings.put(LoggerSettings.filePath, null);                        // Path to the log dir
        settings.put(LoggerSettings.debugMode, "false");                    // Is debug() turned on
        settings.put(LoggerSettings.colorMode, "true");                     // Is Console output color or not
        settings.put(LoggerSettings.logOnly, "error|debug|warning|log");    // If string contains mode, this mode will be printed if used
        settings.put(LoggerSettings.pattern, "dd-MM-yy | HH:mm:ss");        // Pattern to use a date/time in a logs
        settings.put(LoggerSettings.isFileInitialized, "false");            // Is current log file available

        // Colors
        settings.put(LoggerSettings.logColor, Colors.BLUE_BOLD_BRIGHT);
        settings.put(LoggerSettings.warningColor, Colors.RED_BOLD);
        settings.put(LoggerSettings.errorColor, Colors.RED_BOLD_BRIGHT + Colors.RED_UNDERLINED);
        settings.put(LoggerSettings.debugColor, Colors.GREEN_BOLD_BRIGHT);
    }

    /**
     * Method that can edit a logger settings
     * @param s Setting type defined in {@link LoggerSettings}
     * @param value Value that will be associated with the key. If boolean, use "true", "false" or {@link Boolean#toString(boolean)}
     */
    @SuppressWarnings("UnusedReturnValue")
    public void modifyLoggerSetting(LoggerSettings s, String value){
        settings.replace(s, value);
    }

    public void log(String logMessage){
        message(logMessage, LOG_TYPE.LOG);
    }
    public void debug(String debugMessage){
        message(debugMessage, LOG_TYPE.DEBUG);
    }
    public void warning(String warningMessage){
        message(warningMessage, LOG_TYPE.WARNING);
    }
    public void error(String errorMessage){
        message(errorMessage, LOG_TYPE.ERROR);
    }

    private void message(String message, LOG_TYPE type){
        if (!settings.get(LoggerSettings.logOnly).toLowerCase(Locale.ROOT).contains(type.name().toLowerCase(Locale.ROOT))) return;
        String time = LoggerTimer.getTimeAsString(settings.get(LoggerSettings.pattern));
        boolean color = Boolean.parseBoolean(settings.get(LoggerSettings.colorMode));

        StringBuilder b = new StringBuilder();
        b.append('[').append(time).append(']');
        switch (type){
            case LOG -> b.append(" [LOG] => ");
            case WARNING -> b.append(" [WARNING] => ");
            case ERROR -> b.append(" [ERROR] => ");
            case DEBUG -> b.append(" [DEBUG] => ");
        }
        b.append(message);
        if (settings.get(LoggerSettings.filePath) != null){
            saveToFile(message, time, type);
        }
        if (color){
            switch (type){
                case LOG -> b.insert(0, settings.get(LoggerSettings.logColor)).append(Colors.RESET);
                case WARNING -> b.insert(0, settings.get(LoggerSettings.warningColor)).append(Colors.RESET);
                case ERROR -> b.insert(0, settings.get(LoggerSettings.errorColor)).append(Colors.RESET);
                case DEBUG -> b.insert(0, settings.get(LoggerSettings.debugColor)).append(Colors.RESET);
            }
        }
        System.out.println(b);
    }

    private void initFile(){
        String fileP = settings.get(LoggerSettings.filePath);
        if (fileP == null) return;
        File f = new File(fileP);
        if (!f.canWrite()){
            warning("");
            return;
        }
        settings.put(LoggerSettings.isFileInitialized, "true");
    }
    private void saveToFile(String message, String time, LOG_TYPE type){
        if (!Boolean.parseBoolean(settings.get(LoggerSettings.isFileInitialized))) initFile();

    }
    private enum LOG_TYPE{
        LOG,
        WARNING,
        ERROR,
        DEBUG
    }
}
