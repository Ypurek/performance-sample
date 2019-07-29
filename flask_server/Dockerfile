FROM python:3.7-alpine
COPY perf_target.py /app/perf_target.py
WORKDIR /app
RUN pip install --upgrade pip && pip install flask gunicorn
EXPOSE 8888
CMD gunicorn -w 4 -b 0.0.0.0:8888 --access-logfile access.log perf_target:app
