set CLASSPATH=CLASSPATH=mysql-connector-java-5.1.35.jar:mapper-3.4.3.jar:mybatis-generator-core-1.3.2.jar
set mainClassName=org.mybatis.generator.api.ShellRunner
java -classpath "%CLASSPATH%" "%mainClassName%" -configfile generatorConfig_koubei.xml -overwrite
pause