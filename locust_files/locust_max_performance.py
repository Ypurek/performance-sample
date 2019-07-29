from locust import HttpLocust, TaskSet, task


class UserBehavior(TaskSet):
    @task(1)
    def posts(self):
        self.client.get("/")


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    host = 'http://nas:8888'
    min_wait = 1000
    max_wait = 2000
