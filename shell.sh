port=9000
pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');
if [  -n  "$pid"  ];  then
    kill  -9  $pid;
    echo " java.shell is killed  by" $pid;
fi
echo "" > nohup.out
git pull origin shell;
mvn clean package;
nohup java -jar target/shell.jar &
tail -f nohup.out
