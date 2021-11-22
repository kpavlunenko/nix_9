call cd..
call cd json_util
call mvn clean install
call cd..
call cd hw_7_ionio
call mvn clean install
call chcp 1251
call java -jar target/hw_7_ionio.jar