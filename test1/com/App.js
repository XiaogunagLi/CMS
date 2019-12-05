const express = require("express");
const app = express();

var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: false }))

const people = require("./module/PeopleAction");

app.use("/people", people);


app.listen(3000);
console.log("server start");