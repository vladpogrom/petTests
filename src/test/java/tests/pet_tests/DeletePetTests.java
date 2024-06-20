package tests.pet_tests;

import heplers.PetData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tests.BaseTest;

@DisplayName("Тесты для метода DELETE /pet/{pet_id}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeletePetTests extends BaseTest {

    @BeforeAll
    void beforeAll() {
        api.postPet(200, PetData.getPetBody());
    }

    @Test
    @DisplayName("Тест проверки метода DELETE /pet/{pet_id} с кодом 200")
    public void deletePet200() {
        // Удаляем
        api.deletePet(200, PetData.getPetId(), "unknown", String.valueOf(PetData.getPetId()));
        // Проверяем, что удален
        api.getPetNegative(404, PetData.getPetId(), "error", PetData.getError404());
    }

    @Test
    @DisplayName("Тест проверки метода DELETE /pet/{pet_id} с кодом 404")
    public void deletePet404() {
        api.deletePet(404, 123123, "", "");
    }
}
