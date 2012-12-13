***************************************************************************
Details for Trust5 Wrapper Keystore (30/05/2012 - Ruairí O Brien)
***************************************************************************

Key store name: trust5Wrapper.keystore
Alias:		trust5Wrapper
Password: 	trust5
Owner: 		Trust5
Unit: 		Wrapper
Organization: 	Trust5
City: 		Dublin
Province: 	Leinster
Country Code: 	IE


Usage:
jarsigner -verbose -digestalg SHA1 -sigalg MD5withRSA -keystore
 %path to key store here%
-storepass %password%
-signedjar "the name you want for your signed apk here" "the apk you want to sign here" %alias%

Example:
jarsigner -verbose -digestalg SHA1 -sigalg MD5withRSA 
-keystore "C:\keys\trust5Wrapper.keystore"
-storepass "trust5" 
-signedjar "somegame_signed.apk" "somegame.apk" trust5Wrapper


******************************************************************************
To verify:

jarsigner -verify -verbose -certs somegame_signed.apk


******************************************************************************
Finally:

zipalign -v 4 somegame_signed.apk somegame_ready.apk
