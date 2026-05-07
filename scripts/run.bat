@echo off
REM Script de compilação e execução - Versão Clássica
REM Compilações organizada - gera .class em build/

echo ========================================
echo Simulador de Substituicao de Paginas
echo Versao Classica
echo ========================================
echo.

echo Compilando arquivos Java...
cd src
javac -d ../build *.java

if %errorlevel% neq 0 (
    echo Erro na compilacao!
    cd ..
    pause
    exit /b 1
)

echo Compilacao concluida com sucesso!
echo.
echo Iniciando aplicacao...
echo.

cd ../build
java SimulatorGUI

cd ..
pause
