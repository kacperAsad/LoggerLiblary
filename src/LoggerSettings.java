public enum LoggerSettings {
    filePath,                 // dir is got from file path, name of file is format pattern; use format like HH:mm:ss
    debugMode,                // Define is debug mode turned on
    colorMode,
    logOnly,                 // Only error etc. Symbols: w warning e error l log d debug
    pattern,
    isFileInitialized,       // Do not edit by yourself
    override,                // Define is Log file with same name has been overridden


                             // Colors
    logColor,
    warningColor,
    debugColor,
    errorColor
}
