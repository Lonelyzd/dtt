@echo off
echo.
echo [ÐÅÏ¢] ´ò°üWeb¹¤³Ì£¬Éú³ÉdistÎÄ¼þ¡£
echo.

%~d0
cd %~dp0

cd ..
npm run build:prod

pause