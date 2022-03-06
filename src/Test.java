public class Test {
    public static void main(String[] args) {
        AdvancedLogger logger = new AdvancedLogger();
        logger.modifyLoggerSetting(LoggerSettings.debugMode, "true");
        logger.modifyLoggerSetting(LoggerSettings.filePath, "data/logs/dd-MM-yy_HH:mm:ss");

        logger.log("Message");
        logger.warning("Warning Message");
        logger.error("String contains whitespaces");
    }
}
