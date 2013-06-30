package com.fly.base.info;

import com.fly.base.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 系统基本信息
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class SystemInfo {
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_VERSION = System.getProperty("os.version").toLowerCase();

    private static final String _OS_NAME = OS_NAME.toLowerCase();
    public static final boolean isWindows = _OS_NAME.startsWith("windows");
    public static final boolean isOS2 = _OS_NAME.startsWith("os/2") || _OS_NAME.startsWith("os2");
    public static final boolean isMac = _OS_NAME.startsWith("mac");
    public static final boolean isLinux = _OS_NAME.startsWith("linux");
    public static final boolean isUnix = !isWindows && !isOS2;

    public static final boolean isFileSystemCaseSensitive = isUnix && !isMac;

    public static final String OS_ARCH = System.getProperty("os.arch");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");
    public static final String ARCH_DATA_MODEL = System.getProperty("sun.arch.data.model");
    public static final String SUN_DESKTOP = System.getProperty("sun.desktop", "");

    public static final boolean isFreeBSD = _OS_NAME.startsWith("freebsd");
    public static final boolean isSolaris = _OS_NAME.startsWith("sunos");

    public static boolean isOsVersionAtLeast(@NotNull String version) {
        return StringUtil.compareVersionNumbers(OS_VERSION, version) >= 0;
    }

    // version numbers from http://msdn.microsoft.com/en-us/library/windows/desktop/ms724832.aspx
    public static final boolean isWin2kOrNewer = isWindows && isOsVersionAtLeast("5.0");
    public static final boolean isWinVistaOrNewer = isWindows && isOsVersionAtLeast("6.0");
    public static final boolean isWin7OrNewer = isWindows && isOsVersionAtLeast("6.1");
    public static final boolean isWindows9x = _OS_NAME.startsWith("windows 9") || _OS_NAME.startsWith("windows me");
    public static final boolean isWindowsNT = _OS_NAME.startsWith("windows nt");
    public static final boolean isWindows2000 = _OS_NAME.startsWith("windows 2000");
    public static final boolean isWindows2003 = _OS_NAME.startsWith("windows 2003");
    public static final boolean isWindowsXP = _OS_NAME.startsWith("windows xp");
    public static final boolean isWindowsVista = _OS_NAME.startsWith("windows vista");
    public static final boolean isWindows7 = _OS_NAME.startsWith("windows 7");

    public static final boolean isKDE = SUN_DESKTOP.toLowerCase().contains("kde");
    public static final boolean isGnome = SUN_DESKTOP.toLowerCase().contains("gnome");

    public static final boolean isXWindow = isUnix && !isMac;

    public static final boolean isMacSystemMenu = isMac && "true".equals(System.getProperty("apple.laf.useScreenMenuBar"));

    public static final boolean areSymLinksSupported = isUnix || isWinVistaOrNewer;

    public static final boolean is32Bit = ARCH_DATA_MODEL == null || ARCH_DATA_MODEL.equals("32");
    public static final boolean is64Bit = !is32Bit;
    public static final boolean isAMD64 = "amd64".equals(OS_ARCH);
    public static final boolean isMacIntel64 = isMac && "x86_64".equals(OS_ARCH);

    /**
     * running under MacOS X version 10.4 or later.
     */
    public static final boolean isMacOSTiger = isTiger();

    /**
     * running under MacOS X on an Intel Machine
     */
    public static final boolean isIntelMac = isIntelMac();

    /**
     * Running under MacOS X version 10.5 or later;
     */
    public static final boolean isMacOSLeopard = isLeopard();

    /**
     * Running under MacOS X version 10.6 or later;
     */
    public static final boolean isMacOSSnowLeopard = isSnowLeopard();

    /**
     * Running under MacOS X version 10.7 or later;
     */
    public static final boolean isMacOSLion = isLion();

    /**
     * Running under MacOS X version 10.8 or later;
     */
    public static final boolean isMacOSMountainLion = isMountainLion();

    public static boolean X11PasteEnabledSystem = isXWindow;

    private static boolean isIntelMac() {
        return isMac && "i386".equals(OS_ARCH);
    }

    private static boolean isTiger() {
        return isMac &&
                !OS_VERSION.startsWith("10.0") &&
                !OS_VERSION.startsWith("10.1") &&
                !OS_VERSION.startsWith("10.2") &&
                !OS_VERSION.startsWith("10.3");
    }

    private static boolean isLeopard() {
        return isMac && isTiger() && !OS_VERSION.startsWith("10.4");
    }

    private static boolean isSnowLeopard() {
        return isMac && isLeopard() && !OS_VERSION.startsWith("10.5");
    }

    private static boolean isLion() {
        return isMac && isSnowLeopard() && !OS_VERSION.startsWith("10.6");
    }

    private static boolean isMountainLion() {
        return isMac && isLion() && !OS_VERSION.startsWith("10.7");
    }

    @NotNull
    public static String getMacOSMajorVersion() {
        return getMacOSMajorVersion(OS_VERSION);
    }

    public static String getMacOSMajorVersion(String version) {
        int[] parts = getMacOSVersionParts(version);
        return String.format("%d.%d", parts[0], parts[1]);
    }

    @NotNull
    public static String getMacOSVersionCode() {
        return getMacOSVersionCode(OS_VERSION);
    }

    @NotNull
    public static String getMacOSMajorVersionCode() {
        return getMacOSMajorVersionCode(OS_VERSION);
    }

    @NotNull
    public static String getMacOSMinorVersionCode() {
        return getMacOSMinorVersionCode(OS_VERSION);
    }

    @NotNull
    public static String getMacOSVersionCode(@NotNull String version) {
        int[] parts = getMacOSVersionParts(version);
        return String.format("%02d%d%d", parts[0], normalize(parts[1]), normalize(parts[2]));
    }

    @NotNull
    public static String getMacOSMajorVersionCode(@NotNull String version) {
        int[] parts = getMacOSVersionParts(version);
        return String.format("%02d%d%d", parts[0], normalize(parts[1]), 0);
    }

    @NotNull
    public static String getMacOSMinorVersionCode(@NotNull String version) {
        int[] parts = getMacOSVersionParts(version);
        return String.format("%02d%02d", parts[1], parts[2]);
    }

    private static int[] getMacOSVersionParts(@NotNull String version) {
        List<String> parts = StringUtil.split(version, ".");
        while (parts.size() < 3) {
            parts.add("0");
        }
        return new int[]{toInt(parts.get(0)), toInt(parts.get(1)), toInt(parts.get(2))};
    }

    private static int normalize(int number) {
        return number > 9 ? 9 : number;
    }

    private static int toInt(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public static boolean isJavaVersionAtLeast(String v) {
        return StringUtil.compareVersionNumbers(JAVA_RUNTIME_VERSION, v) >= 0;
    }
}
