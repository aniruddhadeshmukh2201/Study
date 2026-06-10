require("dotenv").config({ path : `.env.${process.env.NODE_ENV || 'dev'}` });
const express  = require("express");
const app = express();
const Logger  = require("./Logger.js");


process.on("uncaughtException", (err) => {
    Logger.debug("we are in the global error handler...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    Logger.error("node global : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", err);
});



app.use((req, res, next) => {
    Logger.warn("we are in the middleware...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    next();
});


const router = express.Router();

router.get('/api', (req, res) => {
    Logger.info("this is the first log....");
    res.send("from router/api route ");
});


app.use('/router', router);

app.get('/', (req, res) => {
    res.send("api called");
    return;
});


app.use((err, req, res, next) => {
    Logger.error("----we are in the error middleware--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", err);
    next(err);
});


app.listen(process.env.PORT, () => {
    setInterval(() => {
        Logger.info("----printing this in every 1 sec");
        throw new Error("just checking....");
    }, 1000);
    Logger.info("express web server", process.env.PORT);

});



