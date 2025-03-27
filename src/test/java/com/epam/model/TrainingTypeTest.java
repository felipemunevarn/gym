package com.epam.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



class TrainingTypeTest {

    @Test
    void testEnumValues() {
        TrainingType.Type[] types = TrainingType.Type.values();
        assertNotNull(types);
        assertEquals(5, types.length);
        assertArrayEquals(new TrainingType.Type[]{
            TrainingType.Type.YOGA,
            TrainingType.Type.CARDIO,
            TrainingType.Type.STRENGTH,
            TrainingType.Type.PILATES,
            TrainingType.Type.CROSSFIT
        }, types);
    }

    @Test
    void testEnumValueOf() {
        assertEquals(TrainingType.Type.YOGA, TrainingType.Type.valueOf("YOGA"));
        assertEquals(TrainingType.Type.CARDIO, TrainingType.Type.valueOf("CARDIO"));
        assertEquals(TrainingType.Type.STRENGTH, TrainingType.Type.valueOf("STRENGTH"));
        assertEquals(TrainingType.Type.PILATES, TrainingType.Type.valueOf("PILATES"));
        assertEquals(TrainingType.Type.CROSSFIT, TrainingType.Type.valueOf("CROSSFIT"));
    }

    @Test
    void testEnumValueOfThrowsExceptionForInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> TrainingType.Type.valueOf("INVALID"));
    }
}