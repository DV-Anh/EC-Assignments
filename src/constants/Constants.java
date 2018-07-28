package constants;

/**
 * Static class containing constants
 */
public final class Constants {

    private Constants() {
        // restrict instantiation
    }
    public static final String[] TESTFIES = {
            "EIL51.tsp",
            "EIL76.tsp",
            "EIL101.tsp",
            "ST70.tsp",
            "KROA100.tsp",
            "KROC100.tsp",
            "KROD100.tsp",
            "LIN105.tsp",
            "PCB442.tsp",
            "PR2392.tsp",
            "USA13509.tsp"
    };
    public static final String TESTPATH = "./testfiles/";
    public static final String COORDSTART = "NODE_COORD_SECTION";
    public static final String EOF = "EOF";
    public static final String DIMENSION = "DIMENSION";
    public static final String HEADER_DELIMITER = ":";
    public static final String COORD_DELIMITER = " ";
}
