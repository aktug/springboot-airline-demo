# SPRINGBOOT - AIRPORT

### Run Instructions

```bash
mvn clean install --fail-at-end -Dmaven.test.skip=true
docker build ./ -t springbootapp
docker-compose rm -fv postgres
docker-compose up
```

### API Docs

You can view API docs at http://localhost:8080/swagger-ui.html

### Postman Collection

Postman collection is located at ./Postman/SpringBootDocker-AirlineDemo.postman_collection.json