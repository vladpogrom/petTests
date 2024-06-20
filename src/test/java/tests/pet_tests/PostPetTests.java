package tests.pet_tests;

import heplers.PetData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tests.BaseTest;

@DisplayName("Тесты для метода POST /pet")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostPetTests extends BaseTest {


    @AfterAll
    static void afterAll() {
        // Удаляем
        api.deletePet(200, PetData.getPetId(), "unknown", String.valueOf(PetData.getPetId()));
        // Проверяем, что удален
        api.getPetNegative(404, PetData.getPetId(), "error", PetData.getError404());
    }

    @Test
    @DisplayName("Тест проверки метода POST /pet с кодом 200")
    public void postPet200() {
        api.postPet(200, PetData.getPetBody());
        // Проверяем наличие
        api.getPetPositive(200, PetData.getPetId(), PetData.getPetName());
    }

    @Test
    @DisplayName("Тест проверки метода POST /pet с кодом 400")
    public void postPet400() {
        api.postPet(400, PetData.getWrongBody());
    }
}
