from locust import HttpLocust, TaskSet, task, between


class UserBehavior(TaskSet):
    @task
    def posts(self):
        self.client.get("/")


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    host = 'http://192.168.1.86:8888'
    wait_time = between(1, 2)
