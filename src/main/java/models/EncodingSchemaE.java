package models;

public enum EncodingSchemaE {
    GZIP;


    public static EncodingSchemaE constantOf(String encoding) {
        try {
            return EncodingSchemaE.valueOf(encoding.toUpperCase());
        } catch (IllegalArgumentException exc) {
            System.out.println("not a valid enum constant");
            return null;
        }
    }
}
