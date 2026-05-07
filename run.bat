@echo off
REM Script de compilação e execução - Windows

echo Compilando arquivos Java...
javac *.java

if %errorlevel% neq 0 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo.
echo Compilacao concluida com sucesso!
echo Iniciando aplicacao...
echo.

java SimulatorGUI

pause
