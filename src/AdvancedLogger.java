import java.util.HashMap;
import java.util.Map;

public class AdvancedLogger {
    private final Map<LoggerSettings, String> settings;
    private Options opt = new Options();


    public AdvancedLogger(){
        settings = new HashMap<>();
        settings.put(LoggerSettings.filePath, null);
        settings.put(LoggerSettings.debugMode, "false");
        settings.put(LoggerSettings.colorMode, "true");
        settings.put(LoggerSettings.logOnly, "ewld");
    }
    public AdvancedLogger(Map<LoggerSettings, String> customSettings){
        settings = customSettings;
    }

    public boolean modifyLoggerSetting(LoggerSettings s, String value){
        return settings.replace(s, value) != null;
    }
    public void changeOption(Options opt){
        this.opt = opt;
    }

    public void log(String message){
        // if logging a log is off, return a none
        if (!settings.get(LoggerSettings.logOnly).contains("l"))return;
        boolean c = Boolean.parseBoolean(settings.get(LoggerSettings.colorMode));
        StringBuilder b = new StringBuilder();
        b.append('[')
                .append(LoggerTimer.getTimeAsString())
                .append(']')
                .append(" [LOG] => ")
                .append(message);
        if (settings.get(LoggerSettings.filePath) != null){
            saveToFile(b.toString());
        }
        if (c){
            b.insert(0, opt.logColorCode).append(Colors.RESET);
        }
        System.out.println(b);
    }

    public void warning(String warningMessage){
        // if logging a log is off, return a none
        if (!settings.get(LoggerSettings.logOnly).contains("w"))return;
        boolean c = Boolean.parseBoolean(settings.get(LoggerSettings.colorMode));
        StringBuilder b = new StringBuilder();
        b.append('[')
                .append(LoggerTimer.getTimeAsString())
                .append(']')
                .append(" [WARNING] => ")
                .append(warningMessage);
        if (settings.get(LoggerSettings.filePath) != null){
            saveToFile(b.toString());
        }
        if (c){
            b.insert(0, opt.warningColorCode).append(Colors.RESET);
        }
        System.out.println(b);
    }






    private void saveToFile(String message){
        // TODO
    }
}
