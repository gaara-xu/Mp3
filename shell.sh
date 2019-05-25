git pull origin shell;
mvn clean package;
nohup java -jar target/shell.jar &
ls