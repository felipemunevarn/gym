package com.epam.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



class TrainingTypeTest {

    @Test
    void testEnumValues() {
        TrainingType[] types = TrainingType.values();
        assertNotNull(types);
        assertEquals(5, types.length);
        assertArrayEquals(new TrainingType[]{
            TrainingType.YOGA,
            TrainingType.CARDIO,
            TrainingType.STRENGTH,
            TrainingType.PILATES,
            TrainingType.CROSSFIT
        }, types);
    }

    @Test
    void testEnumValueOf() {
        assertEquals(TrainingType.YOGA, TrainingType.valueOf("YOGA"));
        assertEquals(TrainingType.CARDIO, TrainingType.valueOf("CARDIO"));
        assertEquals(TrainingType.STRENGTH, TrainingType.valueOf("STRENGTH"));
        assertEquals(TrainingType.PILATES, TrainingType.valueOf("PILATES"));
        assertEquals(TrainingType.CROSSFIT, TrainingType.valueOf("CROSSFIT"));
    }

    @Test
    void testEnumValueOfThrowsExceptionForInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> TrainingType.valueOf("INVALID"));
    }
}