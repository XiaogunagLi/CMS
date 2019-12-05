const fs = require('fs');
const db = require("./DB");
const mongoose = require('mongoose');

const peopleSchema = new mongoose.Schema({
	is_active: Boolean ,
	age: String,
	eye_color: String,
	name: String,
	gender: String,
	tags:[]
});
const peopleModel = mongoose.model('people', peopleSchema);

var item = {
    "is_active": true,
    "age": 33,
    "eye_color": "green",
    "name": "Downs Walters",
    "gender": "male",
    "tags": [
      "consequat",
      "consequat",
      "quis",
      "adipisicing",
      "adipisicing",
      "in",
      "amet"
    ]
  }

//db.save(peopleModel, item, function(){
//	console.log("success");
//})


db.findAll(peopleModel, function(data){
	console.log(data);
})



//let rawdata = fs.readFileSync('people.json');
//let data = JSON.parse(rawdata);
//for(var i in data){ 
//	console.log(data[i]);
//} 





