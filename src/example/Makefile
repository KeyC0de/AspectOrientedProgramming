# Input Java files (components).
JAVA_FILES=example1.java
# Aspect in AspectJ language.
AJ_ASPECT=AspectDemo.aj

justJavaCompile: ${JAVA_FILES}
	@javac ${JAVA_FILES}

justJavaRun: justJavaCompile
	@echo "== Running without AspectJ =="
	@java example1

aspectJCompile: ${JAVA_FILES} ${AJ_ASPECT}
#	@ajc -source 1.8 ${JAVA_FILES} ${JAVA_ASPECT}
	@ajc -source 1.8 ${JAVA_FILES} ${AJ_ASPECT}

aspectJRun: aspectJCompile
	@echo "== Running with AspectJ =="
	@java example1
