sudo docker exec -it HOMEDB mysqldump --no-data --skip-comments -u root -ppleasehackme recipes > ../db/recipes_schema.sql
grep -v 'mysqldump: \[Warning\]' ../db/recipes_schema.sql > ../db/recipes_schema_tmp.sql
mv ../db/recipes_schema_tmp.sql ../db/recipes_schema.sql

