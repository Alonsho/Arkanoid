compile_unix: bin
	find src -name "*.java" > sources.txt
	javac -cp resources:src:. -d bin @sources.txt 

run_unix:
	java -cp bin:resources:. Ass7Game

compile_windows: bin
	where /r . *.java > sources.txt
	javac -cp resources;src;. -d bin @sources.txt 

run_windows:
	java -cp bin;resources;. Ass7Game

bin:
	mkdir bin	

jar:
	jar cvfm ass7game.jar Manifest.mf -C bin/ . -C resources/ .

runj:
	java -jar ass7game.jar