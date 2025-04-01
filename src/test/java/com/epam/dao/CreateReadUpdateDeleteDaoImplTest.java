package com.epam.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.dao.impl.CreateReadUpdateDeleteDaoImpl;
import com.epam.model.Identifiable;

class CreateReadUpdateDeleteDaoImplTest {

    private CreateReadUpdateDeleteDaoImpl<TestEntity, String> createReadUpdateDeleteDao;
    private Map<String, TestEntity> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        createReadUpdateDeleteDao = new CreateReadUpdateDeleteDaoImpl<>(storage);
    }

    @Test
    void testDeleteEntitySuccessfully() {
        TestEntity entity = new TestEntity("1", "Entity to Delete");
        storage.put("1", entity);

        createReadUpdateDeleteDao.delete("1");

        assertFalse(storage.containsKey("1"));
    }

    @Test
    void testDeleteNonexistentEntity() {
        createReadUpdateDeleteDao.delete("1");

        assertFalse(storage.containsKey("1")); // Ensure no exception is thrown and storage remains unaffected
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