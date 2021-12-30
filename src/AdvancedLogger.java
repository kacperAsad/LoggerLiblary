import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdvancedLogger {

    private final Map<LoggerSettings, String> settings;

    public AdvancedLogger(){
        settings = new HashMap<>();
        settings.put(LoggerSettings.filePath, null);
        settings.put(LoggerSettings.debugMode, "false");
        settings.put(LoggerSettings.colorMode, "true");
        settings.put(LoggerSettings.logOnly, "error|debug|warning|log");

        // Colors
        settings.put(LoggerSettings.logColor, Colors.BLUE_BOLD_BRIGHT);
        settings.put(LoggerSettings.warningColor, Colors.RED_BOLD);
        settings.put(LoggerSettings.errorColor, Colors.RED_BOLD_BRIGHT + Colors.RED_UNDERLINED);
        settings.put(LoggerSettings.debugColor, Colors.GREEN_BOLD_BRIGHT);
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean modifyLoggerSetting(LoggerSettings s, String value){
        return settings.replace(s, value) != null;
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
        boolean color = Boolean.parseBoolean(settings.get(LoggerSettings.colorMode));

        StringBuilder b = new StringBuilder();
        b.append('[').append(LoggerTimer.getTimeAsString()).append(']');
        switch (type){
            case LOG -> b.append(" [LOG] => ");
            case WARNING -> b.append(" [WARNING] => ");
            case ERROR -> b.append(" [ERROR] => ");
            case DEBUG -> b.append(" [DEBUG] => ");
        }
        b.append(message);
        if (settings.get(LoggerSettings.filePath) != null){
            saveToFile();
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
    private void saveToFile(){

    }
    private enum LOG_TYPE{
        LOG,
        WARNING,
        ERROR,
        DEBUG
    }
}
