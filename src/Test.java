public class Test {
    public static void main(String[] args) {
        AdvancedLogger logger = new AdvancedLogger();
        logger.modifyLoggerSetting(LoggerSettings.override, "true");
        logger.modifyLoggerSetting(LoggerSettings.debugMode, "true");
        logger.modifyLoggerSetting(LoggerSettings.filePath, "data/logs/hh_mm  dd.MM.yy");

        logger.log("Message");
        logger.warning("Warning Mee");
        logger.error("String contains whs");
    }
}
