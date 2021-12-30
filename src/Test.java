public class Test {
    public static void main(String[] args) {
//        SimpleLogger.useColors(true);
//        SimpleLogger.setDebugMode(true);
//        SimpleLogger.log("Send message complete");
//        SimpleLogger.warning("Test Warning Message");
//        SimpleLogger.debug("Test Debug Message");
//        SimpleLogger.error("Test Error Message");
//        SimpleLogger.setDebugMode(false);
//        SimpleLogger.setDebugMode(true);
//        Options co = new Options(Colors.BLUE_BOLD_BRIGHT, Colors.WHITE, Colors.CYAN, Colors.PURPLE_UNDERLINED);
//        SimpleLogger.setOptions(co);
//        SimpleLogger.log("Send message complete");
//        SimpleLogger.warning("Test Warning Message");
//        SimpleLogger.debug("Test Debug Message");
//        SimpleLogger.error("Test Error Message");
        AdvancedLogger logger = new AdvancedLogger();
        logger.modifyLoggerSetting(LoggerSettings.debugMode, "true");
        logger.log("Message");
        logger.warning("Warning Message");
    }
}
