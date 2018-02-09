/**
 * 2017-12-06
 */
// Page Configuration
var config = {
	page: {
		min: 1,
		max: 10,
		view: 10
	}
}
// Page Elements
var elements = {
	remote:{
		prev: document.getElementById("btn-prev"),
		next: document.getElementById("btn-next")
	},
	page:{
		numbers: document.getElementsByClassName("page-number"),
		now: document.querySelector("a.page-number.active")
	},
	list:{
		contents: document.getElementsByClassName("content-item"),
	}
}

// Remote Handler
/**
 * TODO: Active Page Number Method
 */
var remoteHandler = {
	movePrev: function( event ){
		var pageNumber = parseInt(elements.page.now.getAttribute("page-number"));
		if( pageNumber > config.page.min ) {
			pageNumber -= 1;
			location.href = service.makeURL("list", { "page":pageNumber });
		}
	},
	moveNext: function( event ){
		var pageNumber = parseInt(elements.page.now.getAttribute("page-number"));
		if( pageNumber < config.page.max ) {
			pageNumber += 1;
			location.href = service.makeURL("list", { "page":pageNumber });
		}
	},
	movePage: function( event ){
		var pageNumber = parseInt( event.target.getAttribute("page-number") );
		location.href = service.makeURL("list", { "page":pageNumber });
	}
}

// List-Item Handler
var listHandler = {
	onView: function(event){
		console.log("clicked", event.target, service);
		location.href = service.makeURL("view",
			{ 
				"page": elements.page.now.getAttribute("page-number"),
				"contentId" : event.target.getAttribute("content-id"),
			}
		);
	}
}

// AddEventListener
// Remote
elements.remote.prev.addEventListener("click", remoteHandler.movePrev);
elements.remote.next.addEventListener("click", remoteHandler.moveNext);
for(var i = 0; i < elements.page.numbers.length; i++){
	elements.page.numbers[i].addEventListener("click", remoteHandler.movePage);			
}

// Contents
for(var i = 0; i < elements.list.contents.length; i++){
	elements.list.contents[i].addEventListener("click", listHandler.onView);			
}





