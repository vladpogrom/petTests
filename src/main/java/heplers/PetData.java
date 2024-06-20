package heplers;

import java.io.File;

public class PetData {
    // Values
    static final String PET_NAME = "TestName";
    static final int PET_ID = 1;
    // Errors
    static final String ERROR_404 = "Pet not found";
    // Paths
    static final String PATH_TO_BODY = "src/test/resources/json_schemas/pet/successPet.json";
    static final String WRONG_BODY = "src/test/resources/json_schemas/pet/wrongPet.txt";
    static final String SCHEMA_PATH = "json_schemas/pet/getPet.json";
    // File
    static File petBody = new File(getSuccessPathBody());
    static File wrongBody = new File(getWrongPathBody());

    public static File getWrongBody() {
        return wrongBody;
    }

    public static File getPetBody() {
        return petBody;
    }

    public static String getPetName() {
        return PET_NAME;
    }

    public static int getPetId() {
        return PET_ID;
    }

    public static String getError404() {
        return ERROR_404;
    }

    public static String getSuccessPathBody() {
        return PATH_TO_BODY;
    }

    public static String getWrongPathBody() {
        return WRONG_BODY;
    }

    public static String getSchemaPath() {
        return SCHEMA_PATH;
    }
}
