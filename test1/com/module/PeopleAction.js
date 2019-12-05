const express = require("express");
const fs = require('fs');
const router = express.Router();
const db = require("../DB");
const util = require("../Util");
const mongoose = require('mongoose');


router.get("/import", importData);
router.get("/tag", tag);
router.get("/find", findById);
router.get("/gender", gender);


const peopleSchema = new mongoose.Schema({
	is_active: Boolean ,
	age: String,
	eye_color: String,
	name: String,
	gender: String,
	tags:[]
});
const peopleModel = mongoose.model('people', peopleSchema);

/**
 * import and save data
 * @param req
 * @param res
 * @param next
 * @returns
 */
function importData(req, res, next){
	var rawdata = fs.readFileSync('people.json');
	var data = JSON.parse(rawdata);
	var len = data.length;
	var count = 0;
	for(var i=0; i<len; i++){
		item = {}
		for(key in data[i]){
			item[util.toSnakeCased(key)] = data[i][key]; 
		}
		db.save(peopleModel, item, function(){
			count++;
			if(count == len){
				console.log(count);
				res.send("success " + count);
			}
	    })
	} 
}

/**
 * count tag
 * @param req
 * @param res
 * @param next
 * @returns
 */
function tag(req, res, next){
	db.findAll(peopleModel, function(data){
		var len = data.length;
		var result = {};
		for(var i=0; i<len; i++){
			console.log(data[i]._id);
			var tags = data[i].tags;
			for(j in tags){
				var tag = tags[j];
				if(result[tag]){
					result[tag] += 1;
				}else{
					result[tag] = 1;
				}
			}
		}
		res.send(result);
	});
}

/**
 * find people by ID
 * @param req
 * @param res
 * @param next
 * @returns
 */
function findById(req, res, next){
	db.find(peopleModel,{_id:req.query.id}, function(data){
		result = {}
		result["_id"] = data[0]._id;
		result["is_active"] = data[0].is_active;
		result["tags"] = data[0].tags;
		result["eye_color"] = data[0].eye_color;
		result["name"] = data[0].name;
		result["gender"] = data[0].gender;
		res.send(result);
	});
}

/**
 * counter gender
 * @param req
 * @param res
 * @param next
 * @returns
 */
function gender(req, res, next){
	db.findAll(peopleModel, function(data){
		var len = data.length;
		var result = {female:0, male:0};
		for(var i=0; i<len; i++){
			var gender = data[i].gender;
			if(gender == "female"){
				result["female"]++;
			}else{
				result["male"]++;
			}
		}
		res.send(result);
	});
}


module.exports = router;