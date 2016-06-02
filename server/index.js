var app = require('http').createServer(handler)
var io = require('socket.io')(app);
var fs = require('fs');

app.listen(3001);

function handler (req, res) {
  fs.readFile(__dirname + '/index.html',
  function (err, data) {
    if (err) {
      res.writeHead(500);
      return res.end('Error loading index.html');
    }

    res.writeHead(200);
    res.end(data);
  });
}

io.on('connection', function (socket) {
  console.log("connection");
  socket.emit('abcd', { hello: 'world' });
  process.stdin.setEncoding('utf8');
  process.stdin.on('data', function (chunk) {
	socket.emit('message', { hello: chunk });
  });
  process.stdin.on('end', () => {
	process.stdout.write('end');
  });
  socket.on('message', function (data) {
  console.log(data);
  });
});