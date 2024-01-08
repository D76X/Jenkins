int courseCount = 14;
Boolean isProgrammer = true;
String[] singers = ["Bob", "George", "Jeff", "Roy", "Tom"]

if (isProgrammer) {
    println "He's a programmer, alright"
}
else{
    println "Not a programmer, tho"
}

for (int i = 0; i < courseCount; i++){
    println "Chris made course " + (i+1) + "!!!"
}


for(String singer: singers){
    println singer
}

singers.each{x -> println(x)}
singers.each{println(it)}