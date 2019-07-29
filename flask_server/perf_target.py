from flask import Flask, escape, request, send_file

app = Flask(__name__)

@app.route('/')
def hello():
    name = request.args.get("name", "World")
    return f'Hello, {escape(name)}!'

@app.route('/access.log')
def access_log():
    return send_file('access.log', cache_timeout=-1)
