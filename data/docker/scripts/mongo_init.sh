#!/bin/bash

sleep 7

echo Mongo cluster initialization was started...

mongosh --host mongodb1:27017 --username $DB_USERNAME --password $DB_PASSWORD <<EOF

 var mongo_config = {
   "_id": "mongo-replica",
   "version": 1,
   "members": [
     {"_id": 0, "host": "mongodb1", "priority": 2},
     {"_id": 1, "host": "mongodb2", "priority": 1},
     {"_id": 2, "host": "mongodb3", "priority": 0}
   ]
 };

 rs.initiate(mongo_config, { force: true });
 rs.reconfig(mongo_config, { force: true });

 rs.secondaryOk();

 db.getMongo().setReadPref('nearest');
 db.getMongo().setSecondaryOk();

EOF
