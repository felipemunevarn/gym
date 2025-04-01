package com.epam.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.dao.impl.CreateReadUpdateDaoImpl;
import com.epam.model.Identifiable;

class CreateReadUpdateDaoImplTest {

    private CreateReadUpdateDaoImpl<TestEntity, String> createReadUpdateDao;
    private Map<String, TestEntity> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        createReadUpdateDao = new CreateReadUpdateDaoImpl<>(storage);
    }

    @Test
    void testUpdateEntitySuccessfully() {
        TestEntity entity = new TestEntity("1", "Original Entity");
        storage.put("1", entity);

        TestEntity updatedEntity = new TestEntity("1", "Updated Entity");
        createReadUpdateDao.update(updatedEntity);

        assertEquals(updatedEntity, storage.get("1"));
    }

    @Test
    void testUpdateThrowsExceptionWhenEntityDoesNotExist() {
        TestEntity entity = new TestEntity("1", "Nonexistent Entity");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createReadUpdateDao.update(entity);
        });

        assertEquals("Entity with ID 1 does not exist.", exception.getMessage());
    }

    // Helper class for testing
    private static class TestEntity implements Identifiable<String> {
        private final String id;
        private final String name;

        public TestEntity(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestEntity that = (TestEntity) o;
            return id.equals(that.id) && name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}