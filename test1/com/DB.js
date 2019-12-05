const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/test8', {
	  useNewUrlParser: true,
	  useUnifiedTopology: true
});

function save(model, obj, callBack){
	let instance = new model(obj);
	instance.save(function(err){
		if(err){
			console.log(err);
		}else{
			callBack();
		}
	});
}

function findAll(model,callBack){
	model.find({}, function (err, docs) {
		if(err){
			console.log(err);
		}else{
			callBack(docs);
		}
	});
}

function find(model, condition,callBack){
	model.find(condition, function (err, docs) {
		if(err){
			console.log(err);
		}else{
			callBack(docs);
		}
	});
}

function del(model, id,callBack){
	var conditions = {_id: id};
	model.deleteOne(conditions, function(error){
	    if(error) {
	        console.log(error);
	    } else {
	    	callBack();
	    }
	});
}

module.exports.save = save;
module.exports.findAll = findAll;
module.exports.del = del;
module.exports.find = find;
