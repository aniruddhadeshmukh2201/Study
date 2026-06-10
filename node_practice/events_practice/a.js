let eventEmmitter = require("events");



let e = new eventEmmitter();

e.addListener('clicked', (e) => {
    console.log("click event triggered!! ", e);
});


e.addListener('change', (e) => {
    console.log("change event triggered!! ", e);
});


e.addListener('drag', (e) => {
    console.log("drag event triggered!! ", e);
});

console.log(e.emit('change'));




