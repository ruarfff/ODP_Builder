:: Folders where specific content gets added
SET Configs=Workspace\Configuration_XML_Files
SET Images=Workspace\Images
SET Games=Workspace\Place_Games_Here
:: Where all the good stuff happens
SET WorkingDir=App\WORKING
SET Decompiled=%WorkingDir%\DECOMPILED
SET Signed=%WorkingDir%\SIGNED
SET Temp=%WorkingDir%\TEMP
SET Unsigned=%WorkingDir%\UNSIGNED

:: ODP Out
SET OutFolder=APK_OUT

IF Not EXIST "Workspace" (mkdir "Workspace") 
IF Not EXIST "%Configs%" (mkdir "%Configs%")
IF Not EXIST "%Images%" (mkdir "%Images%")
IF Not EXIST "%Games%" (mkdir "%Games%")

IF Not EXIST "%OutFolder%" (mkdir "%OutFolder%")

IF Not EXIST "%WorkingDir%" (mkdir "%WorkingDir%")
IF Not EXIST "%Decompiled%" (mkdir "%Decompiled%")
IF Not EXIST "%Signed%" (mkdir "%Signed%")
IF NOT EXIST "%Temp%" (mkdir "%Temp%")
IF NOT EXIST "%Unsigned%" (mkdir "%Unsigned%")