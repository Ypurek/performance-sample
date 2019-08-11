from locust import HttpLocust, TaskSet, task


class UserBehavior(TaskSet):
    @task
    def posts(self):
        self.client.get("/")


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    host = 'http://192.168.2.136:8888/'
    min_wait = 1000
    max_wait = 2000
