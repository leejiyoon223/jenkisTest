# Makefile 예시: Java 소스 컴파일 및 JUnit 테스트 실행

# 소스 디렉토리
SRCDIR = Test1/src
BINDIR = Test1/bin
LIBDIR = Test1/lib
REPORTDIR = Test1/test-reports

# JUnit JAR 경로 (실제 경로에 맞게 수정)
JUNIT_JAR = $(LIBDIR)/junit.jar

# 모든 .java 파일을 찾기
JAVA_SOURCES = $(wildcard $(SRCDIR)/**/*.java)

# 기본 타겟 (make 만 입력 시 실행)
all: build test

# 빌드 타겟
build: $(BINDIR)
	@echo "[+] Compiling Java sources..."
	javac -encoding UTF-8 -d $(BINDIR) -cp $(JUNIT_JAR) $(JAVA_SOURCES)

# 테스트 타겟 (make check 입력 시 실행)
test: $(BINDIR) $(REPORTDIR)
	@echo "[+] Running tests with JUnit..."
	# JUnit Platform Console Standalone JAR가 bin/lib/junit.jar에 있다고 가정
	java -jar $(JUNIT_JAR) \
		--class-path $(BINDIR) \
		--scan-class-path \
		--details=tree \
		--details-theme=ascii \
		--reports-dir $(REPORTDIR) \
		--config=junit.platform.output.capture.stdout=true \
		--config=junit.platform.reporting.open.xml.enabled=true \
		> $(REPORTDIR)/test-output.txt

# 디렉토리 생성
$(BINDIR):
	mkdir -p $(BINDIR)
$(LIBDIR):
	mkdir -p $(LIBDIR)
$(REPORTDIR):
	mkdir -p $(REPORTDIR)

# 정리 타겟
clean:
	@echo "[+] Cleaning up..."
	rm -rf $(BINDIR) $(REPORTDIR)

.PHONY: all build test clean