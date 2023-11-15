## Introduction

This project is a CQRS (Command Query Responsibility Segregation) pattern architecture

* Data-Team service for commands
* E-Commerce service for queries

## Environment Variables

#### Data-Team:
```
MONGO_HOST=127.0.0.1;MONGO_USERNAME=admin;MONGO_PASSWORD=r00t;MONGO_AUTH_DATABASE=admin;REDIS_PASSWORD=master;REDIS_PORT=6379;REDIS_HOST=127.0.0.1;MONGO_PORT=27017;APPLICATION_HOST=8080
```
#### E-Commerce:

```
REDIS_PASSWORD=master;REDIS_HOST=127.0.0.1;REDIS_PORT=6379;APPLICATION_HOST=8088
```
## UI-Services
#### Portainer:
 - Host: http://localhost:9000/
 - Username: admin
 - Password: }dg$ZWkX!=o"3`hJ58619V[c7D_qEI2N

#### Redis-commander
 - Host: http://localhost:8081/
 - Username: master 
 - Password: master

## Useful commands:

#### Reset portainer password (Linux Fedora):

- docker run --rm -v /var/lib/docker/volumes/docker_portainer_data/_data/:/data portainer/helper-reset-password

#### Start docker cluster:
 - ./docker-start.sh

#### Api-protocol collection for the Postman:
- ./data/postman/api-protocol.json