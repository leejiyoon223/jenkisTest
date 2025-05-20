package student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentManagerTest {

	StudentManager sm;
	
	
	@BeforeEach
	void setUp() throws Exception {
		sm = new StudentManager();
	}

	@Test
	void testAddStudent() {
		sm.addStudent("John");
		assertTrue(sm.hasStudent("John"));
		assertThrows(IllegalArgumentException.class, () -> sm.addStudent("John"));
		
	}

	@Test
	void testRemoveStudent() {
		assertThrows(IllegalArgumentException.class, () -> sm.removeStudent("John"));	
		sm.addStudent("John");
		sm.removeStudent("John");
		assertFalse(sm.hasStudent("John"));
	}
}
