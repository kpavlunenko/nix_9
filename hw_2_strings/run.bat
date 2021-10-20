call cd..
call cd string_util
call mvn clean install
call cd..
call cd hw_2_strings
call mvn clean install
call chcp 1251
call java -jar target/hw_2_strings.jar