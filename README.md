# demo-multi-module-example
Demo project to showcase our technology

[![Github Action](https://github.com/mobileaction/demo-multi-module-example/actions/workflows/main.yaml/badge.svg)](https://github.com/mobileaction/demo-multi-module-example/actions/workflows/main.yaml)
[![DeepSource](https://deepsource.io/gh/mobileaction/demo-multi-module-example.svg/?label=active+issues&show_trend=true&token=anxMR95oOiOk9nLdPlEXrows)](https://deepsource.io/gh/mobileaction/demo-multi-module-example/?ref=repository-badge)

#### Queues
```
ma-example-request-queue
ma-example-result-queue
ma-example-result-problem-queue
ma-example-request-problem-queue
```

#### Endpoint 1 - Queue-posts
```
POST /api/admin/queue/posts
Host: localhost:${PORT}
Authorization: Basic base64(username:password)
Content-Type: application/json
```

#### Endpoint 2 - Get Posts
```
GET /api/posts
Host: localhost:${PORT}
Authorization: Basic base64(username:password)
Content-Type: application/json
```

#### Endpoint 3 - Delete Post By ID
```
DELETE /api/posts/{postId}
Host: localhost:${PORT}
Authorization: Basic base64(username:password)
```

#### Task 2 - Local Test Steps

Start RabbitMQ:
```
docker run --rm --name ma-rabbit \
  -p 5672:5672 -p 15672:15672 \
  rabbitmq:3-management
```

Create required local queues:
```
for q in \
  ma-example-request-queue \
  ma-example-result-queue \
  ma-example-result-problem-queue \
  ma-example-request-problem-queue \
  ma-example-user-request-queue \
  ma-example-user-result-queue
do
  curl -u guest:guest -X PUT \
    -H "content-type: application/json" \
    -d '{"durable":true}' \
    "http://localhost:15672/api/queues/%2F/$q"
done
```

Start web:
```
java -classpath gradle/wrapper/gradle-wrapper.jar \
  org.gradle.wrapper.GradleWrapperMain \
  :web:bootRun --args='--spring.profiles.active=dev --server.port=9090 --messaging.server.url=amqp://guest:guest@localhost/%2F'
```

Start worker in another terminal:
```
java -classpath gradle/wrapper/gradle-wrapper.jar \
  org.gradle.wrapper.GradleWrapperMain \
  :worker:bootRun --args='--spring.profiles.active=dev --messaging.server.url=amqp://guest:guest@localhost/%2F'
```

Seed posts first:
```
curl -u admin1: -X POST http://localhost:9090/api/admin/queue/posts
```

Check posts:
```
curl -u user1: http://localhost:9090/api/posts
```

Trigger user crawl requests from distinct post user IDs:
```
curl -u admin1: -X POST http://localhost:9090/api/admin/queue/users
```

Verify saved users in H2 console:
```
URL: http://localhost:9090/h2-console
JDBC URL: jdbc:h2:mem:db_example
User Name: sa
Password:
```

Run:
```sql
select * from user_details;
```
# demo-multi-module-example-dev
