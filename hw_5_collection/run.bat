call cd..
call cd collection_util
call mvn clean install
call cd..
call cd hw_5_collection
call mvn clean install
call chcp 1251
call java -jar target/hw_5_collection.jar
