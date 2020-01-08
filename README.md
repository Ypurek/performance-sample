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
* Python 3.8
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
* 2.3 Gb RAM
* 10 039 Threads
* 155 227 requests
* 82.74% failed
* Avg resp time 15 sec
* Median resp time 21 sec
* Throughtput 404.75/s
### Locust results
* version 0.13.5
* 13% CPU
* 450 Mb RAM
* 14 Threads
* 111 232 requests
* 100% pass
* Avg resp time 952 ms
* Median resp time 120 ms
* Throughtput 370/s
Notes:
* locust works slower with PC names (than IP addresses)
* locust ramp time <= 4000 users/min
### Gatling results
* version 3.1.3
* 20% CPU
* 1200 Mb RAM
* 77 Thre241 466 requests
* 80% failed
* Avg resp time 8 sec
* Median resp time 3.3 sec
* Throughtput 805/s !!!!!

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



## Run test server

## Run JMeter
```bash
jmeter -n -t C:\Dev\Projects\performance-sample\jmeter_files\jmeter_max_performance.jmx -l results111.txt -e -o C:\Dev\NewReport
```

## Run Locust
Run test
```bash
locust -f my_locust_file.py --host=http://localhost:3000
```
## Run Tsung
https://gitlab.com/logi-ce/docker-tsung
```bash
docker run -d --name tsung -v  C:\Dev\Projects\performance-sample\tsung-files:/root/.tsung/ -p 8090:8090 -p 8091:8091 castelislogice/tsung

docker exec tsung tsung start -f tsung_max_performance.xml
```

## Run Yandex Tank
https://yandextank.readthedocs.io/en/latest/install.html
```bash
docker run -v C:\Dev\Projects\performance-sample\yandex_tank_files:/var/loadtest --net host -it direvius/yandex-tank


```
