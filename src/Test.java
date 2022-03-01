public class Test {
    public static void main(String[] args) {
        AdvancedLogger logger = new AdvancedLogger();
        logger.modifyLoggerSetting(LoggerSettings.debugMode, "true");
        logger.log("Message");
        logger.warning("Warning Message");
        logger.error("String contains whitespaces");
    }
}
