from locust import HttpLocust, TaskSet, task
import random as r


# class UserBehavior(TaskSet):
#     def __init__(self, parent):
#         super(UserBehavior, self).__init__(parent)
#         self.created_posts = list()
class UserBehavior(TaskSet):
    created_posts = []

    @task(1)
    def create_post(self):
        new_post = {'userId': 1, 'title': 'my shiny new post', 'body': 'hello everybody'}
        post_response = self.client.post('/posts', json=new_post)
        if post_response.status_code != 201:
            return
        post_id = post_response.json().get('id')
        self.created_posts.append(post_id)

    @task(10)
    def read_post(self):
        if len(self.created_posts) == 0:
            return
        post_id = r.choice(self.created_posts)
        self.client.get(f'/posts/{post_id}', name='read post')


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 1000
    max_wait = 2000
