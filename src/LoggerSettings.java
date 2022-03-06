public enum LoggerSettings {
    filePath, // dir is got from file path, name of file is format pattern; use format like HH:mm:ss
    debugMode,
    colorMode,
    logOnly,  // Only error etc. Symbols: w warning e error l log d debug
    pattern,
    isFileInitialized, // Do not edit by yourself


    // Colors
    logColor,
    warningColor,
    debugColor,
    errorColor
}
