@ECHO OFF

:: Author - Ruairí O'Brien
:: Created - 25/07/2012

:: This scripts compiles an ODP APK file with provided configurations and places the finished file in the APK_OUT folder.

:: Heap size for running JARs
SET heapSize=512

:: Define working folders

:: Folders where specific content gets added
SET Configs=Workspace\Configuration_XML_Files
SET Images=Workspace\Images
SET Games=Workspace\Place_Games_Here

:: Define tools
SET Tools=App\tools
SET ApkTool=%Tools%\apktool.jar
SET BatchSubstitute=%Tools%\BatchSubstitute.bat
SET SevenZip=%Tools%\7z
SET Jarsigner=%Tools%\jarsigner
SET Zipalign=%Tools%\zipalign

:: Where all the good stuff happens
SET WorkingDir=App\WORKING
SET Decompiled=%WorkingDir%\DECOMPILED
SET Signed=%WorkingDir%\SIGNED
SET Temp=%WorkingDir%\TEMP
SET Unsigned=%WorkingDir%\UNSIGNED

:: ODP In and Out
SET ODP_APK=App\ODP_APK\ODP.apk
SET ODP_APK_Decompiled=%Decompiled%\ODP.apk
SET OutFolder=APK_OUT

:: The key for signing APKs
SET key=App\key\trust5Wrapper.keystore


:Initialize
REM :: Just making sure all the neccessary folder exist.
IF Not EXIST "Workspace" (mkdir "Workspace") 
IF Not EXIST "%Configs%" (mkdir "%Configs%")
IF Not EXIST "%Images%" (mkdir "%Images%")
IF Not EXIST "%Games%" (mkdir "%Games%")

IF Not EXIST "%OutFolder%" (mkdir "%OutFolder%")
:: Remove any old APKs.
FOR %%A IN ("%OutFolder%"\*) DO DEL %%A

IF Not EXIST "%WorkingDir%" (mkdir "%WorkingDir%")
IF Not EXIST "%Decompiled%" (mkdir "%Decompiled%")
IF Not EXIST "%Signed%" (mkdir "%Signed%")
IF NOT EXIST "%Temp%" (mkdir "%Temp%")
IF NOT EXIST "%Unsigned%" (mkdir "%Unsigned%")

REM :: Always decompile the ODP at start so the decomiled code doesn't have to be remove manualy when updating.
IF EXIST "%ODP_APK_Decompiled%" (rmdir /q /s "%ODP_APK_Decompiled%" 2>nul)
java -Xmx%heapSize%m -jar %ApkTool% d "%ODP_APK%" "%ODP_APK_Decompiled%"

echo finished Initialization

:CopyAssets
SET AssetsFolder=%ODP_APK_Decompiled%\assets
SET EmbedImageFolder=%AssetsFolder%\site\img
SET BackgroundImageFolder=%Images%\background
SET EmbedBackgroundImageFolder=%EmbedImageFolder%\background 
SET MoreGamesButtonImageFolder=%Images%\moregamesbutton
SET EmbedMoreGamesButtonImageFolder=%EmbedImageFolder%\moregamesbutton
SET PlayFreeImageFolder=%Images%\playfree
SET EmbedPlayFreeImageFolder=%EmbedImageFolder%\playfree
SET TryFreeImageFolder=%Images%\tryfree
SET EmbedTryFreeImageFolder=%EmbedImageFolder%\tryfree

SET ResFolder=%ODP_APK_Decompiled%\res\values

REM :: Put games in the assets folder
If Not EXIST "%AssetsFolder%" (mkdir "%AssetsFolder%")
xcopy %Games% %AssetsFolder% /s/y/e/i


REM :: Create Images fodler for embeded HTML
IF Not EXIST "%EmbedImageFolder%" (mkdir "%EmbedImageFolder%")

REM :: Copy Background Image
IF Not EXIST "%BackgroundImageFolder%" (mkdir "%BackgroundImageFolder%")
IF Not EXIST "%EmbedBackgroundImageFolder%" (mkdir "%EmbedBackgroundImageFolder%")
xcopy %BackgroundImageFolder% %EmbedBackgroundImageFolder% /s/y/e/i
REM :: Copy More Games Button
IF Not EXIST "%MoreGamesButtonImageFolder%" (mkdir "%MoreGamesButtonImageFolder%")
IF Not EXIST "%EmbedMoreGamesButtonImageFolder%" (mkdir "%EmbedMoreGamesButtonImageFolder%")
xcopy %MoreGamesButtonImageFolder% %EmbedMoreGamesButtonImageFolder% /s/y/e/i
REM :: Copy Play Free Banner
IF Not EXIST "%PlayFreeImageFolder%" (mkdir "%PlayFreeImageFolder%")
IF Not EXIST "%EmbedPlayFreeImageFolder%" (mkdir "%EmbedPlayFreeImageFolder%")
xcopy %PlayFreeImageFolder% %EmbedPlayFreeImageFolder% /s/y/e/i
REM :: Copy Try Free Banners
IF Not EXIST "%TryFreeImageFolder%" (mkdir "%TryFreeImageFolder%")
IF Not EXIST "%EmbedTryFreeImageFolder%" (mkdir "%EmbedTryFreeImageFolder%")
xcopy %TryFreeImageFolder% %EmbedTryFreeImageFolder% /s/y/e/i

REM :: Copy xml resources
xcopy %Configs% %ResFolder% /s/y/e/i

echo finished copying assets

:CompileAPK
java -Xmx512m -jar %ApkTool% b "%ODP_APK_Decompiled%" "%Unsigned%\ODP.apk" 

echo finished compiling

:SignAndZipAlign

REM :: ... sign the apk and put the signed file in the APK_OUT folder
%Jarsigner% -verbose -digestalg SHA1 -sigalg MD5withRSA ^
-keystore "%key%" ^
-storepass "trust5" ^
-signedjar "%Signed%\ODP.apk" "%Unsigned%\ODP.apk" trust5Wrapper
	
REM :: zipalign the signed apk and put in the APK_OUT folder
%Zipalign% -v 4 "%Signed%\ODP.apk" "%OutFolder%\ODP.apk"
REM :: Cleaning up
DEL "%Unsigned%\ODP.apk"
DEL "%Signed%\ODP.apk"

echo Finished ziplign and sign

:End
ECHO Script finished
	



