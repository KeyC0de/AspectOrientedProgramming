# Input Java files (components).
JAVA_FILES=Ask2TestLList.java MyThread.java NikLList.java
# Aspect in AspectJ language.
AJ_ASPECT=NikLList_aspect_Ts.aj

justJavaCompile: ${JAVA_FILES}
	@javac ${JAVA_FILES}

justJavaRun: justJavaCompile
	@echo "== Running without AspectJ =="
	@java Ask2TestLList

aspectJCompile: ${JAVA_FILES} ${AJ_ASPECT}
#	@ajc -source 1.8 ${JAVA_FILES} ${JAVA_ASPECT}
	@ajc -source 1.8 ${JAVA_FILES} ${AJ_ASPECT}

aspectJRun: aspectJCompile
	@echo "== Running with AspectJ =="
	@java Ask2TestLList
