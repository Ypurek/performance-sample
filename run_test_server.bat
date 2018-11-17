echo off
CD sample_server
COPY /Y db_clean.json db.json
CD ..
json-server --watch sample_server/db.json