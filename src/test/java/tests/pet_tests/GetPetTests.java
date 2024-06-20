package tests.pet_tests;

import heplers.PetData;
import org.junit.jupiter.api.*;
import tests.BaseTest;

import java.io.File;

@DisplayName("Тесты для метода GET /pet/{pet_id}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetPetTests extends BaseTest {
    File petBody = new File(PetData.getSuccessPathBody());

    @AfterAll
    static void afterAll() {
        // Удаляем
        api.deletePet(200, PetData.getPetId(), "unknown", String.valueOf(PetData.getPetId()));
        // Проверяем, что удален
        api.getPetNegative(404, PetData.getPetId(), "error", PetData.getError404());
    }

    @BeforeAll
    void beforeAll() {
        api.postPet(200, petBody);
    }

    @Test
    @DisplayName("Тест проверки json схемы ответа метода GET /pet/{pet_id}")
    public void getPetSchemaCheck() {
        api.getPet(200, PetData.getPetId(), PetData.getSchemaPath());
    }

    @Test
    @DisplayName("Тест проверки метода GET /pet/{pet_id} с кодом 200")
    public void getPet200() {
        api.getPetPositive(200, PetData.getPetId(), PetData.getPetName());
    }

    @Test
    @DisplayName("Тест проверки метода GET /pet/{pet_id} с кодом 404")
    public void getPet404() {
        api.getPetNegative(404, 123123, "error", PetData.getError404());
    }
}
