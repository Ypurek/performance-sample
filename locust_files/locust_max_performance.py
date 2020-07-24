from locust import HttpUser, task, between


class WebsiteUser(HttpUser):
    host = 'http://192.168.1.86:8888/'
    wait_time = between(1, 2)

    @task
    def posts(self):
        self.client.get("/")
