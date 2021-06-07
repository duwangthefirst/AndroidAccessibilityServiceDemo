### AndroidAccessibilityServiceDemo

already implemented:
- AccessibilityService related config
    - AndroidManifest.xml
    - TestAccessibilityService.java
    - res/xml/accessibility_service_config.xml
- detect whether window state has changed and print current package name in toast.

- future work:
    - jump to system settings to enable the according accessibility service
    - add switch for start and stop service
    - add switch for hide app in back task list
    - add switch for permission of SYSTEM_ALERT_WINDOW
    - add switch for permission of REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    - add switch for registering app as device manager
    - inform user to lock app in back
    - inform user to allow app to auto-start when power on