@echo off
set fname=%date:~0,4%%date:~5,2%%date:~8,2%0%time:~1,1%%time:~3,2%%time:~6,2%
set mypath=%cd%\worm
java -jar worm\whvWorm_htmlunitSrc.jar %mypath% 2>&1| tee log_%fname%.txt

pause;
cmd


