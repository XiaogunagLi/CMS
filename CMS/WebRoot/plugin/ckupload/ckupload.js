var app = angular.module('imgUpload', []);
var url = "/ck";

app.config(['$locationProvider', function($locationProvider) {
  $locationProvider.html5Mode(true);
}]);

app.controller('imgAction', [ "$scope", "$location", "sendHttp", "mainAction",function($scope, $location, sendHttp, mainAction){
	  $scope.fileName = "";
	  $scope.dirName = "";
	  $scope.curDir = "ckUpload";
	  $scope.images = [];
	  $scope.chooseFile = function() {
		 $('#file').click();
	  }
	  
	  $scope.sendFile = function(){
		mainAction.uploadFile($scope);
	  }
	  
	  mainAction.dirList($scope);
	  
	  $scope.imgList = function(path, scope){
	  		mainAction.imgList(path, scope);
	  }
	  
	  $scope.createDir = function(){
		  mainAction.createDir($scope);
	  }
	  $scope.imgClick = function(){
		  mainAction.ckShow($location.search().CKEditorFuncNum, this.src);
	  }
}]);

app.directive("fileModel", [ "$parse", function($parse) {
	return {
		restrict : "A",
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind("change", function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
					scope.fileName = element[0].files[0].name;
				})
			})
		}
	}
} ])

app.service("sendHttp", [ "$http", function($http) {
	this.sendPost = function(formData, success, fail) {
		$http.post(url, formData, {
			transformRequest : angular.identity,
			headers : {
				"Content-Type" : undefined
			}
		}).then(function(data) {
			success(data);
		},function myError(data) {
		    fail(data);
		})
	}
} ])

app.service("mainAction", ["sendHttp", function(sendHttp){
	var that = this;
	this.dirList = function(scope){
		var fd = new FormData();
	    fd.append("action", "dirList")
	    sendHttp.sendPost(fd, function(res){
	    	that.displayTree(res, scope);
	    	that.imgList(scope.curDir, scope);
	    });
	}
	
	this.displayTree = function(res, scope){
		var data = res.data;
		d = new dTree('d');
		for(var i=0; i<data.length; i++){
			var item = data[i];
			d.add(item.id, item.pId, item.name, "javascript:chooseDir('"+item.path+"')");
		}
		document.getElementById("tree").innerHTML = d;
	}
	
	this.imgList = function(path, scope){
		var fd = new FormData();
	    fd.append("action", "imgList");
	    fd.append("path", path);
	    sendHttp.sendPost(fd, function(res){
	    	scope.images=[];
	    	for(var i=0; i<res.data.length; i++){
	    		scope.images.push(res.data[i].src);
	    	}
	    });
	}
	
	this.createDir = function(scope){
		var fd = new FormData();
	    fd.append("action", "createDir");
	    fd.append("path", scope.curDir);
	    fd.append("name", scope.dirName);
	    sendHttp.sendPost(fd, function(){
	    	that.dirList(scope);
	    	scope.dirName = "";
	    });
	}
	
	this.uploadFile = function(scope){
		if(scope.fileName == ""){
			alert("choose image");
			return;
		}
		var file = scope.fileToUpload;
	    if ( !file ){
	    	return;
	    }
	    var fd = new FormData();
	    fd.append("action", "upload");
	    fd.append("dir", scope.curDir);
		fd.append("file", file);
	    sendHttp.sendPost(fd, function(){
	    	that.imgList(scope.curDir, scope);
	    	scope.fileName = "";
	    });
	}
	
	this.ckShow = function(funcNum, src){
		var parentWindow = (window.parent == window) ? window.opener: window.parent;
		parentWindow.CKEDITOR.tools.callFunction(funcNum, src);
		window.close();
	}
}])

function chooseDir(path){
	var appElement = document.querySelector('[ng-controller=imgAction]');
	var $scope = angular.element(appElement).scope(); 
	$scope.curDir = path;
	$scope.imgList(path, $scope);
	//$scope.$apply();
}