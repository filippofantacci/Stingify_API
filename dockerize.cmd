@ECHO OFF 

echo ------------ generate jar ------------
pause
call mvn clean install -DskipTests


echo ---------- greate image-----------
pause
call docker build -t stingify/stingify-api .


echo --------- run container ---------
pause
::call docker run  --name stingify-api -p 8081:8081 stingify/stingify-api


