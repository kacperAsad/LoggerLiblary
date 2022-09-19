## Logger Library 
Logger liblary is created and maintained for my personal use. 

Contains `SimpleLogger` class, which is currently deprecated, and `AdvancedLogger` which is like new version of SimpleLogger.

### Advanced Logger
Advanced Logger contains 4 methods that can be used to Log message to `System.out PrintStream`
 
- log
- warning
- error
- debug

All of this types can have own colors (not formatting)
Logger can be configured to display only some types, like only warnings, or logs and errors. 
Debug mode also can be turned on and off to display debug messages (don't implemented deleting debug code in runtime yet)
This logger can also save logs to files, with own format name (can use real time and date to generate name)

