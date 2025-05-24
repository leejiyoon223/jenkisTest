pipeline {
    agent any

    environment {
        JUNIT_JAR_URL = 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.1/junit-platform-console-standalone-1.7.1.jar'
        JUNIT_JAR_PATH = 'jenkinsTest_0524/lib/junit.jar'
	    CLASS_DIR = 'jenkinsTest_0524/bin'
	    REPORT_DIR = 'jenkinsTest_0524/test-reports'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare') {
            steps {
                sh '''
                    mkdir -p jenkinsTest_0524/bin
		            mkdir -p jenkinsTest_0524/test-reports
		            mkdir -p jenkinsTest_0524/lib
		            echo "[+] Downloading JUnit JAR..."
		            curl -L -o jenkinsTest_0524/lib/junit.jar ${JUNIT_JAR_URL}
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                    echo "[+] Compiling source files..."
                    find jenkinsTest_0524/src -name "*.java" > sources.txt
                    javac -encoding UTF-8 -d ${CLASS_DIR} -cp ${JUNIT_JAR_PATH} @sources.txt
                '''
            }
        }

        stage('Test') {
            steps {
                sh '''
                    echo "[+] Running tests with JUnit..."
                    java -jar ${JUNIT_JAR_PATH} \
                         --class-path ${CLASS_DIR} \
                         --scan-class-path \
                         --details=tree \
                         --details-theme=ascii \
                         --reports-dir ${REPORT_DIR} \
                         --config=junit.platform.output.capture.stdout=true \
                         --config=junit.platform.reporting.open.xml.enabled=true \
                         > ${REPORT_DIR}/test-output.txt
                '''
            }
        }
    }

    post {
        always {
            echo "[*] Archiving test results..."
            junit "${REPORT_DIR}/**/*.xml"
            archiveArtifacts artifacts: "${REPORT_DIR}/**/*", allowEmptyArchive: true
        }

        failure {
            echo "Build or test failed!"
        }

        success {
	        echo "Build and test succeeded!"
	        mail to: 'leejiyoon223@naver.com',
	             subject: "빌드 성공: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
	             body: "Jenkins 빌드가 성공적으로 완료되었습니다.\n자세한 내용은 Jenkins 콘솔 출력을 확인하세요."
	    }
    }
}