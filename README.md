# performance samples
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
* CPU Core i7-7700 2.8 Ghz
* RAM 16 Gb
* Windows 10 Pro x64
* Java 12
* Scala 2.13
* Python 3.6
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
### Test Profile #2:
* 500 users
* 1 get request
* pause 1000 - 2000 ms
* Ramp time 10 sec
* Test time 2 min
### Test Results
TODO


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


