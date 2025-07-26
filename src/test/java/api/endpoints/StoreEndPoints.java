package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {
	
	public static Response createPetOrder(Store payload) {
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.Storepost_url);
				
			return response;
	}
	
	public static Response readPetOrder(long orderId) {
			
			Response response=given()
					.pathParam("orderId",orderId)
				.when()
					.get(Routes.Storeget_url);
					
				return response;
		}
	
	
	public static Response deletePetOrder(long orderId) {
		
		Response response=given()
				.pathParam("orderId",orderId)
			.when()
				.delete(Routes.Storedelete_url);
				
			return response;
	}


	

}
