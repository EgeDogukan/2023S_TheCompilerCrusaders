module MainModule {
	exports databasePackage;
	requires java.desktop;
	requires java.sql;
	requires mongo.java.driver;
	requires gson;
	requires json.simple;
	opens RiskPackage to gson;
	//requires mongo.java.driver;
}