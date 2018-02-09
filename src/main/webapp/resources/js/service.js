/**
 * 
 */
const serviceConfig = {
	url: "http://localhost:8080/board",
}

const service = {
	 /**
	 * @param conditions is JSON { field : value }
	 * @returns String URL
	 */
	makeURL:
		function( path, conditions ){
			if( path.charAt(0) !== "/" ){
				path = "/" + path;
			}
			if( conditions === null || typeof conditions !== "object") {
				return document.URL + "?makeURL=failure";
			}
			let count = 0;
			let condition = "?";
			for( let name in conditions ){
				let value = conditions[name];
				if( value !== null || value !== "" || value !== undefined ){
					if( count > 0 ){ condition += "&"; }
					condition += ( name + "=" + value );
					count += 1;
				}
			}
			return serviceConfig.url + path + condition;
		}
}