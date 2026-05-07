@echo off
REM Script de compilação e execução - Versão Moderna

echo ========================================
echo Simulador de Substituicao de Paginas
echo Versao Moderna com Design Profissional
echo ========================================
echo.

echo Compilando arquivos Java...
javac *.java

if %errorlevel% neq 0 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo Compilacao concluida com sucesso!
echo.
echo Iniciando aplicacao...
echo.

java ModernSimulatorGUI

pause
