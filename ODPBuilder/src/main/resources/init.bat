@ECHO OFF

:: Author - Ruairí O'Brien
:: Created - 25/07/2012

:: This script prepares the workspace for generating ODP builds.

:: Folders where specific content gets added
SET Workspace=workspace
SET Images=%Workspace%\images
SET Games=%Workspace%\place_games_here
:: Folders for tools
SET App=app
SET ODP=%App%\odp_apk
:: Where all the good stuff happens
SET WorkingDir=%App%\working
SET Decompiled=%WorkingDir%\decompiled
SET Signed=%WorkingDir%\signed
SET Temp=%WorkingDir%\temp
SET Unsigned=%WorkingDir%\unsigned

:: ODP Out
SET OutFolder=APK_OUT

IF NOT EXIST "%Workspace%" (mkdir "%Workspace%")
IF NOT EXIST "%Images%" (mkdir "%Images%")
IF NOT EXIST "%Games%" (mkdir "%Games%")

IF NOT EXIST "%OutFolder%" (mkdir "%OutFolder%")

IF NOT EXIST "%App%" (mkdir "%App%")
IF NOT EXIST "%ODP%" (mkdir "%ODP%")
IF NOT EXIST "%WorkingDir%" (mkdir "%WorkingDir%")
IF NOT EXIST "%Decompiled%" (mkdir "%Decompiled%")
IF NOT EXIST "%Signed%" (mkdir "%Signed%")
IF NOT EXIST "%Temp%" (mkdir "%Temp%")
IF NOT EXIST "%Unsigned%" (mkdir "%Unsigned%")

:: Add image directories
SET BackgroundImageFolder=%Images%\background
SET MoreGamesButtonImageFolder=%Images%\moregamesbutton
SET PlayFreeImageFolder=%Images%\playfree
SET TryFreeImageFolder=%Images%\tryfree

IF NOT EXIST "%BackgroundImageFolder%" (mkdir "%BackgroundImageFolder%")
IF NOT EXIST "%MoreGamesButtonImageFolder%" (mkdir "%MoreGamesButtonImageFolder%")
IF NOT EXIST "%PlayFreeImageFolder%" (mkdir "%PlayFreeImageFolder%")
IF NOT EXIST "%TryFreeImageFolder%" (mkdir "%TryFreeImageFolder%")