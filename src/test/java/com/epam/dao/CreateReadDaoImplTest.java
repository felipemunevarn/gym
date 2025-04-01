package com.epam.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.dao.impl.CreateReadDaoImpl;
import com.epam.model.Identifiable;

class CreateReadDaoImplTest {

    private CreateReadDaoImpl<TestEntity, String> createReadDao;
    private Map<String, TestEntity> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
        createReadDao = new CreateReadDaoImpl<>(storage);
    }

    @Test
    void testSaveEntitySuccessfully() {
        TestEntity entity = new TestEntity("1", "Test Entity");

        createReadDao.save(entity);

        assertTrue(storage.containsKey("1"));
        assertEquals(entity, storage.get("1"));
    }

    @Test
    void testFindByUsernameSuccessfully() {
        TestEntity entity = new TestEntity("1", "Test Entity");
        storage.put("1", entity);

        TestEntity foundEntity = createReadDao.findByUsername("1");

        assertNotNull(foundEntity);
        assertEquals(entity, foundEntity);
    }

    @Test
    void testFindByUsernameReturnsNullWhenNotFound() {
        TestEntity foundEntity = createReadDao.findByUsername("nonexistent");

        assertNull(foundEntity);
    }

    @Test
    void testExistsReturnsTrueWhenEntityExists() {
        TestEntity entity = new TestEntity("1", "Test Entity");
        storage.put("1", entity);

        assertTrue(createReadDao.exists("1"));
    }

    @Test
    void testExistsReturnsFalseWhenEntityDoesNotExist() {
        assertFalse(createReadDao.exists("nonexistent"));
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