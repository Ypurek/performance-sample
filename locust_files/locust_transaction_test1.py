from locust import HttpLocust, TaskSet, task


class FlowException(Exception):
    pass


class UserBehavior(TaskSet):
    @task(1)
    def check_flow(self):
        # step 1
        new_post = {'userId': 1, 'title': 'my shiny new post', 'body': 'hello everybody'}
        post_response = self.client.post('/posts', json=new_post)
        if post_response.status_code != 201:
            raise FlowException('post not created')
        post_id = post_response.json().get('id')

        # step 2
        new_comment = {
            "postId": post_id,
            "name": "my comment",
            "email": "test@user.habr",
            "body": "Author is cool. Some text. Hello world!"
        }
        comment_response = self.client.post('/comments', json=new_comment)
        if comment_response.status_code != 201:
            raise FlowException('comment not created')
        comment_id = comment_response.json().get('id')

        # step 3
        self.client.get(f'/comments/{comment_id}', name='/comments/[id]')
        if comment_response.status_code != 200:
            raise FlowException('comment not read')


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 1000
    max_wait = 2000
