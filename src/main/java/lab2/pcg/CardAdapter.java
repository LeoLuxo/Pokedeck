package lab2.pcg;

import com.google.gson.*;
import lab2.pcg.deck.card.*;

import java.lang.reflect.Type;


public class CardAdapter implements JsonSerializer<Card>, JsonDeserializer<Card> {
	
	@Override
	public JsonElement serialize(Card card, Type type, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		obj.addProperty("type", card.getClass().getSimpleName());
		obj.add("card", new Gson().toJsonTree(card));
		System.out.println("SERIALIZE: " + obj);
		return obj;
	}
	
	@Override
	public Card deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
		Card card;
		JsonObject json = jsonElement.getAsJsonObject();
		String cardTypeName = json.get("type").getAsString();
		Class<? extends Card> cardType;
		
		if (cardTypeName.equals("PokemonCard")) {
			cardType = PokemonCard.class;
		} else if (cardTypeName.equals("TrainerCard")) {
			cardType = TrainerCard.class;
		} else if (cardTypeName.equals("EnergyCard")) {
			cardType = EnergyCard.class;
		} else {
			throw new JsonParseException("Card type \"" + cardTypeName + "\" not defined");
		}
		
		card = new Gson().fromJson(json.get("card"), cardType);
		System.out.println("DESERIALIZE: " + json.get("card"));
		return card;
	}
	
}
