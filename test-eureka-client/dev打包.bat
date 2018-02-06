@echo off

@echo start package
@echo.

::打包client-test包
@echo start compile test-eureka-client-test
cd ../test-eureka-client-test
call mvn clean install -D maven.test.skip=true -e
@echo.
@echo.
@echo.

:: 打包从client包
@echo start compile test-eureka-client
call mvn clean install -D maven.test.skip=true -P dev -e
@echo.

@echo end build package

pause
