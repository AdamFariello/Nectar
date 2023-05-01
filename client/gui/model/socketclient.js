var net = require('net');
var client = net.connect(8999, 'localhost');
client.write('Hello from node.js');
client.end();