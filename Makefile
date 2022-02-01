.PHONY: build
install: # очистить результаты предыдущей сборки и запустить новую.
	./gradlew clean install
run-dist: # запуск исполняемого файла
	./build/install/app/bin/app
check-updates: # проверка обновлений зависимостей
	./gradlew dependencyUpdates
lint: # проверка линтером
	./gradlew checkstyleMain
build: # сборка и проверка линтером
	./gradlew clean build
test:
	./gradlew test
report:
		./gradlew jacocoTestReport