# performance samples
This repo contains sample tests for
* Locust https://locust.io
* JMeter https://Jmeter.apache.org
* Gatling https://gatling.io
and few sample servers to play with

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
## Target server to test high performance
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
## Locust Samples
Samples contains:
* basic tests
* transaction tests
* advanced tests to check Locusts features
### Preocnditions
* Python (_v3.8 used to create samples_) 
* Locust (_v0.13.5 used to create samples_) 
### Running
Run test
```bash
locust -f my_locust_file.py --host=http://localhost:3000
```
## JMeter Samples
TODO

## Gatling Samples
TODO

## Performance tools comparison
| Tool                | Locust        | JMeter          | Gatling                                |
|---------------------|---------------|-----------------|----------------------------------------|
| Language            | Python        | Java            | Scala                                  |
| Platform            | X-platform    | X-platform      | X-platform                             |
| Install             | 1 line comand | Unpack arcive   | Get maven, get plugins, unpack archive |
| Record/playback     | No            | Yes             | Yes                                    |
| Simple test, aprox. |  5 min        | 10 min          | 20 min                                 |
| Transaction tests   | Yes           | Yes             | Yes                                    |
| Protocols           | HTTP+         | various         | HTTP+                                  |
| Distributed testing | Yes           | Yes             | Only Enterprise                        |
| Reports             | Live          | Live, after run | after run                              |

Q: Why there are no Yandex.Tank, Artillery Load Runner?
A: Don't support transactions, too raw, paid one. Maybe will add some tools later

## Max performance test
### Target prepaparion
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

### Test PC spec  
* CPU AMD Ryzen 7 4800H 2.9 Ghz
* RAM 16 Gb
* Windows 10 2004 Pro x64
* Java 8
* Scala 2.12
* Python 3.8
### Tested Server on NAS:
* CPU Intel Celeron J1900 2 Ghz
* RAM 8 Gb
* Flask WS in Docker python:3.7-alpine 4 Gb RAM
### Network:
* 100 Mbps
### Test Profile #1:
* 10 000 users
* 1 get request
* pause 1000 - 2000 ms
* Ramp time 100 sec
* Test time 5 min  

|Metric                      | Locust | JMeter | Gatling |
|----------------------------|--------|--------|---------|
| Throughput (PRS)           | 533    | 475    | 794     |
| Average response time, sec | 10.5   | 15.6   | 9.8     |
| Median response time, sec  | 13     | 21     | 10      |
| Test PC RAM, Gb            | 0.8    | 2.3    | 1.5     |
| Test PC Threads            | 14     | 10056  | 117     |
| Total requests             | 114 882| 100 797| 142 997 |
| Success rate               | 100    | 36     | 29      |

### Test Profile #2:
* 500 users
* 1 get request
* pause 1000 - 2000 ms
* Ramp time 10 sec
* Test time 2 min  

|Metric                     | Locust | JMeter | Gatling |
|---------------------------|--------|--------|---------|
| Throughput (PRS)          | 330    | 235    | 269     |
| Average response time, ms | 30     | 50     | 27      |
| Median response time, ms  | 14     | 6      | 10      |
| Test PC RAM, Mb           | 59     | 1280   | 1252    |
| Test PC Threads           | 14     | 528    | 84      |
| Total requests            | 36 731 | 27 805 | 32 335  |
| Success rate              | 100    | 100    | 100     |
