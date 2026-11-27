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
ma-example-user-request-queue
ma-example-user-result-queue
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

#### Endpoint 4 - Queue-users
```
POST /api/admin/queue/users
Host: localhost:${PORT}
Authorization: Basic base64(username:password)
Content-Type: application/json
```
Crawls users of distinct userIds in posts table and saves them to DB.

# demo-multi-module-example-dev
