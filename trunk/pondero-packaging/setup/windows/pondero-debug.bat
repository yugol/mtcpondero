rem @echo off
echo PONDERO CONSOLE
set PONDERO_HOME=%~dp0
set JAVA="%PONDERO_HOME%jre\bin\java.exe"
cd "%PONDERO_HOME%"
%JAVA% -jar bin\pondero-install.jar "%PONDERO_HOME%"
%JAVA% -cp bin\pondero.jar;bin\pondero-libs.jar;tests\* pondero.ui.Pondero "%PONDERO_HOME%"
