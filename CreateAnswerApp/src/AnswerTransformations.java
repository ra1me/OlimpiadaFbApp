
public class AnswerTransformations {
	
	int getTypeOfAnswer(String answer){
		if(answer.contains("A.")&&answer.contains("B.")&&answer.contains("C.")&&answer.contains("D."))
			return 1;
		else if(answer.contains("data:image"))
			return 2;
		else if(answer.contains("Po��cz w pary")||answer.contains("Po��cz okre�lenia z ich wyja�nieniem")||answer.contains("Po��cz"))
			return 3;
		else
			return 0;
	}
	
	String generateAnswer(int type,String answer){
		int start,end,good;
		String goodAnswer;
		switch(type){
		case 1:
			String clearAnswer="";
			String[] places = {"A.","B.","C.","D.","Nast�pne pytanie"};
			for(int i=1;i<=4;i++){
				start=answer.indexOf(places[i-1]);
				end=answer.indexOf(places[i]);
				clearAnswer+=answer.substring(start, end).trim().replace(" ", "").replace("\n", "").replace("-Twojaodpowied�", "").replace("-Prawid�owaodpowied�", "").replace(",Twojaodpowied�", "");
			}
			good=answer.indexOf("Prawid�owa odpowied�");
			while(!(answer.charAt(good)=='.'&&(answer.charAt(good-1)=='A'||answer.charAt(good-1)=='B'||answer.charAt(good-1)=='C'||answer.charAt(good-1)=='D')))
				good--;
			return type+"|"+clearAnswer+"|"+answer.substring((good)-1,good);
		case 2:
			start = answer.indexOf("data:image");
			end = answer.indexOf("Nast�pne pytanie");
			String link = answer.substring(start,end).trim().replace(" ", "").replace("\n", "").replace("-Twojaodpowied�", "").replace(",Twojaodpowied�", "");
			int lastGood;
			good=answer.indexOf("Prawid�owa odpowie")-3;
			lastGood=good;
			while(answer.charAt(good)!='\n'){
				good--;
			}
			goodAnswer=answer.substring(good,lastGood);
			return type+"|"+link+"|"+goodAnswer;
		case 3:
			start=answer.indexOf("Twoje odpowiedzi")+17;
			end=answer.indexOf("Nast�pne pytanie");
			String everyAnswer=answer.substring(start,end);
			String[] option = everyAnswer.split("\n");
			String goodOption[]=new String[4];
			String sortedOption[]=new String[4];
			String answers="";
			for(int i=0;i<4;i++){
				start=option[i].indexOf("\t");
				end=option[i].lastIndexOf("\t");
				goodOption[i]=option[i].substring(start, end).trim();
				start=option[i].lastIndexOf("\t");
				end=option[i].length();
				sortedOption[i]=option[i].substring(start, end).trim();
				start=0;
				end=option[i].indexOf("\t");
				answers+=option[i].substring(start, end).trim();
			}
			String finalOrder="";
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(goodOption[i].equals(sortedOption[j]))
						finalOrder+=(j+1);
				}
				
			}
			return type+"|"+answers+"|"+finalOrder;
		default:
			return "error";
		}
	}
}
