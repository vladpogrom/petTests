package tests.pet_tests;

import heplers.PetData;
import org.junit.jupiter.api.*;
import tests.BaseTest;

@DisplayName("Тесты для метода PUT /pet")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PutPetTests extends BaseTest {

    @AfterAll
    static void afterAll() {
        // Удаляем
        api.deletePet(200, PetData.getPetId(), "unknown", String.valueOf(PetData.getPetId()));
        // Проверяем, что удален
        api.getPetNegative(404, PetData.getPetId(), "error", PetData.getError404());
    }

    @BeforeAll
    void beforeAll() {
        api.postPet(200, PetData.getPetBody());
    }

    @Test
    @DisplayName("Тест проверки метода PUT /pet с кодом 200")
    public void getPet200() {
        String putBody = "{\"id\": " + PetData.getPetId() + ",\n" +
                "  \"name\": \"ChangedTest\"}";
        api.putPet(200, putBody);
        // Проверяем изменения
        api.getPetPositive(200, PetData.getPetId(), "ChangedTest");
    }

    @Test
    @DisplayName("Тест проверки метода PUT /pet с кодом 400")
    public void getPet400() {
        api.putPet(400, PetData.getWrongBody().toString());
    }
}
