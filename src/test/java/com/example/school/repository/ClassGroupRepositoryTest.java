package com.example.school.repository;

import com.example.school.model.ClassGroup;
import com.example.school.model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClassGroupRepositoryTest {

    @Autowired
    private ClassGroupRepository classGroupRepository;

    @Test
    void testSaveAndFindById() {
        ClassGroup classGroup = new ClassGroup();
        classGroup.setName("Class A");
        classGroup.setCapacity(30);

        ClassGroup savedClassGroup = classGroupRepository.save(classGroup);

        assertNotNull(savedClassGroup.getId());
        assertEquals("Class A", savedClassGroup.getName());
        assertEquals(30, savedClassGroup.getCapacity());
    }

    @Test
    void testFindAll() {
        ClassGroup classGroup1 = new ClassGroup();
        classGroup1.setName("Class A");
        classGroup1.setCapacity(30);

        ClassGroup classGroup2 = new ClassGroup();
        classGroup2.setName("Class B");
        classGroup2.setCapacity(25);

        classGroupRepository.save(classGroup1);
        classGroupRepository.save(classGroup2);

        List<ClassGroup> classGroups = classGroupRepository.findAll();

        assertEquals(2, classGroups.size());
    }

    @Test
    void testDelete() {
        ClassGroup classGroup = new ClassGroup();
        classGroup.setName("Class A");
        classGroup.setCapacity(30);

        ClassGroup savedClassGroup = classGroupRepository.save(classGroup);
        classGroupRepository.delete(savedClassGroup);

        assertTrue(classGroupRepository.findById(savedClassGroup.getId()).isEmpty());
    }
}
