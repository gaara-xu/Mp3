echo "" > nohup.out
git pull origin shell;
mvn clean package;
nohup java -jar target/shell.jar &
tail -f nohup.out
