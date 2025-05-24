package student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentManagerTest3 {

	StudentManager sm;
	
	@BeforeEach
	void setUp() throws Exception {
		sm = new StudentManager();
		sm.addStudent("John");
		sm.addStudent("길동");
	}

	@Test
	void testAddStudent() {
		assertTrue(sm.hasStudent("John"));
		assertTrue(sm.hasStudent("길동"));
	}

	@Test
	void testRemoveStudent() {
		sm.removeStudent("John");
		assertFalse(sm.hasStudent("John"));
		sm.removeStudent("길동");
		assertFalse(sm.hasStudent("길동"));
	}

	@Test
	void testAddStudentError() {
		assertThrows(IllegalArgumentException.class, () -> sm.addStudent("John"));
		assertThrows(IllegalArgumentException.class, () -> sm.addStudent("길동"));
	}
	
	@Test
	void testRemoveStudentError() {
		sm.removeStudent("John");
		assertThrows(IllegalArgumentException.class, () -> sm.removeStudent("John"));
		sm.removeStudent("길동");
		assertThrows(IllegalArgumentException.class, () -> sm.removeStudent("길동"));	
	}

}
