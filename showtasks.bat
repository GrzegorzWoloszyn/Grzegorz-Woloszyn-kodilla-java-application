call runcrud
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo runcrud.bat has errors- breaking work
got to fail

:browser
echo.
start chrome http://localhost:8080/crud/v1/task/getTasks

goto end

:fail
echo.
echo There were some errors.

:end
echo.
echo Work is done.