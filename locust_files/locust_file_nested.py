from locust import HttpLocust, TaskSet, task


class Todo(TaskSet):
    @task(3)
    def index(self):
        self.client.get("/todos")

    @task(1)
    def stop(self):
        self.interrupt()


class UserBehavior(TaskSet):
    tasks = {Todo: 1}

    @task(3)
    def index(self):
        self.client.get("/")

    @task(2)
    def posts(self):
        self.client.get("/posts")


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 1000
    max_wait = 2000
