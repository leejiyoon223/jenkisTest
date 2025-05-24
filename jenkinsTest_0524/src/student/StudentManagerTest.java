package student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentManagerTest {

	private StudentManager studentManager;
	private String engName;
	private String korName;
	
	@BeforeEach //처음에 시작
	void setUp() throws Exception {
		studentManager = new StudentManager();
		engName = "Jiyoon";
		korName = "지윤";
	}

	@Test // 정상 추가
	void testAddStudent() {
		// 영문
		studentManager.addStudent(engName);
	    assertTrue(studentManager.hasStudent(engName));		
	    
	    // 국문
	    studentManager.addStudent(korName);
	    assertTrue(studentManager.hasStudent(korName));		
	}
	
	@Test // 중복 추가
	void testAddStudentDuplicateThrowsException() {
		// 영문
		// 미리 들어가 있었음
		studentManager.addStudent(engName);
        assertThrows(IllegalArgumentException.class, () -> {
        	// 중복 예외 처리
            studentManager.addStudent(engName);
        });
        
        // 국문
        studentManager.addStudent(korName);
        assertThrows(IllegalArgumentException.class, () -> {
        	// 중복 예외 처리
            studentManager.addStudent(korName);
        });
	}

	@Test // 정상 삭제
	void testRemoveStudent() {
		// 영문
		studentManager.addStudent(engName);
		studentManager.removeStudent(engName);
		assertFalse(studentManager.hasStudent(engName));
		
		// 국문
		studentManager.addStudent(korName);
		studentManager.removeStudent(korName);
		assertFalse(studentManager.hasStudent(korName));
	}
	
	@Test // 없는 이름 삭제
	void testRemoveNonexistentStudentThrowsException() {
		// 영문
		assertThrows(IllegalArgumentException.class, () -> {
			// 추가 없는데 삭제하는 경우 예외 처리
            studentManager.removeStudent(engName);
        });
		
		// 국문
		assertThrows(IllegalArgumentException.class, () -> {
			// 추가 없는데 삭제하는 경우 예외 처리
            studentManager.removeStudent(korName);
        });
	}
}
