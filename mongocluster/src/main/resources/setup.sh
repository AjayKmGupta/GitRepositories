mvn clean package
docker build -t my-spring-app .
#This has to be run where the docker-compose.yml file is present
docker-compose -p mongo-local-cluster up -d
docker exec -it mongo1 mongosh
rs.initiate({
  _id: "rs0",
  members: [
    { _id: 0, host: "mongo1:27017" },
    { _id: 1, host: "mongo2:27017" },
    { _id: 2, host: "mongo-arbiter:27017", arbiterOnly: true }
  ]
})
use localTestDB;
db.products.insertOne({
  name: "Sample Product"
})