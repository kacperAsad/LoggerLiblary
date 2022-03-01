public enum LoggerSettings {
    filePath, // dir is getted from file path, name of file is format pattern
    debugMode,
    colorMode,
    logOnly,  // Only error etc. Symbols: w warning e error l log d debug
    pattern,
    isFileInitialized,

    // Colors
    logColor,
    warningColor,
    debugColor,
    errorColor
}
