# performance samples
## Performance tools comparison

TODO add table

## Max performance test
To collect metrics of performance testing tools created small server on Flask running in 4 gunicorn workers  
1. Get docker
2. Run commands below
```bash
cd flask_server
docker build --tag <your docker registry>/perf-target .
docker push <your docker registry>/perf-target:latest
```
To check docker container:  
1. Check port 8888 not used
2. Use next command
3. Open http://localhost:8888
```bash
docker run --rm -p 8888:8888 <your docker registry>/perf-target
```


Test PC:  
* CPU Core i7-7700 2.8 Ghz
* RAM 16 Gb
* Windows 10 Pro x64
* Java 12
* Scala 2.13
Tested Server on NAS:
* CPU Intel Celeron J1900 2 Ghz
* RAM 8 Gb
* Flask WS in Docker python:3.7-alpine 4 Gb RAM
Network:
* 100 Mbps
Profile:
* 10 000 users
* 1 get request
* pause 1000 - 2000 ms
* Ramp time 30 sec
* Test time 2 min

### JMeter results:
* version 5.1.1
* 20% CPU
* 2 Gb RAM
* 10 000 Threads
* ~70K requests
* 62% failed
* Avg resp time 14.7 sec
### Locust results
* version 0.11
* 12% CPU
* 450 Mb RAM
* 14 Threads
* ~25K requests
* 100% pass
* Avg resp time 21.7 sec
Notes:
* locust works slower with PC names (than IP addresses)
* locust ramp time <= 4000 users/min
### Gatling results
* version 3.1.3
* 30% CPU
* 800 Mb RAM
* 75 Threads
* ~127K requests
* 59% failed
* Avg resp time 10 sec


## Simple test server to play with
Server - https://jsonplaceholder.typicode.com/  
To run test server NodeJS required. NodeJS 11 used in provided samples  
To run server use command:  
```bash
json-server --watch sample_server/db.json
```

Data file provided in **sample_server** folder

To use clean data each time execute **run_test_server.bat** file
```bash
run_test_server.bat
```
or 
```bash
run_test_server.sh
```

## Locust
Locust - https://locust.io/
### Preocnditions
* Python (_v3.6 used to create samples_) 
* Locust (_v0.11.0 used to create samples_) 
### Running
Run test
```bash
locust -f my_locust_file.py --host=http://localhost:3000
```


## Run test server

## Run Locust


