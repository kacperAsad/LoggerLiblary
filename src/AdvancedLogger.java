import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdvancedLogger {

    /**
     * Used for holding settings data, like a path to log-file
     */
    private final Map<LoggerSettings, String> settings;

    /**
     * Log File is used to store information about current log file path and others
     */
    private File logFile = null;
    /**
     * logWriter is used to write logs to file
     */
    private FileWriter logWriter = null;

    /**
     * Default Constructor for AdvancedLogger object
     */
    public AdvancedLogger() {
        settings = new HashMap<>();
        settings.put(LoggerSettings.filePath, null);                        // Path to the log dir Starting with name, not '/' like 'path/to/file' not '/path/to/file'
        settings.put(LoggerSettings.debugMode, "false");                    // Is debug() turned on
        settings.put(LoggerSettings.colorMode, "true");                     // Is Console output color or not
        settings.put(LoggerSettings.logOnly, "error|debug|warning|log");    // If string contains mode, this mode will be printed if used
        settings.put(LoggerSettings.pattern, "dd-MM-yy | HH:mm:ss");        // Pattern to use a date/time in a logs
        settings.put(LoggerSettings.isFileInitialized, "false");            // Is current log file available
        settings.put(LoggerSettings.override , "false");                    // Is file with same name may be overridden

        // Colors
        settings.put(LoggerSettings.logColor, Colors.BLUE_BOLD_BRIGHT);
        settings.put(LoggerSettings.warningColor, Colors.RED_BOLD);
        settings.put(LoggerSettings.errorColor, Colors.RED_BOLD_BRIGHT + Colors.RED_UNDERLINED);
        settings.put(LoggerSettings.debugColor, Colors.GREEN_BOLD_BRIGHT);
    }

    /**
     * Method that can edit a logger settings
     *
     * @param s     Setting type defined in {@link LoggerSettings}
     * @param value Value that will be associated with the key. If boolean, use "true", "false" or {@link Boolean#toString(boolean)}
     */
    public void modifyLoggerSetting(LoggerSettings s, String value) {
        settings.replace(s, value);
    }

    @SuppressWarnings("unused")
    public void log(Object logMessage) {
        message(logMessage, LOG_TYPE.LOG);
    }

    @SuppressWarnings("unused")
    public void debug(Object debugMessage) {
        message(debugMessage, LOG_TYPE.DEBUG);
    }

    @SuppressWarnings("unused")
    public void warning(Object warningMessage) {
        message(warningMessage, LOG_TYPE.WARNING);
    }

    @SuppressWarnings("unused")
    public void error(Object errorMessage) {
        message(errorMessage, LOG_TYPE.ERROR);
    }

    private void message(Object message, LOG_TYPE type) {
        if (!settings.get(LoggerSettings.logOnly).toLowerCase(Locale.ROOT).contains(type.name().toLowerCase(Locale.ROOT))) return;
        String time = LoggerTimer.getTimeAsString(settings.get(LoggerSettings.pattern));
        boolean color = Boolean.parseBoolean(settings.get(LoggerSettings.colorMode));

        StringBuilder b = new StringBuilder();
        b.append('[').append(time).append(']');
        switch (type) {
            case LOG -> b.append(" [LOG] => ");
            case WARNING -> b.append(" [WARNING] => ");
            case ERROR -> b.append(" [ERROR] => ");
            case DEBUG -> b.append(" [DEBUG] => ");
        }
        b.append(message);
        if (settings.get(LoggerSettings.filePath) != null) {
            saveToFile(message, time, type);
        }
        if (color) {
            switch (type) {
                case LOG -> b.insert(0, settings.get(LoggerSettings.logColor)).append(Colors.RESET);
                case WARNING -> b.insert(0, settings.get(LoggerSettings.warningColor)).append(Colors.RESET);
                case ERROR -> b.insert(0, settings.get(LoggerSettings.errorColor)).append(Colors.RESET);
                case DEBUG -> b.insert(0, settings.get(LoggerSettings.debugColor)).append(Colors.RESET);
            }
        }
        System.out.println(b);
    }

    private boolean initFile() {
        String fileP = settings.get(LoggerSettings.filePath);
        if (fileP == null) return false;
        File buffFile = new File(fileP);
        File logDir = new File(buffFile.getParent());
        String pattern = buffFile.getName();

        if (!logDir.exists()) {
            try {
                if (!logDir.mkdirs()) {
                    System.out.println("Can't make a log directory; Try to make all directories for yourself and try again");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Exception occurred; Message from it: " + e.getMessage());
                return false;
            }
        }
        if (!logDir.isDirectory()) {
            System.out.println("Log path is not directory!");
            return false;
        }
        logFile = new File(logDir.getAbsolutePath() + '\\' + LoggerTimer.getTimeAsString(pattern));
        if (logFile.exists()){
            if (Boolean.parseBoolean(settings.get(LoggerSettings.override))) {
                try {
                    if (logFile.delete()) {
                        System.out.println("Log File Override");
                    } else {
                        System.out.println("Can't override file");
                    }
                } catch (SecurityException se){
                    se.printStackTrace();
                }
            } else {
                System.out.println("Another log file have the same name, log will be lost");
                return false;
            }
        }
        try {
            if (!logFile.createNewFile()) {
                System.out.println("Log file can't be created; Another file have the same name. Try to change the pattern");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Exception occurred; Message from it: " + e.getMessage());
            return false;
        }
        settings.put(LoggerSettings.isFileInitialized, "true");

        try {
            logWriter = new FileWriter(logFile);
        } catch (IOException e) {
            System.out.println("Exception occurred; Message from it: " + e.getMessage());
        }

        return true;
    }

    private void saveToFile(Object message, String time, LOG_TYPE type) {
        if (!Boolean.parseBoolean(settings.get(LoggerSettings.isFileInitialized)) || logFile == null) {
            if (!initFile()) return;
        }  // Run initFile method
        if (logWriter == null){
            System.out.println("You have turned on saving logs to file, but it's not saving your data. Try to re-launch app");
            return;
        }
        if (message == null) {
            return;
        }
        StringBuilder text = new StringBuilder();
        text.append('[');
        text.append(time);
        text.append(']');
        text.append(" ");
        text.append("[");
        text.append(type.toString());
        text.append(']');
        text.append(" -> ");
        text.append(message);
        text.append('\n');

        try {
            logWriter.write(text.toString());
            logWriter.flush();
        } catch (IOException e) {
            System.out.println("Exception occurred; Message from it: " + e.getMessage());
        }
    }

    private enum LOG_TYPE {
        LOG,
        WARNING,
        ERROR,
        DEBUG
    }
}
