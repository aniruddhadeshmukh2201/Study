require("dotenv").config({ path : `.env.${process.env.NODE_ENV || 'dev'}` });
const express  = require("express");
const app = express();

const router = express.Router();

router.get('/api', (req, res) => {
    res.send("from router/api route ");
});




app.use((req, res, next) => {
    console.log("we are in the middleware...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    next();
});


app.use('/router', router);

app.get('/', (req, res) => {
    res.send("api called");
    return;
});


app.use((err, req, res, next) => {
    console.log("----we are in the error middleware--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", err.message);
    next(err);
});


process.on("uncaughtException", (err) => {
    console.log("we are in the global error handler...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    console.log("node global : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", err);
    
});

app.listen(3000, () => {
    setInterval(() => {
        console.log("----printing this in every 1 sec");
    }, 1000);
    console.log("express web server", process.env.PORT);
});



