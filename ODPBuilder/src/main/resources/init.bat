@ECHO OFF

:: Author - Ruairí O'Brien
:: Created - 25/07/2012

:: This script prepares the workspace for generating ODP builds.


:: Folders where specific content gets added
SET Configs=Workspace\Configuration_XML_Files
SET Images=Workspace\Images
SET Games=Workspace\Place_Games_Here
:: Folders for tools
SET App=App
SET key=%App%\key
SET lib=%App%\lib
SET ODP=%App%\ODP_APK
SET tools=%App%\tools
:: Where all the good stuff happens
SET WorkingDir=%App%\WORKING
SET Decompiled=%WorkingDir%\DECOMPILED
SET Signed=%WorkingDir%\SIGNED
SET Temp=%WorkingDir%\TEMP
SET Unsigned=%WorkingDir%\UNSIGNED

:: ODP Out
SET OutFolder=APK_OUT

IF NOT EXIST "Workspace" (mkdir "Workspace")
IF NOT EXIST "%Configs%" (mkdir "%Configs%")
IF NOT EXIST "%Images%" (mkdir "%Images%")
IF NOT EXIST "%Games%" (mkdir "%Games%")

IF NOT EXIST "%OutFolder%" (mkdir "%OutFolder%")

IF NOT EXIST "%App%" (mkdir "%App%")
IF NOT EXIST "%key%" (mkdir "%key%")
IF NOT EXIST "%lib%" (mkdir "%lib%")
IF NOT EXIST "%ODP%" (mkdir "%ODP%")
IF NOT EXIST "%tools%" (mkdir "%tools%")
IF NOT EXIST "%WorkingDir%" (mkdir "%WorkingDir%")
IF NOT EXIST "%Decompiled%" (mkdir "%Decompiled%")
IF NOT EXIST "%Signed%" (mkdir "%Signed%")
IF NOT EXIST "%Temp%" (mkdir "%Temp%")
IF NOT EXIST "%Unsigned%" (mkdir "%Unsigned%")