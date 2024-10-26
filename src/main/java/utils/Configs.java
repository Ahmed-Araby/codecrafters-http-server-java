package utils;

public class Configs {
    public static String FILES_ABSOLUTE_PATH;


    public static void setConfigs(String[] args) {
        System.out.println("set server configs");
        if(args.length >= 2 && "--directory".equals(args[0])) {
            FILES_ABSOLUTE_PATH = args[1];
        }
    }
}
