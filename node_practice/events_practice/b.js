const { Readable } = require("stream");

const arr = [1, 2, 3];
let idx = 0;

const RS = new Readable({
  read(size) {
    if (idx < arr.length) {
      

      setTimeout(() => {
        const chunk = Buffer.from(arr[idx].toString());
        this.push(chunk);
        idx++;
      }, 5000);
      
    } else {
      this.push(null); // end of stream
    }
  }
});

let ans = [];

RS.on('data', (chunk) => {
  ans.push(chunk.toString()); // convert buffer to string or number
});

RS.on('end', () => {
  console.log(ans); // ["1", "2", "3"]
});
