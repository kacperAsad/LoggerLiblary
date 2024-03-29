public class Test {
    public static void main(String[] args) {
        AdvancedLogger logger = new AdvancedLogger();
        logger.modifyLoggerSetting(LoggerSettings.override, "true");
        //logger.modifyLoggerSetting(LoggerSettings.debugMode, "false");
        logger.modifyLoggerSetting(LoggerSettings.filePath, "data/logs/hh_mm  dd.MM.yy");

        logger.modifyLoggerSetting(LoggerSettings.logColor, Colors.YELLOW_BACKGROUND_BRIGHT);

        logger.log("Message");
        logger.warning("Warning Mee");
        logger.error("String contains whs");
        logger.debug("ELO");
    }
}
