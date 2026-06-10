const { Writable} = require("stream");



const ws = new Writable({
    write(chunk, encoding, callback) {
        console.log(chunk.toString());
        callback();
    }
});



ws.write("writing....");
ws.end();