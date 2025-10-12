Mongo Cluster Testing with Spring Boot Java

This application runs three Mongo DB nodes as below.
1. Data Node (primary/secondary)
2. Data Node (primary/secondary)
3. Arbiter Node (Used only for voting)

It also creates a docker container of the given spring boot application.
All these containers are started using the command provided in setup.sh file //docker-compose -p mongo-local-cluster up -d
Execute curl http://localhost:8080/api/products to verify the response

Bring down the primary Node and try the command curl http://localhost:8080/api/products

The response is same without any error.

Execute this curl to create a new product curl -X POST http://localhost:8080/api/products/create -H "Content-Type: application/json" -d '{"name": "cream"}'

The mongo cluster is functioning without any error/issue even though the primary is down. Since the secondary node is promoted as primary.

Both read and write operations are working without any issue