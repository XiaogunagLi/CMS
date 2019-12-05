	
//A 65 - Z 90
function toSnakeCased(str){
	var res = ""
		for(var i=0; i<str.length; i++){
		var code = str[i].charCodeAt(0);
		if( code >= 65 && code <= 90){
			res += "_" + str[i].toLowerCase();
		}else{
			res += str[i];
		}
	}
	return res;
}

function toCamelCased(str){
	var res = ""
	for(var i=0; i<str.length; i++){
		if( str[i] == "_"){
			res += str[i+1].toUpperCase();
			i += 1;
		}else{
			res += str[i];
		}
	}
	return res;
}
//console.log(toSnakeCased("isActive"));
//console.log(toCamelCased("is_active"));

module.exports.toSnakeCased = toSnakeCased;
module.exports.toCamelCased = toCamelCased;