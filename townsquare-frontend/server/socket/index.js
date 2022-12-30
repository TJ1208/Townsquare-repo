module.exports = (io) => {
    
    io.on('connection', (socket) => {
        console.log('new connection');
        
        socket.on('disconnect', () => {
            console.log('disconnected');
        })

        socket.on('message', (message) => {
            io.emit('message', message);
        });
    })
    
}