::关闭 echo命令本身 @本行命令也不显示
@echo off
@echo.

@echo start compile test-auth-token
cd ../test-auth-token
call mvn clean install -Dmaven.test.skip=true -e
@echo end buil test-auth-token
@echo.
@echo.

::相对于打包文件的目录
@echo start compile test-eureka-web
cd ../test-eureka-web
call mvn clean install -Dmaven.test.skip=true -P dev  -e
@echo end build test-eureka-web
@echo.
@echo.

@echo end package

pause


