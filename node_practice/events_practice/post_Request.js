// let fetch = require("fetch");


fetch("http://example.com").then((res) => {
    // console.log(res);
    return res.text();
}).then((x) => {
    // console.log(x)
})



const https = require('https')



https.get('https://example.com', (res) => {
    let data = "";

    res.on('data', (chunk) => {
        data += chunk;
    });
    res.on('error', (err) => {
        throw err;
    } );

    res.on('end', () => {
        console.log(data);
    });
});



let options = {
    path : '', 
    method : '', 
    headers : {

    }
};

let req = https.request(options, (res) => {
    res.on('data', (chunk) => {

    });
    res.on('end', () => {

    });
});

req.write();
req.end();


