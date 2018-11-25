# performance sample
## Links
* Locust - https://locust.io/
* Sample server - https://jsonplaceholder.typicode.com/

## preconditions
* To use locust python required. Python 3.6 used in provided samples
* To run test server NodeJS required. NodeJS 11 used in provided samples

## Run test server
```bash
json-server --watch sample_server/db.json
```
## Run Locust
```bash
locust -f my_locust_file.py --host=http://localhost:3000
```

