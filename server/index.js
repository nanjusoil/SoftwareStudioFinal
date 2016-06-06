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
  socket.emit('message', { hello: 'world' });
  process.stdin.setEncoding('utf8');
  process.stdin.on('data', function (chunk) {
	socket.emit(chunk.substring(0 , chunk.length-2), { hello: chunk });
  });
  process.stdin.on('end', () => {
	process.stdout.write('end');
  });
  socket.on('message', function (data) {
  console.log(data);
  });
  
  socket.on('leftboxopen', function (data) {
  	socket.broadcast.emit('leftboxopen', { hello: 'world' });
  console.log(data);
  });

  socket.on('rightboxopen', function (data) {
  	socket.broadcast.emit('rightboxopen', { hello: 'world' });
  console.log(data);
  });

  socket.on('putinCardbaijuyi', function (data) {
  	socket.broadcast.emit('putinCardbaijuyi', { hello: 'world' });
  console.log(data);
  });

  socket.on('checkCardbaijuyi', function (data) {
  	socket.broadcast.emit('checkCardbaijuyi', { hello: 'world' });
  console.log(data);
  });

  socket.on('useCardbaijuyi', function (data) {
  	socket.broadcast.emit('useCardbaijuyi', { hello: 'world' });
  console.log(data);
  });

  socket.on('putinCarddufu', function (data) {
  	socket.broadcast.emit('putinCarddufu', { hello: 'world' });
  console.log(data);
  });

  socket.on('checkCarddufu', function (data) {
  	socket.broadcast.emit('checkCarddufu', { hello: 'world' });
  console.log(data);
  });

  socket.on('useCarddufu', function (data) {
  	socket.broadcast.emit('useCarddufu', { hello: 'world' });
  console.log(data);
  });

  socket.on('putinCardwangwei', function (data) {
  	socket.broadcast.emit('putinCardwangwei', { hello: 'world' });
  console.log(data);
  });

  socket.on('checkCardwangwei', function (data) {
  	socket.broadcast.emit('checkCardwangwei', { hello: 'world' });
  console.log(data);
  });

  socket.on('useCardwangwei', function (data) {
  	socket.broadcast.emit('useCardwangwei', { hello: 'world' });
  console.log(data);
  });

  socket.on('putinCardlibai', function (data) {
  	socket.broadcast.emit('putinCardlibai', { hello: 'world' });
  console.log(data);
  });

  socket.on('checkCardlibai', function (data) {
  	socket.broadcast.emit('checkCardlibai', { hello: 'world' });
  console.log(data);
  });

  socket.on('useCardlibai', function (data) {
  	socket.broadcast.emit('useCardlibai', { hello: 'world' });
  console.log(data);
  });

  socket.on('putinItemmykey', function (data) {
  	socket.broadcast.emit('putinItemmykey', { hello: 'world' });
  console.log(data);
  });

  socket.on('checkItemmykey', function (data) {
  	socket.broadcast.emit('checkItemmykey', { hello: 'world' });
  console.log(data);
  });  

  socket.on('useItemmykey', function (data) {
  	socket.broadcast.emit('useItemmykey', { hello: 'world' });
  console.log(data);
  });

  socket.on('nextRoom', function (data) {
  	socket.broadcast.emit('nextRoom', { hello: 'world' });
  console.log(data);
  });
 
  socket.on('safeopen', function (data) {
	  	socket.broadcast.emit('safeopen', { hello: 'world' });
	  console.log(data);
  });
  
  socket.on('faithPlay', function (data) {
	  	socket.broadcast.emit('faithPlay', { hello: 'world' });
	  console.log(data);
  });
  
  socket.on('heroPlay', function (data) {
	  	socket.broadcast.emit('heroPlay', { hello: 'world' });
	  console.log(data);
  });
  
  socket.on('vocalPlay', function (data) {
	  	socket.broadcast.emit('vocalPlay', { hello: 'world' });
	  console.log(data);
  });
  
  socket.on('ruralsPlay', function (data) {
	  	socket.broadcast.emit('ruralsPlay', { hello: 'world' });
	  console.log(data);
  });

  socket.on('faithStop', function (data) {
	  	socket.broadcast.emit('faithStop', { hello: 'world' });
	  console.log(data);
  });
  
  socket.on('vocalStop', function (data) {
	  	socket.broadcast.emit('vocalStop', { hello: 'world' });
	  console.log(data);
});

  socket.on('heroStop', function (data) {
	  	socket.broadcast.emit('heroStop', { hello: 'world' });
	  console.log(data);
});

  socket.on('ruralsStop', function (data) {
	  	socket.broadcast.emit('ruralsStop', { hello: 'world' });
	  console.log(data);
});

  socket.on('putinItempaperball', function (data) {
	  	socket.broadcast.emit('putinItempaperball', { hello: 'world' });
	  console.log(data);
});

  socket.on('checkItempaperball', function (data) {
	  	socket.broadcast.emit('checkItempaperball', { hello: 'world' });
	  console.log(data);
});
  
  socket.on('safeopenastro', function (data) {
	  	socket.broadcast.emit('safeopenastro', { hello: 'world' });
	  console.log(data);
});


});





