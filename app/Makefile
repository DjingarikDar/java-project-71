#Makefile
run-dist: install
	./build/install/app/bin/app

build:
	./gradlew clean build
	
install:
	./gradlew installDist

report:
	./gradlew jacocoTestReport


.PHONY: build
