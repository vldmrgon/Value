#!/bin/bash

sudo chown systemd-coredump ./data/docker/mongodb/keyfile/key
sudo chmod 400 ./data/docker/mongodb/keyfile/key

docker-compose -f ./data/docker/docker-compose.yml --env-file ./data/docker/development.env up -d